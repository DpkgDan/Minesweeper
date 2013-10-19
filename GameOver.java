

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameOver extends JFrame{
	private BackEnd backEnd = null;
	private GameScreen gameScreen = null;

	public GameOver(){
		setLayout(new BorderLayout());
		
		backEnd = FrontEnd.backEnd();
		gameScreen = FrontEnd.gameScreen();
		
		int D = backEnd.getD();
		int numberOfMines = backEnd.getNumberOfMines();
		Minefield field = backEnd.getMinefield();
		int[][] mineLocations = field.getMineLocations();
		
		//Sorry message
		JPanel sorryPanel = new JPanel(new GridBagLayout());
		sorryPanel.setBackground(Color.BLACK);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 0;
		c1.weightx = 1;
		c1.gridwidth = 2;
		
		JLabel sorryMessage = new JLabel("You hit a mine. Sorry, you lose.");
		sorryMessage.setHorizontalAlignment(SwingConstants.CENTER);
		int fontSize = MinesweeperUtils.roundToInt(4.4 * D);
		sorryMessage.setFont(new Font("YouMurderer BB", Font.PLAIN, fontSize));
		sorryMessage.setForeground(Color.WHITE);
		sorryPanel.add(sorryMessage, c1);
		
		//New game
		NewGameButton newGameButton = new NewGameButton(this);
		c1.gridx = 0;
		c1.gridy = 1;
		c1.gridwidth = 1;
		c1.weightx = .5;
		sorryPanel.add(newGameButton, c1);
		
		//Quit button
		QuitButton quitButton = new QuitButton();
		c1.gridx = 1;
		c1.gridy = 1;
		c1.gridwidth = 1;
		c1.weightx = .5;
		sorryPanel.add(quitButton, c1);
		
		//Grid
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(Color.BLACK);
		gridPanel.setLayout(new GridLayout(D, D));
		gridPanel.setSize(gameScreen.getGameSize());
		ButtonNumber[][] button = new ButtonNumber[(D+1)][(D+1)];
		
		for (int x = 1; x <= D; x++){
			for (int y = 1; y <= D; y++){
				button[x][y] = new ButtonNumber(MinesweeperIcons.NULL);
				gridPanel.add(button[x][y]);
				button[x][y].setEnabled(false);
			}
		}
		
		for (int counter = 0; counter < numberOfMines; counter++){
			int x = mineLocations[counter][0];
			int y = mineLocations[counter][1];
			
			button[x][y].setEnabled(true);
			button[x][y].setButtonNumber(MinesweeperIcons.BOMB);
		}
		
		add(sorryPanel, BorderLayout.NORTH);
		add(gridPanel);
		
		setTitle("Game Over");
		setSize(FrontEnd.gameScreen().getGameSize());
		setLocation(MinesweeperUtils.getCornerPoint(getSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setVisible(true);
		
		if (MusicEnabledCheckbox.musicEnabled()){
			Songs.song2.play();
		}
	}
}
