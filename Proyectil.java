import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Proyectil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Proyectil extends Actor
{
    /**
     * Act - do whatever the Proyectil wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /*
    int velocidad = 8;
    
    public Proyectil(int direccion){
        velocidad = 10 * direccion;
        GreenfootImage img = getImage();
        img.scale(30,30);
    }*/
    
    //Variables para normalizar la velocidad de apuntado
    private double vx;
    private double vy;
    
    public Proyectil(int mouseX,int mouseY, int inicioX, int inicioY){
        int velocidad = 10;
        
        GreenfootImage img = getImage();
        img.scale(30,30);
        
        //Apuntar hacia la dirección del mouse
        double dx = mouseX - inicioX;
        double dy = mouseY - inicioY;
        double distancia = Math.sqrt(dx*dx + dy*dy);
        //Normalizar
        vx = velocidad * (dx / distancia);
        vy = velocidad * (dy / distancia);
        
    }
    
    
    public void act()
    {
        // Add your action code here.
        if(getWorld() == null) return; //Evaluamos si el mundo nos devuelve null
        movimiento();
        eliminarAlBorde();
        if(getWorld() == null) return; 
        detectarColision(); 
    }
    
    /*
    //Para horizontal
    private void movimiento(){
        //Movimiento en Horizontal
        setLocation(getX() + velocidad, getY());
    }*/
    
    private void movimiento(){

        //Movimiento en horizontal, solo derecha

        setLocation((int)(getX() + vx), (int)(getY() + vy));

    }
    
    private void eliminarAlBorde(){
         
        if(isAtEdge()){
            World mundo = getWorld();
            if(mundo != null){
                mundo.removeObject(this);
                return;
            }
        }
    }  
    
     //Metodo para colisión
    private void detectarColision(){
        //Evaluamos si el proyectil tiene contacto con el enemigo
        if(isTouching(Enemigo.class)){ //Llamamos a la clase del Enemigo con .class
            removeTouching(Enemigo.class);//Elimina al enemigo si lo toca
            ((MyWorld)getWorld()).aumentarPuntaje(25); //Invocamos al mundo y ejecutamos el metodo perderVida
            
            getWorld().removeObject(this);
            return;
        }
    }
}
