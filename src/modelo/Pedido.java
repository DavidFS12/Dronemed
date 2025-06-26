package modelo;

public class Pedido {
    private int id;
    private int idCliente;
    private String prioridad;
    private String estado;
    private String fecha;
    private int idDron;
    
    public Pedido(int id, int idCliente, String prioridad, String estado, String fecha, int idDron){
        this.id=id;
        this.idCliente=idCliente;
        this.prioridad=prioridad;
        this.estado=estado;
        this.fecha=fecha;
        this.idDron=idDron;
    }
    
    // Getters y setters
    public int getId() { return id; }
    public int getIdCliente() { return idCliente; }
    public String getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
    public String getFecha() { return fecha; }
    public int getIdDron() { return idDron; }

    public void setId(int id) { this.id = id; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setIdDron(int idDron) { this.idDron = idDron; }
}
