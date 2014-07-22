package memory;

public enum Moeilijkheid {MAKKELIJK (8), NORMAAL (18), MOEILIJK (32);

	
	  private int waarde = 0;

	  private Moeilijkheid (int waarde){
	    this.waarde = waarde;
	  }
	  public int getWaarde(){
	    return waarde;
	  }
}
