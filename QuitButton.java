import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


@SuppressWarnings("serial")
public class QuitButton extends JButton{

	public QuitButton(){
		super ("Quit");
		super.addActionListener(new QuitListener());
	}
	
	class QuitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
