/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
/**
 *
 * @author e10417a
 */
public class Conexion {
    static String bd = "wallet.db";
    static String url = "jdbc:sqlite:C:\\Users\\e10417a\\Documents\\NetBeansProjects\\MiBilletera\\bd\\";

    Connection conn = null;
    
    public Conexion(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url + bd);
            System.out.println("presentacion.Conexion.<init>()");
        } catch (SQLException e){
            System.out.print(e);
        } catch (ClassNotFoundException e){
            System.out.print(e);
        } catch (Exception e){
            System.out.print(e);
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void desconectar() {
        conn = null;
    }

    public CallableStatement prepareCall(String begin_insert_into_fabricantes_NOMBRE_PAIS) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
