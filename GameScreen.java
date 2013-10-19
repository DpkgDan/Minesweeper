import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

@SuppressWarnings("serial")
public class GameScreen extends JFrame {
	private ButtonNumber[][] button;
	private BackEnd backEnd = null;
	private boolean firstRun = true;
	private Dimension gameSize = null;
	
		public GameScreen() {
			backEnd = FrontEnd.backEnd();
			int D = backEnd.getD();
			button = new ButtonNumber[(D+2)][(D+2)];
			
			//Main panel
			JPanel mainPanel = new JPanel();
			mainPanel.setBackground(FrontEnd.backgroundColor);
			mainPanel.setLayout(new BorderLayout(5, 5));
			
			//Menu bar
			JMenuBar menuBar = new JMenuBar();
			JMenu settingsMenu2 = new JMenu("Settings");
			settingsMenu2.addMenuListener(new OpenSettings2Listener());
			menuBar.setBackground(FrontEnd.backgroundColor);
			settingsMenu2.setBackground(FrontEnd.backgroundColor);
			menuBar.add(settingsMenu2);
			setJMenuBar(menuBar);
			
			//Grid panel
			JPanel gridPanel = new JPanel();
			gridPanel.setBackground(Color.GRAY);
			gridPanel.setLayout(new GridLayout(D, D));
			mainPanel.add(gridPanel, BorderLayout.CENTER);
			
			for (int x = 1; x <= D; x++){
				for (int y = 1; y <= D; y++){
					button[x][y] = new ButtonNumber(MinesweeperIcons.QUESTION);
					button[x][y].addMouseListener(new ButtonHandler(x, y));
					gridPanel.add(button[x][y]);
				}
			}

			for (int x = 0; x < (D+2); x++){
				button[x][0] = new ButtonNumber(MinesweeperIcons.NULL);
				button[x][(D+1)] = new ButtonNumber(MinesweeperIcons.NULL);
			}
			
			for (int y = 0; y < (D+2); y++){
				button[0][y] = new ButtonNumber(MinesweeperIcons.NULL);
				button[(D+1)][y] = new ButtonNumber(MinesweeperIcons.NULL);
			}
			
			//Size calculations
			//Each button should occupy 1/25.6 the size of the larger dimension of the screen
			Dimension buttonSize = MinesweeperUtils.getSquareScreenSize(1.0/25.6);
			gameSize = new Dimension((buttonSize.width * D), (buttonSize.height * D));
			
			add(mainPanel);
			setTitle("Minesweeper");
			setSize(gameSize);
			setLocation(MinesweeperUtils.getCornerPoint(getSize().getWidth(), getSize().getHeight()));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		public void uncoverBox(int x, int y, int iconNumber){
			button[x][y].setButtonNumber(iconNumber);
			
			if (iconNumber == MinesweeperIcons.NULL){
				button[x][y].setEnabled(false);
			}
			
			MouseListener[] mL = button[x][y].getMouseListeners();
			
			for (int counter = 0; counter < mL.length; counter++){
				button[x][y].removeMouseListener(mL[counter]);
			}
			
			button[x][y].repaint();
		}
		
		public void toggleFlag(int x, int y){
			if (button[x][y].getButtonNumber() == MinesweeperIcons.QUESTION){
				button[x][y].setButtonNumber(MinesweeperIcons.FLAG);
			}
			else {
				button[x][y].setButtonNumber(MinesweeperIcons.QUESTION);
			}
			button[x][y].repaint();
		}
		
		public int getButtonNumber(int x, int y){
			return button[x][y].getButtonNumber();
		}
		
		public Dimension getGameSize(){
			return gameSize;
		}
		

 	class ButtonHandler implements MouseListener {
		private int x;
		private int y;
		
		public ButtonHandler(int x, int y){
			this.x = x;
			this.y = y;
		}

		public void mouseClicked(MouseEvent e) {
			if (backEnd.handlersEnabled()) {
				if (firstRun){
					backEnd.linkToGameScreen();
					firstRun = false;
				}
				
				if (e.getButton() == MouseEvent.BUTTON3){
					if (button[x][y].isEnabled()){
						toggleFlag(this.x, this.y);
					}
				}
				if (e.getButton() == MouseEvent.BUTTON1){
					if (button[x][y].isEnabled()){
						backEnd.newGrid(this.x, this.y);
						FrontEnd.gameScreen().repaint();
					}	
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
 	
 	class OpenSettings2Listener implements MenuListener {
 		SettingsMenu2 settingsMenu2 = null;
 		
		@Override
		public void menuSelected(MenuEvent e) {
			if (settingsMenu2 == null){
				settingsMenu2 = new SettingsMenu2();
			}
			else if (!settingsMenu2.isVisible()){
				settingsMenu2.setVisible(true);
			}
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
 		
 	}
}
