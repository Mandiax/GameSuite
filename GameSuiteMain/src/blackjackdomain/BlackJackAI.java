package blackjackdomain;

import java.util.ArrayList;
import java.util.Random;

public class BlackJackAI extends BlackJackSpeler{
  int credits = 0;     
  boolean heeftBlackJack = false;
  ArrayList <BlackJackKaart> hand = null;  
  String naam = null;
  int inzet = 0;
  int score = 0;
  
  public BlackJackAI (){       
    mijnInit();
    }
    
    private void mijnInit(){
      Random ran = new Random(); 
      credits = 400 + (ran.nextInt(200));      
      ArrayList <BlackJackKaart> hand = new ArrayList <BlackJackKaart> (); 
      this.hand=hand;
    }  
  
  public int getCredits() {    
    return credits;    
  }  
  
  public void setCredits(int inzet) {    
	credits = credits - inzet;    
  }
  
  public int getInzet (){
    return inzet;
  }
  
  public void setInzet(int inzet){
    this.inzet = inzet;   
	this.setCredits(inzet);
  }
  
  public void setNaam(String naam){
    this.naam =  naam;
  }
  
  public String getNaam(){
    return naam;
  }
    
  public boolean getHeeftBlackJack(){
    return heeftBlackJack;
  }
  
  public void setHeeftBlackJack(){
    heeftBlackJack = true;
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
    hand.get(index);
    return (hand.get(index));
  }
  
  public void setResetBlackJack(){
	heeftBlackJack = false;  
  }
  
  public void clearHand (){    
    hand.clear();
  }
  
  public int handLengte(){
    return hand.size();    
  } 
}