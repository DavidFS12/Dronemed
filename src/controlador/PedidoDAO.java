package controlador;

import modelo.Pedido;
import java.sql.*;
import java.util.*;

public class PedidoDAO {
    public static boolean registrarPedido(Pedido pedido){
        String sql = "INSERT INTO pedidos (id_cliente, prioridad, estado, fecha_creacion, id_dron) VALUES (?,?,?,?,?)";
        
        try(Connection conn = ConexionDB.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, pedido.getIdCliente());
            pstmt.setString(2, pedido.getPrioridad());
            pstmt.setString(3, pedido.getEstado());
            pstmt.setString(4, pedido.getFecha());
            pstmt.setInt(5, pedido.getIdDron());
            
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Error al registrar pedido "+ e.getMessage());
            return false;
        }
    }
        
    public static List<Pedido> listarPedidosCliente(int idCliente){
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE id_cliente = ?";
        
        try(Connection conn = ConexionDB.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                Pedido p = new Pedido(
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getString("prioridad"),
                    rs.getString("estado"),
                    rs.getString("fecha_creacion"),
                    rs.getInt("id_dron")
                );
                lista.add(p);
            }
        }catch(SQLException e){
            System.out.println("Error al listar pedidos: "+ e.getMessage());
        }
        
        return lista;
    }
}
