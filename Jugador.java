package LigaDeportiva;

public class Jugador extends Persona implements Estadistico {
    private String posicion;
    private int numero;
    private int goles;
    private int tAmarillas;
    private int tRojas;
    private int faltas;
    
    public Jugador(String nombre, int edad, String posicion, int numero){
        super (nombre, edad);
        this.posicion = posicion;
        this.numero = numero;
        this.goles = 0;
        this.tAmarillas = 0;
        this.tRojas = 0;
        this.faltas = 0;
    }
    
    public String getPosicion(){
        return posicion;
    }
    public int getNumero(){
        return numero;
    }
    public int getGoles() {
        return goles;
    }
    public void setPosicion(String posicion){
        this.posicion = posicion;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public void registrarGol(int cantidad){
        this.goles += cantidad;
    }
    public void registrarTarjetas(int cantidad1, int cantidad2){
        this.tAmarillas += cantidad1;
        this.tRojas += cantidad2;
    }
    public void registrarFalta(int cantidad){
        this.faltas += cantidad;
    }
    
    @Override
    public void generarEstadisticas() {
        System.out.printf("Jugador: %s \n"
                          +"| Goles: %d \n"
                          +"| Amarillas: %d \n"
                          +"| Rojas: %d \n"
                          +"| Faltas: %d%n\n", super.getNombre(), goles, tAmarillas, tRojas, faltas);
    }
    
    @Override
    public String toString(){
        return "Jugador: "+super.getNombre()+ " #"+numero+ " - " +posicion
                +"\n" + "| Goles: "+goles+" | TA: "+tAmarillas+"\n" +
                "| TR: "+tRojas+" | Faltas: "+faltas+"";
    }

}
