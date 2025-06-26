package modelo;

public abstract class Dron {
    protected int id;
    protected String modelo;
    protected String tipo;
    protected int capacidad;
    protected String estado;
    
    //constructor
    public Dron(int id, String modelo, String tipo, int capacidad, String estado){
        this.id = id;
        this.modelo = modelo;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
    }
    
    //get y set
    public int getId() {return id;}
    public String getModelo() {return modelo;}
    public String getTipo() {return tipo;}
    public int getCapacidad() {return capacidad;}
    public String getEstado() {return estado;}
    
    public void setId(int id) {this.id = id;}
    public void setModelo(String modelo) {this.modelo = modelo;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}
    public void setEstado(String estado) {this.estado = estado;}
    
    public abstract String descripcion();
}
