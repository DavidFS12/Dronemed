package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import controlador.*;
import java.time.LocalDate;
import java.util.List;

public class PanelTecnico extends JFrame {
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public PanelTecnico(String nombre) {
        setTitle("Panel Técnico - " + nombre);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido Técnico: " + nombre);
        lblBienvenida.setBounds(30, 20, 300, 25);
        add(lblBienvenida);

        JButton btnRegistrar = new JButton("Registrar Mantenimiento");
        btnRegistrar.setBounds(30, 60, 200, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                String idDronStr = JOptionPane.showInputDialog(this, "ID del dron:");
                if (idDronStr == null || idDronStr.trim().isEmpty()) return;

                int idDron = Integer.parseInt(idDronStr);
                String detalles = JOptionPane.showInputDialog(this, "Detalles del mantenimiento:");
                if (detalles == null || detalles.trim().isEmpty()) return;

                Mantenimiento m = new Mantenimiento(0, idDron, LocalDate.now().toString(), detalles, nombre);

                if (MantenimientoDAO.registrarMantenimiento(m)) {
                    JOptionPane.showMessageDialog(this, "✅ Mantenimiento registrado");
                    cargarMantenimientos();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al registrar");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠ ID inválido.");
            }
        });

        // Tabla de historial
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("ID Dron");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Detalles");
        modeloTabla.addColumn("Técnico");

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 110, 520, 200);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar Historial");
        btnActualizar.setBounds(400, 60, 150, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> cargarMantenimientos());

        // Carga inicial
        cargarMantenimientos();
    }

    private void cargarMantenimientos() {
        modeloTabla.setRowCount(0);
        List<Mantenimiento> lista = MantenimientoDAO.listarMantenimientos();
        for (Mantenimiento m : lista) {
            modeloTabla.addRow(new Object[]{
                    m.getId(), m.getIdDron(), m.getFecha(), m.getDetalles(), m.getTecnico()
            });
        }
    }
}