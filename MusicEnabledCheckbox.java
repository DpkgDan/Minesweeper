import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class MusicEnabledCheckbox extends JCheckBox {
	private static boolean musicEnabled = true;

	public MusicEnabledCheckbox(){
		super ("Music Enabled", musicEnabled);
		super.setHorizontalTextPosition(SwingConstants.LEFT);
		super.addActionListener(new MusicCheckboxListener(this));
	}
	
	public static boolean musicEnabled(){
		return musicEnabled;
	}
	
	class MusicCheckboxListener implements ActionListener {
		private MusicEnabledCheckbox checkbox;
		
		public MusicCheckboxListener(MusicEnabledCheckbox checkbox){
			this.checkbox = checkbox;
		}

		public void actionPerformed(ActionEvent arg0) {
			if (!checkbox.isSelected()){
				Songs.song1.stop();
				musicEnabled = false;
			}
			else{
				Songs.song1.loop();
				musicEnabled = true;
			}
		}
	}
}
