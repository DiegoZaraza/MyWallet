/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.ArrayList;
import logica.Logica;
import logica.vo.CategoriasVo;
import logica.vo.CuentasVo;
import logica.vo.MovimientosVo;

/**
 *
 * @author e10417a
 */
public class Modelo {

    private Logica log;
    private VentanaCategorias vCat;
    private VentanaMovimientos vMov;
    private VentanaPrincipal vPri;
    private VentanaReportes vRep;

    public VentanaReportes getvRep() {
        if (vRep == null) {
            vRep = new VentanaReportes(this);
        }
        return vRep;
    }

    public void setvRep(VentanaReportes vRep) {
        this.vRep = vRep;
    }

    public Logica getLog() {
        if (log == null) {
            log = new Logica();
        }
        return log;
    }

    public VentanaPrincipal getVPri() {
        if (vPri == null) {
            vPri = new VentanaPrincipal(this);
        }
        return vPri;
    }

    public void iniciar() {
        getVPri().setVisible(true);
        getVPri().getjButtonParametrizar().setVisible(false);
        getVPri().getjButtonReportes().setVisible(false);
        this.cargarSaldos();
    }

    public VentanaCategorias getvCat() {
        if (vCat == null) {
            vCat = new VentanaCategorias(this);
        }
        return vCat;
    }

    public void setvCat(VentanaCategorias vCat) {
        this.vCat = vCat;
    }

    public VentanaMovimientos getvMov() {
        if (vMov == null) {
            vMov = new VentanaMovimientos(this);
        }
        return vMov;
    }

    public void setvMov(VentanaMovimientos vMov) {
        this.vMov = vMov;
    }

    public void setvPri(VentanaPrincipal vPri) {
        this.vPri = vPri;
    }

    public void cargarSaldos() {
        getVPri().getjLabelValorCuenta().setText(getLog().saldos("Cuenta"));
        getVPri().getjLabelValorBilletera().setText(getLog().saldos("Billetera"));
        getVPri().getjLabelValorGastos().setText(getLog().saldos("Egresos"));
        getVPri().getjLabelValorIngresos().setText(getLog().saldos("Ingresos"));
        
        
    }

    public void paginaMovimientos() {
        getVPri().setVisible(false);
        getvCat().setVisible(false);
        getvMov().setVisible(true);
        getvRep().setVisible(false);
        getvMov().getjButtonBuscar().setVisible(false);
        getvMov().getjButtonEditar().setVisible(false);
        getvMov().getjButtonEliminar().setVisible(false);
        getvMov().getjTextIdMovimiento().setText(getLog().idMovNuevo());
        consultarCategorias();
        consultarCuentas();
    }

    public void paginaParametrizar() {
        getVPri().setVisible(false);
        getvCat().setVisible(true);
        getvMov().setVisible(false);
        getvRep().setVisible(false);
    }

    public void paginaReportes() {
        getVPri().setVisible(false);
        getvCat().setVisible(false);
        getvMov().setVisible(false);
        getvRep().setVisible(true);
    }
    
    public void insertaMovimiento(){
        MovimientosVo mov = new MovimientosVo();
        
        mov.setIdMov(Integer.parseInt(getvMov().getjTextIdMovimiento().getText()));
        mov.setFechaMov(getvMov().getjTextFechaMovimiento().getText());
        mov.setIdCategoria(getLog().traerId((String) getvMov().getjComboBoxCategoria().getSelectedItem(), "Cat"));
        mov.setIdCuenta(getLog().traerId((String) getvMov().getjComboBoxCuenta().getSelectedItem(), "Cta"));
        mov.setValorMovimiento(Integer.parseInt(getvMov().getjTextFieldValor().getText()));
        
        getLog().insertarMovimiento(mov);
        
        iniciar();
    }
    
    private void consultarCategorias() {
        getvMov().getjComboBoxCategoria().removeAllItems();
        ArrayList<CategoriasVo> cat = getLog().comboBoxCategoria();

        for (int i = 0; i < cat.size(); i++) {
            getvMov().getjComboBoxCategoria().addItem(cat.get(i).getNombreCategoria());
        }        
    }
    
    private void consultarCuentas() {
        getvMov().getjComboBoxCuenta().removeAllItems();
        ArrayList<CuentasVo> cat = getLog().comboBoxCuentas();

        for (int i = 0; i < cat.size(); i++) {
            getvMov().getjComboBoxCuenta().addItem(cat.get(i).getNomCuenta());
        }        
    }
}
