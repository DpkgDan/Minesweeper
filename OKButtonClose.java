import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class OKButtonClose implements ActionListener {
	private JFrame frame;
	
	public OKButtonClose(JFrame frame){
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent arg0) {
		this.frame.dispose();
	}
}
