import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class Minesweeper extends JFrame {
	private static final int HEIGHT = 400;
	private static final int WIDTH = 400;
	private static final int D = 9;
	public static JButton[][] button = new JButton[(D+2)][(D+2)];
	private static Minefield field;
	private static int[][] realGrid;
	private static ImageIcon[] icon = new ImageIcon[10];
	private static boolean firstRun = true;
	
	public Minesweeper(){
		loadImages();
		Container pane = getContentPane();
		pane.add(new JMenuBar());
		
		for (int x = 1; x <= D; x++){
			for (int y = 1; y <= D; y++){
				button[x][y] = new JButton(icon[0]);
				button[x][y].addMouseListener(new ButtonHandler(x, y));
				pane.add(button[x][y]);
			}
		}

		for (int x = 0; x < (D+2); x++){
			button[x][0] = new JButton(" ");
			button[x][(D+1)] = new JButton(" ");
		}
		
		for (int y = 0; y < (D+2); y++){
			button[0][y] = new JButton(" ");
			button[(D+1)][y] = new JButton(" ");
		}
		
		setVisible(true);
		setSize(HEIGHT, WIDTH);
		setLayout(new GridLayout(D, D));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	public void newGrid(int x, int y){
		if (firstRun){
			field = new Minefield(x, y);
			realGrid = field.getMinefield();
			firstRun = false;
		}
			
		int numberOfMines = realGrid[x][y];
		
		if (numberOfMines == -1){
			setVisible(false);
			dispose();
		}
		
		if (numberOfMines > 0){
			button[x][y].setIcon(getIconFromNumber(numberOfMines));
		}
		
		if (numberOfMines == 0){
			button[x][y].setIcon(null);
			button[x][y].setEnabled(false);
			pointIsZero(x, y);
		}
	}
	
	private static void pointIsZero(int x, int y){
		int[] xPosition = Minefield.getPositionX(x);
		int[] yPosition = Minefield.getPositionY(y);
		
		for (int counter = 0; counter < 8; counter++){
			int currentX = xPosition[counter];
			int currentY = yPosition[counter];
			
			if (button[currentX][currentY].getIcon() != null){
				if (button[currentX][currentY].getIcon() == icon[0]){
					int neighboringMines = realGrid[currentX][currentY];
					
					if (neighboringMines > 0){
						button[currentX][currentY].setIcon(getIconFromNumber(neighboringMines));
					}
					
					if (neighboringMines == 0){
						button[currentX][currentY].setIcon(null);
						button[currentX][currentY].setEnabled(false);
						pointIsZero(currentX, currentY);
					}
				}
			}
		}
	}
	
	public static void markBox(int x, int y){
		if (button[x][y].getIcon().equals(icon[0])){
			button[x][y].setIcon(icon[9]);
		}
		else {
			button[x][y].setIcon(icon[9]);
		}
	}
	
	public static ImageIcon getIconFromNumber(int number){
		switch (number) {
			case 1: return icon[1];
			case 2: return icon[2];
			case 3: return icon[3];
			case 4: return icon[4];
			case 5: return icon[5];
			case 6: return icon[6];
			case 7: return icon[7];
			case 8: return icon[8];
			default: return null;
		}
	}
	
	private static void loadImages(){		
		try {
			icon[0] = new ImageIcon(ImageIO.read(new File("images/question.png")));
			icon[1] = new ImageIcon(ImageIO.read(new File("images/1.png")));
			icon[2] = new ImageIcon(ImageIO.read(new File("images/2.png")));
			icon[3] = new ImageIcon(ImageIO.read(new File("images/3.png")));
			icon[4] = new ImageIcon(ImageIO.read(new File("images/4.png")));
			icon[5] = new ImageIcon(ImageIO.read(new File("images/5.png")));
			icon[6] = new ImageIcon(ImageIO.read(new File("images/6.png")));
			icon[7] = new ImageIcon(ImageIO.read(new File("images/7.png")));
			icon[8] = new ImageIcon(ImageIO.read(new File("images/8.png")));
			icon[9] = new ImageIcon(ImageIO.read(new File("images/bomb.png")));
			
		}
		catch (IOException e){
			System.exit(0);
		}
		catch (IllegalArgumentException e) {
			System.exit(0);
		}
		
	}
	
	public ImageIcon getIcon(int iconNumber){
		return icon[iconNumber];
	}
	
	class ButtonHandler implements MouseListener {
		private int x;
		private int y;
		
		public ButtonHandler(int x, int y){
			this.x = x;
			this.y = y;
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3){
				if (button[x][y].isEnabled()){
					markBox(this.x, this.y);
				}
			}
			if (e.getButton() == MouseEvent.BUTTON1){
				if (button[x][y].isEnabled()){
					newGrid(this.x, this.y);
				}	
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
 }

