import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class SettingsLabel extends JLabel{

	public SettingsLabel(){
		super ("Settings");
		super.setFont(new Font("Krungthep", Font.PLAIN, 24));
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}
}