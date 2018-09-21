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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import persistencia.Conexion;
import logica.vo.CategoriasVo;
import logica.vo.CuentasVo;
import logica.vo.MovimientosVo;

/**
 *
 * @author e10417a
 */
public class MovimientosDao {

    public void registrarMovimientos(MovimientosVo miMovimiento) {
        Conexion conex = new Conexion();

        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO MOVIMIENTOS VALUES ('" + miMovimiento.getIdMov() + "', '"
                    + miMovimiento.getFechaMov() + "', '" + miMovimiento.getValorMovimiento() + "', '"
                    + miMovimiento.getIdCuenta() + "', '" + miMovimiento.getIdCategoria() + "')");
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Registro");
        }
    }

    public MovimientosVo buscarMovimientos(int idMov) {
        Conexion conex = new Conexion();

        MovimientosVo movimiento;
        movimiento = new MovimientosVo();

        boolean existe = false;

        try {
            //Statement estatuto = conex.getConnection().createStatement();
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM MOVIMIENTOS where IdMov = ? ");
            consulta.setInt(1, idMov);
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                existe = true;
                movimiento.setIdMov(Integer.parseInt(res.getString("IdMov")));
                movimiento.setFechaMov(res.getString("FechaMov"));
                movimiento.setValorMovimiento(res.getInt("NamCategoria"));
                movimiento.setIdCuenta(res.getInt("NamCategoria"));
                movimiento.setIdCategoria(res.getInt("NamCategoria"));
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }

        if (existe) {
            return movimiento;
        } else {
            return null;
        }
    }

    public void modificarMovimientos(MovimientosVo miMovimientos) {
        Conexion conex = new Conexion();
        try {
            String consulta = "UPDATE MOVIMIENTOS SET IdMov = ?, FechaMov = ?, ValorMov = ?, IdCuenta = ?, IdCategoria = ? WHERE IdTipoCat = ? ";
            PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

            estatuto.setInt(1, miMovimientos.getIdCuenta());
            estatuto.setString(2, miMovimientos.getFechaMov());
            estatuto.setInt(3, Integer.parseInt("" + miMovimientos.getValorMovimiento()));
            estatuto.setInt(4, miMovimientos.getIdCuenta());
            estatuto.setInt(5, miMovimientos.getIdCategoria());
            estatuto.executeUpdate();

            JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente ", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al Modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarMovimientos(int idMov) {
        Conexion conex = new Conexion();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("DELETE FROM MOVIMIENTOS WHERE IdMov = '" + idMov + "'");
            JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            estatuto.close();
            conex.desconectar();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se Elimino");
        }
    }
    
    public String sumarMovimientos(String query) {
        Conexion conex = new Conexion();
        String saldo = "";
        System.out.println("presentacion.dao.MovimientosDao.sumarMovimientos() - " + query);
        try {
            Statement estatuto = conex.getConnection().createStatement();
            ResultSet res = estatuto.executeQuery(query);
            while (res.next()) {
                saldo = res.getString(1);
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }
        
        System.out.println("presentacion.dao.MovimientosDao.sumarMovimientos() - " + saldo);
        
        return saldo;
    }
    
    public String traerId(){
        Conexion conex = new Conexion();
        int id = 0;
        try {
            Statement estatuto = conex.getConnection().createStatement();
            ResultSet res = estatuto.executeQuery("SELECT MAX(IdMov) FROM MOVIMIENTOS");
            while (res.next()) {
                id = res.getInt(1);
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }
        return "" + (id + 1);
    }
    
    public ArrayList<CategoriasVo> consultarListaCategoria(){
        Conexion conex = new Conexion();
        CategoriasVo categorias;
        ArrayList<CategoriasVo> mov = new ArrayList<>();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            ResultSet res = estatuto.executeQuery("SELECT * FROM CATEGORIAS");
            while (res.next()) {
                categorias = new CategoriasVo();
                categorias.setIdCategoria(res.getInt("IdCategoria"));
                categorias.setNombreCategoria(res.getString("NamCategoria"));
                mov.add(categorias);
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }
        return mov;
    }
    
    public ArrayList<CuentasVo> consultarListaCuentas(){
        Conexion conex = new Conexion();
        CuentasVo cuentas;
        ArrayList<CuentasVo> mov = new ArrayList<>();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            ResultSet res = estatuto.executeQuery("SELECT * FROM CUENTAS");
            while (res.next()) {
                cuentas = new CuentasVo();
                cuentas.setIdCuenta(res.getInt("IdCuenta"));
                cuentas.setNomCuenta(res.getString("NamCuenta"));
                mov.add(cuentas);
            }
            res.close();
            conex.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, no se conecto");
            System.out.println(e);
        }
        return mov;
    }
}
