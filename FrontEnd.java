import java.awt.Color;

public class FrontEnd {
	private static WelcomeScreen welcomeScreen = null;
	private static GameScreen gameScreen = null;
	private static BackEnd backEnd = null;
	private static MinesweeperIcons msIcons = null;
	public static final Color backgroundColor = new Color(102, 178, 255);
	
	public static void main(String[] args){
		//came from old game - OLDGAME as arg
		if (args.length > 0){
			if (args[0].equals("OLDGAME")){
				welcomeScreen = null;
				gameScreen = null;
				backEnd = null;
				msIcons = null;
				
				if (MusicEnabledCheckbox.musicEnabled()){
					Songs.song1.stop();
					Songs.song2.stop();
				}
			}
		}
	
		msIcons = new MinesweeperIcons();
		
		if (MusicEnabledCheckbox.musicEnabled()){
			Songs.song1.loop();
		}
		
		welcomeScreen = new WelcomeScreen();
	}
	
	public static WelcomeScreen welcomeScreen(){
		if (welcomeScreen == null){
			welcomeScreen = new WelcomeScreen();
		}
		return welcomeScreen;
	}
	
	public static GameScreen gameScreen(){
		if (gameScreen == null){
			gameScreen = new GameScreen();
		}
		return gameScreen;
	}
	
	public static BackEnd backEnd(){
		if (backEnd == null){
			backEnd = new BackEnd();
		}
		return backEnd;
	}
	
	public static MinesweeperIcons msIcons(){
		if (msIcons == null){
			msIcons = new MinesweeperIcons();
		}
		return msIcons;
	}
}


