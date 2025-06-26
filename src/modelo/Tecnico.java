
package modelo;

public class Tecnico extends Usuario{
    public Tecnico(int id, String nombre, String correo, String password){
        super(id, nombre, correo, password, "tecnico");
    }
    
    @Override
    public void mostrarPanel(){
      System.out.println("Bienvenido Tecnico: "+nombre);  
    }
}
