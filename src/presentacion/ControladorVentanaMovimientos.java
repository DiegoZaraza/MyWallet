/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author e10417a
 */
public class ControladorVentanaMovimientos implements ActionListener{

   private final VentanaMovimientos vMov;

    public ControladorVentanaMovimientos(VentanaMovimientos aThis) {
        vMov = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        if (boton == vMov.getjButtonRegistrar()) {
            vMov.getModelo().insertaMovimiento();
        } else if (boton == vMov.getjButtonEditar()) {
          //  vMov.getModelo().paginaParametrizar();
        } else if (boton == vMov.getjButtonEliminar()) {
            //vMov.getModelo().paginaReportes();
        } else if (boton == vMov.getjButtonBuscar()) {
            
        }
    }
}
