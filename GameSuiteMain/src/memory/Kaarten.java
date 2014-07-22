package memory;

import java.util.ArrayList;

public class Kaarten {
	
	
	/**
	 * De klasse om de kaart-type te beheren.
	 * 
	 * Oke dan
	 * @author Koen
	 */

	private int nummer = 0;
	private String kaartsoort = "pony";
	

	public Kaarten (String kaartsoort, int nummer){
		this.kaartsoort = kaartsoort;
		this.nummer = nummer;
	}

	public void setKaartSet(String kaartsoort) {
		this.kaartsoort = kaartsoort;
	}
	
	public String getKaartSet(){
		return kaartsoort;
	}
	public void setNummer(int nummer){
		this.nummer = nummer;
		
	}
	
	public int getNummer(){
		return nummer;
	}
	
	public String toString(){
		return kaartsoort +" met nummer: " +nummer +".";
	}
	
	public void maakLeeg(){
		nummer = 0;
		kaartsoort = "leeg";
	}

}
