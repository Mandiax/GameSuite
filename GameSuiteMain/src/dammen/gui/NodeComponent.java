/**
 * 
 */
package dammen.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import dammen.model.Coord;
import dammen.model.Nodes;

/**
 * Class description
 * De visuele component van een Node.
 * Bevat een referentie naar de Node in de speelveld array.
 * Deze klasse regelt het visuele gebeuren van een vakje op het dambord,
 * zoals highlighten, damsteen laten zien, achtergrond, etc.
 * 
 * @version		1.00 17 jul. 2014
 * @author 		Pieter
 */
public class NodeComponent extends JComponent {

    private static final long serialVersionUID = 5104899510098445836L;
    private Nodes node;


    private int nodeSize;
    private Color color;
    
    private BufferedImage image;
        
    /**
     * Constructor. Basis attributen setten op basis van de Node uit de speelveld array.
     * @param node
     */
    public NodeComponent (Nodes node) {
	if (node == null) {
	    System.out.println ("NODES IS NULL!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	this.node = node;
	
	nodeSize = ScreenSize.NODESIZE;
	setSize (nodeSize,nodeSize);
	
	if (node.getKleur() == "zwart") {
	    this.image = ImageHelper.veldDonker;
	    this.color = Color.black;
	} else {
	    this.image = ImageHelper.veldLicht;
	    this.color = Color.white;
	}	
    }
        
    /**
     * @return the node
     */
    public Nodes getNode() {
        return node;
    }
    
    /**
     * 
     * @return Coord van de Node
     */
    public Coord getCoord () {
	return this.node.getCoord();
    }   
    
    /**
     * Voor custom preferred size.
     */
    public Dimension getPreferredSize() {
	this.getSize(getSize());
        return new Dimension(nodeSize,nodeSize);
    }
    
    /**
     * Hier vindt alle painting plaats.
     * Kijkt naar de eigenschappen van de virtuele Node, en paint deze op het scherm.
     * 
     * @see ScreenSize 
     * @see Nodes
     */
    public void paintComponent(Graphics g) {
	super.paintComponent(g);	

	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(this.image, 0, 0, nodeSize, nodeSize, null);
		
	if (node.hasDamsteen()) {
	    drawDamSteen (g2d);    
	}
	
	if (node.isHighLight()) {
	    drawHighlight(g2d);
	    node.setHighLight(false);
	}
	
    }
    
    /**
     * Helper functie voor paintComponent.
     * Teken een highlight op basis van de gegevens van de Node.
     * @param g2d
     */
    public void drawHighlight (Graphics2D g2d) {
	g2d.setStroke(new BasicStroke(4));
	if (node.hasDamsteen())
	    g2d.setColor(Color.green);
	else 
	    g2d.setColor(Color.blue);
	g2d.drawRect(0,0, nodeSize, nodeSize);
	g2d.setColor(color);
    }
    
    /**
     * Helper functie voor paintComponent.
     * Teken een damsteen op basis van de gegevens van de Node.
     * @param g2d
     */
    public void drawDamSteen (Graphics2D g2d) {

	BufferedImage img = null;
	if (this.color == Color.black) {
	    
	    if (this.node.getDamsteen().getKleur() == Color.black)
		img = ImageHelper.veldDonkerZwart;
	    
	    else if (this.node.getDamsteen().getKleur() == Color.white)
		img = ImageHelper.veldDonkerWit;
	}	
	//g2d.drawImage(img, 0,0,this);
	g2d.drawImage(img, 0, 0, nodeSize, nodeSize, null);
    }

}
