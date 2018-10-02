/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.ImageIcon;
import persistencia.dao.MovimientosDao;
import logica.vo.CategoriasVo;
import logica.vo.CuentasVo;
import logica.vo.MovimientosVo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

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
        datos.setValue("Misael", 8);
        datos.setValue("Don Enrique",6);
        datos.setValue("Lupe",4);
        
        //Se crea el gráfico y se asignan algunas caracteristicas
        JFreeChart grafico_barras;       
        grafico_barras = ChartFactory.createPieChart3D("Calificaciones Java", datos);
        ChartFrame frame;
        frame = new ChartFrame("Ejemplo",grafico_barras);
        //frame.pack();
        frame.setVisible(true);
        return frame;
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

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
