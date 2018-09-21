/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import persistencia.dao.MovimientosDao;
import logica.vo.CategoriasVo;
import logica.vo.CuentasVo;
import logica.vo.MovimientosVo;

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
    
}
