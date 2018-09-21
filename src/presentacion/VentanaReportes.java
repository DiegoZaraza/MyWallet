/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author Estudiantes
 */
public class VentanaReportes extends javax.swing.JFrame {

    private final Modelo modelo;
    private ControladorVentanaReportes control;

    public ControladorVentanaReportes getControl() {
        if(control == null){
            control = new ControladorVentanaReportes(this);
        }
        return control;
    }

    public void setControl(ControladorVentanaReportes control) {
        this.control = control;
    }
    
     public Modelo getModelo() {
        return modelo;
    }
    
    
    /**
     * Creates new form Vista
     */
    public VentanaReportes(Modelo s) {
        modelo = s;
        initComponents();
        capturarEventos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReporteFecha = new javax.swing.JButton();
        jButtonReporteCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonReporteFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonReporteFecha.setText("Ingreso vs Gastos");

        jButtonReporteCategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonReporteCategoria.setText("Por Categoria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButtonReporteFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164)
                .addComponent(jButtonReporteCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(401, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReporteCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReporteFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReporteCategoria;
    private javax.swing.JButton jButtonReporteFecha;
    // End of variables declaration//GEN-END:variables

    

   
    public JButton getjButtonParametrizar() {
        return jButtonReporteCategoria;
    }

    public void setjButtonParametrizar(JButton jButtonParametrizar) {
        this.jButtonReporteCategoria = jButtonParametrizar;
    }

    public JButton getjButtonReportes() {
        return jButtonReporteFecha;
    }

    public void setjButtonReportes(JButton jButtonReportes) {
        this.jButtonReporteFecha = jButtonReportes;
    }

    private void capturarEventos() {
        jButtonReporteCategoria.addActionListener(getControl());
        jButtonReporteFecha.addActionListener(getControl());
    }
}