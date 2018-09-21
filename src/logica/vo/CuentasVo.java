/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.vo;

/**
 *
 * @author e10417a
 */
public class CuentasVo {
    private Integer idCuenta;
    private String nomCuenta;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public String getNomCuenta() {
        return nomCuenta;
    }
    
    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setNomCuenta(String nomCuenta) {
        this.nomCuenta = nomCuenta;
    }
}