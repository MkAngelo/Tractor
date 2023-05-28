
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal {
    
    public static int reiniciarJuego=-1;
    
    public static void main(String[] args){
        
        JOptionPane.showMessageDialog(null, "Are You Ready?");
        
        JFrame ventana = new JFrame("Jueguito"); // Crea la ventana del juego
        Juego jueguito = new Juego(); // Instanciamos la clase Juego
        ventana.add(jueguito); // Agregamos el juego a la ventana
        
        // Configuraciones de la ventana
        ventana.setSize(1300,400);
        ventana.setLocation(70,200);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while(true){
            if(jueguito.juegoFinalizado){
                reiniciarJuego = JOptionPane.showConfirmDialog(null, "Perdiste, quieres jugar de nuevo?", "Haz perdido", JOptionPane.YES_NO_OPTION);
                if(reiniciarJuego==0){
                    reiniciarValores();
                }else if(reiniciarJuego == 1){
                    System.exit(0);
                }
            }else{
                jueguito.repaint(); // Loop para volver a pintar la pantalla
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(Juego.pierdeVida == true){
                    JOptionPane.showMessageDialog(null, "Cuidado!");
                    Juego.pierdeVida=false;
                    Juego.vidas--;
                    Auto.y_inicial=270;
                    Auto.saltando=false;
                    Obstaculo.x_inicial=1300;
                }
            }
        }
    }
    
    public static void reiniciarValores(){
        Juego.juegoFinalizado=false;
        Obstaculo.x_auxiliar=-4;
        Juego.puntos=1;
        Juego.vidas=3;
        reiniciarJuego=-1;
        Obstaculo.x_inicial=1300;
    }
}
