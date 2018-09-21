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
public class ControladorVentanaPrincipal implements ActionListener {

    private final VentanaPrincipal vPri;

    public ControladorVentanaPrincipal(VentanaPrincipal aThis) {
        vPri = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        if (boton == vPri.getjButtonMovimientos()) {
            vPri.getModelo().paginaMovimientos();
        } else if (boton == vPri.getjButtonParametrizar()) {
            vPri.getModelo().paginaParametrizar();
        } else if (boton == vPri.getjButtonReportes()) {
            vPri.getModelo().paginaReportes();
        }
    }
}
