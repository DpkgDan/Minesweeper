import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;


public class MinesweeperUtils {
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int roundToInt(double number){
		int periodLocation = 0;
		String numberString = "" + number;
		
		for (int counter = 0; counter < numberString.length(); counter++){
			if (numberString.charAt(counter) == '.'){
				periodLocation = counter;
				break;
			}
		}
		
		String roundedNumString = numberString.substring(0, periodLocation);
		int roundedNum = Integer.parseInt(roundedNumString);
		
		if (Integer.parseInt("" + numberString.charAt(periodLocation + 1)) >= 5){
			roundedNum++;
		}
		
		return roundedNum;
	}
	
	public static Dimension getSquareScreenSize(double ratio){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		double screenWidth = dim.getWidth();
		double screenHeight = dim.getHeight();
		Dimension gameScreenSize = null;
		
		if (screenWidth > screenHeight){
			int gameScreenWidth = roundToInt(ratio * screenWidth);
			gameScreenSize = new Dimension(gameScreenWidth, gameScreenWidth);
		}
		else {
			int gameScreenHeight = roundToInt(ratio * screenHeight);
			gameScreenSize = new Dimension(gameScreenHeight, gameScreenHeight);
		}
		return gameScreenSize;
	}
	
	public static Dimension getGameScreenSize(double widthRatio, double heightRatio){
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		
		int gameScreenWidth = roundToInt(widthRatio * screenWidth);
		int gameScreenHeight = roundToInt(heightRatio * screenHeight);
		
		Dimension gameScreenSize = new Dimension(gameScreenWidth, gameScreenHeight);
		return gameScreenSize;
	}
	
	public static Dimension getGameScreenSize
			(double widthRatio, 
			double heightRatio, 
			Dimension screenSize){
		
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		
		int gameScreenWidth = roundToInt(widthRatio * screenWidth);
		int gameScreenHeight = roundToInt(heightRatio * screenHeight);
		
		Dimension gameScreenSize = new Dimension(gameScreenWidth, gameScreenHeight);
		return gameScreenSize;
	}
	
	public static Dimension getSquareScreenSize(double ratio, Dimension screenSize){
		double screenWidth = screenSize.getWidth();

		int gameScreenWidth = roundToInt(ratio * screenWidth);
		
		Dimension gameScreenSize = new Dimension(gameScreenWidth, gameScreenWidth);
		return gameScreenSize;
	}
	
	public static Point getCornerPoint(Dimension windowSize){	
		int windowWidthR = roundToInt(windowSize.getWidth());
		int windowHeightR = roundToInt(windowSize.getHeight());
		
		int x = (screenSize.width-windowWidthR) / 2;
		int y = (screenSize.height-windowHeightR) / 2;
		
		Point windowLocation = new Point(x, y);
		return windowLocation;
	}
	
	public static Point getCornerPoint(double windowWidth, double windowHeight){
		int windowWidthR = roundToInt(windowWidth);
		int windowHeightR = roundToInt(windowHeight);
		
		int x = (screenSize.width-windowWidthR) / 2;
		int y = (screenSize.height-windowHeightR) / 2;
		
		Point windowLocation = new Point(x, y);
		return windowLocation;
	}
}
