import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SettingsMenu extends JFrame {
		private static JTextField setD = null;
		private static JTextField setMines = null;
		
		public SettingsMenu(){
			setLayout(new BorderLayout(5, 5));
			
			//Main Panel
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(2, 2, 5, 5));
		
			//Label for dimension
			JLabel dimensionLabel = new JLabel("Dimension (4 - 30):");
			dimensionLabel.setHorizontalAlignment(SwingConstants.CENTER);
			mainPanel.add(dimensionLabel);
			
			//Text box for dimension
			setD = new JTextField("9");
			mainPanel.add(setD);
			
			//Label for number of mines
			JLabel numberOfMines = new JLabel("Number of Mines:");
			numberOfMines.setHorizontalAlignment(SwingConstants.CENTER);
			mainPanel.add(numberOfMines);
			
			//Text box for number of mines
			setMines = new JTextField("10");
			mainPanel.add(setMines);
			
			//OK button and panel
			JPanel OKPanel = new JPanel();
			JButton OK = new JButton("OK");
			OK.addActionListener(new OKListener());
			OKPanel.add(OK);
			
			add(OKPanel, BorderLayout.SOUTH);
			add(mainPanel, BorderLayout.CENTER);
			setLocation(FrontEnd.getWindowLocation());
			setSize(300, 150);
			//setSize(
				//(Calculate.roundToInt(.375 * dim.width)), (Calculate.roundToInt(.125 * dim.height)));
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
		class OKListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				try {
					int number = Integer.parseInt(setD.getText());
					Backend.setD(number);
				}
				catch (NumberFormatException e){
					Backend.setD(9);
				}
				try {
					int number = Integer.parseInt(setMines.getText());
					Backend.setNumberOfMines(number);
				}
				catch (NumberFormatException e){
					Backend.setNumberOfMines(-1);
				}
				FrontEnd.setD();
				FrontEnd.settingsMenu.dispose();
			}	
		}
	}