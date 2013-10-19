import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WinScreen extends JFrame {
	
	public static void main(String[] args){
		new WinScreen();
	}
	
	public WinScreen(){
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		
		//Text
		JLabel text = new JLabel("Congratulations! You've discovered all the mines.");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		Insets padding = new Insets(5, 10, 10, 5);
		c1.insets = padding;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.weightx = 1;
		c1.gridwidth = 2;
		c1.fill = GridBagConstraints.BOTH;
		pane.add(text, c1);
		
		//New game button
		NewGameButton newGame = new NewGameButton(this);
		c1.gridx = 0;
		c1.gridy = 1;
		c1.weightx = .5;
		c1.gridwidth = 1;
		c1.fill = GridBagConstraints.NONE;
		pane.add(newGame, c1);
		
		//Quit button
		QuitButton quit = new QuitButton();
		c1.gridx = 1;
		c1.gridy = 1;
		c1.gridwidth = 1;
		c1.weightx = .5;
		c1.fill = GridBagConstraints.NONE;
		pane.add(quit, c1);
		
		
		add(pane);
		setSize(MinesweeperUtils.getGameScreenSize((.267), (.167)));
		setTitle("Winner!");
		setLocation(MinesweeperUtils.getCornerPoint(getSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}