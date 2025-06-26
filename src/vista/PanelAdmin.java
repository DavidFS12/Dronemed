package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import controlador.*;
import java.util.List;

public class PanelAdmin extends JFrame{
    private JTable tablaDrones;
    private DefaultTableModel modeloTabla;
    
    public PanelAdmin(String nombre){
        setTitle("Panel Administrador - "+nombre);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel lblBienvenida = new JLabel("Bienvenido Administrador "+nombre);
        lblBienvenida.setBounds(30,20,300,25);
        add(lblBienvenida);
        
        //------
        JButton btnRegistrar = new JButton("Registrar Dron");
        btnRegistrar.setBounds(30, 60, 150, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                String modelo = JOptionPane.showInputDialog(this, "Modelo:");
                if (modelo == null || modelo.isEmpty()) return;

                String capacidadStr = JOptionPane.showInputDialog(this, "Capacidad:");
                if (capacidadStr == null || capacidadStr.isEmpty()) return;

                int capacidad = Integer.parseInt(capacidadStr);
                String tipo = "Ligero"; // puedes expandirlo luego con JComboBox
                String estado = "Activo";

                Dron dron = new DronLigero(0, modelo, capacidad, estado);

                if (DronDAO.registrarDron(dron)) {
                    JOptionPane.showMessageDialog(this, "Dron registrado");
                    cargarDrones(); // Actualizar tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacidad debe ser un número.");
            }
        });
        
        JButton btnEditar = new JButton("Editar Dron");
        btnEditar.setBounds(30, 320, 150, 30);
        add(btnEditar);

        btnEditar.addActionListener(e -> {
            int fila = tablaDrones.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un dron para editar.");
                return;
            }

            int id = (int) modeloTabla.getValueAt(fila, 0);
            String modelo = JOptionPane.showInputDialog(this, "Nuevo modelo:", modeloTabla.getValueAt(fila, 1));
            String capacidadStr = JOptionPane.showInputDialog(this, "Nueva capacidad:", modeloTabla.getValueAt(fila, 3));

            try {
                int capacidad = Integer.parseInt(capacidadStr);
                String tipo = (String) modeloTabla.getValueAt(fila, 2); // Ligero por ahora
                String estado = (String) modeloTabla.getValueAt(fila, 4);

                Dron dron = new DronLigero(id, modelo, capacidad, estado);
                dron.setTipo(tipo);

                if (DronDAO.actualizarDron(dron)) {
                    JOptionPane.showMessageDialog(this, "Dron actualizado.");
                    cargarDrones();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacidad inválida.");
            }
        });
        
        JButton btnEliminar = new JButton("Eliminar Dron");
        btnEliminar.setBounds(200, 320, 150, 30);
        add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            int fila = tablaDrones.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un dron para eliminar.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            int id = (int) modeloTabla.getValueAt(fila, 0);

            if (DronDAO.eliminarDron(id)) {
                JOptionPane.showMessageDialog(this, "Dron eliminado.");
                cargarDrones();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        });
        
        // ------ Tabla de drones ------
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Capacidad");
        modeloTabla.addColumn("Estado");

        tablaDrones = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaDrones);
        scroll.setBounds(30, 110, 520, 200);
        add(scroll);

        // ------ Botón actualizar ------
        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setBounds(400, 60, 150, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> cargarDrones());

        // Cargar datos al inicio
        cargarDrones();
    }
    
    private void cargarDrones() {
        modeloTabla.setRowCount(0); // limpiar tabla

        List<Dron> lista = DronDAO.listarDrones();
        for (Dron dron : lista) {
            modeloTabla.addRow(new Object[]{
                dron.getId(),
                dron.getModelo(),
                dron.getTipo(),
                dron.getCapacidad(),
                dron.getEstado()
            });
        }
    }
}
