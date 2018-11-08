package harrypotter.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Task;

@SuppressWarnings("serial")
public class AllChampsPanel extends JPanel {
	private Game game;
	private ArrayList<Champion> champions;
	private JPanel pnl;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	public void generateAllChampsPanel(){
		pnl.removeAll();
		for(int i=0;i<champions.size();i++){
			Wizard currentChampion = (Wizard) champions.get(i);
			JLabel playerName = new JLabel(currentChampion.getName());
			pnl.add(Box.createRigidArea(new Dimension(0, 15)));
			JLabel playerIcon = new JLabel();
			if(currentChampion.getHp()>0 && game.getCurrentTask().getChampions().contains(currentChampion)){
			try {
				if (currentChampion instanceof GryffindorWizard) {
					playerIcon
							.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/GryffWizard.png"))));
				}
				if (currentChampion instanceof SlytherinWizard) {
					playerIcon
							.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/SlythWizard.png"))));
				}
				if (currentChampion instanceof HufflepuffWizard) {
					playerIcon
							.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/HuffleWizard.png"))));
				}
				if (currentChampion instanceof RavenclawWizard) {
					playerIcon
							.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/RavenWizard.png"))));
				}
			} catch (IOException e1) {
				System.exit(1);
			}
			}
			else if(currentChampion.getHp()==0){
				try {
					if (currentChampion instanceof GryffindorWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/GryffDead.png"))));
					}
					if (currentChampion instanceof SlytherinWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/SlythDead.png"))));
					}
					if (currentChampion instanceof HufflepuffWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/HuffleDead.png"))));
					}
					if (currentChampion instanceof RavenclawWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/RavenDead.png"))));
					}
				} catch (IOException e1) {
					System.exit(1);
				}
			}
			else{
				try {
					if (currentChampion instanceof GryffindorWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/GryffWin.png"))));
					}
					if (currentChampion instanceof SlytherinWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/SlythWin.png"))));
					}
					if (currentChampion instanceof HufflepuffWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/HuffleWin.png"))));
					}
					if (currentChampion instanceof RavenclawWizard) {
						playerIcon
								.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/RavenWin.png"))));
					}
				} catch (IOException e1) {
					System.exit(1);
				}
				
			}
			playerIcon.setAlignmentX(pnl.CENTER_ALIGNMENT);
			pnl.add(playerIcon);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playerName.setForeground(Color.BLACK);
			playerName.setAlignmentX(pnl.CENTER_ALIGNMENT);
			playerName.setFont(new Font("Algerian", Font.BOLD, 20));
			pnl.add(playerName);

			int health = currentChampion.getHp();
			String healthString = "Health : " + String.valueOf(health);
			JLabel playerHealth = new JLabel(healthString);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playerHealth.setForeground(Color.BLACK);
			playerHealth.setAlignmentX(pnl.CENTER_ALIGNMENT);
			playerHealth.setFont(new Font("Algerian", Font.BOLD, 20));
			pnl.add(playerHealth);

			int ip = currentChampion.getIp();
			String Iptring = "Ip : " + String.valueOf(ip);
			JLabel playerIp = new JLabel(Iptring);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playerIp.setForeground(Color.BLACK);
			playerIp.setAlignmentX(CENTER_ALIGNMENT);
			playerIp.setFont(new Font("Algerian", Font.BOLD, 20));
			pnl.add(playerIp);
			}
			setPreferredSize(new Dimension(200, 800));
			this.revalidate();
			this.repaint();
	}
	public void CharacterInformationInit(JPanel pnl) {
		this.pnl=pnl;
		pnl.removeAll();
		pnl.setOpaque(false);
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		if(game.getCurrentTask() instanceof FirstTask){
		champions=(ArrayList<Champion>) game.getCurrentTask().getChampions().clone();
		}
		//System.out.println(champions.size());
		for(int i=0;i<champions.size();i++){
		Wizard currentChampion = (Wizard) champions.get(i);
		JLabel playerName = new JLabel(currentChampion.getName());
		pnl.add(Box.createRigidArea(new Dimension(0, 15)));
		JLabel playerIcon = new JLabel();
		try {
			if (currentChampion instanceof GryffindorWizard) {
				playerIcon
						.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/GryffWizard.png"))));
			}
			if (currentChampion instanceof SlytherinWizard) {
				playerIcon
						.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/SlythWizard.png"))));
			}
			if (currentChampion instanceof HufflepuffWizard) {
				playerIcon
						.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/HuffleWizard.png"))));
			}
			if (currentChampion instanceof RavenclawWizard) {
				playerIcon
						.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/RavenWizard.png"))));
			}
		} catch (IOException e1) {
			System.exit(1);
		}
		playerIcon.setAlignmentX(pnl.CENTER_ALIGNMENT);
		pnl.add(playerIcon);
		pnl.add(Box.createRigidArea(new Dimension(0, 5)));
		playerName.setForeground(Color.BLACK);
		playerName.setAlignmentX(pnl.CENTER_ALIGNMENT);
		playerName.setFont(new Font("Algerian", Font.BOLD, 20));
		pnl.add(playerName);

		int health = currentChampion.getHp();
		String healthString = "Health : " + String.valueOf(health);
		JLabel playerHealth = new JLabel(healthString);
		pnl.add(Box.createRigidArea(new Dimension(0, 5)));
		playerHealth.setForeground(Color.BLACK);
		playerHealth.setAlignmentX(pnl.CENTER_ALIGNMENT);
		playerHealth.setFont(new Font("Algerian", Font.BOLD, 20));
		pnl.add(playerHealth);

		int ip = currentChampion.getIp();
		String Iptring = "Ip : " + String.valueOf(ip);
		JLabel playerIp = new JLabel(Iptring);
		pnl.add(Box.createRigidArea(new Dimension(0, 5)));
		playerIp.setForeground(Color.BLACK);
		playerIp.setAlignmentX(CENTER_ALIGNMENT);
		playerIp.setFont(new Font("Algerian", Font.BOLD, 20));
		pnl.add(playerIp);
		}
		setPreferredSize(new Dimension(200, 800));
		setMaximumSize(getPreferredSize());
		revalidate();
		repaint();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/Panel.png")), 0, 0, getWidth(), getHeight(),this);
		} catch (Exception e) {
			System.exit(0);
		}
	}

}
