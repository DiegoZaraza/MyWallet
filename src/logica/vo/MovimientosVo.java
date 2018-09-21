/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.vo;

import java.util.Date;

/**
 *
 * @author e10417a
 */
public class MovimientosVo {
    private Integer idMov;
    private String fechaMov;
    private Integer valorMovimiento;
    private Integer idCuenta;
    private Integer idCategoria;

    public Integer getIdMov() {
        return idMov;
    }

    public String getFechaMov() {
        return fechaMov;
    }

    public Integer getValorMovimiento() {
        return valorMovimiento;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdMov(Integer idMov) {
        this.idMov = idMov;
    }

    public void setFechaMov(String fechaMov) {
        this.fechaMov = fechaMov;
    }

    public void setValorMovimiento(Integer valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}
