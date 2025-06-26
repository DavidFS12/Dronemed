package controlador;

import modelo.Mantenimiento;
import java.sql.*;
import java.util.*;

public class MantenimientoDAO {

    public static boolean registrarMantenimiento(Mantenimiento m) {
        String sql = "INSERT INTO mantenimientos (id_dron, fecha, detalles, tecnico) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, m.getIdDron());
            pstmt.setString(2, m.getFecha());
            pstmt.setString(3, m.getDetalles());
            pstmt.setString(4, m.getTecnico());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar mantenimiento: " + e.getMessage());
            return false;
        }
    }

    public static List<Mantenimiento> listarMantenimientos() {
        List<Mantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM mantenimientos";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mantenimiento m = new Mantenimiento(
                        rs.getInt("id"),
                        rs.getInt("id_dron"),
                        rs.getString("fecha"),
                        rs.getString("detalles"),
                        rs.getString("tecnico")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar mantenimientos: " + e.getMessage());
        }

        return lista;
    }
}