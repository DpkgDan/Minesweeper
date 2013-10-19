import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SettingsMenu2 extends JFrame {

	public SettingsMenu2(){
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		Insets padding = new Insets(0, 10, 10, 0);
		
		//Settings label
		SettingsLabel settingsLabel = new SettingsLabel();
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridheight = 1;
		c1.insets = padding;
		pane.add(settingsLabel, c1);
		
		//Music enabled checkbox
		MusicEnabledCheckbox musicEnabledCheckbox = new MusicEnabledCheckbox();
		c1.gridx = 0;
		c1.gridy = 1;
		c1.gridheight = 1;
		pane.add(musicEnabledCheckbox, c1);
		
		//OKButton
		JButton OKButton = new JButton("OK");
		OKButton.addActionListener(new OKButtonClose(this));
		c1.gridx = 0;
		c1.gridy = 2;
		pane.add(OKButton, c1);
		
		add(pane);
		setSize(MinesweeperUtils.getGameScreenSize(.27, .18));
		setLocation(MinesweeperUtils.getCornerPoint(getSize()));
		setAlwaysOnTop(true);
		setVisible(true);
	}
}