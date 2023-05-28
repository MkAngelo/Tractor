
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;


public class Auto {
    // Objeto de la clase juego
    Juego jueguito; 
      
    // Variables para saber si el juego salta o no
    static boolean saltando = false;
    boolean sube = false;
    boolean baja = false;
    
    // Varibales que delimitan el area del objeto
    Area llantaDelantera, llantaTrasera, carroceria, tractor;
    
    // Varibales de tamaño del personaje
    int anchoPersonaje=122;
    int altoPersonaje=78;
    
    // Coordenadas iniciales del personaje
    static int x_inicial = 50;
    static int y_inicial = 270;
    
    // Coordenadas para manipular al personaje
    int x_auxiliar = 0;
    int y_auxiliar = 0;
    
    
    // Contructor de la clase
    public Auto(Juego jueguito){
        this.jueguito = jueguito;
    }
    
    public void mover(){
        if(x_inicial+x_auxiliar>0 && x_inicial+x_auxiliar<jueguito.getWidth()-anchoPersonaje){
            x_inicial+=x_auxiliar;
        }
        if(saltando){
            if(y_inicial==270){ // Si el auto esta en el suelo
                sube=true;
                y_auxiliar=-2;
                baja=false;
            }
            if(y_inicial == 150){ // Si el auto llego al limite
                baja = true;
                y_auxiliar=2;
                sube=false;
            }
            
            if(sube){
                y_inicial+=y_auxiliar;
            }
            if(baja){
                y_inicial+=y_auxiliar;
                if(y_inicial==270){ // si el auto llego al suelo
                    saltando=false;
                }
            }
        }
    }
    
    // Pintar
    public void paint(Graphics2D g){
        ImageIcon auto = new ImageIcon(getClass().getResource("/multimedia/tractor.png"));
        g.drawImage(auto.getImage(), x_inicial, y_inicial, anchoPersonaje, altoPersonaje, null);
    }
    
    // Evento del salto
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            saltando = true;
        }
    }
    
    public Area getBounds(){
        Rectangle forma1 = new Rectangle(x_inicial,y_inicial,95,62);
        carroceria = new Area(forma1);
        
        Ellipse2D forma2 = new Ellipse2D.Double(x_inicial,y_inicial+28,48,48);
        llantaTrasera = new Area(forma2);
        
        Ellipse2D forma3 = new Ellipse2D.Double(x_inicial+73,y_inicial+39,38,38);
        llantaDelantera = new Area(forma3);
        
        tractor=carroceria;
        tractor.add(carroceria);
        tractor.add(llantaTrasera);
        tractor.add(llantaDelantera);
        
        return tractor;
    }
}
