import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonNumber extends JButton{
		private int buttonNumber;
		private MinesweeperIcons msIcons = null;
		
		public ButtonNumber(int buttonNumber){
			msIcons = FrontEnd.msIcons();
			setButtonNumber(buttonNumber);
		}
		
		public void setButtonNumber(int buttonNumber){
			if (buttonNumber >= MinesweeperIcons.QUESTION && buttonNumber <= MinesweeperIcons.NULL){
				this.buttonNumber = buttonNumber;
			}
		}
		
		public int getButtonNumber(){
			return buttonNumber;
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			if (buttonNumber != MinesweeperIcons.NULL) {
				ImageIcon icon = msIcons.getIcon(buttonNumber);
				Image img = icon.getImage();
				
				int x = MinesweeperUtils.roundToInt(getSize().getWidth() / 5);
				int y = MinesweeperUtils.roundToInt(getSize().getHeight() / 5);
				int width = MinesweeperUtils.roundToInt((3.0/5.0) * getSize().getWidth());
				int height = MinesweeperUtils.roundToInt((3.0/5.0) * getSize().getHeight());
		
				g.drawImage(img, x, y, width, height, this);
			}
		}
}