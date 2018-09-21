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
public class CategoriasVo {
    private Integer idCategoria;
    private Integer TipoCategoria;
    private String NombreCategoria;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public Integer getTipoCategoria() {
        return TipoCategoria;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setTipoCategoria(Integer TipoCategoria) {
        this.TipoCategoria = TipoCategoria;
    }

    public void setNombreCategoria(String NombreCategoria) {
        this.NombreCategoria = NombreCategoria;
    }            
}
