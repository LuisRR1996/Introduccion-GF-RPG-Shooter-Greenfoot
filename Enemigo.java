import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemigo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemigo extends Actor
{
    /**
     * Act - do whatever the Enemigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //int velocidad = 4;
    private int velocidadX;
    private int velocidadY;
    
    //Nuevas variables para cantidad de rebotes
    private int limiteRebotes = 3; 
    private int contadorRebotes = 0;
    
    //Constructor
    public Enemigo(int dx, int dy){
        
        velocidadX = dx;
        velocidadY = dy;
        GreenfootImage img = getImage();
        img.scale(90,90);
        
    }
    
    public void act()
    {
        // Add your action code here.
        mover();
        
        detectarColision();
        
        if(getWorld() != null){
           rebotarBordes(); 
        }
        
        //eliminarAlBorde();
        
    }
    
    //Movimiento
    private void mover(){
        //setLocation(getX() + velocidad,getY());
        setLocation(getX() + velocidadX,getY() + velocidadY);
    }
    
    //Metodo para rebotar en los bordes - metodo 01
    //private void rebotarBordes(){
        //Se obtenie el centro del enemigo para realizar el retorno en direccion contraria
        //int mitad = getImage().getWidth()/2;
        //Se evalua si la velocidad del enemigo es 0 o si el personaje sale del borde en horizontal
        //if(getX() <= mitad || getX() >= getWorld().getWidth() - mitad){
         //  velocidad = -velocidad;
    //    }
    //}
    
    //Segundo método para horizontal y vertical
    
    //Metodo para rebotar en los bordes - Solo para los que trabajan en horizontal.
    private void rebotarBordes(){
        //Se obtenie el centro del enemigo para realizar el retorno en direccion contraria
        //int mitad = getImage().getWidth()/2;
        int mitadAncho = getImage().getWidth()/2;
        int mitadAlto = getImage().getHeight()/2;
        
        boolean reboto = false; // Bandera para saber si tocó un borde
        
        //Se evalua si la velocidad del enemigo es 0 o si el personaje sale del borde en horizontal
        if(getX() <= mitadAncho || getX() >= getWorld().getWidth() - mitadAncho){
            velocidadX = -velocidadX;
            reboto = true; // Avisamos que rebotó
        }
        
        if(getY() <= mitadAlto || getY() >= getWorld().getHeight() - mitadAlto){
            velocidadY = -velocidadY;
            reboto = true; // Avisamos que rebotó
        }
        
        // Si hubo un rebote
        if(reboto) {
            contadorRebotes++; // Le sumamos 1 al contador
            
            // Si ya rebotó la cantidad, lo borramos de la memoria
            if(contadorRebotes >= limiteRebotes) {
                getWorld().removeObject(this);
            }
        }
    }
    
    /*
    private void eliminarAlBorde(){
        World mundo = getWorld();
        if(mundo == null) return;
        
        int mitadAncho = getImage().getWidth()/2;
        int mitadAlto = getImage().getHeight()/2;
        
        //Evaluamos los personajes que salen o tocan el borde
        if(getX() < -50 || getX() > getWorld().getWidth() + 50 || getY() < -50 || getY() > getWorld().getHeight() + 50){
            getWorld().removeObject(this);
        }
        /////////////////////////////////////////////////////////////////////
        if(isAtEdge()){
            World mundo = getWorld();
            if(mundo != null){
                mundo.removeObject(this);
            }
        }
    }*/
    
    //Metodo para colisión
    private void detectarColision(){
        //Evaluamos si el enemigo tiene contacto con el personaje
        if(isTouching(Player.class)){ //Llamamos a la clase del personaje con .class
            ((MyWorld)getWorld()).perderVida(); //Invocamos al mundo y ejecutamos el metodo perderVida
            getWorld().removeObject(this);
        }
    }
}
