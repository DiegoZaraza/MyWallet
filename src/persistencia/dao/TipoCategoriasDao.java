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
import persistencia.Conexion;
import logica.vo.CategoriasVo;
import logica.vo.TipoCategoriaVo;

/**
 *
 * @author e10417a
 */
public class TipoCategoriasDao {

    public void registrarTipoCat(TipoCategoriaVo miTipoCat) {
        Conexion conex = new Conexion();

        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO TIPOCAT VALUES ('" + miTipoCat.getIdTipoCategoria() + "', '"
                    + miTipoCat.getNombreTipoCat() + "')");
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
    }

    public TipoCategoriaVo buscarMovimientos(int idTipoCat) {
        Conexion conex = new Conexion();

        TipoCategoriaVo tipo;
        tipo = new TipoCategoriaVo();

        boolean existe = false;

        try {
            //Statement estatuto = conex.getConnection().createStatement();
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM TIPOCAT where IdTipoCat = ? ");
            consulta.setInt(1, idTipoCat);
            try (ResultSet res = consulta.executeQuery()) {
                while (res.next()) {
                    existe = true;
                    tipo.setIdTipoCategoria(Integer.parseInt(res.getString("IdTipoCat")));
                    tipo.setNombreTipoCat(res.getString("NamTipoCat"));
                }
            }
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }

        if (existe) {
            return tipo;
        } else {
            return null;
        }
    }

    public void modificarTipoCat(TipoCategoriaVo miTipoCat) {
        Conexion conex = new Conexion();
        try {
            String consulta = "UPDATE TIPOCAT SET IdTipoCat = ? ,NamTipoCat = ? WHERE IdTipoCat = ? ";
            PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

            estatuto.setInt(1, miTipoCat.getIdTipoCategoria());
            estatuto.setString(2, miTipoCat.getNombreTipoCat());
            estatuto.executeUpdate();

            JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarTipoCat(int idTipoCat) {
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("DELETE FROM TIPOCAT WHERE IdTipoCat = '" + idTipoCat + "'");
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Elimino");
        }
    }
}
