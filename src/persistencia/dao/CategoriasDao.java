/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import logica.vo.CategoriasVo;
import persistencia.Conexion;

/**
 *
 * @author e10417a
 */
public class CategoriasDao {

    public void registrarCategoria(CategoriasVo miCategoria) {
        Conexion conex = new Conexion();

        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO CATEGORIAS VALUES ('" + miCategoria.getIdCategoria() + "', '"
                    + miCategoria.getTipoCategoria() + "', '" + miCategoria.getNombreCategoria() + "')");
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
    }

    public CategoriasVo buscarCategoria(int idCategoria) {
        Conexion conex = new Conexion();
        
        CategoriasVo categoria;
        categoria = new CategoriasVo();
        
        boolean existe = false;
        
        try {
            //Statement estatuto = conex.getConnection().createStatement();
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM CATEGORIAS where IdCategoria = ? ");
            consulta.setInt(1, idCategoria);
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                existe = true;
                categoria.setIdCategoria(Integer.parseInt(res.getString("IdCategoria")));
                categoria.setTipoCategoria(Integer.parseInt(res.getString("IdTipoCategoria")));
                categoria.setNombreCategoria(res.getString("NamCategoria")); 
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }
        
        if (existe) {
            return categoria;
        } else {
            return null;
        }
    }

    public void modificarCategoria(CategoriasVo miCategoria) {
        Conexion conex = new Conexion();
        try {
            String consulta = "UPDATE CATEGORIAS SET IdCategoria = ? ,IdTipoCategoria = ? , NamCategoria = ? WHERE IdCategoria = ? ";
            PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

            estatuto.setInt(1, miCategoria.getIdCategoria());
            estatuto.setInt(2, miCategoria.getTipoCategoria());
            estatuto.setString(3, miCategoria.getNombreCategoria());
            estatuto.executeUpdate();

            JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarCategoria(int idCategoria) {
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("DELETE FROM CATEGORIAS WHERE IdCategoria = '" + idCategoria + "'");
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Elimino");
        }
    }
}
