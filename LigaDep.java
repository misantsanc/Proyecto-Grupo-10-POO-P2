package LigaDeportiva;

import java.util.ArrayList;
import java.util.List;

public class LigaDep {
    private List<Torneo> torneos;

    public LigaDep() {
        this.torneos = new ArrayList<>();
    }

    public void agregarTorneo(Torneo t) {
        torneos.add(t);
    }
    public void mostrarReportes() {
        System.out.println("\n=== REPORTES GENERALES DE LA LIGA ===");
        for (Torneo t : torneos) {
            System.out.println("\nTorneo: " + t.getNombre());
            t.mostrarTabla();
        }
    }

    @Override
    public String toString() {
        return "Liga con "+torneos.size()+" torneos registrados.";
    }
    
}