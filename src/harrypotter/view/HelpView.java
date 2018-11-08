package harrypotter.view;

import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.JPanel;

public class HelpView extends JPanel{
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/controlsHelp.png")), 0, 0, getWidth(),
					getHeight(), this);
		} catch (Exception e) {
			System.exit(0);
		}
	}
}
