package memory;

import java.util.ArrayList;

public class MemoryTest {
	public static Memory memory = new Memory();
	
	
	public static ArrayList<Integer> random = memory.randomNummers(8);

	public static void main(String[] args) {
		memory.nieuwSpel("pony", 8);

		for(int i = 0; i <random.size(); i++){
		System.out.println(random.get(i));
		}
		memory.nieuwSpel("pony", 8);
		ArrayList<Kaarten> kaarten = memory.getKaarten();
		
		for(int i = 0; i < kaarten.size(); i++){
			System.out.println(kaarten.get(i).toString());
		}
		
		
	}

}
