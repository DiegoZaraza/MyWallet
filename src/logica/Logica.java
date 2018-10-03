/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.PopupMenu;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import persistencia.dao.MovimientosDao;
import logica.vo.CategoriasVo;
import logica.vo.CuentasVo;
import logica.vo.MovimientosVo;
import logica.vo.ProcesosVo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import persistencia.dao.ProcesoDao;

/**
 *
 * @author e10417a
 */
public class Logica {
    private float ingresos;
    private float egresos;
    MovimientosDao mov = new MovimientosDao();
    
    public float getIngresos() {
        return ingresos;
    }

    public void setIngresos(float ingresos) {
        this.ingresos = ingresos;
    }

    public float getEgresos() {
        return egresos;
    }

    public void setEgresos(float egresos) {
        this.egresos = egresos;
    }
    
    public String saldos(String cat){
        String query = "";
        String saldo = null;
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        if (cat.equals("Ingresos")){
            saldo = mov.sumarMovimientos("SELECT SUM(ValorMov) AS Saldo FROM MOVIMIENTOS WHERE IdCategoria IN (SELECT IdCategoria FROM CATEGORIAS WHERE IdTipoCategoria = 11)");
        } else if (cat.equals("Egresos")){
            saldo = mov.sumarMovimientos("SELECT SUM(ValorMov) AS Saldo FROM MOVIMIENTOS WHERE IdCategoria IN (SELECT IdCategoria FROM CATEGORIAS WHERE IdTipoCategoria = 12)");
        } else if (cat.equals("Cuenta")) {
            int sal;
            sal = Integer.parseInt(mov.sumarMovimientos("SELECT SUM(ValorMov) AS Saldo FROM MOVIMIENTOS WHERE IdCuenta = '1001' AND IdCategoria IN (SELECT IdCategoria FROM CATEGORIAS WHERE IdTipoCategoria = 11)")) - 
                    Integer.parseInt(mov.sumarMovimientos("SELECT SUM(ValorMov) AS Saldo FROM MOVIMIENTOS WHERE IdCuenta = '1001' AND IdCategoria IN (SELECT IdCategoria FROM CATEGORIAS WHERE IdTipoCategoria = 12)"));
            saldo = Integer.toString(sal);
        }
        else if (cat.equals("Billetera")) {
            int sal;
            sal = Integer.parseInt(mov.sumarMovimientos("SELECT SUM(ValorMov) AS Saldo FROM MOVIMIENTOS WHERE IdCuenta = '1000' AND IdCategoria IN (SELECT IdCategoria FROM CATEGORIAS WHERE IdTipoCategoria = 11)")) - 
                    Integer.parseInt(mov.sumarMovimientos("SELECT SUM(ValorMov) AS Saldo FROM MOVIMIENTOS WHERE IdCuenta = '1000' AND IdCategoria IN (SELECT IdCategoria FROM CATEGORIAS WHERE IdTipoCategoria = 12)"));
            saldo = Integer.toString(sal);
        }
        return saldo;
    }
    
    public ChartFrame pintarGraficos(){       
        //Se almacenan los datos que seran usados en el gráfico
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Ingresos", Integer.parseInt(saldos("Ingresos")));
        datos.setValue("Gastos",Integer.parseInt(saldos("Egresos")));
        
        //Se crea el gráfico y se asignan algunas caracteristicas
        JFreeChart grafico_barras;       
        grafico_barras = ChartFactory.createPieChart3D("Calificaciones Java", datos);
        ChartFrame frame;
        frame = new ChartFrame("Ejemplo",grafico_barras);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    
    public DefaultTableModel tabla(){
        DefaultTableModel modelo = new DefaultTableModel();
        
       /* ResultSetMetaData rsMd = rs.getMetaData();
        ResultSet rs = consulta.Consulta(Query);
		int cantidadColumnas = rsMd.getColumnCount();
		String nomColumna;
		
		for (int i = 1; i <= cantidadColumnas; i++)
		{
			nomColumna = rsMd.getColumnName(i);
			modelo.addColumn(nomColumna);
		}
        */
        return modelo;
    }
    
    public String idMovNuevo(){
       return mov.traerId();
    }
    
    public ArrayList<CategoriasVo> comboBoxCategoria(){
        return mov.consultarListaCategoria();
    }
    
    public ArrayList<CuentasVo> comboBoxCuentas(){
        return mov.consultarListaCuentas();
    }
    
    public void insertarMovimiento(MovimientosVo miMovimiento){
        mov.registrarMovimientos(miMovimiento);
    }

    public Integer traerId(String text, String tipo) {
        if (tipo.equals("Cat"))
            return Integer.parseInt(mov.sumarMovimientos("SELECT IdCategoria FROM CATEGORIAS WHERE NamCategoria ='" + text + "'"));
        else
            return Integer.parseInt(mov.sumarMovimientos("SELECT IdCuenta FROM CUENTAS WHERE  NamCuenta ='" + text + "'"));
    }

    public ChartFrame graficarCuenta() {
        //Se almacenan los datos que seran usados en el gráfico
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        ProcesoDao pr = new ProcesoDao();
        ArrayList<ProcesosVo> data;
        data = pr.datosGraficoCuentas();

        for (int i = 0; i < data.size(); i++) {
            datos.setValue(data.get(i).getValor(), data.get(i).getTipo(), data.get(i).getCuenta());
        }

        //Se crea el gráfico y se asignan algunas caracteristicas
        JFreeChart grafico_barras;
        grafico_barras = ChartFactory.createBarChart3D("Ingresos y Egresos por Cuenta", "Cuenta",
                "Valor", datos, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame;
        frame = new ChartFrame("Grafico", grafico_barras);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
    
    public ChartFrame graficarCategoria() {
        //Se almacenan los datos que seran usados en el gráfico
        DefaultPieDataset datos = new DefaultPieDataset();
        ProcesoDao pr = new ProcesoDao();
        ArrayList<ProcesosVo> data;
        data = pr.datosGraficoCategoria();

        for (int i = 0; i < data.size(); i++) {
            datos.setValue(data.get(i).getCuenta() + " - " + data.get(i).getValor(), data.get(i).getValor());
        }

        //Se crea el gráfico y se asignan algunas caracteristicas
        JFreeChart grafico_barras;
        grafico_barras = ChartFactory.createPieChart3D("Ingresos y Egresos por Categoria", datos);
        ChartFrame frame;
        frame = new ChartFrame("Grafico", grafico_barras);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    public ChartFrame graficarIngreso() {
        //Se almacenan los datos que seran usados en el gráfico
        DefaultPieDataset datos = new DefaultPieDataset();
        ProcesoDao pr = new ProcesoDao();
        ArrayList<ProcesosVo> data;
        data = pr.datosIngreso();

        for (int i = 0; i < data.size(); i++) {
            datos.setValue(data.get(i).getTipo()+ " - " + data.get(i).getValor(), data.get(i).getValor());
        }

        //Se crea el gráfico y se asignan algunas caracteristicas
        JFreeChart grafico_barras;
        grafico_barras = ChartFactory.createPieChart3D("Ingresos y Egresos por Categoria", datos);
        ChartFrame frame;
        frame = new ChartFrame("Grafico", grafico_barras);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    public void insertarCategoria(CategoriasVo cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
