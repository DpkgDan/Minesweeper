
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SettingsMenu extends JFrame {
		private static final int textFieldSize = 9;
		private BackEnd backEnd = null;
		private JComboBox<Integer> selectDimension;
		private JTextField minesTextField;
		private MusicEnabledCheckbox musicEnabled;
		
		public SettingsMenu(){
			backEnd = FrontEnd.backEnd();
			int D = backEnd.getD();
			
			setSize(MinesweeperUtils.getGameScreenSize(.27, .27));
			setLocation(MinesweeperUtils.getCornerPoint(getSize()));
			
			JPanel pane = new JPanel(new GridBagLayout());
			GridBagConstraints c1 = new GridBagConstraints();
			
			//Settings label
			SettingsLabel settingsLabel = new SettingsLabel();
			c1.fill = GridBagConstraints.BOTH;
			c1.gridx = 0;
			c1.gridy = 0;
			c1.gridwidth = 6;
			c1.weightx = .5;
			c1.weighty = .5;
			pane.add(settingsLabel, c1);
			
			//Dimension label
			Insets padding = new Insets(10, 5, 5, 10);
			JLabel dimensionLabel = new JLabel("Grid Size (Dimension):");
			c1.insets = padding;
			c1.gridwidth = 1;
			c1.gridx = 0;
			c1.gridy = 1;
			c1.weightx = (3/6);
			pane.add(dimensionLabel, c1);
			
			//Dimension scroll box
			Integer[] dimensionOptions = new Integer[22];
			
			for (int counter = 0; counter < 21;){
				for (int number = 4; number < 26; number++){
					dimensionOptions[counter] = number;
					counter++;
				}
			}
			
			selectDimension = new JComboBox<>(dimensionOptions);
			selectDimension.setSelectedIndex(D - 4);
			c1.insets = padding;
			c1.gridx = 4;
			c1.gridy = 1;
			c1.gridwidth = 2;
			c1.weightx = (1/3);
			pane.add(selectDimension, c1);
			
			//Number of mines label
			JLabel minesLabel = new JLabel("Number of Mines:");
			c1.insets = padding;
			c1.gridx = 0;
			c1.gridy = 2;
			c1.gridwidth = 1;
			c1.weightx = (3/6);
			pane.add(minesLabel, c1);
			
			//Default button
			JButton defaultButton = new JButton("Default");
			defaultButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int D = (Integer)selectDimension.getSelectedItem();
					int defaultMines = Minefield.defaultNumberOfMines(D);
						
					minesTextField.setText("" + defaultMines);
				}
			});
			
			c1.insets = padding;
			c1.gridx = 3;
			c1.gridy = 2;
			c1.weightx = (2/6);
			c1.gridwidth = 2;
			pane.add(defaultButton, c1);
			
			//Number of mines text field
			minesTextField = new JTextField(textFieldSize);
			minesTextField.setText("" + backEnd.getNumberOfMines());
			minesTextField.setFocusable(true);
			c1.insets = padding;
			c1.gridx = 5;
			c1.gridy = 2;
			c1.weightx = (1/6);
			pane.add(minesTextField, c1);
			
			//Disable music checkbox
			musicEnabled = new MusicEnabledCheckbox();
			padding = new Insets(10, 0, 5, 10);
			c1.insets = padding;
			c1.gridx = 0;
			c1.gridy = 3;
			c1.gridwidth = 1;
			c1.weightx = (3/6);
			pane.add(musicEnabled, c1);
			
			//OKButton
			JButton OKButton = new JButton("OK");
			OKButton.addActionListener(new OKButton(this));
			c1.insets = padding;
			c1.fill = GridBagConstraints.HORIZONTAL;
			c1.gridx = 3;
			c1.gridy = 4;
			c1.gridwidth = 4;
			c1.weightx = 1;
			pane.add(OKButton, c1);
			
			add(pane);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			setVisible(true);
		}
		
		class OKButton extends OKButtonClose {

			public OKButton(JFrame frame) {
				super(frame);
			}

			public void actionPerformed(ActionEvent arg0) {
				super.actionPerformed(arg0);
				
				int D = (Integer)selectDimension.getSelectedItem();
				
				try {
					int numberOfMines = Integer.parseInt(minesTextField.getText());
						backEnd.setD(D);
						backEnd.setNumberOfMines(numberOfMines);
				}
				catch (NumberFormatException e){
					
				}
			}
		}
}