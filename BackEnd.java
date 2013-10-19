

public class BackEnd {
	private Minefield field = null;
	private int[][] realGrid;
	private boolean firstRun = true;
	private int spacesUncovered = 0;
	private int D = 9;
	private int numberOfMines = 10;
	private GameScreen gameScreen = null;
	private boolean gameScreenLinked = false;
	private boolean handlersEnabled = true;
	
	public void linkToGameScreen(){
		if (!gameScreenLinked){
			gameScreen = FrontEnd.gameScreen();
			gameScreenLinked = true;
		}
	}
	
	public void setD(int D){
		if (field == null){
			if (Minefield.DIsValid(D)){
				this.D = D;
			}
			else {
				this.D = 9;
			}
		}
		else {
			System.out.println("Minefield already created. Cannot modify D.");
		}
	}
	
	public int getD(){
		return this.D;
	}
	
	public void setNumberOfMines(int numberOfMines){
		if (field == null){
			if (Minefield.mineNumberIsValid(this.D, numberOfMines)){
				this.numberOfMines = numberOfMines;
			}
			else {
				numberOfMines = Minefield.defaultNumberOfMines(this.D);
			}
		}
		else {
			System.out.println("Minefield already created. Cannot modify D.");
		}
	}
	
	public int getNumberOfMines(){
		return numberOfMines;
	}

	public void newGrid(int x, int y){
		if (firstRun){
			field = new Minefield(D, numberOfMines, x, y);
			realGrid = field.getMinefield();
			firstRun = false;
		}
			
		int neighboringMines = realGrid[x][y];
		
		if (neighboringMines == -1){
			gameScreen.setVisible(false);
			gameScreen.dispose();
			Songs.song1.stop();
			new GameOver();
		}
		
		if (neighboringMines > 0){
			gameScreen.uncoverBox(x, y, neighboringMines);
			spacesUncovered++;
		}
		
		if (neighboringMines == 0){
			gameScreen.uncoverBox(x, y, MinesweeperIcons.NULL);
			spacesUncovered++;
			pointIsZero(x, y);
		}
		
		if (spacesUncovered == (field.getD() * field.getD() - field.getNumberOfMines())){
			handlersEnabled = false;
			new WinScreen();
		}
	}
	
	public boolean handlersEnabled(){
		return handlersEnabled;
	}
	
	private void pointIsZero(int x, int y){
		int[] xPosition = Minefield.getPositionX(x);
		int[] yPosition = Minefield.getPositionY(y);
		
		for (int counter = 0; counter < 8; counter++){
			int currentX = xPosition[counter];
			int currentY = yPosition[counter];
		
			if (gameScreen.getButtonNumber(currentX, currentY) == MinesweeperIcons.QUESTION){
				int neighboringMines = realGrid[currentX][currentY];
				
				if (neighboringMines > 0){
					gameScreen.uncoverBox(currentX, currentY, neighboringMines);
					spacesUncovered++;
				}
				
				if (neighboringMines == 0){
					gameScreen.uncoverBox(currentX, currentY, MinesweeperIcons.NULL);
					spacesUncovered++;
					pointIsZero(currentX, currentY);
				}
			}
		}
	}
	
	public Minefield getMinefield(){
		return field;
	}
}
