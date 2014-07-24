package blackjackdomain;

import java.util.ArrayList;

public class BlackJackBank extends BlackJackSpeler {
  ArrayList <BlackJackKaart> hand = null;
  int credits = 0;      
  boolean heeftBlackJack = false;    
  String naam = "Bank";  
  int score = 0;
  
  public BlackJackBank (){   
    mijnInit();
    }
    
    public void mijnInit(){       
      ArrayList <BlackJackKaart> hand = new ArrayList <BlackJackKaart> (); 
      this.hand=hand;
    }  
  
  public String getNaam(){
    return naam;
  }    
  
  public int getScore(){
    return score;
  }

  public void setScore(int punten){
    score = punten;
  }
  
  public void addKAART (BlackJackKaart kaart){
    hand.add(kaart);
  }
  
  public BlackJackKaart getKAART (int index){ 
	BlackJackKaart kaart = hand.get(index);  
    return kaart;
  }
  
  public void clearHand (){    
    hand.clear();
  }
  
  public int handLengte(){
    return hand.size();
  } 
}