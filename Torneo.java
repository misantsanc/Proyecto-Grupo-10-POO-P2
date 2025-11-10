package LigaDeportiva;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Torneo {
    private String nombre;
    private List<Equipo> equipos;
    private List<Partido> partidos;

    public Torneo(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    public List<Equipo> getEquipos() {
        return equipos;
    }
    public List<Partido> getPartidos() {
        return partidos;
    }

    public void agregarEquipo(Equipo e) {
        if (equipos.contains(e)){
            System.out.println("Equipo ya inscrito en el torneo"); 
            return;
        }
        equipos.add(e);
    }
    public void generarPartido(Partido p) {
        if (partidos.contains(p)){
            System.out.println("El partido ya esta en el torneo");
            return;
        }    
        partidos.add(p);
    }
    public void agendarPartido(Partido partido){
        if (partido.getEquipoLocal().equals(partido.getEquipoVisitante())){
            System.out.println("Los equipos del partido no pueden ser el mismo");
            return;
        }
        for (Partido p : partidos){
            if (p.getFecha().equals(partido.getFecha()) && partido.getHora().equals(p.getHora())){
                if (p.getEstadio().equals(partido.getEstadio())){
                    System.out.println("Estadio ocupado en la misma fecha y hora");
                    return;
                } else if (p.getArbitro().equals(partido.getArbitro())){
                    System.out.println("El árbitro ya está asignado a otro partido en la misma fecha y hora");
                    return;
                } else if (p.getEquipoLocal().equals(partido.getEquipoVisitante())){
                    System.out.print("Un equipo ya esta registrado en la misma fecha y hora");
                    return;
                }
            }            
        }
        partidos.add(partido);
    }
    public void registrarResultado(Partido p, int golesLocal, int golesVisitante) {
        p.registrarResultado(golesLocal, golesVisitante);
    }    
    public void registrarGolesPorJugador(Equipo equipo, int totalGoles, Scanner sc){
        if (totalGoles == 0) {
            System.out.println("El equipo "+equipo.getNombre()+" no marcó goles.");
            return;
        }
        List<Jugador> jugadores = equipo.getJugadores();
        if (jugadores == null || jugadores.isEmpty()) {
            System.out.println("El equipo no tiene jugadores registrados.");
            return;
        }
        int golesRestantes = totalGoles;
        while (golesRestantes > 0) {
            System.out.println("Jugadores del equipo "+equipo.getNombre()+":");
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println((i + 1) + ". "+jugadores.get(i).getNombre()+
                               " (Goles actuales: "+jugadores.get(i).getGoles()+")");
            }
            System.out.print("Seleccione el jugador que marcó (1-" +jugadores.size()+ "): ");
            int idx = sc.nextInt() - 1;
            if (idx < 0 || idx >= jugadores.size()) {
             System.out.println("Selección inválida.");
             continue;
            }
            System.out.print("¿Cuántos goles marcó este jugador? (Restan "+golesRestantes+"): ");
            int cantidad = sc.nextInt();
            if (cantidad <= 0 || cantidad > golesRestantes) {
             System.out.println("Cantidad inválida. Intente de nuevo.");
            continue;
            }
            jugadores.get(idx).registrarGol(cantidad);
            golesRestantes -= cantidad;

        System.out.println("Se registraron "+cantidad+" goles a "+jugadores.get(idx).getNombre()+"");
        }
    }
    public void registrarEstadisticasPorJugador(Equipo equipo, Scanner sc){
        System.out.println("\nRegistrar estadísticas disciplinarias del equipo "+equipo.getNombre()+"");
        List<Jugador> jugadores = equipo.getJugadores();
        if (jugadores == null || jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados en el equipo.");
            return;
        }
        for (Jugador j : jugadores) {
            System.out.println("Jugador: " + j.getNombre());
            System.out.print("Tarjetas amarillas recibidas: ");
            int amarillas = sc.nextInt();
            sc.nextLine();
            System.out.print("Tarjetas rojas recibidas: ");
            int rojas = sc.nextInt();
            sc.nextLine();
            System.out.print("Faltas cometidas: ");
            int faltas = sc.nextInt();
            sc.nextLine();

        j.registrarTarjetas(amarillas, rojas);
        j.registrarFalta(faltas);
        }
        System.out.println("Estadísticas disciplinarias registradas para "+equipo.getNombre()+"");
    }
    public void mostrarTabla() {
        System.out.println("\n=== TABLA DE POSICIONES - " + nombre + " ===");
        for (Equipo e : equipos) {
            e.generarEstadisticas();
            System.out.println("-----------------------------------");
        }
    }
    public Jugador obtenerGoleador(){
        Jugador mejor = null;
        int maxGoles = -1;
        for (Equipo e : equipos) {
            for (Jugador j : e.getJugadores()) {
                if (j.getGoles() > maxGoles) {
                    maxGoles = j.getGoles();
                    mejor = j;
                }
            }
        }
        return mejor;
    }
    public Equipo equipoMasVictorias() {
        Equipo mejor = null;
        int maxVictorias = -1;
        for (Equipo eq : equipos) {
            if (eq.getVictorias() > maxVictorias) {
            maxVictorias = eq.getVictorias();
            mejor = eq;
            }
        }
        return mejor;
    }
    public void listaPartidosJugados() {
        System.out.println("\n=== PARTIDOS JUGADOS ===");
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.JUGADO)
                System.out.println(p);
        }
    }
    public void listaPartidosPendientes() {
        System.out.println("\n=== PARTIDOS PENDIENTES ===");
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.PENDIENTE)
                System.out.println(p);
        }
    }
    public int totalGolesTorneo() {
        int total = 0;
        for (Partido p : partidos) {
            if (p.getEstado() == EstadoPartido.JUGADO) {
                total += (p.getGolesLocal() + p.getGolesVisitante());
            }
        }
        return total;
    }
   
    @Override
    public String toString() {
        return "Torneo: "+nombre+" | Equipos: "+equipos.size()+" | Partidos: "+partidos.size();
    }
    
}