package LigaDeportiva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Liga {
  
    private static final Scanner sc = new Scanner(System.in);
    
    private static List<Equipo> equipos = new ArrayList<>();
    private static List<Entrenador> entrenadores = new ArrayList<>();
    private static List<Arbitro> arbitros = new ArrayList<>();
    private static List<Torneo> torneos = new ArrayList<>();
    private static AuxiliaresLiga exepciones = new AuxiliaresLiga(equipos, entrenadores, arbitros, torneos);
    
    public static void main(String[] args){
        Liga app = new Liga();
        app.Menu();
    }
    
    private void Menu(){
        int opcion;
        do {
            System.out.println("\n=== MENU LIGA DEPORTIVA ===");
            System.out.println("(Sugerido ir en orden de opciones)");
            System.out.println("1. Registrar equipo y sus jugadores");
            System.out.println("2. Registrar entrenador y árbitro");
            System.out.println("3. Crear torneo y agregar equipos");
            System.out.println("4. Agendar partido");
            System.out.println("5. Registrar resultado de partido");
            System.out.println("6. Mostrar tabla de posiciones");
            System.out.println("7. Reporte: máximo goleador");
            System.out.println("8. Reporte: equipo con más victorias");
            System.out.println("9. Mostrar partidos programados");
            System.out.println("10. Generar estadísticas de torneo");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();
            
                switch (opcion) {
                    case 1 -> registrarEquipoYJugadores();
                    case 2 -> registrarEntrenadorYArbitro();
                    case 3 -> crearTorneoYAgregarEquipos();
                    case 4 -> agendarPartido();
                    case 5 -> registrarResultadoPartido();
                    case 6 -> mostrarTablaPosiciones();
                    case 7 -> reporteMaximoGoleador();
                    case 8 -> reporteEquipoMasVictorias();
                    case 9 -> mostrarPartidosProgramados();
                    case 10 -> generarEstadisticasTorneo();
                    case 11 -> System.out.println("Gracias por usar el Programa. Saliendo...");
                    default -> System.out.println("Opción inválida");
                }
            } while (opcion != 11);
        } 
     
    
    private void registrarEquipoYJugadores(){
        System.out.println("\n== Registrar Equipo y Jugadores ==");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = sc.nextLine().trim();
        if (nombreEquipo.isEmpty()) {
        System.out.println("El nombre del equipo no puede estar vacío.");
        return;
        }
        if (exepciones.buscarEquipoPorNombre(nombreEquipo) != null){
            System.out.println("Ya existe un equipo con ese nombre");
            return;
        }
        
        System.out.print("Ciudad del equipo: ");
        String ciudad = sc.nextLine().trim();
        System.out.print("Nombre del entrendador del equipo: ");
        String nombreEntrenador = sc.nextLine().trim();
        System.out.print("Edad del entrenador: ");
        int edadEntrenador = sc.nextInt();
        sc.nextLine();
        System.out.print("Estrategia del entrenador: ");
        String estrategiaEntrenador = sc.nextLine().trim();
        
        Entrenador entrenador = new Entrenador(nombreEntrenador, edadEntrenador, estrategiaEntrenador);
        entrenadores.add(entrenador);
        Equipo e = new Equipo(nombreEquipo, ciudad, entrenador);
        equipos.add(e);
        
        System.out.print("¿Cuantos jugadores va haber en el equio?: ");
        int nJugadores = sc.nextInt();
        sc.nextLine();
        if (nJugadores <= 0) {
            System.out.println("El equipo debe tener al menos un jugador.");
            return;
        }
        
        for (int i = 0 ; i < nJugadores ; i++){
            System.out.println("-- Jugador #"+(i+1)+" --");
            System.out.print("Nombre: ");
            String nombreJugador = sc.nextLine().trim();
            System.out.print("Edad: ");
            int edadJugador = sc.nextInt();
            sc.nextLine();
            System.out.print("Posicion: ");
            String posicionJugador = sc.nextLine().trim();
            System.out.print("Numero: ");
            int numeroJugador = sc.nextInt();
            sc.nextLine();
            
            Jugador j = new Jugador(nombreJugador, edadJugador, posicionJugador, numeroJugador);
            e.agregarJugador(j);
        }
        System.out.println("Equipo registrado: " +e);
        System.out.println("Total de jugadores: " +e.getJugadores().size());
    }
    
    private void registrarEntrenadorYArbitro(){
        System.out.println("\n== Registrar Entrenador y Arbirtros ==");
        System.out.print("Registrar como 1. Entrenador o 2. Arbitro: ");
        int opc = sc.nextInt();
        sc.nextLine();
        if (opc == 1){
            System.out.println("-- 1. Registrar Entrenador --");
            System.out.print("Nombre del entrenador: ");
            String nombreEnt = sc.nextLine().trim();
            System.out.print("Edad del entrenador: ");
            int edadEnt = sc.nextInt();
            sc.nextLine();
            System.out.print("Estrategia del entrenador: ");
            String estrategiaEnt = sc.nextLine();
            
            Entrenador entrenador = new Entrenador(nombreEnt, edadEnt, estrategiaEnt);
            entrenadores.add(entrenador);
            System.out.println("Entrenador Registrado: "+entrenador);
        } else if (opc == 2){
            System.out.println("-- 2. Registrar Arbitro --");
            System.out.print("Nombre del arbirto: ");
            String nombreArb = sc.nextLine();
            System.out.print("Edad del arbitro: ");
            int edadArb = sc.nextInt();
            sc.nextLine();
            System.out.print("Años de Experiencia del arbitro: ");
            int añosExpArb = sc.nextInt();
            sc.nextLine();
            System.out.print("Tipo de arbitro: ");
            String tipoArb = sc.nextLine();
            
            Arbitro arbitro = new Arbitro(nombreArb, edadArb, añosExpArb, tipoArb);
            arbitros.add(arbitro);
            System.out.println("Arbitro registrado: "+arbitro+"");
        } else {
            System.out.println("Opcion Invalida");
        }
        
    }
    
    private void crearTorneoYAgregarEquipos(){
        System.out.println("\n== Crear Torneo y agregar Equipos ==");
        System.out.print("Nombre del Torneo: ");
        String nombreTor = sc.nextLine().trim();
        if (exepciones.buscarTorneoPorNombre(nombreTor) != null) {
            System.out.println("Ya existe un torneo con ese nombre.");
            return;
        }
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        Torneo t = new Torneo(nombreTor);
        torneos.add(t);
        System.out.println("Torneo Creado: "+nombreTor+"");
        
        System.out.println("Agregar equipos al torneo (escriba 'fin' para terminar)");
        while (true){
            System.out.print("Nombre de equipo a agregar: ");
            String nombreEq = sc.nextLine().trim();
            if (nombreEq.equalsIgnoreCase("fin")){
                break;
            } 
            Equipo e = exepciones.buscarEquipoPorNombre(nombreEq);
            if (e == null){
                System.out.println("No existe un equipo con ese nombre. Registrelo primero.");
            } else{
                t.agregarEquipo(e);
                System.out.println("Equipo agregado al torneo: "+e.getNombre());
            }  
        }
        System.out.println("Torneo '"+nombreTor+"' creado con "+t.getEquipos().size()+" equipos.");
    }
    
    private void agendarPartido(){
        System.out.println("\n== Agendar Partidos ==");
        if (torneos.isEmpty()) { 
            System.out.println("Primero cree un torneo para poder agendar partidos."); 
            return; 
        }
        
        System.out.print("Nombre del torneo donde agendar el partido: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
         if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        if (torneo.getEquipos().size() < 2){
            System.out.println("El torneo debe tener al menos 2 equipos");
            return;
        }
        
        System.out.print("Equipo Local: ");
        String equiLocal = sc.nextLine().trim();
        System.out.print("Equipo Visitante: ");
        String equiVisitante = sc.nextLine().trim();
        if (equiLocal.equalsIgnoreCase(equiVisitante)){
            System.out.println("No se permiten equipos iguales en un partido");
            return;
        }
        Equipo equipoLocal = exepciones.buscarEquipoEnTorneoPorNombre(torneo, equiLocal);
        Equipo equipoVisitante = exepciones.buscarEquipoEnTorneoPorNombre(torneo, equiVisitante);
        if (equipoLocal == null || equipoVisitante == null){
            System.out.println("Uno o ambos equipos no estan inscritos en el torneo");
            return;
        }
        
        System.out.print("Nombre del estadio para agendar el partido: ");
        String nombreEsta = sc.nextLine().trim();
        System.out.print("Ciudad donde pertenece el estadio: ");
        String ciudadEsta = sc.nextLine().trim();
        System.out.print("Capacidad del estadio: ");
        int capacidadEsta = sc.nextInt();
        sc.nextLine();
        Estadio estadio = new Estadio(nombreEsta, ciudadEsta, capacidadEsta);
        
        
        System.out.print("Nombre del Arbitro (registrado): ");
        String nombreArb = sc.nextLine().trim();
        Arbitro arbitro = exepciones.buscarArbitroPorNombre(nombreArb);
        if (arbitro == null){
            System.out.println("Arbitro no encontrado. Primero tiene que registrarlo");
            return;
        }
        
        System.out.print("Fecha del partido (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine().trim();
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr);
        } catch (DateTimeParseException ex){
            System.out.println("Formato de fecha invalido");
            return;
        }
        System.out.print("Hora del partido (HH:MM): ");
        String horaStr = sc.nextLine().trim();
        LocalTime hora;
        try {
            hora = LocalTime.parse(horaStr);
        } catch (DateTimeParseException ex){
            System.out.println("Formato de fecha invalido");
            return;
        }
        Partido p = new Partido(equipoLocal, equipoVisitante, arbitro, estadio, fecha, hora);
        torneo.agendarPartido(p);
        System.out.println("Partido agendado:");
        System.out.println(p);
        
    }
    
    private void registrarResultadoPartido(){
        System.out.println("\n== Registrar Resultado de Partido ==");
        if (torneos.isEmpty()){
            System.out.println("No existen torneos registrados. Por lo que no se puede registrar el resultado de un partido.");
            return;
        }
        System.out.print("Nombre del torneo: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        
        List<Partido> partidos = torneo.getPartidos();
        if (partidos.isEmpty()){
            System.out.println("No hay partidos en este torneo");
            return;
        }
        
        System.out.println("Partidos: ");
        for (int i = 0; i<partidos.size();i++){
            System.out.println(""+(i+1)+". "+partidos.get(i)+"");
        }
        System.out.print("Seleccione el partido para registrar resultado: ");
        int idx = sc.nextInt()-1;
        sc.nextLine();
        if (idx < 0 || idx >= partidos.size()){
            System.out.print("Seleccion invalida. Intente de nuevo.");
            return;
        }
        
        Partido partido = partidos.get(idx);
        System.out.print("Goles de equipo local ("+partido.getEquipoLocal().getNombre()+"): ");
        int golesLocal = sc.nextInt();
        sc.nextLine();
        System.out.print("Goles equipo visitante ("+partido.getEquipoVisitante().getNombre()+"): ");
        int golesVisitante = sc.nextInt();
        sc.nextLine();
        
        partido.registrarResultado(golesLocal, golesVisitante);
        torneo.registrarResultado(partido, golesLocal, golesVisitante);
        
        System.out.println("\nRegistrar goles de jugadores del equipo local en el partido: ");
        torneo.registrarGolesPorJugador(partido.getEquipoLocal(), golesLocal, sc);
        System.out.println("\nRegistrar goles de jugadores del equipo visitante en el partido:");
        torneo.registrarGolesPorJugador(partido.getEquipoVisitante() , golesVisitante, sc);
        
        torneo.registrarEstadisticasPorJugador(partido.getEquipoLocal(), sc);
        torneo.registrarEstadisticasPorJugador(partido.getEquipoVisitante(), sc);
        
        System.out.println("\nResultado registrado correctamente:");
        System.out.println(""+partido.getEquipoLocal().getNombre()+ " "+golesLocal+" - " +golesVisitante+" "+partido.getEquipoVisitante().getNombre()+"");
    }
    
    private void mostrarTablaPosiciones(){
        System.out.println("\n== Mostrar Tabla de Posiciones ==");
        if (torneos.isEmpty()) {
            System.out.println("No hay torneos registrados aún. Por lo que no hay tabla de Posiciones");
            return;
        }
        System.out.print("Nombre del Torneo: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        torneo.mostrarTabla();
    }
    
    private void reporteMaximoGoleador(){
        System.out.println("\n== Reporte de Maximo Goleador ==");
        if (torneos.isEmpty()) {
            System.out.println("No hay torneos registrados aún. Por lo que no hay reporte de maximo goleador.");
            return;
        }
        System.out.print("Nombre del Torneo: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        Jugador goleador = torneo.obtenerGoleador();
        if (goleador != null){
            System.out.println("\nMáximo Goleador del Torneo '"+torneo.getNombre()+"'");
            System.out.println("---------------------------------------------");
            System.out.println("Jugador: " +goleador.getNombre());
            System.out.println("Goles: " +goleador.getGoles());
        } else{
            System.out.print("No hay datos de goleadores aun");
        } 
    }
    
    private void reporteEquipoMasVictorias(){
        System.out.println("\n== Reporte de Equipos con Mas Victorias ==");
        if (torneos.isEmpty()) {
            System.out.println("No hay torneos registrados aún. Por lo que no hay reporte de equipo con mas victorias");
            return;
        }
        System.out.print("Nombre del Torneo: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        Equipo e = torneo.equipoMasVictorias();
        if (e != null){
            System.out.println("Equipo con mas victorias: "+e.getNombre()+" (V: "+e.getVictorias()+")");
        } else{
            System.out.println("No hay datos aun de victorias");
        }
    }
    
    private void mostrarPartidosProgramados(){
        System.out.println("\n== Partidos Programados ==");
        if (torneos.isEmpty()) {
            System.out.println("No hay torneos registrados aún. Por lo que no hay partidos programados.");
            return;
        }
        System.out.print("Nombre del Torneo: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        torneo.listaPartidosPendientes();
        torneo.listaPartidosJugados();
    }
    
    private void generarEstadisticasTorneo(){
        System.out.println("\n== Estadisticas de Torneo ==");
        if (torneos.isEmpty()) {
            System.out.println("No hay torneos registrados aún. Por lo que no se puede generar estadisticas.");
            return;
        }
        System.out.print("Nombre del Torneo: ");
        String nombreTor = sc.nextLine().trim();
        Torneo torneo = exepciones.buscarTorneoPorNombre(nombreTor);
        if (torneo == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        System.out.println("\nEstadísticas del Torneo: " +torneo.getNombre());
        System.out.println("Total goles del torneo: "+torneo.totalGolesTorneo());
        System.out.println("Tabla de Posiciones: ");
        torneo.mostrarTabla();
    }         
    
}
