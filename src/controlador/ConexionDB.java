package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB{
    private static final String URL = "jdbc:sqlite:db/dronemed.db";
    
    public static Connection conectar(){
        try{
            return DriverManager.getConnection(URL);
        } catch(SQLException e){
            System.out.println("Error de conexion: " + e.getMessage());
            return null;
        }
    }
}