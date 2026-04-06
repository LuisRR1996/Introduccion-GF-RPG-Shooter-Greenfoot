import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Marcador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Marcador extends Actor
{
    /**
     * Act - do whatever the Marcador wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int valor;
    private String texto;
    //Constructor
    public Marcador(String textoInicial,int valorInicial){
        texto = textoInicial;
        valor = valorInicial;
        actualizar();
    }
    
    public void act()
    {
        // Add your action code here.
        
    }
    
    //Metodo para actualizar datos
    public void actualizar(){
        setImage(new GreenfootImage(texto + valor, 28, Color.WHITE, Color.BLACK));
    }
    //Metodo para sumar puntaje
    public void sumar(int cantidad){
        valor += cantidad;
        actualizar();
    }
    //Metodo para restar vida
    public void restar(int cantidad){
        valor -= cantidad;
        actualizar();
    }
    //obtener el valor
    public int getValor(){
        return valor;
    }
}
