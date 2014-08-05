package vieropeenrij;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Grafische weergave van het speelveld.
 * @author Peter
 *
 */
public class Veld extends JPanel{
  
  protected static final int RIJEN = 6;
  protected static final int KOLOMMEN = 7;
  protected static final int RANDLENGTE = 50;
  private Rectangle2D rand = null;
  private Ellipse2D cirkel = null;
  private int beurt = 0;
  Vakje vakje = null;
  Vakje[][] raster = new Vakje[KOLOMMEN][RIJEN];
  
  /**
   * Constructor: maakt een nieuw speelveld aan
   */
  public Veld(){
    super();
    setPreferredSize(new Dimension(RANDLENGTE * KOLOMMEN, RANDLENGTE * RIJEN));
    for(int col = 0; col < KOLOMMEN; col++){
      for(int rij = 0; rij < RIJEN; rij++){
        raster[col][rij] = new Vakje (col, rij);
      }
    }
  }
  
  /**
   * Tekent een vier-op-een-rij veld.
   * @param g2d grafische context
   */
  protected void paintComponent(Graphics g){
    super.paintComponents(g);
    Graphics2D g2d = (Graphics2D)g;
    for(int i = 0; i < KOLOMMEN; i++){
      for(int j = 0; j < RIJEN; j++){
        rand = new Rectangle2D.Double(i*RANDLENGTE, j*RANDLENGTE, RANDLENGTE, RANDLENGTE); 
        g2d.setColor(Color.BLUE);
        g2d.fill(rand);
        cirkel = new Ellipse2D.Double(i*RANDLENGTE, j*RANDLENGTE, RANDLENGTE * 0.9, RANDLENGTE * 0.9);
        if(raster[i][j].getGevuld()){
          g2d.setColor(raster[i][j].getKleur());
        }
        else{
          g2d.setColor(Color.WHITE);
        }
        g2d.fill(cirkel);
      }
    }
  }

  /**
   * Levert het raster van vakjes waarop het veld gebaseerd is.
   * @return  2 dimensionale array van vakjes.
   */
  public Vakje[][]getRaster(){
    return raster;
  }
  
  /**
   * Verhoogt het aantal beurten met 1.
   */
  public void volgendeBeurt(){
    beurt = beurt + 1;
  }
  
  /**
   * Vraagt op welk beurtnummer het is.
   * @return  het beurtnummmer
   */
  public int getBeurt(){
    return beurt;
  }
    
  /**
   * Checkt of er vier schijfjes van dezelfde kleur op een rij liggen.
   */
  public void herkenWinnaar(){
    winnaarHorizontaal();
    winnaarVerticaal();
    winnaarDiagonaalLR();
    winnaarDiagonaalRL();
  }
  
  /**
   * Controleert of er horizontaal 4 schijven van dezelfde kleur op een rij staan.
   * Indien dit het geval is verschijnt er een pop-up met de winnaar in beeld.
   */
  private void winnaarHorizontaal(){
    for(int i = 0; i < KOLOMMEN - 3; i++){
      for(int j = 0; j < RIJEN; j++){
        if(raster[i][j].getKleur() == Color.YELLOW &&
           raster[i + 1][j].getKleur() == Color.YELLOW &&
           raster[i + 2][j].getKleur() == Color.YELLOW &&
           raster[i + 3][j].getKleur() == Color.YELLOW){
           JOptionPane.showMessageDialog(this, "Geel heeft gewonnen!");
        }
        if(raster[i][j].getKleur() == Color.red &&
           raster[i + 1][j].getKleur() == Color.RED &&
           raster[i + 2][j].getKleur() == Color.RED &&
           raster[i + 3][j].getKleur() == Color.RED){
           JOptionPane.showMessageDialog(this, "Rood heeft gewonnen!"); 
        }
      }
    }
  }
  
  /**
   * Controleert of er verticaal 4 schijven van dezelfde kleur op een rij staan.
   * Indien dit het geval is verschijnt er een pop-up met de winnaar in beeld.
   */
  private void winnaarVerticaal(){
    for(int i = 0; i < KOLOMMEN; i++){
      for(int j = 0; j < RIJEN - 3; j++){
        if(raster[i][j].getKleur() == Color.YELLOW &&
           raster[i][j + 1].getKleur() == Color.YELLOW &&
           raster[i][j + 2].getKleur() == Color.YELLOW &&
           raster[i][j + 3].getKleur() == Color.YELLOW){
           JOptionPane.showMessageDialog(this, "Geel heeft gewonnen!");
        }
        if(raster[i][j].getKleur() == Color.red &&
           raster[i][j + 1].getKleur() == Color.RED &&
           raster[i][j + 2].getKleur() == Color.RED &&
           raster[i][j + 3].getKleur() == Color.RED){
           JOptionPane.showMessageDialog(this, "Rood heeft gewonnen!"); 
        }
      }
    }
  }
  
  /**
   * Controleert of er diagonaal (linksboven naar rechtsonder) 4 schijven van dezelfde kleur op een rij staan.
   * Indien dit het geval is verschijnt er een pop-up met de winnaar in beeld.
   */
  private void winnaarDiagonaalLR(){
    for(int i = 0; i < KOLOMMEN -3; i++){
      for(int j = 0; j < RIJEN - 3; j++){
        if(raster[i][j].getKleur() == Color.YELLOW &&
           raster[i + 1][j + 1].getKleur() == Color.YELLOW &&
           raster[i + 2][j + 2].getKleur() == Color.YELLOW &&
           raster[i + 3][j + 3].getKleur() == Color.YELLOW){
           JOptionPane.showMessageDialog(this, "Geel heeft gewonnen!");
        }
        if(raster[i][j].getKleur() == Color.RED &&
           raster[i + 1][j + 1].getKleur() == Color.RED &&
           raster[i + 2][j + 2].getKleur() == Color.RED &&
           raster[i + 3][j + 3].getKleur() == Color.RED){
           JOptionPane.showMessageDialog(this, "Rood heeft gewonnen!"); 
        }
      }
    }
  }
  
  /**
   * Controleert of er diagonaal (rechtsboven naar linksonder) 4 schijven van dezelfde kleur op een rij staan.
   * Indien dit het geval is verschijnt er een pop-up met de winnaar in beeld.
   */
  private void winnaarDiagonaalRL(){
    for(int i = KOLOMMEN - 4; i < KOLOMMEN - 1; i++){
      for(int j = 0; j < RIJEN - 3; j++){
        if(raster[i][j].getKleur() == Color.YELLOW &&
           raster[i - 1][j + 1].getKleur() == Color.YELLOW &&
           raster[i - 2][j + 2].getKleur() == Color.YELLOW &&
           raster[i - 3][j + 3].getKleur() == Color.YELLOW){
           JOptionPane.showMessageDialog(this, "Geel heeft gewonnen!");
        }
        if(raster[i][j].getKleur() == Color.RED &&
           raster[i - 1][j + 1].getKleur() == Color.RED &&
           raster[i - 2][j + 2].getKleur() == Color.RED &&
           raster[i - 3][j + 3].getKleur() == Color.RED){
           JOptionPane.showMessageDialog(this, "Rood heeft gewonnen!"); 
        }
      }
    }
  }
}
