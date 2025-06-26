package controlador;

import modelo.*;
import java.sql.*;
import java.util.*;

public class DronDAO {

    public static boolean registrarDron(Dron dron) {
        String sql = "INSERT INTO drones (modelo, tipo, capacidad, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dron.getModelo());
            pstmt.setString(2, dron.getTipo());
            pstmt.setInt(3, dron.getCapacidad());
            pstmt.setString(4, dron.getEstado());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar dron: " + e.getMessage());
            return false;
        }
    }

    public static List<Dron> listarDrones() {
        List<Dron> lista = new ArrayList<>();
        String sql = "SELECT * FROM drones";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String modelo = rs.getString("modelo");
                String tipo = rs.getString("tipo");
                int capacidad = rs.getInt("capacidad");
                String estado = rs.getString("estado");

                Dron dron = new DronLigero(id, modelo, capacidad, estado); // por ahora todos ligeros
                lista.add(dron);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar drones: " + e.getMessage());
        }

        return lista;
    }
    
    public static boolean actualizarDron(Dron dron) {
        String sql = "UPDATE drones SET modelo = ?, tipo = ?, capacidad = ?, estado = ? WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dron.getModelo());
            pstmt.setString(2, dron.getTipo());
            pstmt.setInt(3, dron.getCapacidad());
            pstmt.setString(4, dron.getEstado());
            pstmt.setInt(5, dron.getId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar dron: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean eliminarDron(int id) {
        String sql = "DELETE FROM drones WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar dron: " + e.getMessage());
            return false;
        }
    }
}