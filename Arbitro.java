package LigaDeportiva;

public class Arbitro extends Persona {
    private int aniosExp;
    private String tipo;
    
    public Arbitro (String nombre, int edad, int aniosExp, String tipo){
        super(nombre, edad);
        this.aniosExp = aniosExp;
        this.tipo = tipo;
               
    }
    public int getAniosExp(){
        return aniosExp;
    }
    public String getTipo(){
        return tipo;
    }
    public void setAniosExp(int aniosexp){
        this.aniosExp = aniosExp;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return "Arbitro: "+super.getNombre()+" ("+tipo+", "+aniosExp+" años de experiencia)";
    }
    
}
