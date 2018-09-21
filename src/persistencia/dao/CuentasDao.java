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
import logica.vo.CuentasVo;
import logica.vo.TipoCategoriaVo;

/**
 *
 * @author e10417a
 */
public class CuentasDao {
    public void registrarCuentas(CuentasVo miCuenta){
        Conexion conex = new Conexion();

        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO CUENTAS VALUES ('" + miCuenta.getIdCuenta() + "', '"
                    + miCuenta.getNomCuenta() + "')");
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
    }
    
    public CuentasVo buscarCuentas(int idCuenta){
        Conexion conex = new Conexion();
        
        CuentasVo cuenta;
        cuenta = new CuentasVo();
        
        boolean existe = false;
        
        try {
            //Statement estatuto = conex.getConnection().createStatement();
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM CUENTAS where IdCuenta = ? ");
            consulta.setInt(1, idCuenta);
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                existe = true;
                cuenta.setIdCuenta(Integer.parseInt(res.getString("IdCuenta")));
                cuenta.setNomCuenta(res.getString("NamCuenta")); 
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }
        
        if (existe) {
            return cuenta;
        } else {
            return null;
        }
    }
    
    public void modificarCuentas(CuentasVo miCuenta){
        Conexion conex = new Conexion();
        try {
            String consulta = "UPDATE TIPOCAT SET IdTipoCat = ? ,NamTipoCat = ? WHERE IdTipoCat = ? ";
            PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

            estatuto.setInt(1, miCuenta.getIdCuenta());
            estatuto.setString(2, miCuenta.getNomCuenta());
            estatuto.executeUpdate();

            JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarCuentas(int idCuenta){
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("DELETE FROM TIPOCAT WHERE IdTipoCat = '" + idCuenta + "'");
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Elimino");
        }
    }
}
