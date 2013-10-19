

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CommandLine {
	private static int D = 9;
	private static int numberOfMines = 10;
	private static char[][] visibleGrid;
	private static Minefield field;
	private static int[][] realGrid;
	private static int x;
	private static int y;
	private static boolean mineUncovered = false;
	private static boolean uncoverOrMark = true;
	private static boolean firstRun = true;
	private static char markSymbol = 'M';
	private static int spacesUncovered = 0;
	private static int markedSpaces = 0;
	private static int DLength;
	private static Scanner scanner = new Scanner(System.in);
	private static boolean wasLoaded = false;

	public static void main(String[] args) {
		/* For uncoverOrMark, true = uncover; false = mark
		*/
		
		options();
		
		if (!wasLoaded){
			generateVisibleGrid();
		}
		else {
			if (args.length > 0){
				if (args[0].equals("showhidden")){
					field.printGrid();
				}
			}
		}
		
		DLength = ("" + D).length();
		
		boolean goOnForever = true;

		while (goOnForever){
			boolean spaceHasBeenSelected = true;
			
			System.out.println("Spaces uncovered: " + spacesUncovered + "/" + (D*D));
			System.out.println("Marked spaces: " + markedSpaces + "/" + numberOfMines + "\n");
			printVisibleGrid();
			
			while (spaceHasBeenSelected){
				uncoverOrMark = true;
				
				selectCoordinate();
				
				if (firstRun && !wasLoaded){
					field = new Minefield(D, numberOfMines, x, y);
					realGrid = field.getMinefield();
					firstRun = false;
					
					if (args.length > 0){
						if (args[0].equals("showhidden")){
							field.printGrid();
						}
					}
				}
				
				if (visibleGrid[x][y] != '?' && visibleGrid[x][y] != markSymbol){
					System.out.println
					("That space has already been uncovered. Pick again.\n");
				}
				else {
					newGrid();
					spaceHasBeenSelected = false;
				}
			}
			if (spacesUncovered == ((D*D) - numberOfMines)){
				System.out.println("Spaces uncovered: " + spacesUncovered);
				System.out.println("Marked spaces: " + markedSpaces + "\n");
				printVisibleGrid();
				congratulations();
				System.out.println("You've discovered all the mines!");
				clearSave();
				System.exit(0);
			}
			
			if (mineUncovered){
				System.out.println("You hit a mine! You lose :(");
				printFinalGrid();
				clearSave();
				System.exit(0);
			}
			saveGame();
		}
	}
	
	public static void options(){
		System.out.println
		("Welcome to Minesweeper! " +
		"Press any key to play on a typical 9x9 grid with 10 mines. " +
		"Otherwise, type \'o\'.");
		
		String userResponse = scanner.nextLine();
			
		if (userResponse != null){
			if (userResponse.equals("o") || userResponse.equals("O")){
				boolean Continue = true;
					
				while (Continue){
					System.out.println("**Options**");
					System.out.println("1. Change the dimensions of the minefield.");
					System.out.println("2. Change the number of mines.");
					System.out.println("3. Load previous game.");
					System.out.println("4. Go to the game.");
				
					String userResponse2 = scanner.nextLine();
					int ascii = (int)userResponse2.charAt(0);
				
					if (userResponse2.length() != 1 || 
					   (ascii < 49 || ascii > 52)){
							invalidEntry();
					}
					else {
						int option = Integer.parseInt(userResponse2);
						
						switch (option){
						case 1: setD();
								break;
						case 2: setNumberOfMines();
								break;
						case 3: loadGame();
								if (wasLoaded){
									Continue = false;
								}
								break;
						case 4: Continue = false;
								break;
						}
					}
				}
			}
		}
	}
	
	public static void setD(){
		boolean responseIsInvalid = true;
		
		while (responseIsInvalid){
			System.out.println
			("Enter a new dimension from " + Minefield.getMinD() + 
			" to " + Minefield.getMaxD() + ". The grid is a square, so only " +
			"one dimension is necessary.");
			
			String userResponse = scanner.nextLine();
			
			try {
				int tempD = Integer.parseInt(userResponse);
				
				if (Minefield.DIsValid(tempD)){
					responseIsInvalid = false;
					D = tempD;
					System.out.println
					("The dimensions have been changed to " + D + "x" + D + ".\n");
					
					numberOfMines = Minefield.defaultNumberOfMines(D);
					
					System.out.println
					("The number of mines has been set to the default ("
					+ numberOfMines + ").\n");
				}
				else {
					System.out.println("Dimensions must be between " + Minefield.getMinD() 
					+ " and " + Minefield.getMaxD() + ". Automatically setting " +
					"dimensions to 9x9...\n");
					responseIsInvalid = false;
				}
			}
			catch (NumberFormatException e){
				invalidEntry();
			}
		}
	}
	
	public static void setNumberOfMines(){
		boolean responseIsInvalid = true;
		
		while (responseIsInvalid){
			System.out.println
			("Enter the number of the mines. " +
			"Note: The number of mines must be less than " + (D*D) + ", the number of " +
			"spaces on the grid.");
			
			String userResponse = scanner.nextLine();
			
			try {
				int tempNumberOfMines = Integer.parseInt(userResponse);
				
				if (Minefield.mineNumberIsValid(D, tempNumberOfMines)){
					responseIsInvalid = false;
					numberOfMines = tempNumberOfMines;
					System.out.println
					("The number of mines has been changed to " 
					+ numberOfMines + ".\n");
				}
				else {
					invalidEntry();
				}
			}
			catch (NumberFormatException e){
				invalidEntry();
			}
		}
	}
	
	public static void selectCoordinate(){
		boolean responseIsInvalid = true;
		
		while (responseIsInvalid){
			if (firstRun && !wasLoaded){
				System.out.println
				("Enter the coordinate of the space you would " +
						"like to uncover.");
			}
			else if (uncoverOrMark){
				System.out.println
				("Enter the coordinate of the space " +
						"you would like to uncover. Type \'2\' to mark a space instead.");
			}
			else {
				System.out.println
				("Enter the coordinate of the space you would like to mark.");
			}
			
			String userResponse = scanner.nextLine();
			
			if (userResponse.equals("")){
				invalidEntry();
			}

			else if (userResponse.length() == 1){
				if (userResponse.equals("2") && (!firstRun || wasLoaded)){
					uncoverOrMark = false;
					responseIsInvalid = false;
					selectCoordinate();
				}
				else {
					invalidEntry();
				}
			}
			
			else if (userResponse.length() <= (DLength + 1)){
				char first = userResponse.charAt(0);
				String second = userResponse.substring(1, userResponse.length());
				
				boolean firstIsValid = firstIsValid(first);
				boolean secondIsValid = secondIsValid(second);
				
				if (firstIsValid && secondIsValid){
					responseIsInvalid = false;
					x = convertLetterToNumber(userResponse.charAt(0));
					y = Integer.parseInt(second);
				}
				else {
					invalidEntry();
				}
			}
			else {
				invalidEntry();
			}
		}
	}
	
	public static void invalidEntry(){
		System.out.println("Invalid entry.\n");
	}
	
	public static boolean firstIsValid(char first){
		int ascii = (int)first;
		
		if (ascii >= 65 && ascii < (65 + D)){
			return true;
		}
		else if (ascii >= 97 && ascii < (97 + D)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean secondIsValid(String second){
		try {
			int number = Integer.parseInt(second);
			
			if (number > 0 && number <= D){
				return true;
			}
			else {
				return false;
			}
		}
		catch (NumberFormatException e){
			return false;
		}
	}
	
	private static int convertLetterToNumber(char letterCoordinate){
		int ascii = (int)letterCoordinate;
		int number = 0;
		
		if (ascii >= 65 && ascii <= 90){
			number = (ascii - 65) + 1;
		}
		
		if (ascii >= 97 && ascii <= 122){
			number = (ascii - 97) + 1;
		}
		return number;
	}
	
	private static void generateVisibleGrid(){
		visibleGrid = new char[(D+2)][(D+2)];
		
		for (int x = 0; x < (D+2); x++){
			visibleGrid[x][0] = '-';
			visibleGrid[x][(D+1)] = '-';
			visibleGrid[(D+1)][x] = '-';
			visibleGrid[0][x] = '-';
		}	
		
		for (int x = 1; x <= D; x++){
			for (int y = 1; y <= D; y++){
				visibleGrid[x][y] = '?';
			}
		}
	}
	
	private static void newGrid(){
		int numberOfMines = field.getNeighboringMines(x, y);
		
		if (uncoverOrMark){
			if (realGrid[x][y] == -1){
				mineUncovered = true;
			}
			
			else {
				spacesUncovered++;
				
				if (numberOfMines == 0){
				visibleGrid[x][y] = '-';
				pointIsZero(x, y);
				}
				
				if (numberOfMines > 0){
					String stringNumberOfMines = "" + numberOfMines;
					char charNumberOfMines = stringNumberOfMines.charAt(0);
					visibleGrid[x][y] = charNumberOfMines;
				}
			}
		}
		if (!uncoverOrMark) {
			if (visibleGrid[x][y] != '?' && visibleGrid[x][y] != markSymbol){
				System.out.println
				("Error: Can't toggle marking on a space that is already uncovered.");
			}
			else if (visibleGrid[x][y] == '?'){
				visibleGrid[x][y] = markSymbol;
				markedSpaces++;
			}
			else {
				visibleGrid[x][y] = '?';
				markedSpaces--;
			}
		}
	}
	

	
	private static void pointIsZero(int x, int y){
		int[] xPosition = Minefield.getPositionX(x);
		int[] yPosition = Minefield.getPositionY(y);
		
		for (int counter = 0; counter < 8; counter++){
			int currentX = xPosition[counter];
			int currentY = yPosition[counter];
			
			if (visibleGrid[currentX][currentY] == '?'){
				spacesUncovered++;
				
				int neighboringMines = realGrid[currentX][currentY];
				
				if (neighboringMines > 0){
					String stringNeighboringMines = "" + neighboringMines;
					visibleGrid[currentX][currentY] = stringNeighboringMines.charAt(0);
				}
				
				if (neighboringMines == 0){
					visibleGrid[currentX][currentY] = '-';
					pointIsZero(currentX, currentY);
				}
			}
		}
	}
	
	private static void printVisibleGrid(){
		for (int counter = 0; counter < (5 + DLength); counter++){
			System.out.print(" ");
		}
		
		for (int counter = 0; counter < D; counter++){
			System.out.print((char)(65 + counter) + "     ");
		}
		System.out.println("\n");
		
		int rowNumber = 1;
		
		for (int y = 1; y <= D; y++){
			for (int x = 0; x <= D; x++){
				if (x == 0){
					int yLength = ("" + y).length();
					System.out.print(rowNumber);
					
					for (int counter = 0; 
					counter < (5 + DLength - yLength); counter++){
						System.out.print(" ");
					}
					rowNumber++;
				}
				else {
					System.out.print(visibleGrid[x][y] + "     ");
				}
				
				if (x == D){
					System.out.println("\n");
				}
			}
		}
	}
	
	private static void printFinalGrid(){
		System.out.print("      ");
		
		for (int counter = 0; counter < D; counter++){
			System.out.print((char)(65 + counter) + "     ");
		}
		System.out.println("\n");
		
		int rowNumber = 1;
		
		for (int y = 1; y <= D; y++){
			for (int x = 0; x <= D; x++){
				if (x == 0 && y > 9){
					System.out.print(rowNumber + "    ");
					rowNumber++;
				}
				else if (x == 0){
					System.out.print(rowNumber + "     ");
					rowNumber++;
				}
				else {
					if (realGrid[x][y] == -1){
						System.out.print("X" + "     ");
					}
					else {
						System.out.print("-" + "     ");
					}
				}
				
				if (x == D){
					System.out.println("\n");
				}
			}
		}
	}
	
	private static void saveGame(){
		File visibleGridFile = new File(".visiblegrid");
		FileWriter fw;
		field.saveGrid();
		
		try {
			visibleGridFile.createNewFile();
			fw = new FileWriter(visibleGridFile);
			BufferedWriter bw = new BufferedWriter(fw);
			String visibleGridString = "";
			
			bw.write("" + D + "\n");
			bw.write("" + numberOfMines + "\n");
			
			for (int x = 0; x < (D+2); x++){
				for (int y = 0; y < (D+2); y++){
					visibleGridString += visibleGrid[x][y] + " ";
				}
			}
			
			bw.write(visibleGridString);
			bw.close();
		} 
		catch (IOException e) {
			System.out.println("Error creating save file.\n");
		}
	}
	
	private static void loadGame(){
		File visibleGridFile = new File(".visiblegrid");
		Scanner fileScanner = null;
		
		field = new Minefield(1,1);
		
		if (field.loadGrid()){
			try {
				fileScanner = new Scanner(visibleGridFile);
				D = Integer.parseInt(fileScanner.nextLine());
				numberOfMines = Integer.parseInt(fileScanner.nextLine());
				
				String visibleGridString = fileScanner.nextLine();
				StringTokenizer token = new StringTokenizer(visibleGridString);
				
				visibleGrid = new char[(D+2)][(D+2)];
				
				for (int x = 0; x < (D+2); x++){
					for (int y = 0; y < (D+2); y++){
						visibleGrid[x][y] = token.nextToken().charAt(0);
					}
				}
				
				realGrid = field.getMinefield();
				
				System.out.println("Game successfully loaded!\n");
				wasLoaded = true;
			}
			catch (IOException e){
				System.out.println("No previous game states located.\n");
			}
		}
		else {
			wasLoaded = false;
		}
	}
	
	private static void clearSave(){
		field.clearSave();
		
		File visibleGridFile = new File(".visiblegrid");
		
		if (visibleGridFile.exists()){
			visibleGridFile.delete();
		}
	}
	
	private static void congratulations(){
		 System.out.println("   ___                            _         _       _   _                    _ ");
		 System.out.println("  / __\\___  _ __   __ _ _ __ __ _| |_ _   _| | __ _| |_(_) ___  _ __  ___   / \\");
		 System.out.println(" / /  / _ \\| '_ \\ / _` | '__/ _` | __| | | | |/ _` | __| |/ _ \\| '_ \\/ __| /  /");
		 System.out.println("/ /__| (_) | | | | (_| | | | (_| | |_| |_| | | (_| | |_| | (_) | | | \\__ \\/\\_/ "); 
		 System.out.println("\\____/\\___/|_| |_|\\__, |_|  \\__,_|\\__|\\__,_|_|\\__,_|\\__|_|\\___/|_| |_|___/\\/   ");   
		 System.out.println("                  |___/                                                        ");
	}
}
