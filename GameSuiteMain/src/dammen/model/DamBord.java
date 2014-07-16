/**
 * 
 */
package dammen.model;

import dammen.gui.ScreenSize;

/**
 * Class description
 * Klasse die een virtueel dambord moet voorstellen (bestaande uit Nodes).
 * Deze klasse is in principe de Controller, en staat tussen de GUI, AI en Dammen class in.
 * 
 * @todo		Refactor, functies die stenen kunnen verplaatsen, informatie ophalen, etc.
 * @version		1.00 14 jul. 2014
 * @author 		Pieter
 */
public class DamBord {
    private int kolommen;
    private int rijen;    
    private Nodes[][] speelbord;
    
    /**
     * Constructor.
     * Creeer een virtueel dambord op basis van de opgegeven specificaties 
     * @param aantal kolommen (X coordinaat)
     * @param aantal rijen (Y coordinaat)
     */
    public DamBord(int aantalKolommen, int aantalRijen) {
	if (aantalKolommen % 2 == 0 && aantalKolommen >= 4)
	    kolommen = aantalKolommen;
	else 
	    kolommen = 10;
	
	if (aantalRijen % 2 == 0 && aantalRijen >= 4)
	    rijen = aantalRijen;
	else
	    rijen = 10;
	
	speelbord = new Nodes[kolommen][rijen];
	maakBord();
	vulBord();
    }
    
    /**
     * Beschermde methode om het bord te vullen met 'Nodes':
     * Lege vlakken op het speelbord, die een damsteen kunnen bevatten.
     * @see dammen.model.Nodes
     */
    private void maakBord () {
	ScreenSize.setDammenSize(kolommen, rijen);
	for (int i = 0; i < kolommen ; i++ ) {
	    for (int j = 0; j < rijen ; j++ ) {
		this.speelbord[i][j] = new Nodes (i,j);
	    }	    
	}
    }
    
    private void vulBord () {
	String kleur = "wit";
	
	for (int i = 0; i < rijen ; i++ ) {
	    if ( i < (rijen/2) - 1 ) 
		    kleur = "wit";
		else if (i >= (rijen/2) + 1 )
		    kleur = "zwart";
		else
		    continue;
	    
	    for (int j = 0; j < kolommen ; j++ ) {		
		if (this.speelbord[j][i].getKleur() == "zwart")
		    this.speelbord[j][i].setDamSteen(new DamSteen(kleur));
	    }	    
	}
	
    }
    
    public Nodes[][] getSpeelbord () {
	return this.speelbord;
    }
    
    public int getRijen () {
	return this.rijen;
    }
    
    public int getKolommen () {
	return this.kolommen;
    }
    
    public Coord[] showFreeNodes (int x, int y) {
	Coord[] res = new Coord[2];
	res[0] = speelbord[x][y].getCoordLeft(x, y);
	res[1] = speelbord[x][y].getCoordRight(x, y);
	
	return res;
    }
    
    /**
     * Test functie. Laat alle Nodes op het bord zien.
     */
    public void showBord () {
	for (int i = 0; i < kolommen ; i++ ) {
	    for (int j = 0; j < rijen ; j++ ) {
		System.out.println(speelbord[i][j].toString());
	    }	    
	}
    }
    
    /**
     * Test functie. Achterhaal 'links' tussen de nodes op het bord.
     */
    public void showLinks () {
	System.out.println("Neighbour left of coord 1 : 1 = " 
		+ speelbord[1][1].getCoordLeft(1, 1).toString() ) ;
	System.out.println("Neighbour right of coord 1 : 1 = " 
		+ speelbord[1][1].getCoordRight(1, 1).toString() ) ;
    }
}
