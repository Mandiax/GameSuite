/**
 * 
 */
package dammen.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dammen.model.DamBord;
import dammen.model.Moves;
import dammen.model.Nodes;

/**
 * Class description
 * De main GUI klasse. Zorgt ervoor dat het virtuele dambord op het scherm verschijnt, en dat de gebruiker 
 * kan dammen :)
 * 
 * @version 1.00 15 jul. 2014
 * @author Pieter
 */
public class DambordGUI implements MouseListener, MouseMotionListener, ComponentListener {

    JFrame frame;
    JPanel panel;
    BordPanel bord;
    JLabel label;

    private DamBord dambord;    
    private Nodes selectedNode;
    
    /**
     * Zet de DamBord klasse, en maak het frame en de componenten.
     * @param dambord
     */
    public DambordGUI(DamBord dambord) {
	this.dambord = dambord;

	initFrame();
	initPanels();
	maakDambord();
	updateBord();
    }
    
    /**
     * Zet de waarden van het frame.
     */
    private void initFrame() {
	frame = new JFrame( "Dammen" );
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setSize(ScreenSize.WIDTH + 25, ScreenSize.HEIGHT + 50);

	frame.setVisible(true);
	
	frame.setLocationRelativeTo(null);
    }
    
    /**
     * Maak alle visuele componenten aan.
     */
    private void initPanels() {
	
	panel = new JPanel();
	panel.setBounds(0, 0, ScreenSize.WIDTH + 25, ScreenSize.HEIGHT + 50);
	panel.setLayout(new BorderLayout());
	panel.addComponentListener(this);

	bord = new BordPanel();
	bord.setBounds(0 ,0 ,ScreenSize.WIDTH, ScreenSize.HEIGHT);
	bord.setOpaque(true);

	label = new JLabel();
	label.setText("Text");

	panel.add(label, BorderLayout.SOUTH);
	panel.add(bord, BorderLayout.CENTER);
	
	frame.add(panel);
    }
    
    /**
     * Deze functie geeft het virtuele speelbord door aan het visuele speelbord. In de klasse BordPanel (extends
     * JPanel) wordt op basis van NodeComponents (visuele nodes, gekoppeld aan virtuele nodes) een visueel bord gecreeerd.
     * @see BordPanel
     */
    public void maakDambord() {	
	bord.addSpeelBord(dambord.getSpeelbord());
	frame.pack();
    }

    /**
     * Deze functie zorgt ervoor dat op basis van het virtuele dambord (de speelveld array in de klasse Dambord) 
     * mouseListeners worden toegewezen aan Nodes die een damsteen hebben met bewegingsvrijheid.
     * Alleen de damstenen van de menselijke speler kunnen geselecteerd worden.
     * Als er al een damsteen geselecteerd is, dan zorgt deze functie ervoor dat de nodes waar de damsteen heen kan gaan
     * ook selecteerbaar zijn. 
     *
     */
    public void updateBord() {
	maakDambord();
	for (int i = 0; i < dambord.getKolommen(); i++) {
	    for (int j = 0; j < dambord.getRijen(); j++) {
		if ( (dambord.getNodeAt(i, j).hasDamsteen() && dambord.getNodeAt(i, j).getDamsteen().hasMoves() ) &&
			dambord.getNodeAt(i, j).getDamsteen().isOwnedByPlayer() ) {
		    bord.getNodeComponent(i, j).addMouseListener(this);
		    bord.getNodeComponent(i, j).addMouseMotionListener(this);
		} else if (dambord.getNodeAt(i, j).isHighLight()) {
		    bord.getNodeComponent(i, j).addMouseListener(this);
		    bord.getNodeComponent(i, j).addMouseMotionListener(this);
		}
	    }
	}
    }
    
    /**
     * Overridden method to handle mouseClicks
     * Deze methode maakt het mogelijk om een selecteerbare Node aan te klikken, en deze - en de Nodes met mogelijke
     * zetten - te highlighten. Vervolgens krijgen de nodes met een highlight een mouseListener toegewezen (in updateBord()),
     * en krijgt de selectedNode variabele een referentie naar de geselecteerde Node.
     * Als de geselecteerde Node niet null is (i.e. een Node met mogelijke moves is geselecteerd), en er wordt een vrije 
     * nabijgelegenen Node aangeklikt, dan wordt de damsteen op de oorspronkelijke node verplaatst naar de nieuwe node.
     * 
     * Vervolgens wordt het bord geupdate.
     * 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	if (selectedNode == null) {
	    highlightNodes(e);
	} else if (selectedNode != null) {
	    moveSteen(e);
	}
	updateBord();
    }

    /**
     * Fucntie voor het verplaatsen van een damsteen van de eerder geselecteerde Node naar een 
     * nieuwe vrije Node.
     * @param MouseEvent e
     */
    private void moveSteen(MouseEvent e) {
	NodeComponent node = (NodeComponent)e.getComponent();	
	Moves[] tmp = selectedNode.getDamsteen().getMoveToArray();
	
	for (Moves m : tmp) {
	    if (m.getTargetNode() == node.getNode())
		dambord.makeMove(m);
	}	
	selectedNode = null;
    }

    /**
     * Functie voor het highlighten van een geselecteerde Node (met damsteen die te verplaatsen is), en 
     * de Nodes waar de damsteen heen kan.
     * @param MouseEvent e
     */
    private void highlightNodes(MouseEvent e) {
	NodeComponent node = (NodeComponent)e.getComponent();
	label.setText( node.getCoord().toString() );
	selectedNode = node.getNode();
	selectedNode.setHighLight(true);
	Moves[] tmp = selectedNode.getDamsteen().getMoveToArray();
	for (Moves m : tmp) {
	    m.getTargetNode().setHighLight(true);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	NodeComponent n = (NodeComponent) e.getComponent();
	n.setCursor(new Cursor(Cursor.HAND_CURSOR));
	label.setText(n.getCoord().toString());
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
	// TODO Auto-generated method stub
	ScreenSize.reSize (panel.getSize());
	System.out.println(ScreenSize.NODESIZE);
	updateBord();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentMoved(ComponentEvent e) {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentShown(ComponentEvent e) {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentHidden(ComponentEvent e) {
	// TODO Auto-generated method stub
	
    }

}
