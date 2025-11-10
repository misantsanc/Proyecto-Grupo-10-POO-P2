package LigaDeportiva;

public class Entrenador extends Persona {
    private String estrategia;
    
    public Entrenador (String nombre, int edad, String estrategia){
        super(nombre, edad);
        this.estrategia = estrategia;
    }
    
    public String getEstrategia(){
        return estrategia;
    }
    public void setEstrategia(String estrategia){
        this.estrategia = estrategia;
    }
    
    @Override
    public String toString(){
        return "Entrenador: " +super.getNombre()+" - Estretegia: "+estrategia+"";
    }
    
}
