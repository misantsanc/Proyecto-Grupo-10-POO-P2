package LigaDeportiva;

import java.time.LocalDate;
import java.time.LocalTime;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private Arbitro arbitro;
    private Estadio estadio;
    private EstadoPartido estado;
    private LocalDate fecha;
    private LocalTime hora;

    public Partido(Equipo local, Equipo visitante, Arbitro arbitro, Estadio estadio, LocalDate fecha, LocalTime hora) {
        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        this.arbitro = arbitro;
        this.estadio = estadio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoPartido.PENDIENTE;
    }
 
    public Equipo getEquipoLocal(){ 
        return equipoLocal; 
    }
    public Equipo getEquipoVisitante(){
        return equipoVisitante; 
    }
    public int getGolesLocal() {
        return golesLocal;
    }
    public int getGolesVisitante() {
        return golesVisitante;
    }
    public EstadoPartido getEstado() {
        return estado;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public Arbitro getArbitro() {
        return arbitro;
    }
    public Estadio getEstadio() {
        return estadio;
    }
    
    public void registrarResultado(int gL, int gV) {
        this.golesLocal = gL;
        this.golesVisitante = gV;
        this.estado = EstadoPartido.JUGADO;

        equipoLocal.registrarPartido(gL, gV);
        equipoVisitante.registrarPartido(gV, gL);
    }
    
    @Override
    public String toString() {
        String marcador = estado == EstadoPartido.PENDIENTE ? "": ""+golesLocal+" - "+golesVisitante+"";
        return
        ""+equipoLocal.getNombre()+ " "+marcador+" " +equipoVisitante.getNombre()+",\nArbitro: "+arbitro+",\nEstadio: "+estadio+",\nFecha: "+fecha+", Hora: "+hora+"\n";
    }
    
}