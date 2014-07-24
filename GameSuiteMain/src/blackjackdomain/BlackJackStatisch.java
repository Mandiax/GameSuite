package blackjackdomain;

import java.awt.Dimension;
import java.awt.Toolkit;

public class BlackJackStatisch {
	private static final int BJKAARTSIZEX = 72;
	private static final int BJKAARTSIZEY = 96;
	private static final int BJRANDEN = 30;
	private static final int BJSCHERMX = 1024;
	private static final int BJSCHERMY = 768;	
	private float BJREFACTORWAARDEX = 1.0F;
	private float BJREFACTORWAARDEY = 1.0F;
	public int KAARTSIZEX = 0;
	public int KAARTSIZEY = 0;
	public int RANDENX = 0;
	public int RANDENY = 0;
	public int SCHERMX = 0;
	public int SCHERMY = 0;	
	
	public BlackJackStatisch(){
	mijnInit();		
	}
	
	public void mijnInit(){
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
	BJREFACTORWAARDEX = dim.width / 1024;
	BJREFACTORWAARDEY = dim.height / 768;
	KAARTSIZEX = (int)(BJKAARTSIZEX*BJREFACTORWAARDEX);
	KAARTSIZEY = (int)(BJKAARTSIZEY*BJREFACTORWAARDEY);
	RANDENX = (int)(BJRANDEN*BJREFACTORWAARDEX);
	RANDENY = (int)(BJRANDEN*BJREFACTORWAARDEY);
	SCHERMX = (int)(BJSCHERMX*BJREFACTORWAARDEX);
	SCHERMY = (int)(BJSCHERMY*BJREFACTORWAARDEY);		
	}
}




