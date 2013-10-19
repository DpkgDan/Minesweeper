import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameOver extends JFrame{

	public GameOver(){
		setLayout(new BorderLayout());
		
		int D = Backend.getD();
		int numberOfMines = Backend.getNumberOfMines();
		Minefield field = Backend.getMinefield();
		int[][] mineLocations = field.getMineLocations();
		
		//Sorry message
		JPanel sorryPanel = new JPanel();
		JLabel sorryMessage = new JLabel("You hit a mine! Sorry, you lose.");
		sorryMessage.setHorizontalAlignment(SwingConstants.CENTER);
		sorryMessage.setFont(new Font("Baskerville", Font.BOLD, 20));
		sorryPanel.add(sorryMessage);
		
		//Grid
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(D, D));
		gridPanel.setSize(FrontEnd.getDIMENSION(), FrontEnd.getDIMENSION());
		JButton[][] button = new JButton[(D+1)][(D+1)];
		
		for (int x = 1; x <= D; x++){
			for (int y = 1; y <= D; y++){
				button[x][y] = new JButton();
				gridPanel.add(button[x][y]);
				button[x][y].setEnabled(false);
			}
		}
		
		for (int counter = 0; counter < numberOfMines; counter++){
			int x = mineLocations[counter][0];
			int y = mineLocations[counter][1];
			
			button[x][y].setEnabled(true);
			button[x][y].setIcon(FrontEnd.getIcon(9));
		}
		
		add(sorryPanel, BorderLayout.NORTH);
		add(gridPanel);
		
		setLocation(FrontEnd.getWindowLocation());
		setTitle("Game Over");
		setSize((sorryPanel.getSize().height + FrontEnd.getDIMENSION()), 
				(sorryPanel.getSize().width + FrontEnd.getDIMENSION()));
		setLocation(FrontEnd.getWindowLocation());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
