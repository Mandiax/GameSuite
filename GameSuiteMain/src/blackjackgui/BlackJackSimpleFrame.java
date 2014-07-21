package blackjackgui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import blackjackdomain.BlackJackMain;
/** CLEARHAND
 * 
 * @author JJV
 * Ieder BlackJackFrame heeft refactorwaarde voor aanpassing schermgrootte
 *
 */
public class BlackJackSimpleFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private static float refactorwaardeX = 1.0F;
	private static float refactorwaardeY = 1.0F;
	private static int KAARTSIZEX = (int)(72*refactorwaardeX);
	private static int KAARTSIZEY = (int)(96*refactorwaardeY);
	private static int RANDEN = (int)(30*refactorwaardeX);
	private static int TEKSTBREEDTE = (int)(80*refactorwaardeX);
	private static int SCHERMX = 0;
	private static int SCHERMY = 0;	
	public JTextArea mededelingenVeld = new JTextArea ();
	private BlackJackMain bjMain;
	protected int aantalAI = 0;
	protected String naam = null;	
	
	/**
	 * @param args
	 * lege constructor
	 */
	public BlackJackSimpleFrame(){
	  super();
	  initialize();	  
	}
	
	/*
	 * instantiering van schermonderdelen. Op ContentPane komen 3 sub-containers. 1 om de spelers weer te geven, 1 om de opties voor de speler weer te geven
	 * een 3e pane wordt gebruikt om info voor de speler weer te geven. 
	 */
	public void initialize(){	
		this.setTitle("BlackJack!");			
		begin();
	}	
	
	public void begin(){
	    kiesNaam();		
		kiesAI();		
		inzetKeuze();
	}
	
	public void mijnInit(){			
	  this.getContentPane().removeAll();	  
	  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	  SCHERMX = dim.width;
	  SCHERMY = dim.height;
	  this.setSize(SCHERMX, SCHERMY);	  
	  Container pane = getContentPane();
	  pane.setLayout(null);	  	 
	  Border lineBlack = BorderFactory.createLineBorder(Color.BLACK);	  
	  
	  Container spelerPane = new Container();
	  spelerPane.setBounds(RANDEN, 0, 10*KAARTSIZEX, SCHERMY-2*RANDEN);
	  pane.add(spelerPane);
	  spelerPane.setLayout(new GridLayout(0, 2,2,2));	  	  
	  
	  Container optiePane = new Container ();
	  optiePane.setBounds(2*RANDEN+10*KAARTSIZEX, 0, TEKSTBREEDTE, 5*RANDEN);
	  optiePane.setLayout(new GridLayout(4, 1));
	  pane.add(optiePane);	
	  
	  Container mededelingenPane = new Container ();
	  mededelingenPane.setBounds(2*RANDEN+10*KAARTSIZEX, 6*RANDEN, 2*TEKSTBREEDTE, 4*RANDEN);
	  pane.add(mededelingenPane);
	  mededelingenPane.setLayout(new BorderLayout());
	  mededelingenPane.removeAll();
	  
	  // invullen mededelingenPane	  
	  JTextArea mededelingenVeld = new JTextArea();
      mededelingenVeld.setText("U bent aan de beurt!");
      mededelingenVeld.setBorder(lineBlack);
      mededelingenVeld.setEditable(false);
      mededelingenPane.add(mededelingenVeld, BorderLayout.CENTER); 
      
      // toevoegen elementen aan spelerPane        
      for (int spelerNummer=0; spelerNummer<bjMain.getAantalSpelers(); spelerNummer++){
    	  Container spelerContainer = new Container();
    	  spelerContainer.setBounds(0, 0, 4*KAARTSIZEX, KAARTSIZEY);
    	  spelerPane.add(spelerContainer);
    	  spelerContainer.setLayout(new BorderLayout());	
    	  spelerContainer.setName("spelerContainer"+spelerNummer);
    	  // maken infobalk voor elke speler
    	  Container spelerInfoScherm = new Container ();
    	  spelerInfoScherm.setBounds(0, 0, 4*KAARTSIZEX, KAARTSIZEY);
    	  spelerContainer.add(spelerInfoScherm, BorderLayout.NORTH);
    	  spelerInfoScherm.setLayout(new GridLayout(1,4,0,1));	
    	  spelerInfoScherm.setName("spelerInfoScherm"+spelerNummer);   
    	  
    	  Container kaartScherm = new Container();	
    	  kaartScherm.setLayout(null);
    	  spelerContainer.add(kaartScherm, BorderLayout.CENTER);    	  
    	  
    	  JLabel spelersNaam = new JLabel();
    	  spelersNaam.setBorder(lineBlack);       
    	  spelersNaam.setOpaque(true);
    	  spelersNaam.setBackground(Color.LIGHT_GRAY);    	  
    	  spelersNaam.setText(bjMain.getSpelerNaam(spelerNummer));
    	  spelersNaam.setHorizontalAlignment(0);    	  
    	  spelerInfoScherm.add(spelersNaam); 
    	  
    	  JLabel aantalCredits = new JLabel();
    	  aantalCredits.setBorder(lineBlack);       
    	  aantalCredits.setOpaque(true);
    	  aantalCredits.setBackground(Color.LIGHT_GRAY);    	  
    	  aantalCredits.setText("Credits: " + bjMain.getSpelerCredits(spelerNummer));
    	  aantalCredits.setHorizontalAlignment(0);    	  
    	  spelerInfoScherm.add(aantalCredits); 
    	  
    	  JLabel aantalInzet = new JLabel();
    	  aantalInzet.setBorder(lineBlack);       
    	  aantalInzet.setOpaque(true);
    	  aantalInzet.setBackground(Color.LIGHT_GRAY);    	  
    	  aantalInzet.setText("Inzet: " + bjMain.getSpelerInzet(spelerNummer));
    	  aantalInzet.setHorizontalAlignment(0);    	  
    	  spelerInfoScherm.add(aantalInzet); 
    	  
    	  JLabel score = new JLabel();
    	  score.setBorder(lineBlack);       
    	  score.setOpaque(true);
    	  score.setText("" + bjMain.vraagScore(spelerNummer));
    	  score.setHorizontalAlignment(JLabel.CENTER);    	  
    	  if (bjMain.vraagScore(spelerNummer)>21){
    	  score.setBackground(Color.RED);  
    	  }
    	  else {
    	  score.setBackground(Color.GREEN);   
    	  }
    	  if (bjMain.getHeeftBlackJack(spelerNummer)==true){
    		  String appendix = mededelingenVeld.getText();
    		  appendix = appendix + " \n " + (bjMain.getSpelerNaam(spelerNummer) + " heeft een BlackJack!");
    		  mededelingenVeld.setText(appendix);    		  
    	  }
    	  spelerInfoScherm.add(score);     	  
    	  for (int kaartNummer=bjMain.vraagHandLengte(spelerNummer)-1; kaartNummer>=0; kaartNummer--){
    		  revalidate();
    		  String kaartID = "kaart" + spelerNummer + "" + kaartNummer + "";
    	   	  JLabel spelerKaart= new JLabel (kaartID);         	         	  		
    	   	  spelerKaart.setBounds(kaartNummer*RANDEN, 0, KAARTSIZEX, KAARTSIZEY);
    	   	  String omzetter = bjMain.vraagKAART(spelerNummer, kaartNummer).toString();
    	   	  if (omzetter!= null){
    	   		  omzetter = omzetter+ ".png";
    	   		  ImageIcon deckKaart = new ImageIcon ("src/blackjackdata/"+ omzetter +"");        		          	          	  
    	   		  spelerKaart.setIcon(deckKaart);
    	   	  	  kaartScherm.add(spelerKaart);        	  	  
    	   	  }     		 
    	  }
      pane.revalidate();	  
   	  }
      
      //toevoegen elementen aan optiePane
      JButton hit = new JButton ("Hit");
      optiePane.add(hit);
      hit.addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent e) {	
		  bjMain.hit();
		  bjMain.berekenScore();
		  if (bjMain.vraagScore(1)<22){
			  mijnInit();			  
		  }
		  if (bjMain.vraagScore(1)>21){			  
			  end();			  
		  }
		  }
      });      
      JButton pass = new JButton ("Pass");
      optiePane.add(pass);
      pass.addMouseListener(new MouseAdapter(){
    	  public void mousePressed(MouseEvent e) { 
    	  end();	  
    	  }
      });
      JButton dubbel = new JButton ("Dubbel");
      optiePane.add(dubbel);
      // add mouselistener
      JButton splitsen = new JButton ("Splitsen");
      optiePane.add(splitsen);   
      // add mouselistener       
	}
	
	public void end (){
		bjMain.einde();		
		mijnInit();		
		JOptionPane eindKlassement = new JOptionPane();
		String uitslag = "En de uitslag is: ";		
		for (int spelerNummer=1; spelerNummer<bjMain.getAantalSpelers(); spelerNummer++){
			int bankScore = bjMain.vraagScore(0);
			int eindScore = bjMain.vraagScore(spelerNummer);
			if ((eindScore>bankScore && eindScore <22)||(bankScore>21 && eindScore<22)){
			uitslag = uitslag + " \n " + " Gefeliciteerd "+ bjMain.getSpelerNaam(spelerNummer) +(", je hebt je inzet van: "+ bjMain.getSpelerInzet(spelerNummer)+ " verdubbeld!.");
			}
		    else if (eindScore==bankScore && eindScore <22){
		    uitslag = uitslag + " \n " + " Gelukkig "+ bjMain.getSpelerNaam(spelerNummer)+(", je hebt je inzet van: "+ bjMain.getSpelerInzet(spelerNummer)+ " teruggekregen.");
		    }
		    else {
		  	uitslag = uitslag + " \n "  + " Helaas "+ bjMain.getSpelerNaam(spelerNummer)+(", je hebt je inzet van: "+ bjMain.getSpelerInzet(spelerNummer)+ " verloren.");        		
		    }
		}
		JTextArea display = new JTextArea();
		display.setText(uitslag);
		eindKlassement.showMessageDialog(eindKlassement, display, "Uitslag",1);
		inzetKeuze();
	}	
	
	public void inzetKeuze(){// TRY CATCH TOEVOEGEN		
		if (Double.parseDouble(bjMain.getSpelerCredits(1))<1){			
			JOptionPane inzetVerhoger = new JOptionPane();					
			inzetVerhoger.showMessageDialog(this.getContentPane(), "U heeft geen credits meer. Start een nieuw spel om met 500 credits te beginnen");			
			;}		
		if (Double.parseDouble(bjMain.getSpelerCredits(1))>0){				
				JOptionPane inzetKeuze= new JOptionPane();
		double credits = Double.parseDouble(bjMain.getSpelerCredits(1));
		double inzett = Double.parseDouble(inzetKeuze.showInputDialog("Vul hier uw inzet in, uw Credits zijn: " + credits + ""));
		this.getContentPane().add(inzetKeuze);			
		if (bjMain.inZetten(inzett) == true){			
			mijnInit();}			
		else {
			JOptionPane inzetFout = new JOptionPane();
			String message = "Voor een getal in, lager dan uw Credits";
			inzetFout.showMessageDialog(this.getContentPane(), message);
			inzetKeuze();
		}
		}
	}
	
	public void kiesNaam(){ // TRY CATCH TOEVOEGEN
		JOptionPane keuzeNaam = new JOptionPane();
		naam = keuzeNaam.showInputDialog("Vul uw naam in");
		this.getContentPane().add(keuzeNaam);
		//DEZE METHODE KAN WEG ALS GAMESUITE NAAM DOORGEEFT
	}
	
	public void kiesAI(){ // TRY CATCH TOEVOEGEN
		JOptionPane aiKeuze= new JOptionPane();
		aantalAI = Integer.parseInt(aiKeuze.showInputDialog("Vul aantal AI lager dan 17 in"));
		bjMain = new BlackJackMain(naam, aantalAI);
		this.getContentPane().add(aiKeuze);		
	}
	
	public static void main(String[] args) {
		BlackJackSimpleFrame blackJackSimpleGui = new BlackJackSimpleFrame();
	    blackJackSimpleGui.setDefaultCloseOperation(1);
		blackJackSimpleGui.setVisible(true); 
	}
}