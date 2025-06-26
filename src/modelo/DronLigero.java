package modelo;

public class DronLigero extends Dron{
    public DronLigero(int id, String modelo, int capacidad, String estado){
        super(id, modelo, "Ligero", capacidad, estado);
    }
    
    @Override
    public String descripcion(){
        return "Dron ligero de baja capacidad";
    }
}
