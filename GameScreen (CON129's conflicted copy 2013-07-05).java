import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameScreen extends JFrame {
		public GameScreen() {
			int D = Backend.getD();
			
			//Main panel
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout(5, 5));
			
			//Grid panel
			JPanel gridPanel = new JPanel();
			gridPanel.setLayout(new GridLayout(D, D));
			mainPanel.add(gridPanel, BorderLayout.CENTER);
			
			for (int x = 1; x <= D; x++){
				for (int y = 1; y <= D; y++){
					FrontEnd.button[x][y] = new JButton(FrontEnd.getIcon(0));
					FrontEnd.button[x][y].addMouseListener(new ButtonHandler(x, y));
					gridPanel.add(FrontEnd.button[x][y]);
				}
			}

			for (int x = 0; x < (D+2); x++){
				FrontEnd.button[x][0] = new JButton(" ");
				FrontEnd.button[x][(D+1)] = new JButton(" ");
			}
			
			for (int y = 0; y < (D+2); y++){
				FrontEnd.button[0][y] = new JButton(" ");
				FrontEnd.button[(D+1)][y] = new JButton(" ");
			}
		
			add(mainPanel);
			setTitle("Minesweeper");
			setBackground(Color.GRAY);
			setSize(FrontEnd.getDIMENSION(), FrontEnd.getDIMENSION());
			setLocation(FrontEnd.getWindowLocation());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		class ButtonHandler implements MouseListener {
			
			private int x;
			private int y;
			
			public ButtonHandler(int x, int y){
				this.x = x;
				this.y = y;
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3){
					if (FrontEnd.button[x][y].isEnabled()){
						Backend.markBox(this.x, this.y);
					}
				}
				if (e.getButton() == MouseEvent.BUTTON1){
					if (FrontEnd.button[x][y].isEnabled()){
						Backend.newGrid(this.x, this.y);
					}	
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
}
