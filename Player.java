import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int velocidad = 5;
    GreenfootImage imgArriba, imgAbajo, imgIzquierda, imgDerecha, imgIdle;
    private int timepoDisparo = 0;
    private int dirección = 1;
    //Constructor
    public Player()
    {
        // Add your action code here.
        //Agregar las imagenes en una variable
        imgArriba = new GreenfootImage("Player_IDLE_Front_Walk01.png");
        imgAbajo = new GreenfootImage("Player_IDLE_Front Dow_Walk01.png");
        imgIzquierda = new GreenfootImage("Player_IDLE_Left_Walk01.png");
        imgDerecha = new GreenfootImage("Player_IDLE_Right_Walk01.png");
        imgIdle = new GreenfootImage("Player_IDLE_Front Dow.png");
        
        //Ajustar el tamaño del personaje
        imgArriba.scale(90,190);
        imgAbajo.scale(90,190);
        imgIzquierda.scale(90,190);
        imgDerecha.scale(90,190);
        imgIdle.scale(90,190);
        
        setImage(imgIdle);
    }
    
    /*
    //Con espacio
    public void act()
    {
        // Add your action code here.
        mover();
        timepoDisparo++;
        if(Greenfoot.isKeyDown("space")&& timepoDisparo > 15){
            disparar();
            timepoDisparo = 0;
        }
    }*/
    
    //Uso del mouse
    public void act()
    {
        // Add your action code here.
        mover();
        timepoDisparo++;
        if(Greenfoot.mousePressed(null) && timepoDisparo > 25){
            disparar();
            timepoDisparo = 0;
        }
    }
    
    //Metodo de movimiento
    private void mover(){
        
        int dx = 0;
        int dy = 0;
        
        if(Greenfoot.isKeyDown("w")){
            dy--;
            setImage(imgArriba);
            
        }
        else if(Greenfoot.isKeyDown("s")) {
            dy++;
            setImage(imgAbajo);
            
        }
        else if(Greenfoot.isKeyDown("a")) {
            dx--;
            dirección = -1;
            setImage(imgIzquierda);
            
        }
        else if(Greenfoot.isKeyDown("d")) {
            dx++;
            dirección = 1;
            setImage(imgDerecha);
            
        }
        else{
            setImage(imgIdle);
        }
        setLocation(getX()+ dx * velocidad,getY()+dy*velocidad);
    }
    
    //Metodo para que el proyectil salga del personaje
    private void disparar(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null && getWorld() != null){
            
            int mx = mouse.getX();
            int my = mouse.getY();
            getWorld().addObject(new Proyectil(mx,my,getX(),getY()),getX() + 50, getY());
        }
    }
}
