package harrypotter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;

@SuppressWarnings("serial")
public class WinningScreen extends JPanel{
	private GameView gameView;
	
	public WinningScreen() {
		// TODO Auto-generated constructor stub
	}
	public void initialize(){
		JPanel pnl=new JPanel();
		Wizard currentChampion=(Wizard)(gameView.getGame().getTournament().getWinner());
		JLabel playerName = new JLabel(currentChampion.getName());
		pnl.add(Box.createRigidArea(new Dimension(0, 20)));
		JLabel playerIcon = new JLabel();
		try {
			if (currentChampion instanceof GryffindorWizard) {
				playerIcon.setIcon(
						new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/GryffWizard.png"))));
			}
			if (currentChampion instanceof SlytherinWizard) {
				playerIcon.setIcon(
						new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/SlythWizard.png"))));
			}
			if (currentChampion instanceof HufflepuffWizard) {
				playerIcon.setIcon(
						new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/HuffleWizard.png"))));
			}
			if (currentChampion instanceof RavenclawWizard) {
				playerIcon.setIcon(
						new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/RavenWizard.png"))));
			}
		} catch (IOException e1) {
			System.exit(1);
		}
		playerIcon.setAlignmentX(CENTER_ALIGNMENT);
		pnl.add(playerIcon);
		pnl.add(Box.createRigidArea(new Dimension(0, 5)));
		playerName.setForeground(Color.BLACK);
		playerName.setAlignmentX(CENTER_ALIGNMENT);
		playerName.setFont(new Font("Algerian", Font.BOLD, 20));
		pnl.add(playerName);
		add(pnl);
		this.repaint();
		this.revalidate();
		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/trophy.jpeg")), 0, 0, getWidth(),
					getHeight(), this);
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
	

}
