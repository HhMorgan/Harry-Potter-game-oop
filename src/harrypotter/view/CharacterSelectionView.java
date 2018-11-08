package harrypotter.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;

import harrypotter.controller.Game.CellButton;

@SuppressWarnings("serial")
public class CharacterSelectionView extends JPanel {

	private GameView gameView;
	private String[] spells;
	private ArrayList<ArrayList<String>> chosenSpells;
	private ArrayList<String> name;
	private ArrayList<String> houses;

	public CharacterSelectionView(String[] spells) {
		this.spells = spells;
		chosenSpells = new ArrayList<ArrayList<String>>();
		name = new ArrayList<String>();
		houses = new ArrayList<String>();
		setLayout(null);
	}

	public void assign() {
		JButton buttonMain = new JButton();
		buttonMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				name.add("Noctis");
				name.add("Akira");
				name.add("Yuri");
				name.add("Rean");
				houses.add("Gryffindor");
				houses.add("Slytherin");
				houses.add("Ravenclaw");
				houses.add("Hufflepuff");
				int k = 0;
				for (int i = 0; i < 4; i++) {
					chosenSpells.add(new ArrayList<String>());
//					for (int j = 0; j < 3; j++) {
//						chosenSpells.get(i).add(spells[k]);
//						++k;
//					}
					chosenSpells.get(i).add(spells[1]);
					chosenSpells.get(i).add(spells[14]);
					chosenSpells.get(i).add(spells[20]);
				}
				gameView.initChamps(chosenSpells, name, houses);
				gameView.initFirstTask();
				gameView.switchView("Help");
				Timer timer = new Timer(5000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameView.switchView("first");
					}
				});
				timer.start();
				timer.setRepeats(false);
			}
		});

		ImageIcon icon = new ImageIcon(getClass().getResource("/Tiles/icon.png"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		buttonMain.setIcon(icon);
		buttonMain.setText("Proceed");
		buttonMain.setForeground(Color.white);
		buttonMain.setFont(new Font("Algerian", Font.BOLD, 20));
		buttonMain.setHorizontalTextPosition(JButton.CENTER);
		buttonMain.setVerticalTextPosition(JButton.BOTTOM);

		buttonMain.setOpaque(false);
		buttonMain.setContentAreaFilled(false);
		buttonMain.setBorderPainted(false);
		buttonMain.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				buttonMain.setSize(new Dimension(250, 230));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				buttonMain.setSize(new Dimension(250, 250));
			}
		});
		buttonMain.setBounds(511, 300, 250, 250);
		add(buttonMain);
	}

	public void initialize() {
		//assign();

		add(new StartGameButton());
		CharacterPanel[] players = new CharacterPanel[4]; // added
		// this to put the boxes in the right position in pixels
		for (int i = 0; i < 4; i++) {
			int x = 0;
			int y = 0;
			switch (i) {
			case 0:
				x = 60;
				y = 10;
				break;
			case 1:
				x = 700;
				y = 10;
				break;
			case 2:
				x = 60;
				y = 370;
				break;
			case 3:
				x = 700;
				y = 370;
				break;
			} // end of the coordinates
				// selector
			players[i] = new CharacterPanel();
			final CharacterPanel characterPanel = players[i];
			characterPanel.setOpaque(false);
			characterPanel.setLayout(new BoxLayout(players[i], BoxLayout.Y_AXIS));
			characterPanel.add(Box.createRigidArea(new Dimension(0, 35)));
			JLabel playerNo = new JLabel("Player " + (i + 1));
			playerNo.setForeground(Color.white);
			playerNo.setAlignmentX(CENTER_ALIGNMENT);
			characterPanel.add(playerNo);

			characterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

			JLabel playerIcon = new JLabel();
			try {
				playerIcon.setIcon(new ImageIcon(ImageIO.read(ImageReader.class.getResource("/Tiles/tmpWiz.png"))));
			} catch (IOException e1) {
				System.exit(1);
			}
			playerIcon.setAlignmentX(CENTER_ALIGNMENT);
			characterPanel.add(playerIcon);
			characterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

			JTextField playerName = new JTextField("Enter the champion's name");
			playerName.setMaximumSize(playerName.getPreferredSize());
			playerName.setAlignmentX(CENTER_ALIGNMENT);
			playerName.setHorizontalAlignment(0);
			playerName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!characterPanel.isFirstClick()) {
						playerName.setText("");
						characterPanel.setFirstClick(true);
					}
				}
			});
			playerName.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent arg0) {
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					if (!(playerName.getText().equals("Enter the champion's name")))
						name.add(playerName.getText());

				}
			});

			players[i].add(playerName);
			characterPanel.add(Box.createRigidArea(new Dimension(0, 20)));
			players[i].add(Box.createRigidArea(new Dimension(0, 10)));

			HouseSelectionBox houseBox = new HouseSelectionBox();
			houseBox.setAlignmentX(CENTER_ALIGNMENT);
			houseBox.setMaximumSize(playerName.getPreferredSize());
			players[i].add(houseBox);

			houseBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!(houseBox.getSelectedItem().toString().equals("Choose a house:")))
						houses.add(houseBox.getSelectedItem().toString());
				}
			});
			characterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

			SpellSelectionBox spellBox = new SpellSelectionBox(spells);
			spellBox.setAlignmentX(CENTER_ALIGNMENT);
			spellBox.setMaximumSize(playerName.getPreferredSize());
			players[i].add(spellBox);

			characterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

			SpellSelectionBox spellBox2 = new SpellSelectionBox(spells);
			spellBox2.setAlignmentX(CENTER_ALIGNMENT);
			spellBox2.setMaximumSize(playerName.getPreferredSize());
			players[i].add(spellBox2);

			characterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

			SpellSelectionBox spellBox3 = new SpellSelectionBox(spells);
			spellBox3.setAlignmentX(CENTER_ALIGNMENT);
			spellBox3.setMaximumSize(playerName.getPreferredSize());
			players[i].add(spellBox3);

			ArrayList<String> tmpSpell = new ArrayList<String>();
			ActionListener aActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!(spellBox.getSelectedItem().toString().equals("Choose a Spell :")))
						tmpSpell.add(spellBox.getSelectedItem().toString());
				}
			};
			spellBox.addActionListener(aActionListener);

			ActionListener bActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!(spellBox.getSelectedItem().toString().equals("Choose a Spell :")))
						tmpSpell.add(spellBox2.getSelectedItem().toString());
				}
			};
			spellBox2.addActionListener(bActionListener);

			ActionListener cActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!(spellBox.getSelectedItem().toString().equals("Choose a Spell :")))
						tmpSpell.add(spellBox3.getSelectedItem().toString());
				}
			};
			spellBox3.addActionListener(cActionListener);
			chosenSpells.add(tmpSpell);

			players[i].setBounds(x, y, 500, 500);
			add(players[i]);
		}

	}

	public void popup(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	class HouseSelectionBox extends JComboBox<String> {
		public HouseSelectionBox() {
			super(new String[] { "Choose a house:", "Gryffindor", "Slytherin", "Ravenclaw", "Hufflepuff" });
		}

	}

	class SpellSelectionBox extends JComboBox<String> {
		public SpellSelectionBox(String[] potions) {

			super(potions);

		}

	}

	class CharacterPanel extends JPanel {

		private boolean firstClick = false;

		public boolean isFirstClick() {
			return firstClick;
		}

		public void setFirstClick(boolean firstClick) {
			this.firstClick = firstClick;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/selectionScreen.jpg")), 0, 0, getWidth(),
					getHeight(), this);
		} catch (Exception e) {
			System.exit(0);
		}
	}

	class StartGameButton extends JButton {

		public StartGameButton() {
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Boolean s = false;
					Boolean s1 = false;
					Boolean s2 = false;
					Boolean s3 = false;
					Boolean s4 = false;
					Boolean n = false;
					Boolean h = false;
					boolean error = false;
					if (name.size() < 4 && !error) {
						error = true;
						if (name.size() < 3)
							popup("Write champions' names", "Name Error");
						else
							popup("Write champion's name", "Name Error");
					} else
						n = true;

					if (houses.size() < 4 && !error) {
						error = true;
						popup("Choose the remaining Houses", "Houses Error");
					} else
						h = true;
					for (int i = 0; i < chosenSpells.size(); i++) {
						if (chosenSpells.get(i).size() < 3 && !error) {
							popup("Choose " + (3 - chosenSpells.get(i).size()) + " Spells for player " + (i + 1),
									"Spells Error");
							error = true;
						} else {
							switch (i) {
							case 0:
								s1 = true;
								break;
							case 1:
								s2 = true;
								break;
							case 2:
								s3 = true;
								break;
							case 3:
								s4 = true;
								break;
							}
						}
					}
					if (s1 && s2 && s3 && s4)
						s = true;
					if (s && n && h) {
						gameView.initChamps(chosenSpells, name, houses);
						gameView.initFirstTask();
						gameView.switchView("Help");
						Timer timer = new Timer(5000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								gameView.switchView("first");
							}
						});
						timer.start();
						timer.setRepeats(false);
					
					}
				}
			});

			ImageIcon icon = new ImageIcon(getClass().getResource("/Tiles/icon.png"));
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);

			setIcon(icon);
			setText("Proceed");
			setForeground(Color.white);
			setFont(new Font("Algerian", Font.BOLD, 20));
			setHorizontalTextPosition(JButton.CENTER);
			setVerticalTextPosition(JButton.BOTTOM);

			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
			addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					setSize(new Dimension(250, 230));

				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					setSize(new Dimension(250, 250));
				}
			});
			setBounds(511, 300, 250, 250);
		}
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
		initialize();
	}

}
