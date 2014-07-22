package memory;

import java.util.ArrayList;
import java.util.Random;

public class Memory {
	/**
	 * Klass om het memory spel te beheren.
	 * 
	 * @author Koen
	 */
	
	// moeilijkhied moet altijd de helft van het aantal vakjes in het spelersveld zijn.
	private int moeilijkheid = 8;
	
	// zou gevuld moeten worden met de arraylist kaarten voor de gui.
	private Kaarten[][] memoryVeld;
	
	ArrayList<Kaarten> kaarten = new ArrayList<Kaarten>();

	public Memory() {


	}
	
	/**Maakt een nieuw spel en vult de ArrayList kaarten met kaartsoort en een random uniek nummer voor elke kaart welke afhankelijk is van de moeilijkheid. Elke kaart komt twee keer voor.
	 * 
	 * @param kaartsoort
	 * @param <code>moeilijkheid</code> mag zijn 8, 18, 32.
	 */
	public void nieuwSpel(String kaartsoort, int moeilijkheid){
		kaarten.clear();
		this.moeilijkheid = moeilijkheid;
		ArrayList<Integer> rnummers1 = randomNummers(moeilijkheid);
		ArrayList<Integer> rnummers2 = randomNummers(moeilijkheid);
		for(int i = 1; i <=moeilijkheid; i++){
			int randomTijdelijk = rnummers1.get(i -1);
			kaarten.add(new Kaarten(kaartsoort, randomTijdelijk));
			int randomTijdelijk2 = rnummers2.get(i-1);
			kaarten.add(new Kaarten(kaartsoort, randomTijdelijk2));
		}
		
		//memoryVeld = new Kaarten[4][4];
		
		
		
	}
	
	public ArrayList<Kaarten> getKaarten(){
		return kaarten;
	}
	
	/**
	 * Stel de moelijkhied in van het spel. Mogelijke invoeringen 8, 18, 32.
	 * @param moeilijkheid
	 */
	public void setMoeilijkheid(int moeilijkheid) {
		this.moeilijkheid = moeilijkheid;
	}
	
	/**
	 * Kijkt of twee kaarten gelijk zijn aan elkaar.
	 * @param kaart1
	 * @param kaart2
	 * @return <code>false</code> als het niet dezelfde kaarten zijn, <code>true</code> als het wel dezelfde kaarten zijn.
	 */
	public boolean zijnGelijk(Kaarten kaart1, Kaarten kaart2){
		if (kaart1.getNummer() == kaart2.getNummer()){
			return true;
		}
		return false;
	}
	
	
	
	/**Maakt een Arraylist met random unieke nummers tussen 1 en en moeilijkheid van lengte moeilijkheid.
	 * 
	 * @author Koen
	 * @param moeilijkheid
	 * @return een Arraylist gevult met unieke random nummers van de lengte moeilijkheid.
	 */
	public ArrayList<Integer> randomNummers(int moeilijkheid){
		ArrayList<Integer> nummers = new ArrayList<Integer>();
		Random random = new Random();
		while(nummers.size() < moeilijkheid){
			int hulp = 1 + random.nextInt(moeilijkheid);
			if(!nummers.contains(hulp)){
				nummers.add(hulp);
			}
		}
		return nummers;
	}
}
