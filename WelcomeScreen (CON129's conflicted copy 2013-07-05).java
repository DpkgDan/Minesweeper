import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WelcomeScreen extends JFrame {
		
		public WelcomeScreen() {
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			Color backgroundColor = new Color(102, 178, 255);
			
			ImageIcon minesweeperImage = null;
			
			try {
				minesweeperImage = 
						new ImageIcon(ImageIO.read(new File("images/minesweeper.png")));
			} 
			catch (IOException e) {
				System.exit(0);
			}
			
			//Minesweeper image
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(minesweeperImage);
			imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			mainPanel.add(imageLabel, BorderLayout.CENTER);
			
			//Settings menu
			JMenuBar menuBar = new JMenuBar();
			JMenu settingsMenu = new JMenu("Settings");
			settingsMenu.addMouseListener(new MenuListener());
			menuBar.setBackground(backgroundColor);
			settingsMenu.setBackground(backgroundColor);
			menuBar.add(settingsMenu);
			setJMenuBar(menuBar);
			
			//Minesweeper text
			JLabel welcomeText = new JLabel("Minesweeper");
			welcomeText.setFont(new Font("Krungthep", Font.PLAIN, 50));
			welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
			mainPanel.add(welcomeText, BorderLayout.NORTH);
			
			//My name
			JLabel myName = new JLabel("By Dan Saba");
			myName.setFont(new Font("Marker Felt", Font.PLAIN, 24));
			myName.setHorizontalAlignment(SwingConstants.CENTER);
			mainPanel.add(myName, BorderLayout.SOUTH);
			
			//Start button
			JPanel startButtonPanel = new JPanel();
			JButton startGame = new JButton("Start Game");
			startGame.addActionListener(new StartButtonListener());
			startGame.setPreferredSize(new Dimension(300, 50));
			startGame.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
			startButtonPanel.add(startGame);
			
			//Panel with name and start button
			JPanel startAndName = new JPanel();
			startAndName.setLayout(new GridLayout(2, 1));
			startAndName.add(myName);
			startAndName.add(startButtonPanel);
			mainPanel.add(startAndName, BorderLayout.SOUTH);
			
			add(mainPanel);
			setBackground(backgroundColor);
			setTitle("Minesweeper");
			setLocation(FrontEnd.getWindowLocation());
			setSize(FrontEnd.getDIMENSION(), FrontEnd.getDIMENSION());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		class StartButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				FrontEnd.welcomeScreen.dispose();
				FrontEnd.setD();
				FrontEnd.gameScreen = new GameScreen();
			}
		}	
		
		class MenuListener implements MouseListener {

			public void mouseClicked(MouseEvent arg0) {
				FrontEnd.settingsMenu = new SettingsMenu();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
		}
}

