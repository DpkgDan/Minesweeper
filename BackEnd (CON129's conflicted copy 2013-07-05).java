public class Backend {
	private static Minefield field = null;
	private static int[][] realGrid;
	private static boolean firstRun = true;
	private static int spacesUncovered = 0;
	private static int D = 9;
	private static int numberOfMines = 10;
	
	public static void setD(int setD){
		if (Minefield.DIsValid(D)){
			D = setD;
		}
		else {
			D = 9;
		}
	}
	
	public static int getD(){
		return D;
	}
	
	public static int getNumberOfMines(){
		return numberOfMines;
	}
	
	public static void setNumberOfMines(int setNumberOfMines){
		if (Minefield.mineNumberIsValid(D, setNumberOfMines)){
			numberOfMines = setNumberOfMines;
		}
		else {
			numberOfMines = Minefield.defaultNumberOfMines(D);
		}
	}

	public static void newGrid(int x, int y){
		if (firstRun){
			field = new Minefield(D, numberOfMines, x, y);
			realGrid = field.getMinefield();
			firstRun = false;
		}
			
		int neighboringMines = realGrid[x][y];
		
		if (neighboringMines == -1){
			FrontEnd.gameScreen.setVisible(false);
			FrontEnd.gameScreen.dispose();
			FrontEnd.GameOver();
		}
		
		if (neighboringMines > 0){
			FrontEnd.button[x][y].setIcon(FrontEnd.getIcon(neighboringMines));
			spacesUncovered++;
		}
		
		if (neighboringMines == 0){
			FrontEnd.button[x][y].setIcon(null);
			FrontEnd.button[x][y].setEnabled(false);
			spacesUncovered++;
			pointIsZero(x, y);
		}
		
		if (spacesUncovered == (field.getD()*field.getD()) - field.getNumberOfMines()){
			FrontEnd.gameScreen.setVisible(false);
			FrontEnd.gameScreen.dispose();
			new Congratulations();
		}
	}
	
	private static void pointIsZero(int x, int y){
		int[] xPosition = Minefield.getPositionX(x);
		int[] yPosition = Minefield.getPositionY(y);
		
		for (int counter = 0; counter < 8; counter++){
			int currentX = xPosition[counter];
			int currentY = yPosition[counter];
			
			if (FrontEnd.button[currentX][currentY].getIcon() != null){
				if (FrontEnd.button[currentX][currentY].getIcon() == FrontEnd.getIcon(0)){
					int neighboringMines = realGrid[currentX][currentY];
					
					if (neighboringMines > 0){
						FrontEnd.button[currentX][currentY].
						setIcon(FrontEnd.getIcon(neighboringMines));
						spacesUncovered++;
					}
					
					if (neighboringMines == 0){
						FrontEnd.button[currentX][currentY].setIcon(null);
						FrontEnd.button[currentX][currentY].setEnabled(false);
						spacesUncovered++;
						pointIsZero(currentX, currentY);
					}
				}
			}
		}
	}
	
	public static void markBox(int x, int y){
		if (FrontEnd.button[x][y].getIcon().equals(FrontEnd.getIcon(0))){
			FrontEnd.button[x][y].setIcon(FrontEnd.getIcon(9));
		}
		else {
			FrontEnd.button[x][y].setIcon(FrontEnd.getIcon(0));
		}
	}
	
	public static Minefield getMinefield(){
		return field;
	}
}
