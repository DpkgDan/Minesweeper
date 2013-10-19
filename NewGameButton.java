import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class NewGameButton extends JButton {
	private JFrame disposableFrame = null;
	
	public NewGameButton(JFrame disposableFrame){
		super ("New Game");
		this.disposableFrame = disposableFrame;
		
		super.addActionListener(new NewGameButtonListener());
	}
	
	class NewGameButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			disposableFrame.dispose();
			FrontEnd.gameScreen().dispose();
			
			String[] args = {"OLDGAME"};
			FrontEnd.main(args);
		}	
	}
}
