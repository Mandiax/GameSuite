package vieropeenrij;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klasse voor het tonen van een vier-op-een-rij spel
 * @author Peter
 *
 */
public class VierOpEenRijFrame extends JFrame{
  
  private static final int RIJEN = 6;
  private static final int KOLOMMEN = 7;
  private static final int RANDLENGTE = 50;
  private static final int FRAME_BREEDTE = KOLOMMEN * RANDLENGTE;
  private static final int FRAME_HOOGTE = RIJEN * RANDLENGTE;
  private JPanel knoppenveld = null;
  private JButton knop = null;
  private Veld veld = new Veld();
  private JButton[] knoppenArray = new JButton[KOLOMMEN];
  private Vakje[][] raster = veld.getRaster();
  private int rijnummer = RIJEN - 1;
  private int kolomnummer = KOLOMMEN - 1;
  
  public VierOpEenRijFrame(){
  super();
  initialize();
  }
  
  /**
   * Verzorgt de layout van het venster.
   * Venster bestaat uit 2 gedeelten: het knoppengedeelte en het veldgedeelte
   */
  public void initialize(){
    //settings voor venster
    setTitle("Speel vier op een rij!");
    setSize(FRAME_BREEDTE, FRAME_HOOGTE);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((dim.width - FRAME_BREEDTE) / 2,(dim.height - FRAME_HOOGTE) / 2);
    Container pane = getContentPane();
    pane.setLayout(new BorderLayout());
   
    //maakt rij met knoppen
    knoppenveld = new JPanel();
    knoppenveld.setLayout(new GridLayout(0, KOLOMMEN));
    pane.add(knoppenveld, BorderLayout.NORTH);
    for(int i = 0; i < knoppenArray.length; i++){
      knoppenArray[i] = new JButton();
      knoppenArray[i].setText("" + (i + 1));
      knoppenArray[i].setPreferredSize(new Dimension (RANDLENGTE, RANDLENGTE));
      knoppenArray[i].addActionListener(new KnopLuisteraar()); 
      knoppenveld.add(knoppenArray[i]);
    }  
    //maakt speelveld
    pane.add(veld, BorderLayout.CENTER);
    pack();
  }
  
  public static void main(String[] args) {
    VierOpEenRijFrame frame = new VierOpEenRijFrame();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  public class KnopLuisteraar implements ActionListener{
    public void actionPerformed(ActionEvent e){
      knop = (JButton) e.getSource();
      kolomnummer = Integer.parseInt(knop.getText()) - 1;
      veld.volgendeBeurt();
      for(int i = rijnummer; i > 0; rijnummer--){
        if(raster[kolomnummer][rijnummer].getGevuld() == false) {
          raster[kolomnummer][rijnummer].setGevuld(true);
          if(veld.getBeurt() % 2 == 0){
            raster[kolomnummer][rijnummer].setKleur(Color.YELLOW);
          }
          else{
            raster[kolomnummer][rijnummer].setKleur(Color.RED);
          }
          veld.repaint();
          break;
        }
       // herkenWinnaar();
      }
    } 
  } 
  
  /**
   * Checkt of er vier schijfjes van dezelfde kleur op een rij liggen.
   */
/*  private void herkenWinnaar(){
    for(int i = 0; i < KOLOMMEN -1; i++){
      for(int j = 0; j < RIJEN - 1; j++){
        Color kleur = raster[kolomnummer][rijnummer].getKleur();
        if(kleur == raster[kolomnummer + 1][rijnummer].getKleur() &&
            kleur == raster[kolomnummer + 2][rijnummer].getKleur() &&
            kleur == raster[kolomnummer + 3][rijnummer].getKleur()){
          System.out.println("Winnaar!");
        }
      }
    }
  }
*/  
}