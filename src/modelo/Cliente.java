
package modelo;

public class Cliente  extends Usuario{
    public Cliente(int id, String nombre, String correo, String password){
        super(id, nombre, correo, password, "cliente");
    }
    
    @Override
    public void mostrarPanel(){
      System.out.println("Bienvenido Cliente: "+nombre);  
    }
}
