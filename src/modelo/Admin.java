
package modelo;

public class Admin extends Usuario{
     public Admin(int id, String nombre, String correo, String password){
        super(id, nombre, correo, password, "admin");
    }
    
    @Override
    public void mostrarPanel(){
      System.out.println("Bienvenido Administrador: "+nombre);  
    }
}
