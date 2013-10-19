import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

@SuppressWarnings("serial")
public class WelcomeScreen extends JFrame {
	
		public WelcomeScreen() {
			JPanel mainPanel = new JPanel();
			mainPanel.setBackground(FrontEnd.backgroundColor);
			mainPanel.setLayout(new BorderLayout());
			
			//Settings menu
			JMenuBar menuBar = new JMenuBar();
			JMenu settingsMenu = new JMenu("Settings");
			settingsMenu.addMenuListener(new OpenSettingsListener());
			menuBar.setBackground(FrontEnd.backgroundColor);
			settingsMenu.setBackground(FrontEnd.backgroundColor);
			menuBar.add(settingsMenu);
			setJMenuBar(menuBar);
			
			//Minesweeper text
			JLabel welcomeText = new JLabel("Minesweeper");
			welcomeText.setFont(new Font("Krungthep", Font.PLAIN, 50));
			welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
			mainPanel.add(welcomeText, BorderLayout.NORTH);
			
			//Minesweeper image
			MinesweeperImage imagePanel = new MinesweeperImage();
			imagePanel.setBackground(FrontEnd.backgroundColor);
			mainPanel.add(imagePanel, BorderLayout.CENTER);
			
			//My name
			JLabel myName = new JLabel("By Dan Saba");
			myName.setFont(new Font("Marker Felt", Font.PLAIN, 24));
			myName.setHorizontalAlignment(SwingConstants.CENTER);
			
			//Start button
			JPanel startButtonPanel = new JPanel();
			JButton startGame = new JButton("Start Game");
			startButtonPanel.setBackground(FrontEnd.backgroundColor);
			startGame.addActionListener(new StartButtonListener());
			startGame.setPreferredSize(new Dimension(300, 50));
			startGame.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
			startButtonPanel.add(startGame);
			
			//Name and start button panel
			JPanel SandN = new JPanel(new BorderLayout());
			SandN.setBackground(FrontEnd.backgroundColor);
			SandN.add(myName, BorderLayout.NORTH);
			SandN.add(startButtonPanel, BorderLayout.SOUTH);
			mainPanel.add(SandN, BorderLayout.SOUTH);
			
			add(mainPanel);
			setTitle("Minesweeper");
			setSize(MinesweeperUtils.getSquareScreenSize(1.0/3.0));
			setLocation(MinesweeperUtils.getCornerPoint(getSize()));
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		class MinesweeperImage extends JPanel {
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				
				ImageIcon imgIcon = FrontEnd.msIcons().getIcon(MinesweeperIcons.MINESWEEPER);
				Image img = imgIcon.getImage();
				
				int x = MinesweeperUtils.roundToInt(getSize().getWidth() / 4);
				int y = 0;
				int width = MinesweeperUtils.roundToInt(getSize().getWidth() / 2);
				int height = MinesweeperUtils.roundToInt(getSize().getHeight());
				
				g.drawImage(img, x, y, width, height, this);
			}
		}
	
		class StartButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				FrontEnd.welcomeScreen().dispose();
				FrontEnd.gameScreen();
			}
		}	
		
		class OpenSettingsListener implements MenuListener {
			SettingsMenu settingsMenu = null;
			
			@Override
			public void menuSelected(MenuEvent e) {
				if (settingsMenu == null){
					settingsMenu = new SettingsMenu();
				}
				else if (!settingsMenu.isVisible()){
					settingsMenu.setVisible(true);
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
}

