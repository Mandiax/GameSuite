/**
 * 
 */
package dammen.model;

import java.awt.Color;

import dammen.gui.ScreenSize;

/**
 * Class description Klasse die een virtueel dambord moet voorstellen (bestaande
 * uit Nodes). Deze klasse is in principe de Controller, en staat tussen de GUI,
 * AI en Dammen class in.
 * 
 * @todo Refactor, functies die stenen kunnen verplaatsen, informatie ophalen,
 *       etc.
 * @version 1.00 14 jul. 2014
 * @author Pieter
 */
public class DamBord {

    private int kolommen;
    private int rijen;
    private Nodes[][] speelbord;
       
    
    private Player speler;
    private AI ai;

    /**
     * Constructor. Creeer een virtueel dambord op basis van de opgegeven
     * specificaties. Voorwaarden: Aantal rijen en kolommen zijn altijd even, en
     * altijd groter dan 4.
     * 
     * @param aantal
     *            kolommen (X coordinaat)
     * @param aantal
     *            rijen (Y coordinaat)
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
	
	setKleur();
	maakBord();
	vulBord();
	setMoves();
	//showMoves();
    }
    
    public void setMoves () {
	for (int i = 0; i < kolommen; i++) {
	    for (int j = 0; j < rijen; j++) {
		if (speelbord[i][j].hasDamsteen()) {
		    speelbord[i][j].getDamsteen().setMovesToNull();
		    setPossibleMoves(speelbord[i][j]);
		}
	    }
	}
    }
    
    private void setKleur () {
	speler = new Player ("wit");
	if (speler.getKleurToString() == "wit") 
	    ai = new AI(Color.black);
	else 
	    ai = new AI(Color.white);
    }

    /**
     * Beschermde methode om het bord te vullen met 'Nodes': Dit zijn de velden
     * op het bord.
     * 
     * @see dammen.model.Nodes
     */
    private void maakBord() {
	
	ScreenSize.setDammenSize(kolommen, rijen);
	Coord.setLimits(kolommen, rijen);
	
	for (int i = 0; i < kolommen; i++) {
	    for (int j = 0; j < rijen; j++) {
		try {
		    this.speelbord[i][j] = new Nodes(i, j);
		} catch (Exception e) {
		    System.out.println("This error will never occur :)");
		}
	    }
	}
	
    }

    /**
     * Functie om het bord met damstenen te vullen. Alleen aanroepen tijdens het
     * initialiseren van een nieuw spel.
     */
    private void vulBord() {
	String kleur = null;
	
	for (int i = 0; i < rijen; i++) {
	    
	    // De kant van de Speler 
	    if (i < (rijen / 2) - 1)
		kleur = speler.getKleurToString();
	    // De kant van de AI.
	    else if (i >= (rijen / 2) + 1)
		kleur = ai.getKleurToString();
	    // De twee rijen in het midden van het bord mogen geen damstenen
	    // bevatten.
	    else
		continue;

	    // Als Node zwart is, voeg een damsteen toe:
	    for (int j = 0; j < kolommen; j++) {
		if (this.speelbord[j][i].getKleur() == "zwart")
		    this.speelbord[j][i].setDamSteen(new DamSteen(kleur, speler));
	    }
	}
    }
    
    public void makeMove (Moves move) {
	//Move van de speler.
	move.makeMove();
	setMoves();
	
	// Nu mag de AI.
	Moves aiMove = ai.makeMove(speelbord);
	aiMove.makeMove();
	
	//Highlight de move van de AI
	aiMove.getCurrentNode().setHighLight(true);
	aiMove.getTargetNode().setHighLight(true);
	
	//Moves opnieuw bepalen
	setMoves();
    }
    
    public Nodes getNodeAt (Coord coord) {
	if (coord != null)
	    return speelbord[coord.getX()][coord.getY()];
	else 
	    return null;
    }
    
    public Nodes getNodeAt (int x, int y) {
	return speelbord[x][y];
    }

    /**
     * @return the speler
     */
    public Player getSpeler() {
        return speler;
    }

    /**
     * @return the ai
     */
    public AI getAi() {
        return ai;
    }

    /**
     * Haal virtueel speelbord op.
     * 
     * @return Het virtuele speelbord, een array met alle Nodes (vakken) van het
     *         dambord.
     */
    public Nodes[][] getSpeelbord() {
	return this.speelbord;
    }

    /**
     * 
     * @return int met het aantal rijen van het speelbord.
     */
    public int getRijen() {
	return this.rijen;
    }

    /**
     * 
     * @return int met het aantal kolommen van het speelbord.
     */
    public int getKolommen() {
	return this.kolommen;
    }

    /**
     * Deze functie kijkt, op basis van de input node,
     * welke vakjes er vrij zijn voor een eventuele zet.
     * 
     * Deze mogelijke zet wordt opgeslagen in het damsteen object in de node.
     * 
     * @todo Moet netter en dynamischer. AI moet ook gebruik kunnen maken van
     *       deze functie!
     * @param Nodes node
     */
    public void setPossibleMoves (Nodes node) {
	Coord coord = node.getCoord();
	
	if (node.getCoordLeft() != null && !getNodeAt( node.getCoordLeft() ).hasDamsteen() )  {
	    node.getDamsteen().addMove(node, getNodeAt(getNodeAt(coord).getCoordLeft()) );
	} 
	
	if (node.getCoordRight() != null && !getNodeAt( node.getCoordRight() ).hasDamsteen() ) {
	    node.getDamsteen().addMove(node, getNodeAt(getNodeAt(coord).getCoordRight()) );
	}
    }

    /**
     * Test functie. Laat alle Nodes op het bord zien.
     */
    public void showBord() {
	for (int i = 0; i < kolommen; i++) {
	    for (int j = 0; j < rijen; j++) {
		System.out.println(speelbord[i][j].toString());
	    }
	}
    }

    /**
     * Test functie. Achterhaal 'links' tussen de nodes op het bord.
     */
    public void showLinks() {
	System.out.println("Neighbour left of coord 1 : 1 = "
		+ speelbord[1][1].toString());
	System.out.println("Neighbour right of coord 1 : 1 = "
		+ speelbord[1][1].toString());
    }
    
    /**
     * Test functie. Laat alle moves zien in de console.
     */
    public void showMoves () {
	for (int i = 0; i < kolommen; i++) {
	    for (int j = 0; j < rijen; j++) {
		if (speelbord[i][j].hasDamsteen() &&  speelbord[i][j].getDamsteen().hasMoves() ) {
		    System.out.println("Steen op node " + speelbord[i][j].toString() + " has moves");
		}
	    }
	}
    }
}
