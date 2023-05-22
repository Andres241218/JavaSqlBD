/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaSQL;

import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class Consola {
    static Scanner tc = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) throws SQLException{
        try {
            if (Conexion()){
                System.out.println("Conexion establecida.");
                while(true){
                    System.out.println(imprimir_menu(0));
                    int opc = sc.nextInt();
                    sc.nextLine();
                    switch(opc){
                        case 1:
                            System.out.println(imprimir_menu(1));
                            opc = sc.nextInt();
                            LibrosCRUD(opc);
                            break;
                        case 2:
                            System.out.println(imprimir_menu(2));
                            opc = sc.nextInt();
                            UsuariosCRUD(opc);
                            break;
                        case 3:
                            System.out.println(imprimir_menu(3));
                            opc = sc.nextInt();
                            PrestamosCRUD(opc);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opcion incorrecta.");
                    }
                    if(opc==4){
                        break;
                    }
                }
            }
            else{
                System.out.println("Error en la conexion.");
            }
        } 
        catch (Exception e) {
        }
    }
    
    
    public static void PrestamosCRUD(int opc){
        try {
            Connection con = SQLConexion.getConexion();
            PreparedStatement ps;
            switch(opc){
                case 1:
                    ps = con.prepareStatement("INSERT INTO Prestamos (usuarioID,libroID,fecha,activo)"
                    + "VALUES (?,?,?,?)");
                    System.out.print("Ingrese el ID del usuario: \n");
                    ps.setInt(1, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el ID del libro: \n");
                    ps.setInt(2, tc.nextInt());
                    tc.nextLine();
                    LocalDate factual = LocalDate.now();
                    ps.setDate(3, java.sql.Date.valueOf(factual));
                    ps.setBoolean(4, true);
                    ps.executeUpdate();
                    System.out.println("Prestamo registrado con exito.\n\n");
                    break;
                case 2:
                    ps = con.prepareStatement("UPDATE Prestamos SET activo=? WHERE id=?");
                    System.out.print("Ingrese el ID del prestamo: \n");
                    ps.setInt(2, tc.nextInt());
                    ps.setBoolean(1, false);
                    ps.executeUpdate();
                    System.out.println("Devolucion registrada con exito.\n\n");
                    break;
                case 3:
                    ps = con.prepareStatement("SELECT * FROM Prestamos");
                    ResultSet rs = ps.executeQuery();
                    System.out.println("----PRESTAMOS----");
                    while(rs.next()){
                        System.out.println("PRESTAMO: "+rs.getString("id"));
                        System.out.println("ID Usuario: "+rs.getString("usuarioID"));
                        System.out.println("ID Libro: "+rs.getString("libroID"));
                        System.out.println("fecha: "+rs.getString("fecha"));
                        System.out.println("Activo: "+rs.getString("activo")+"\n");
                    }
                    System.out.println("---FIN PRESTAMOS---\n\n");
                    break;

                default:
                    break;
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void UsuariosCRUD(int opc){
        try {
            Connection con = SQLConexion.getConexion();
            PreparedStatement ps;
            switch(opc){
                case 1:
                    ps = con.prepareStatement("INSERT INTO Usuarios (nombre,apellido,tipo_identidad,numero_identidad,numero_celular)"
                    + "VALUES (?,?,?,?,?)");
                    System.out.print("Ingrese el nombre: \n");
                    ps.setString(1, tc.nextLine());
                    System.out.print("Ingrese el apellido: \n");
                    ps.setString(2, tc.nextLine());
                    System.out.print("Ingrese el id del tipo de identificacion: \n");
                    ps.setInt(3, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el numero de identidad: \n");
                    ps.setString(4, tc.nextLine());
                    System.out.print("Ingrese el numero de celular: \n");
                    ps.setString(5, tc.nextLine());
                    ps.executeUpdate();
                    System.out.println("Usuario registrado con exito.\n\n");
                    break;
                case 2:
                    ps = con.prepareStatement("DELETE FROM Usuarios WHERE id=?");
                    System.out.println("Ingrese el ID de usuario:");
                    ps.setInt(1, tc.nextInt());
                    ps.executeUpdate();
                    System.out.println("Usuario Eliminado con exito.\n\n");
                    break;
                case 3:
                    ps = con.prepareStatement("UPDATE Usuarios SET nombre=?, apellido=?, "
                            + "tipo_identidad=?, numero_identidad=?, numero_celular=? WHERE id=?");
                    System.out.print("Ingrese el ID del usuario a actualizar: \n");
                    ps.setInt(6, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el nuevo nombre: \n");
                    ps.setString(1, tc.nextLine());
                    System.out.print("Ingrese el nuevo apellido: \n");
                    ps.setString(2, tc.nextLine());
                    System.out.print("Ingrese el nuevo id del tipo de identificacion: \n");
                    ps.setInt(3, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el nuevo numero de identidad: \n");
                    ps.setString(4, tc.nextLine());
                    System.out.print("Ingrese el nuevo numero de celular: \n");
                    ps.setString(5, tc.nextLine());
                    ps.executeUpdate();
                    System.out.println("Usuario actualizado con exito.\n\n");
                    break;
                default:
                    break;
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void LibrosCRUD(int opc){
        try {
            Connection con = SQLConexion.getConexion();
            PreparedStatement ps;
            switch(opc){
                case 1:
                    ps = con.prepareStatement("INSERT INTO Libros (nombre,paginas,autor,año_publicacion,editorial,genero)"
                    + "VALUES (?,?,?,?,?,?)");
                    System.out.print("Ingrese el nombre: \n");
                    ps.setString(1, tc.nextLine());
                    System.out.print("Ingrese la cantidad de paginas: \n");
                    ps.setInt(2, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el id del autor: \n");
                    ps.setString(3, tc.nextLine());
                    System.out.print("Ingrese el año de publicacion: \n");
                    ps.setInt(4, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el id de la editorial: \n");
                    ps.setInt(5, tc.nextInt());
                    tc.nextLine();
                    System.out.print("Ingrese el id de genero: \n");
                    ps.setInt(6, tc.nextInt());
                    tc.nextLine();
                    ps.executeUpdate();
                    System.out.println("Libro ingresado con exito.\n\n");
                    break;
                case 2:
                    ps = con.prepareStatement("DELETE FROM Libros WHERE id=?");
                    System.out.println("Ingrese el ID del libro:");
                    ps.setInt(1, tc.nextInt());
                    ps.executeUpdate();
                    System.out.println("Libro Eliminado con exito.\n\n");
                    break;
                default:
                    break;
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public static String imprimir_menu(int op){
        //0.Menu, 1.Operaciones Libros, 2.Operaciones Usuarios, 3.Operaciones Prestamos
        switch(op){
            case 0:
                return "--- DIGITE SU OPCION ---\n"
                        + "1. Libros\n"
                        + "2. Usuarios\n"
                        + "3. Prestamos\n"
                        + "4. Salir\n";
            case 1:
                return "LIBROS...\n"
                        + "1. Ingresar nuevo libro\n"
                        + "2. Eliminar libro";
            case 2:
                return "USUARIOS...\n"
                        + "1. Ingresar nuevo usuario\n"
                        + "2. Eliminar usuario\n"
                        + "3. Editar usuario";
            case 3:
                return "PRESTAMOS...\n"
                        + "1. Prestar libro\n"
                        + "2. Regsitrar devolucion\n"
                        + "3. Ver Prestamos por Usuario";
            default:
                return "";
        }
    }
    
    public static boolean Conexion() throws SQLException{
        try {
            String bases="";
            Statement sql = SQLConexion.getConexion().createStatement();
            
            String consulta = "SELECT name FROM master.dbo.sysdatabases";
            ResultSet resultado = sql.executeQuery(consulta);
            
            while(resultado.next()){
                bases += resultado.getString(1)+"\n";
            }
            
            System.out.println(bases);
            return true;
        } 
        catch (SQLException e) {
            throw e;
        }
    }
}
