import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class FrontEnd {
	private static int DIMENSION;
	private static int D = 9;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static Point windowLocation;
	public static JButton[][] button;
	private static ImageIcon[] icon = new ImageIcon[10];
	public static WelcomeScreen welcomeScreen;
	public static SettingsMenu settingsMenu;
	public static GameScreen gameScreen;
	public static final int screenPropConstant = 17;
	
	public FrontEnd(){
		if (dim.getWidth() > dim.getHeight()){
			DIMENSION = Calculate.roundToInt((dim.height/screenPropConstant)*D);
		}
		else {
			DIMENSION = Calculate.roundToInt((dim.width/screenPropConstant)*D);
		}
		windowLocation = new Point(((dim.width-DIMENSION) / 2), ((dim.height-DIMENSION) / 2));
		loadIcons();
		
		//Music player
		try {
			AudioPlayer player = AudioPlayer.player;
			AudioStream stream = new AudioStream(new FileInputStream("song/song.wav"));
			player.start(stream);
		}
		catch (Exception e){
			
		}
		
		welcomeScreen = new WelcomeScreen();
	}
	
	public static void setD(){
		D = Backend.getD();
		button = new JButton[(D+2)][(D+2)];
		DIMENSION = Calculate.roundToInt((dim.height/screenPropConstant)*D);
		windowLocation = new Point(((dim.width-DIMENSION) / 2), ((dim.height-DIMENSION) / 2));
	}
	
	private static void loadIcons(){		
		try {
			FrontEnd.icon[0] = new ImageIcon(ImageIO.read(new File("images/question.png")));
			FrontEnd.icon[1] = new ImageIcon(ImageIO.read(new File("images/1.png")));
			FrontEnd.icon[2] = new ImageIcon(ImageIO.read(new File("images/2.png")));
			FrontEnd.icon[3] = new ImageIcon(ImageIO.read(new File("images/3.png")));
			FrontEnd.icon[4] = new ImageIcon(ImageIO.read(new File("images/4.png")));
			FrontEnd.icon[5] = new ImageIcon(ImageIO.read(new File("images/5.png")));
			FrontEnd.icon[6] = new ImageIcon(ImageIO.read(new File("images/6.png")));
			FrontEnd.icon[7] = new ImageIcon(ImageIO.read(new File("images/7.png")));
			FrontEnd.icon[8] = new ImageIcon(ImageIO.read(new File("images/8.png")));
			FrontEnd.icon[9] = new ImageIcon(ImageIO.read(new File("images/bomb.png")));
			
		}
		catch (IOException e){
			System.exit(0);
		}
		catch (IllegalArgumentException e) {
			System.exit(0);
		}	
	}
	
	public static void GameOver(){
		new GameOver();
	}
	
	public static ImageIcon[] getIconArray(){
		return icon;
	}
	
	public static ImageIcon getIcon(int number){
		return icon[number];
	}
	
	public static int getDIMENSION(){
		return DIMENSION;
	}
	
	public static Dimension getDim(){
		return dim;
	}
	
	public static Point getWindowLocation(){
		return windowLocation;
	}
}


