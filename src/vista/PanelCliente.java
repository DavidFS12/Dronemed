package vista;

import modelo.*;
import controlador.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;


public class PanelCliente extends JFrame{
    
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private Cliente cliente;
    
    public PanelCliente(String nombre){
        setTitle("Panel Cliente - "+nombre);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel lblBienvenida = new JLabel("Bienvenido Cliente "+nombre);
        lblBienvenida.setBounds(30,20,300,25);
        add(lblBienvenida);
        
        //------
        JButton btnPedido = new JButton("Hacer Pedido");
        btnPedido.setBounds(30, 60, 150, 30);
        add(btnPedido);
        
        btnPedido.addActionListener(e -> {
            String[] opciones = {"Urgente", "Estandar"};
            String prioridad = (String) JOptionPane.showInputDialog(this, "Prioridad del Pedido:", "Nuevo Pedido", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
        
            if(prioridad != null){
                Pedido pedido = new Pedido(0, cliente.getId(), prioridad, "Pendiente", LocalDate.now().toString(), 0);
                System.out.println(pedido);
                
                if(PedidoDAO.registrarPedido(pedido)){
                    JOptionPane.showMessageDialog(this, "Pedido registrado");
                    cargarPedidos();
                }else{
                    JOptionPane.showMessageDialog(this, "Error al registrar pedido");
                }
            }
        });
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Prioridad");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Dron asignado");

        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaPedidos);
        scroll.setBounds(30, 110, 520, 200);
        add(scroll);

        // Botón actualizar
        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setBounds(400, 60, 150, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> cargarPedidos());
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        cargarPedidos();
    }
    
    private void cargarPedidos(){
        if (cliente == null) return;
        
        modeloTabla.setRowCount(0);
        List<Pedido> lista = PedidoDAO.listarPedidosCliente(cliente.getId());
        
        for(Pedido p : lista){
            modeloTabla.addRow(new Object[]{
                p.getId(), p.getPrioridad(), p.getEstado(), p.getFecha(), p.getIdDron() == 0 ? "No asignado" :p.getIdDron()
            });
        }
    }
}
