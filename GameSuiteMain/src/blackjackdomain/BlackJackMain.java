package blackjackdomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * BlackJack
 * @author JJV
 * Gehanteerde spelregels in deze BlackJack versie:
 *  - Verzekeren en Surrender zijn geen opties in dit spel
 *  - AAS voor Bank telt altijd als 11 punten 
 *  - de AI-spelers kunnen niet verdubbelen of splitsen
 *  - Splitsen voor de gebruiker is alleen mogelijk bij dezelfde kaart ongeacht de waarde (dus 2 Heren of 2 Boeren, niet bij 10, Boer of Boer, Vrouw)
 *  - bij de AI is een AAS 1 of 11 punten afhankelijk van de situatie
 *  - Een speler heeft BlackJack wanneer de eerste 2 kaarten 21 punten waard zijn && de Bank geen mogelijkheid tot BlackJack heeft
 *  - Een AI-speler met BlackJack krijgt geen nieuwe kaarten. De Gebruiker kan wel kiezen om een kaart aan te nemen
 *  - Een BlackJack wordt aan het einde uitgekeerd als 2* de inzet
 *  - Met het kiezen van "Verdubbelen" (Knop: Dubbel) krijgt de speler automatisch nog 1 kaart
 */

public class BlackJackMain {
  private static final long serialVersionUID = 1L;
  ArrayList <BlackJackKaart> deck = null;  
  ArrayList <BlackJackSpeler> spelersLijst = null;
  ArrayList <String> namenLijst = new ArrayList<String>(
			Arrays.asList("Jan", "Jozef", "Klaas", "Reimer", "Bert", "Hans", "Karel", "Remi",
			"Pieter", "Peter", "Koen", "Patricia", "Johanna", "Anna", "Kofi", "Silvio", "George",
			"Barack", "Mark", "Vladimir"));
  ArrayList <String> wisselLijst = new ArrayList <String>();
  String naam = null;  
  int aantalAI = 0;
  int aantalSpelers = 0;   
  Random ran = new Random();
  boolean isGesplitst = false;
  
 /**
  * @param naam; naam van de speler, aantalAI; aantal computertegenstanders
  * Constructor
  */
  
  public BlackJackMain (String naam, int aantalAI){
    this.aantalAI = aantalAI;  
    this.naam = naam;     
    mijnInit();        
    }  
  
  /*
   * mijnInit;  Vullen van de spelersLijst met op [0] de bank en op [1] de speler, de overige plaatsen worden gevuld met AI-spelers
   */
  
  public void mijnInit(){ 	
	isGesplitst = false;
    ArrayList <BlackJackSpeler> spelersLijst = new ArrayList <BlackJackSpeler>();      
    this.spelersLijst = spelersLijst;
    spelersLijst.add(new BlackJackBank());
    spelersLijst.add(new BlackJackGebruiker());
    spelersLijst.get(1).setNaam(naam);     
    for (int i=0; i<aantalAI; i++){      
      int keuzeNummer = ran.nextInt(namenLijst.size());
      String keuzeNaam = namenLijst.get(keuzeNummer);
      namenLijst.remove(keuzeNummer);
      spelersLijst.add(new BlackJackAI());  
      spelersLijst.get(i+2).setNaam(keuzeNaam);       
    }   
    aantalSpelers = spelersLijst.size();       
  }
  
  /*
   * @param de inzet van de speler voor het begin van het spel, zoals gevraagd in de gui
   * na het controleren van de inzet begint de eersteRonde. Hierna worden de kaarten ook gemaakt.
   */  
  public boolean inZetten(int inzet){ 	  
	mutatieLijst();
	clearHand();
	clearBlackJack();	
	if (spelersLijst.size()>2 && spelersLijst.get(spelersLijst.size()-1) instanceof BlackJackGebruiker){
		spelersLijst.remove(spelersLijst.size()-1);
		aantalSpelers --;
	}
	spelersLijst.get(1).setInzet(0);
	BlackJackDeck blackJackKaarten = new BlackJackDeck();
    deck = blackJackKaarten.maakDeck(aantalSpelers);   
    if (inzet>0 && inzet <= spelersLijst.get(1).getCredits()){
      spelersLijst.get(1).setInzet(inzet);      
      for (int a=2; a<aantalSpelers; a++){     	
        spelersLijst.get(a).setInzet(((int)Math.sqrt(spelersLijst.get(a).getCredits()))+(ran.nextInt(10)));      
      }
      deelKaartenUit(); 
      return true;             
    }
    else {    	
    	return false;
    	}
  }   
  
  /*
   * In de eersteRonde wordt aan de Bank 1 kaart uitgedeeld en aan de rest
   * van de spelers 2 kaarten. Hierbij wordt gekeken of de speler een BlackJack heeft
   * Na dit begin van het spel volgt de Ronde voor de AI
   */
  
  public void deelKaartenUit(){
	for (BlackJackSpeler a:spelersLijst){ 
      if (a instanceof BlackJackBank){
        geefKaart(spelersLijst.indexOf(a));
      }
      else {
    	geefKaart(spelersLijst.indexOf(a));
    	geefKaart(spelersLijst.indexOf(a));      	
        if (a.getKAART(0).getWaarde()+ a.getKAART(1).getWaarde()==21 && spelersLijst.get(0).getKAART(0).getWaarde()<10){                        
            a.setHeeftBlackJack();             
        }
      }
	}   	
    ronde();
  }  
  
  /*
   *  In deze ronde berekent Main voor elke AI hoeveel kaarten deze afneemt
   *  de totale score van de kaarten wordt berekend en opgeslagen in de instantie van de Speler
   */
  
  public void ronde(){ 
    for (int i=2; i<aantalSpelers; i++){
      if (spelersLijst.get(i).getHeeftBlackJack()!= true){
        int score = 0;
        int aantalAzen = 0;
        for (int b=0; b<spelersLijst.get(i).handLengte(); b++){
        	score = score + spelersLijst.get(i).getKAART(b).getWaarde();
          if (spelersLijst.get(i).getKAART(b).getWaarde()==11){
        	  aantalAzen ++;
          }
        }
        while (score-(aantalAzen*10)<17){
          spelersLijst.get(i).addKAART(deck.get(0));
          if (deck.get(0).getWaarde()==11){
        	  aantalAzen ++;
          }
          score = score + deck.get(0).getWaarde();
          deck.remove(0);
        }        
      }       
    }      
  }          
  
  public void hit(int spelerNummer){   
    geefKaart(spelerNummer);   
    berekenScore();    
  }    
  
  public void einde(){	  
    int bankScore = spelersLijst.get(0).getKAART(0).getWaarde();
    while (bankScore<17){
      spelersLijst.get(0).addKAART(deck.get(0)); 
      bankScore = bankScore + deck.get(0).getWaarde();
      deck.remove(0);
    }    
    berekenScore();
    for (BlackJackSpeler g:spelersLijst){
      if (g instanceof BlackJackAI){
    	  int eindScore = g.getScore();
    	  if ((eindScore>bankScore && eindScore <22)||(bankScore>21 && eindScore<22)){
    		  g.setCredits(-2*g.getInzet());        
    	  }
    	  else if (eindScore==bankScore && eindScore <22){
    		  g.setCredits(-1*g.getInzet());        
    	  }
      }
      else if (g instanceof BlackJackGebruiker){
    	  int eindScore = g.getScore();
    	  if ((eindScore>bankScore && eindScore <22)||(bankScore>21 && eindScore<22)){
    		  spelersLijst.get(1).setCredits(-2*g.getInzet());        
    	  }
    	  else if (eindScore==bankScore && eindScore <22){
    		  spelersLijst.get(1).setCredits(-1*g.getInzet());        
    	  }              
      }  
    }
    isGesplitst = false;
  }
  
  public void berekenScore(){	  
	  for (BlackJackSpeler b:spelersLijst){	
		  int score = 0;
		  if (b instanceof BlackJackBank){			  		  
			  for (int d=0; d<b.handLengte(); d++){
				  score = score + b.getKAART(d).getWaarde(); 
		  }}
		  else{	
			  int aantalAAS = 0;
			  for (int d=0; d<b.handLengte(); d++){
				  score = score + b.getKAART(d).getWaarde();			  
				  if (b.getKAART(d).getWaarde()==11 && b.getHeeftBlackJack()!=true){
					  aantalAAS ++;
				  }
			  }
			  while (score>21 && aantalAAS!=0){
				  score = score - 10;
				  aantalAAS --;				  
			  }				  
		  }			   		  
		  b.setScore(score);
	  }
  }   
  
  // HULPMETHODEN INTERN EN VOOR FRAME  
  public void mutatieLijst (){	  
	  String wisselSpeler = null;
	  for (int i=2; i<aantalSpelers; i++){
		  if (spelersLijst.get(i).getCredits()<15){			  			  
			  wisselSpeler = spelersLijst.get(i).getNaam();
			  wisselLijst.add(spelersLijst.get(i).getNaam());
			  spelersLijst.remove(i);
			  int keuzeNummer = ran.nextInt(namenLijst.size());
		      String keuzeNaam = namenLijst.get(keuzeNummer);
		      namenLijst.remove(keuzeNummer);
		      spelersLijst.add(new BlackJackAI());  
		      spelersLijst.get(spelersLijst.size()-1).setNaam(keuzeNaam);
		      wisselLijst.add(spelersLijst.get(spelersLijst.size()-1).getNaam());
		      namenLijst.add(wisselSpeler);
		  }
	  }	  
  }  
  
  public boolean splitsen (){
	  String kaart1 = spelersLijst.get(1).getKAART(0).toString().substring(1);	  
	  String kaart2 = spelersLijst.get(1).getKAART(1).toString().substring(1);	  
	  if (vraagHandLengte(1)== 2 && isGesplitst == false && kaart1.equals(kaart2)){
		  isGesplitst = true;		  
		  spelersLijst.add(new BlackJackGebruiker());
		  spelersLijst.get(spelersLijst.size()-1).setNaam(naam+"GESPLITST");
		  spelersLijst.get(spelersLijst.size()-1).addKAART(spelersLijst.get(1).getKAART(1));
		  BlackJackKaart overzetter = spelersLijst.get(1).getKAART(0);
		  spelersLijst.get(1).clearHand();
		  spelersLijst.get(1).addKAART(overzetter);		  
		  spelersLijst.get(spelersLijst.size()-1).setInzet(spelersLijst.get(1).getInzet());		  
		  spelersLijst.get(spelersLijst.size()-1).setCredits(500-spelersLijst.get(1).getCredits());	
		  spelersLijst.get(1).setInzet(spelersLijst.get(1).getInzet());
		  aantalSpelers++;		  		  
		  return true;		  
	  }
	  else { return false;}
  } 
  
  public void geefKaart(int spelerNummer){
	  spelersLijst.get(spelerNummer).addKAART(deck.get(0));        
      deck.remove(0);
  }
  
  public void clearWisselLijst(){
	  while (wisselLijst.size() != 0){
		  wisselLijst.remove(0);
	  }
  }
  
  public String getWisselLijst(int spelerNummer){
	  return wisselLijst.get(spelerNummer);
  }
  
  public int getWisselLijstLengte(){	  
	  return wisselLijst.size();
  }  
  
  public void setSpelerCredits (int nummer, int credits){
	  spelersLijst.get(nummer).setCredits(credits);
  }
  
  public String getSpelerCredits(int nummer){
	  if (spelersLijst.get(nummer) instanceof BlackJackBank){
		  return "";
	  }
	  else {
	  return "" + spelersLijst.get(nummer).getCredits();
	  }
  }
  
  public String getSpelerInzet (int nummer){
	  if (spelersLijst.get(nummer) instanceof BlackJackBank){
		  return "";
	  }
	  else {
	  return "" + spelersLijst.get(nummer).getInzet();
	  }
  }
  
  public void setInzet (int spelerNummer, int inzet){
	  spelersLijst.get(spelerNummer).setInzet(inzet);
  }
  
  public int getAantalSpelers(){
	  return aantalSpelers;
  }
  
  public String getSpelerNaam(int nummer){
	  String Speler = spelersLijst.get(nummer).getNaam();
	  return Speler;	  
  }  
  
  public int vraagHandLengte (int spelernummer){
	  return spelersLijst.get(spelernummer).handLengte();
  }
  
  public BlackJackKaart vraagKAART(int spelernummer, int kaartnummer){
	  return spelersLijst.get(spelernummer).getKAART(kaartnummer);
  }
  
  public void clearHand(){
	  for (BlackJackSpeler p:spelersLijst){
		  p.clearHand();
	  }
  }
  
  public int vraagScore (int spelernummer){
	  berekenScore();
	  if (spelersLijst.get(spelernummer).getHeeftBlackJack()){
		  return 21;
	  }
	  else {
		  return spelersLijst.get(spelernummer).getScore();
	  }		  
  }
  
  public boolean getHeeftBlackJack(int spelernummer){
	  return spelersLijst.get(spelernummer).getHeeftBlackJack();
  } 
  
  public void clearBlackJack(){
	  for (BlackJackSpeler a: spelersLijst){
		  a.setResetBlackJack();
	  }
  }
  
  public boolean isGesplitst(){
	  return isGesplitst;
  }
  
  public void setGesplitstFalse(){
	  isGesplitst = false;
  }
 
  public static void main(String[] args) {   	  
  }

}