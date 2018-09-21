/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibilletera;

import presentacion.Modelo;

/**
 *
 * @author e10417a
 */
public class MiBilletera {

    private Modelo miApp;
    
    public MiBilletera() {
        miApp = new Modelo();
        miApp.iniciar();        
    }
    
    public static void main(String[] args) {
        MiBilletera miBilletera = new MiBilletera();
    }
}
