import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Congratulations extends JFrame{
	private static int WIDTH = 440;
	private static int HEIGHT = 302;
	
	public static void main(String[] args){
		Congratulations test = new Congratulations();
	}
	
	public Congratulations(){
		Container pane = getContentPane();
		ImageIcon image = null;
		
		try {
			image = new ImageIcon(ImageIO.read(new File("images/congratulations.png")));
		}
		catch (IOException e){
			
		}
		catch (IllegalArgumentException e){
			
		}
		
		JLabel label = new JLabel("");
		label.setIcon(image);
		pane.add(label);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (WIDTH/2), 
		                              middle.y - (HEIGHT/2));
		
		setLocation(newLocation);
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
