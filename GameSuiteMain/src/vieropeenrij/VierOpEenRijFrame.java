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
  
  private static final int FRAME_BREEDTE = Veld.KOLOMMEN * Veld.RANDLENGTE;
  private static final int FRAME_HOOGTE = Veld.RIJEN * Veld.RANDLENGTE;
  private JPanel knoppenveld = null;
  private JButton knop = null;
  private Veld veld = new Veld();
  private JButton[] knoppenArray = new JButton[Veld.KOLOMMEN];
  private Vakje[][] raster = veld.getRaster();
  private int kolomnummer = Veld.KOLOMMEN - 1;
  private Color kleur = null;
  private int[] rijnummer = {5, 5, 5, 5, 5, 5, 5};
  
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
    knoppenveld.setLayout(new GridLayout(0, Veld.KOLOMMEN));
    pane.add(knoppenveld, BorderLayout.NORTH);
    for(int i = 0; i < knoppenArray.length; i++){
      knoppenArray[i] = new JButton();
      knoppenArray[i].setText("" + (i + 1));
      knoppenArray[i].setPreferredSize(new Dimension (Veld.RANDLENGTE, Veld.RANDLENGTE));
      knoppenArray[i].addActionListener(new KnopLuisteraar()); 
      knoppenveld.add(knoppenArray[i]);
    }  
    //maakt speelveld
    pane.add(veld, BorderLayout.CENTER);
    pack();
  }
  
  /**
   * Main method: Toont het speelscherm
   * @param args
   */
  public static void main(String[] args) {
    VierOpEenRijFrame frame = new VierOpEenRijFrame();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
    
  /**
   * Bepaalt welke kleur schijf er geplaatst wordt.
   * @return kleur  de kleur van de schijf
   */
   private Color bepaalSpeler(){
    if(veld.getBeurt() % 2 == 0){
      kleur = Color.YELLOW;
    }
    else{
      kleur = Color.RED;
    }
    return kleur;
  }
  
  /**
   * Luisteraar voor het doen van een zet door een klik op de bijbehorende kolomknop.
   * De schijf wordt geplaatst en er wordt gecontroleerd of er vier dezelfde kleuren op een rij liggen.
   */
   public class KnopLuisteraar implements ActionListener{
    public void actionPerformed(ActionEvent e){
      knop = (JButton) e.getSource();
      kolomnummer = Integer.parseInt(knop.getText()) - 1;
      if(rijnummer[kolomnummer] > -1){
        veld.volgendeBeurt();
        bepaalSpeler();
        raster[kolomnummer][rijnummer[kolomnummer]].setGevuld(true);
        raster[kolomnummer][rijnummer[kolomnummer]].setKleur(kleur);
        veld.repaint();
        rijnummer[kolomnummer]--;
        veld.herkenWinnaar();
      }
    }
  } 
}