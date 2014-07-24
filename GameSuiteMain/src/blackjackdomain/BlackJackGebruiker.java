package blackjackdomain;

import java.util.ArrayList;

public class BlackJackGebruiker extends BlackJackSpeler{  
  int credits = 0;     
  boolean heeftBlackJack = false;
  ArrayList <BlackJackKaart> hand = null;  
  String naam = null;
  int inzet = 0;
  int score = 0;
  
  public BlackJackGebruiker (){    
  mijnInit();
  }
  
  public void mijnInit(){
    credits = 500;    
    ArrayList <BlackJackKaart> hand = new ArrayList <BlackJackKaart> (); 
    this.hand=hand;
  }  
  
  public String getNaam(){
    return naam;
  }
  
  public void setNaam(String naam){
	this.naam = naam;
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
  
  public boolean getHeeftBlackJack(){
    return heeftBlackJack;
  }
  
  public void setHeeftBlackJack(){
    heeftBlackJack = true;
  }  
  
  public void setResetBlackJack(){
	heeftBlackJack = false;  
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
  
  public void clearHand (){    
    hand.clear();
  }
  
  public int handLengte(){
    return hand.size(); 
  }  
}