package modelo;

public class Mantenimiento {
    private int id;
    private int idDron;
    private String fecha;
    private String detalles;
    private String tecnico;
    
    public Mantenimiento(int id, int idDron, String fecha, String detalles, String tecnico){
        this.id = id;
        this.idDron = idDron;
        this.fecha = fecha;
        this.detalles = detalles;
        this.tecnico = tecnico;
    }
    
    //para mantenimiento solo metodos getters
    public int getId() { return id; }
    public int getIdDron() { return idDron; }
    public String getFecha() { return fecha; }
    public String getDetalles() { return detalles; }
    public String getTecnico() { return tecnico; }
}
