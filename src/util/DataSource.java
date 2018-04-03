/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bader
 */
public class DataSource {
    private static DataSource data;
    private Connection con;
    public String user = "root";
    public String password ="";
    public String url = "jdbc:mysql://localhost:3306/entreaide";
    
    private DataSource(){
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("Connexion non établie");
        }
    }
    
    public static DataSource getInstance(){
        if ( data == null ){
            data = new DataSource();
        }
        return data;
    }

    public Connection getConnection(){
        return con;
    }
    
}
