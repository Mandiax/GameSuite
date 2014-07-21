/**
 * 
 */
package dammen.model;


/**
 * Class description Deze klasse representeerd een veld op het dambord. Het veld
 * kan wit of zwart zijn (wit wordt niet gebruikt). Bevat de grootte (voor de
 * GUI) als Dimension. Extends JComponent (voor de GUI). Bevat een funtie om de
 * coordinaten van de aangrenzende zwarte vakjes te geven. Heeft een
 * paintComponent functie (voor de GUI) Aparte setSize functie (voor de GUI)
 * 
 * @todo Refactor, DamSteen toevoegen, functie voor aangrenzende vakje aanpassen
 *       (kan nu alleen voorruit).
 * @version 1.00 14 jul. 2014
 * @author Pieter
 */
public class Nodes {
    
    private String kleur;
    private Coord coord;
    private DamSteen damsteen;
    
    private boolean highLight;
    private boolean selected;


    /**
     * Creeer een nieuw Node (Veld) op basis van de opgegeven parameters.
     * 
     * @param x
     * @param y
     * @throws Exception 
     */
    public Nodes(int x, int y) throws Exception {
	coord = new Coord(x, y);
	if (x % 2 == 1 && y % 2 == 1)
	    this.kleur = "zwart";
	else if (x % 2 == 0 && y % 2 == 0)
	    this.kleur = "zwart";
	else
	    this.kleur = "wit";
    }

    /**
     * Check of deze node een damsteen bevat
     * 
     * @return true als Node een damsteen heeft, anders false;
     */
    public boolean hasDamsteen() {
	if (this.damsteen != null)
	    return true;
	else
	    return false;
    }

    /**
     * Voeg een damsteen toe aan de Node.
     * 
     * @param steen
     */
    public void setDamSteen(DamSteen steen) {
	if (!this.hasDamsteen()) {
	    this.damsteen = steen;
	    this.damsteen.setCoord(this.coord);
	}
    }

    /**
     * Haal de damsteen van deze node af, en verwijder de referentie.
     * 
     * @return DamSteen.
     */
    public DamSteen getDamsteen() {
	return this.damsteen;
    }
    
    public DamSteen moveDamsteen () {
	if (this.hasDamsteen()) {
	    DamSteen steen = this.damsteen;
	    this.damsteen = null;
	    return steen;
	} else 
	    return null;
    }

    /**
     * Haal de kleur van deze Node op
     * 
     * @return String kleur
     */
    public String getKleur() {
	return this.kleur;
    }

    /**
     * Verander de kleur van deze Node in de opgegeven kleur.
     * 
     * @param kleur
     */
    public void setKleur(String kleur) {
	this.kleur=kleur;
    }

    /**
     * Overridden toString methode. In principe alleen voor testing. Zet de
     * coordinaten, kleur en eventuele damsteen om in een String.
     */
    public String toString() {
	String res;
	if (this.damsteen != null) {
	    res = this.coord.getX() + " " + this.coord.getY() + " "
		    + this.damsteen.toString();
	} else {
	    res = this.coord.getX() + " " + this.coord.getY()
		    + " Geen Damsteen";
	}
	return res;
    }

    /**
     * Geef de coordinaten van de Node links boven deze Node.
     * 
     * @return Coord van node linksboven
     * @throws Exception 
     */
    public Coord getCoordLeft() {
	try {
	    if (this.damsteen.isOwnedByPlayer())
		return new Coord(this.coord.getX() - 1, this.coord.getY() + 1);
	    else 
		return new Coord(this.coord.getX() - 1, this.coord.getY() - 1);
	} catch (Exception e) {
	    //System.out.println(e.getMessage());
	    return null;
	}
    }

    /**
     * Geef de coordinaten van de Node rechts boven deze Node.
     * 
     * @return Coord van node rechtsboven
     * @throws Exception 
     */
    public Coord getCoordRight() {
	try {
	    if (this.damsteen.isOwnedByPlayer()) {
		return new Coord(this.coord.getX() + 1, this.coord.getY() + 1);
	    } else {
		return new Coord(this.coord.getX() + 1, this.coord.getY() - 1);
	    }
	} catch (Exception e) {
	    //System.out.println(e.getMessage());
	    return null;
	}
    }

    /**
     * Geef Coordinaten van deze node.
     * 
     * @return Coord van deze Node
     */
    public Coord getCoord() {
	return coord;
    }

    /**
     * Geef x-coordinaat van deze Node
     * 
     * @return int X coordinaat
     */
    public int getXCoord() {
	return coord.getX();
    }

    /**
     * Geef y-coordinaat van deze Node
     * 
     * @return int y coordinaat
     */
    public int getYCoord() {
	return coord.getY();
    }
    

    /**
     * @return the highLight
     */
    public boolean isHighLight() {
        return highLight;
    }

    /**
     * @param highLight the highLight to set
     */
    public void setHighLight(boolean highLight) {
        this.highLight = highLight;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
	return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
	this.selected = selected;
    }



}
