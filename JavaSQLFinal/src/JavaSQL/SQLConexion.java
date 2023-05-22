/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConexion {
    public static Connection getConexion(){
        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                +"database=BIBLIOTECA;"
                +"user=sa;"
                +"password=123456;"
                +"loginTimeout=10;"
                +"TrustServerCertificate=True;";
        
        try {
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } 
        catch (SQLException e) {
            System.out.println("Ocurrio un error en la conexion. \n"+e.toString());
            return null;
        }
    }
}
