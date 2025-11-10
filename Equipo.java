package LigaDeportiva;

import java.util.ArrayList;
import java.util.List;


public class Equipo implements Competible, Estadistico {

    private String nombre;
    private String ciudad;
    private List<Jugador> jugadores;
    private Entrenador entrenador;
    private int puntos;
    private int victorias;
    private int empates;
    private int derrotas;
    private int golesFavor;
    private int golesContra;

    public Equipo(String nombre, String ciudad, Entrenador entrenador) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<>();
        this.puntos = 0;
        this.victorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
    }

    public String getNombre() { 
        return nombre; 
    }
    public String getCiudad() {
        return ciudad;
    }
    public Entrenador getEntrenador() {
        return entrenador;
    }
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    public int getPuntos() {
        return puntos;
    }
    public int getVictorias() {
        return victorias;
    }
    public int getEmpates() {
        return empates;
    }
    public int getDerrotas() {
        return derrotas;
    }
    public int getGolesFavor() {
        return golesFavor;
    }
    public int getGolesContra() {
        return golesContra;
    }
    
    public void agregarJugador(Jugador j) {
        if (jugadores.contains(j)){
            System.out.print("Jugador ya esta en el equipo");
            return;
        }
        jugadores.add(j);
    }
    public void registrarPartido(int golesAFavor, int golesEnContra) {
        this.golesFavor += golesAFavor;
        this.golesContra += golesEnContra;

        if (golesAFavor > golesEnContra) {
            victorias++;
            puntos += 3;
        } else if (golesAFavor == golesEnContra) {
            empates++;
            puntos += 1;
        } else {
            derrotas++;
        }
    } 
    
    @Override
    public void competir() {
        System.out.println(nombre + " está compitiendo en el torneo.");
    }

    @Override
    public void generarEstadisticas() {
        System.out.println("Equipo: "+nombre+"");
        System.out.println("Puntos: "+puntos+" | V: "+victorias+" | E: "+empates+" | D: "+derrotas+"");
        System.out.println("Goles a favor: "+golesFavor+" | Goles en contra: "+golesContra+"");
    }

    @Override
    public String toString() {
        return ""+nombre+" ("+ciudad+") - Pts: "+puntos+"";
    }
    
}