package modelo;

public class DronPesado extends Dron{
    public DronPesado(int id, String modelo, String tipo, int capacidad, String estado){
        super(id, modelo, "Pesado", capacidad, estado);
    }
    
    @Override
    public String descripcion(){
        return "Dron pesado de baja capacidad";
    }
}
