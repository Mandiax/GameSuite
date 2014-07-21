/**
 * 
 */
package dammen.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import dammen.model.Coord;
import dammen.model.Nodes;

/**
 * Class description
 * Een customized JPanel.
 * Heeft een array met NodeComponents, die weer gekoppeld zijn aan Nodes.
 * Zorgt ervoor dat alle Nodes op de juiste plek komen te staan.
 * 
 * @version		1.00 17 jul. 2014
 * @author 		Pieter
 */
public class BordPanel extends JPanel {

    private NodeComponent[][] nodeList;
    private static final long serialVersionUID = 3268182264406801064L;
    private GridBagLayout layout;
    private GridBagConstraints c;
    
    
    {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints ();	
    }
    
    /**
     * Krijgt het virtuele speelbord binnen, en koppelt de Nodes van het virtuele speelbord aan de 
     * Nodes (NodeComponents) van het visuele speelbord.
     * 
     * @remember Rare onvoorspelbare nullpointerException.. Oplossing: het daadwerkelijke toevoegen van componenten
     * in een nieuwe methode (paintSpeelbord) doen. Waarschijnlijk ging het allemaal wat te snel voor de VM..
     * @param Nodes[][] speelbord
     */
    public void addSpeelBord (Nodes [][] speelbord) {
	nodeList = null;
	this.removeAll();

	paintSpeelbord(speelbord);
	
	this.repaint();
    }
    
    /**
     * Methode om de nodeList te vullen met nodeComponents die worden aangemaakt
     * op basis van een Node uit het speelbord.
     * @param speelbord
     */
    public void paintSpeelbord(Nodes[][] speelbord) {
	nodeList = new NodeComponent [speelbord.length][speelbord[0].length];
	
	//System.out.println (speelbord.length + " " + speelbord[0].length);
	
	c.weightx = 1;
	c.weighty = 1;	
	c.fill = GridBagConstraints.BOTH;

	for (int i = 0; i <= Coord.getKolommen(); i++) {
	    for (int j = 0; j <= Coord.getRijen(); j++) {
		c.gridx = i;
		c.gridy = j;
		
		NodeComponent nodeComp = new NodeComponent(speelbord[i][Coord.getRijen()-j]);
				
		nodeList[i][Coord.getRijen()-j] = nodeComp;
		this.add(nodeComp, c);
	    }
	}
    }
    
    
    /**
     * Haal een nodeComponent uit de lijst op basis van x en y coordinaat.
     * Volgens mij kan deze wel weg.
     * @param x
     * @param y
     * @return
     */
    public NodeComponent getNodeComponent (int x, int y) {	
	return nodeList[x][y];
    }
    
    /**
     * Haal een nodeComponent uit de lijst op basis van een Coord.
     * Volgens mij kan deze wel weg.
     * @param x
     * @param y
     * @return
     */
    public NodeComponent getNodeComponent (Coord coord) {
	return nodeList[coord.getX()][coord.getY()];
    }
    
    /**
     * Custom preferredSize (op basis van een NodeSize in de statische screensize klasse, 
     * en het aantal rijen/kolommen van het speelveld.
     */
    public Dimension getPreferredSize() {
        return new Dimension(ScreenSize.KOLOMMEN * ScreenSize.NODESIZE ,ScreenSize.RIJEN * ScreenSize.NODESIZE);
    }

}
