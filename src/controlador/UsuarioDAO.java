package controlador;
import modelo.*;
import java.sql.*;

public class UsuarioDAO {
    public static Usuario buscarUsuario(String correo, String password){
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        
        try(Connection conn = ConexionDB.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, correo);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");
                
                switch(rol.toLowerCase()){
                    case "cliente":
                        usuario = new Cliente(id, nombre, correo, password);
                        break;
                    case "tecnico":
                        usuario = new Tecnico(id, nombre, correo, password);
                        break;
                    case "admin":
                        usuario = new Admin(id, nombre, correo, password);
                        break;
                }
            }
            
            rs.close();
        }catch(SQLException e){
            System.out.println("Error de busqueda: "+e.getMessage());
        }
        
        return usuario;
    }
}
