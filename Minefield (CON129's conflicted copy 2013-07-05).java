import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Minefield {
	private int D;
	private int numberOfMines;
	private int[][] Grid;
	private int firstX;
	private int firstY;
	private static int minD = 3;
	private static int maxD = 26;
	private int[][] mineLocations;
	
	public Minefield(int D, int numberOfMines, int firstX, int firstY){
		setD(D);
		setNumberOfMines(numberOfMines);
		setFirstCoordinates(firstX, firstY);
		generateGrid();
	}
	
	public Minefield(int D, int firstX, int firstY){
		setD(D);
		setNumberOfMines(defaultNumberOfMines(this.D));
		setFirstCoordinates(firstX, firstY);
		generateGrid();
	}
	
	public Minefield(int firstX, int firstY){
		this.D = 9;
		setNumberOfMines(10);
		setFirstCoordinates(firstX, firstY);
		generateGrid();
	}
		
	private void setD(int D){
		if (DIsValid(D)){
			this.D = D;
		}
		else {
			System.out.println
			("D must be between " + minD + " and " + maxD + ". Automatically setting D to 9...\n");
			this.D = 9;
		}
	}
	
	public int getD(){
		return D;
	}
	
	public static int getMinD(){
		return minD;
	}
	
	public static int getMaxD(){
		return maxD;
	}
	
	private void setNumberOfMines(int numberOfMines){
		if (numberOfMines < (D*D) && numberOfMines > 0){
			this.numberOfMines = numberOfMines;
			mineLocations = new int[this.numberOfMines][2];
		}
		else {
			System.out.println
			("The number of mines can't exceed the total number of spaces. " +
					"Automatically using formula to set mines...\n");
			this.numberOfMines = defaultNumberOfMines(D);
		}
	}
	
	public static int defaultNumberOfMines(int D){
		double ratio = (10.0/81.0);
		double unroundedNumber = ratio*(D*D);
		int answer = Calculate.roundToInt(unroundedNumber);
		
		if (answer > 0){
			return answer;
		}
		else {
			return 1;
		}
	}
	
	public int getNumberOfMines(){
		return numberOfMines;
	}
	
	public static boolean DIsValid(int D){
		if (D >= minD && D <= maxD){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean mineNumberIsValid(int D, int numberOfMines){
		if (numberOfMines < (D*D) && numberOfMines > 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	private void setFirstCoordinates(int firstX, int firstY){
		if (firstX >= 1 && firstX <= D){
			this.firstX = firstX;
		}
		if (firstY >= 1 && firstY <= D){
			this.firstY = firstY;
		}
		else {
			System.out.println("Error: Invalid coordinates entered.");
			this.firstX = 1;
			this.firstY = 1;
		}
	}
	
	private void generateGrid(){
		//Rewrite algorithm to make all spaces mines and then randomly select
		// non-mine spaces if the number of mines exceeds half the total number
		// of spaces
		
		Grid = new int[(this.D)+2][(this.D)+2];
		Random random = new Random();
		
		if (numberOfMines < ((D*D) / 2.0)){
			for (int x = 0; x < (D+2); x++){
				for (int y = 0; y < (D+2); y++){
					Grid[x][y] = 0;
				}
			}
			
			for (int counter = 0; counter < numberOfMines;){
				int x = random.nextInt(D) + 1;
				int y = random.nextInt(D) + 1;
			
				if (x != firstX && y != firstY && !hasMine(x, y)){
					Grid[x][y] = -1;
					mineLocations[counter][0] = x;
					mineLocations[counter][1] = y;
					counter++;
					
					int[] xPosition = getPositionX(x);
					int[] yPosition = getPositionY(y);
					
					for (int neighbor = 0; neighbor < 8; neighbor++){
						if (Grid[xPosition[neighbor]][yPosition[neighbor]] != -1){
							Grid[xPosition[neighbor]][yPosition[neighbor]]++;
						}
					}
				}
			}
		}
		else {
			for (int y = 0; y < (D+2); y++){
				Grid[0][y] = 0;
				Grid[(D+1)][y] = 0;
			}
			
			for (int x = 0; x < (D+2); x++){
				Grid[x][0] = 0;
				Grid[x][(D+1)] = 0;
			}
			
			for (int x = 1; x < (D+1); x++){
				for (int y = 1; y < (D+1); y++){
					Grid[x][y] = -1;
				}
			}
			
			Grid[firstX][firstY] = getNeighboringMinesReverse(firstX, firstY);
			
			for (int counter = 0; counter < ((D*D - numberOfMines) - 1);){
				int x = random.nextInt(D) + 1;
				int y = random.nextInt(D) + 1;
			
				if (Grid[x][y] == -1){
					Grid[x][y] = getNeighboringMinesReverse(x, y);
					
					counter++;
					
					int[] xPosition = getPositionX(x);
					int[] yPosition = getPositionY(y);
					
					for (int neighbor = 0; neighbor < 8; neighbor++){
						if (Grid[xPosition[neighbor]][yPosition[neighbor]] != -1 && 
							Grid[xPosition[neighbor]][yPosition[neighbor]] != 0){
								Grid[xPosition[neighbor]][yPosition[neighbor]]--;
						}
					}
				}
			}
		}
	}
	
	private boolean hasMine(int x, int y){
		if (Grid[x][y] == -1){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int[] getPositionX(int x){
		int[] xPosition = new int[8];
		
		xPosition[0] = (x-1);
		xPosition[1] = (x-1);
		xPosition[2] = (x-1);
		xPosition[3] = x;
		xPosition[4] = x;
		xPosition[5] = (x+1);
		xPosition[6] = (x+1);
		xPosition[7] = (x+1);
		
		return xPosition;
	}
	
	public static int[] getPositionY(int y){
		int[] yPosition = new int[8];
		
		yPosition[0] = (y-1);
		yPosition[1] = y;
		yPosition[2] = (y+1);
		yPosition[3] = (y-1);
		yPosition[4] = (y+1);
		yPosition[5] = (y-1);
		yPosition[6] = y;
		yPosition[7] = (y+1);
		
		return yPosition;
	}
	
	public int getNeighboringMines(int x, int y){
		return Grid[x][y];
	}
	
	private int getNeighboringMinesReverse(int x, int y){
		int[] xPosition = getPositionX(x);
		int[] yPosition = getPositionY(y);
		int neighbors = 0;
		
		for (int neighbor = 0; neighbor < 8; neighbor++){
			if (Grid[xPosition[neighbor]][yPosition[neighbor]] == -1){
				neighbors++;
			}
		}
		return neighbors;
	}
	
	public int[][] getMinefield(){
		return Grid;
	}
	
	public int[][] getMineLocations(){
		return mineLocations;
	}
	
	public void printGrid(){
		for (int y = 0; y < (D+2); y++){
			for (int x = 0; x < (D+2); x++){
				if (Grid[x][y] == -1){
					System.out.print("X" + "     ");
				}
				else {
					System.out.print(Grid[x][y] + "     ");
				}
				
				if (x == (D+1)){
					System.out.println("\n");
				}
			}
		}
	}

	public void saveGrid(){
		File gridFile = new File(".grid");
		
		try {
			gridFile.createNewFile();
			FileWriter fw = new FileWriter(gridFile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("" + D + "\n");
			bw.write("" + numberOfMines + "\n");
			
			for (int x = 0; x < (D+2); x++){
				for (int y = 0; y < (D+2); y++){
					bw.write(Grid[x][y] + " ");
				}
			}
			bw.close();
		} 
		catch (IOException e) {
			System.out.println("Unable to write to the disk.\n");
		}
		
	}
	
	public boolean loadGrid(){
		File gridFile = new File(".grid");
		
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(gridFile);
			
			this.D = Integer.parseInt(fileScanner.nextLine());
			this.numberOfMines = Integer.parseInt(fileScanner.nextLine());
			String gridString = fileScanner.nextLine();
			fileScanner.close();
			
			Grid = new int[(this.D)+2][(this.D)+2];
			
			StringTokenizer token = new StringTokenizer(gridString);
			
			for (int x = 0; x < (D+2); x++){
				for (int y = 0; y < (D+2); y++){
					Grid[x][y] = Integer.parseInt(token.nextToken());
				}
			}
			return true;
		} 
		catch (FileNotFoundException e) {
			System.out.println("No previous game states located.\n");
			return false;
		}
	}
	
	public void clearSave(){
		File gridFile = new File(".grid");
		
		if (gridFile.exists()){
			gridFile.delete();
		}
	}
}
