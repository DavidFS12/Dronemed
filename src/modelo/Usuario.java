
package modelo;

public abstract class Usuario {
    protected int id;
    protected String nombre;
    protected String correo;
    protected String password;
    protected String rol;
    
    //constructor
    public Usuario(int id, String nombre, String correo, String password, String rol){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }
    
    //metodos get and set
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    public String getPassword(){return password;}
    public String getRol(){return rol;}
    
    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setCorreo(String correo) {this.correo = correo;}
    public void setPassword(String password) {this.password = password;}
    public void setRol(String rol) {this.rol = rol;}
    
    public abstract void mostrarPanel();
}
