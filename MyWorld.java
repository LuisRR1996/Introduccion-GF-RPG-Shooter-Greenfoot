import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private Marcador vida;
    private Marcador puntaje;
    private int contador = 0;
    private boolean juegoFinalizado = false;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        //(x,y,pixeles) - width | height
        super(1700,800, 1); // <- canvas
        //CambiarFondo();
        //Creamos una variable para almacenar la imagen que tenemos de fondo
        //GreenfootImage fondo = getBackground(); //metodo 1
        //GreenfootImage fondo = new GreenfootImage("Scenario_01.jpg"); //metodo 2: Si no se obtiene el getBackground
        //Ajustamos la imagen tanto en ancho como en altura al canvas
        //fondo.scale(getWidth(), getHeight());
        //Establecemos el nuevo fondo ajustado
        //(fondo);
        player();
        
        //showText("Vida: "+ vida,50,50);
        //showText("Puntaje: "+ puntaje,65,75);
        
        //Agregamos los marcadores
        vida = new Marcador("vida: ", 3);
        puntaje = new Marcador("Puntaje: ", 0);
        //Agregamos los objetos al mundo
        addObject(puntaje,70,60);
        addObject(vida,53,90);
        
    }
    
    public void act(){
       if(juegoFinalizado) return; 
        
        //Establecemos que el contador va ir en incremento
       contador++;
       
       //Condicionamos a la frecuencia de generacion del enemigo
       if(contador % 100 == 0){
           generarEnemigo();
       }
       
       verificarEstado();
    }
    
    //Modo prueba
    //public void act(){
    //    if(Greenfoot.isKeyDown("o")){
    //        vida.sumar(1);
    //        Greenfoot.delay(5);
    //    }
    //    if(Greenfoot.isKeyDown("l")){
    //        puntaje.sumar(10);
    //        Greenfoot.delay(5);
    //    }
    //}
    
    
    // Designamos el modificador de acceso (public-private) y lo que devuelve el metodo (void) 
    private void CambiarFondo(){
       //getBackground().setColor(Color.RED); //Colores default
        getBackground().setColor(new Color(145, 50, 168)); //Color rgb
        getBackground().fill();
    }
    
    private void player(){
        //Crear un objeto
        Player Jugador = new Player();
        //Agregamos al objeto en el medio de la pantalla
        addObject(Jugador, 850, 400);
    }
    
    //Metodo para obtener una dirreción diagonal más fluida y cambiante
    private int randomSign(int valor){
        if(Greenfoot.getRandomNumber(2)==0){
            return valor;
        }else{
            return -valor;
        }
    }
      
    private void generarEnemigo(){
        //int y = Greenfoot.getRandomNumber(getHeight());
        //int x = Greenfoot.getRandomNumber(getWidht());
        int vx = Greenfoot.getRandomNumber(2) + 2;//Movimiento en diagonal
        int vy = Greenfoot.getRandomNumber(2) + 2;//Movimiento en diagonal
        
       
        
        int lado = Greenfoot.getRandomNumber(2); //Codigo para generar en horizontal y vertical los enemigos
        
        Enemigo enemigo;
        /*
        Ubicaciones de generaciones:
        0 = Izquierda
        1 = Derecha
        2 = Arriba
        3 = Abajo
         */
        if(lado == 0){
            //Izquierdo
            enemigo = new Enemigo(vx,randomSign(vy));
            //addObject(enemigo,1000,400);
            addObject(enemigo,enemigo.getImage().getWidth()/2,400);
        }else{
            //Derecha
            enemigo = new Enemigo(-vx,randomSign(vy));
            //addObject(enemigo,400,400);
            addObject(enemigo,getWidth() - enemigo.getImage().getWidth()/2,400);
        }
        
    }
    
    //Metodo para usar desde otros objetos
    public void aumentarPuntaje(int puntos){
       puntaje.sumar(puntos); 
    }
    
    public void perderVida(){
        vida.restar(1);
    }
    
    //Metodo para evaluar si el usuario tiene los puntos o su vida llega a 0
    private void verificarEstado(){
        if(puntaje.getValor() >= 100){
            mostrarPantalla("Win.png");
        }else if(vida.getValor() <= 0){
            mostrarPantalla("Loser.png");
        }
    }
    
    //Metodo para mostrar mensaje en la pantalla
    private void mostrarPantalla(String nombreImagen){
        juegoFinalizado = true;
        
        GreenfootImage img = new GreenfootImage(nombreImagen);
        
        img.scale(getWidth(),getHeight());
        
        Actor mensaje = new Actor(){};
        
        mensaje.setImage(img);
        
        addObject(mensaje,getWidth()/2,getHeight()/2);
        
        Greenfoot.stop(); //Se detiene el programa.
    }
 
}
