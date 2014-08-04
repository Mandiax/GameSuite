package blackjackdomain;

// constructor
public abstract class BlackJackSpeler {    
  
// methoden  
  String getNaam(){return "";} 
  void setNaam(String naam){}
	  
  int getCredits(){return -1;}
  void setCredits(int inzet){}   
  
  int getInzet(){return -1;}
  void setInzet(int inzet){}   
  
  boolean getHeeftBlackJack(){return false;}
  void setHeeftBlackJack(){}  
  void setResetBlackJack(){}
  
  void setScore(int punten){}
  int getScore(){return -1;}
  
  void addKAART (BlackJackKaart kaart){} 
  BlackJackKaart getKAART (int index){return BlackJackKaart.HTWO;}  
  void clearHand(){}  
  int handLengte(){return -1;}  
}