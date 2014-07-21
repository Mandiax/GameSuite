/**
 * 
 */
package dammen.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

/**
 * Class description
 * 
 * 
 * @version		1.00 17 jul. 2014
 * @author 		Pieter
 */
public class ImageHelper {
    public static BufferedImage veldDonker;
    public static BufferedImage veldLicht;
    public static BufferedImage veldDonkerWit;
    public static BufferedImage veldDonkerZwart;
    
    static {
	try {
	    veldDonker = ImageIO.read(new File ("GameSuiteMain/resource/img/VeldDonker.jpg"));
	    veldLicht = ImageIO.read(new File ("GameSuiteMain/resource/img/VeldLicht2.jpg"));
	    veldDonkerWit = ImageIO.read(new File ("GameSuiteMain/resource/img/VeldDonkerMetSteenWit.jpg"));
	    veldDonkerZwart = ImageIO.read(new File ("GameSuiteMain/resource/img/VeldDonkerMetSteenZwart.jpg"));
	} catch (IOException e) {
	    System.out.println(e.toString());
	    System.out.println("File not found :(");
	}
    }
    
    
    static public BufferedImage getImageFromFile (String imageName ) {
	BufferedImage img = null;
	try {
	    Path path = FileSystems.getDefault().getPath("img", imageName+".jpg");
	    System.out.println(path);
	    img = ImageIO.read(new File ("E:\\Java\\Workspace\\GameSuite\\GameSuiteMain\\src\\dammen\\gui\\img\\" + imageName+".jpg"));	    
	} catch (IOException e) {
	    System.out.println(e.toString());
	    System.out.println("File not found :(");
	    return null;
	}
	return img;
    }
    
    

}
