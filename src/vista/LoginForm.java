package vista;

import modelo.*;
import controlador.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JFrame{
    private JTextField txtCorreo;
    private JPasswordField txtClave;
    private JButton btnLogin;
    private JLabel lblMensaje;
    
    public LoginForm(){
        setTitle("Login - Dronemed");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel lblCorreo = new JLabel("Correo: ");
        lblCorreo.setBounds(30, 30, 180, 25);
        add(lblCorreo);
        
        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 30, 180, 25);
        add(txtCorreo);
        
        JLabel lblClave = new JLabel("Clave: ");
        lblClave.setBounds(30, 70, 180, 25);
        add(lblClave);
        
        txtClave = new JPasswordField();
        txtClave.setBounds(120, 70, 180, 25);
        add(txtClave);
        
        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(120, 110, 100, 30);
        add(btnLogin);
        
        lblMensaje = new JLabel("");
        lblMensaje.setBounds(30, 150, 300, 25);
        add(lblMensaje);
        
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String correo = txtCorreo.getText();
                String clave = new String(txtClave.getPassword());
                
                Usuario usuario = UsuarioDAO.buscarUsuario(correo, clave);
                
                if(usuario != null){
                    System.out.println("dentro de if");
                    switch (usuario.getRol().toLowerCase()){
                        case "admin":
                            new PanelAdmin(usuario.getNombre()).setVisible(true);
                            break;
                        case "cliente":
                            PanelCliente panel = new PanelCliente(usuario.getNombre());
                            panel.setCliente((Cliente) usuario);
                            panel.setVisible(true);
                            break;
                        case "tecnico":
                            new PanelTecnico(usuario.getNombre()).setVisible(true);
                            break;
                    }
                    dispose();
                }else{
                    lblMensaje.setText("Usuario o clave incorrectos.");
                }
            }
        });
    }
}
