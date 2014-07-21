/**
 * 
 */
package dammen.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class description
 * 
 * @version		1.00 18 jul. 2014
 * @author 		Pieter
 */
public class AI {
    private Color kleur;
    private ArrayList<Moves> allMoves;
    
    public AI (String kleur) {
	
	if (kleur == "wit") 
	    this.kleur = Color.white;
	else 
	    this.kleur = Color.black;
    }
    
    public AI (Color kleur) {
	this.kleur = kleur;
    }
    
    public Color getKleur () {
	return this.kleur;
    }
    
    public String getKleurToString () {
	if (kleur == Color.white)
	    return "wit";
	else 
	    return "zwart";
    }
    
    public Moves makeMove (Nodes[][] speelbord) {
	allMoves = new ArrayList<>();
	setMoves(speelbord);
	return selectMove ();
    }
    
    private void setMoves (Nodes[][] speelbord) {
	Nodes [][] speelbordAI = new Nodes[Coord.getKolommen()][Coord.getRijen()];
	speelbordAI = speelbord;
	for (Nodes[] nodes : speelbordAI) {
	    for (Nodes node : nodes) {
		if (node.hasDamsteen() && (!node.getDamsteen().isOwnedByPlayer() && node.getDamsteen().hasMoves() )) {
		    setMovesHelper (node);
		}
	    }
	}
    }
    
    private void setMovesHelper (Nodes node) {
	for (Moves move : node.getDamsteen().getMoveList()) {
	    allMoves.add(move);
	}
    }
    
    private Moves selectMove () {
	int pos = (int) (Math.random() * allMoves.size());
	System.out.println ("Move nummer: " + pos);
	return allMoves.get(pos);
    }

}
