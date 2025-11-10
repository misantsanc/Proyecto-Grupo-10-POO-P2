package LigaDeportiva;

import java.util.List;

public class AuxiliaresLiga {
    
    private List<Equipo> equipos;
    private List<Entrenador> entrenadores;
    private List<Arbitro> arbitros;
    private List<Torneo> torneos;

    public AuxiliaresLiga(List<Equipo> equipos, List<Entrenador> entrenadores,
                       List<Arbitro> arbitros, List<Torneo> torneos) {
        this.equipos = equipos;
        this.entrenadores = entrenadores;
        this.arbitros = arbitros;
        this.torneos = torneos;
    }

    public Equipo buscarEquipoPorNombre(String nombre){
        for (Equipo e : equipos){
            if (e.getNombre().equalsIgnoreCase(nombre)){
                return e;
            }
        }
        return null;
    }
    public Torneo buscarTorneoPorNombre(String nombre) {
        for (Torneo t : torneos) {
            if (t.getNombre().equalsIgnoreCase(nombre)) {
                return t;
            }
        }
        return null;
    }
    
    public Equipo buscarEquipoEnTorneoPorNombre(Torneo torneos, String nombre) {
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public Arbitro buscarArbitroPorNombre(String nombre) {
        for (Arbitro a : arbitros) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        return null;
    }

}
