import javax.swing.ImageIcon;


public class MinesweeperIcons {
	private ImageIcon[] icon = null;
	
	//Icon constants
	public static final int QUESTION = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int FLAG = 9;
	public static final int MINESWEEPER = 10;
	public static final int BOMB = 11;
	public static final int NULL = 12;
	
	public MinesweeperIcons(){
		loadIcons();
	}
	
	private void loadIcons(){
		int numberOfIcons = NULL;
		icon = new ImageIcon[numberOfIcons];
		
		for (int counter = 0; counter < numberOfIcons; counter++){
			icon[counter] = new ImageIcon(getClass().getResource("images/"+ counter + ".png"));
		}
	}
	
	public ImageIcon getIcon(int iconNumber){
		return icon[iconNumber];
	}
}
