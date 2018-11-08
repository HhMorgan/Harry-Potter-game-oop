package harrypotter.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainMenuView extends JPanel {

	private GameView gameView;
	private JButton button, exitton;

	public MainMenuView() {
		setSize(new Dimension(1280, 800));
		button = new JButton();
		exitton = new JButton();
	}
	public void ini(){
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.switchView("Select");
			}
		});
		exitton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ImageIcon icon = new ImageIcon(getClass().getResource("/Tiles/button.jpg"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(150, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		exitton.setIcon(icon);
		exitton.setText("Exit");
		exitton.setFont(new Font("Algerian", Font.BOLD, 20));
		exitton.setHorizontalTextPosition(JButton.CENTER);
		exitton.setVerticalTextPosition(JButton.CENTER);
		exitton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				exitton.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 25));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				exitton.setFont(new Font("Algerian", Font.BOLD, 20));
			}
		});

		button.setIcon(icon);
		button.setText("Start");
		button.setFont(new Font("Algerian", Font.BOLD, 20));
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 25));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setFont(new Font("Algerian", Font.BOLD, 20));
			}
		});

		setLayout(null);
		button.setBounds(90, 620, 150, 50);
		exitton.setBounds(1050, 620, 150, 50);
		add(button);
		add(exitton);
		gameView.initChar();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/titlescreen.jpg")), 0, 0, getWidth(),
					getHeight(), null);
		} catch (Exception e) {
			System.exit(1);
		}
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
		ini();
	}

}
