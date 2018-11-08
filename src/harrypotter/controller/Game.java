package harrypotter.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.*;
import harrypotter.model.world.*;
import harrypotter.view.*;

public class Game implements ActionListener {
	private GameView gui;
	private Tournament tournament;
	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private ArrayList<CellButton> cellButtons;
	private AllChampsPanel allChampsPanel;
	private SidePanel sidePanel;
	private Task currentTask;
	private int oldTaskIndicator;

	// M4
	private ArrayList<Point> markedCells;
	private ArrayList<Point> merCells;
	private boolean red;
	private boolean blue;
	private boolean cancelled;
	private boolean s, st;

	public Game() {
		cellButtons = new ArrayList<CellButton>();
		try {
			tournament = new Tournament();
		} catch (IOException e) {
			System.exit(1);
		}
		spells = tournament.getSpells();
		champions = new ArrayList<Champion>();
		gui = new GameView(loadStringSpells());
		gui.setGame(this);

	}

	public String[] loadStringSpells() {
		// will load the spells here
		ArrayList<Spell> spells = tournament.getSpells();
		String[] ret = new String[spells.size() + 1];
		ret[0] = "Choose a Spell :";
		for (int i = 0; i < spells.size(); i++)
			ret[i + 1] = spells.get(i).getName();
		return ret;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public Tournament tournament() {
		return tournament;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public ArrayList<Champion> getChampions() {
		return champions;
	}

	public void createChamps(ArrayList<ArrayList<String>> chosenSpells, ArrayList<String> Name,
			ArrayList<String> Houses) {
		for (int i = 0; i < 4; i++) {
			if (Houses.size() > 0) {
				switch (Houses.get(i)) {
				case "Gryffindor":
					tournament.getChampions().add(new GryffindorWizard(Name.get(i)));
					ArrayList<String> tmp = chosenSpells.get(i);
					for (int j = 0; j < 3; j++) {
						int index = 0;
						for (int k = 0; k < spells.size(); k++) {
							if (spells.get(k).getName().equals(tmp.get(j))) {
								index = k;
							}
						}
						if (spells.get(index) instanceof HealingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new HealingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((HealingSpell) spells.get(index)).getHealingAmount()));
						else if (spells.get(index) instanceof DamagingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new DamagingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((DamagingSpell) spells.get(index)).getDamageAmount()));
						else if (spells.get(index) instanceof RelocatingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells()).add(new RelocatingSpell(
									spells.get(index).getName(), spells.get(index).getCost(),
									spells.get(index).getCoolDown(), ((RelocatingSpell) spells.get(index)).getRange()));
					}
					break;
				case "Slytherin":
					tournament.getChampions().add((new SlytherinWizard(Name.get(i))));
					ArrayList<String> tmp1 = chosenSpells.get(i);
					for (int j = 0; j < 3; j++) {
						int index = 0;
						for (int k = 0; k < spells.size(); k++) {
							if (spells.get(k).getName().equals(tmp1.get(j))) {
								index = k;
							}
						}
						if (spells.get(index) instanceof HealingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new HealingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((HealingSpell) spells.get(index)).getHealingAmount()));
						else if (spells.get(index) instanceof DamagingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new DamagingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((DamagingSpell) spells.get(index)).getDamageAmount()));
						else if (spells.get(index) instanceof RelocatingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells()).add(new RelocatingSpell(
									spells.get(index).getName(), spells.get(index).getCost(),
									spells.get(index).getCoolDown(), ((RelocatingSpell) spells.get(index)).getRange()));
					}
					break;
				case "Ravenclaw":
					tournament.getChampions().add(new RavenclawWizard(Name.get(i)));
					ArrayList<String> tmp2 = chosenSpells.get(i);
					for (int j = 0; j < 3; j++) {
						int index = 0;
						for (int k = 0; k < spells.size(); k++) {
							if (spells.get(k).getName().equals(tmp2.get(j))) {
								index = k;
							}
						}
						if (spells.get(index) instanceof HealingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new HealingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((HealingSpell) spells.get(index)).getHealingAmount()));
						else if (spells.get(index) instanceof DamagingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new DamagingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((DamagingSpell) spells.get(index)).getDamageAmount()));
						else if (spells.get(index) instanceof RelocatingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells()).add(new RelocatingSpell(
									spells.get(index).getName(), spells.get(index).getCost(),
									spells.get(index).getCoolDown(), ((RelocatingSpell) spells.get(index)).getRange()));
					}
					break;
				case "Hufflepuff":
					tournament.getChampions().add(new HufflepuffWizard(Name.get(i)));
					ArrayList<String> tmp3 = chosenSpells.get(i);
					for (int j = 0; j < 3; j++) {
						int index = 0;
						for (int k = 0; k < spells.size(); k++) {
							if (spells.get(k).getName().equals(tmp3.get(j))) {
								index = k;
							}
						}
						if (spells.get(index) instanceof HealingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new HealingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((HealingSpell) spells.get(index)).getHealingAmount()));
						else if (spells.get(index) instanceof DamagingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells())
									.add(new DamagingSpell(spells.get(index).getName(), spells.get(index).getCost(),
											spells.get(index).getCoolDown(),
											((DamagingSpell) spells.get(index)).getDamageAmount()));
						else if (spells.get(index) instanceof RelocatingSpell)
							(((Wizard) tournament.getChampions().get(i)).getSpells()).add(new RelocatingSpell(
									spells.get(index).getName(), spells.get(index).getCost(),
									spells.get(index).getCoolDown(), ((RelocatingSpell) spells.get(index)).getRange()));
					}
					break;
				}
			}
			champions = tournament.getChampions();
		}
	}

	public JPanel generateMapFirstTask() {
		// markedCells = tournament.getFirstTask().getMarkedCells();
		sidePanel = new SidePanel(this);

		Cell[][] map = currentTask.getMap();
		JPanel mapPNL = new JPanel();
		mapPNL.setLayout(new GridLayout(10, 10, 0, 0));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				cellButtons.add(new CellButton(map[i][j], i, j, 88, 78));
				mapPNL.add(cellButtons.get((i * 10) + j));
			}
		}
		mapPNL.setPreferredSize(new Dimension(800, 750));
		return mapPNL;
	}

	public void updateCellButton() {
		Cell[][] map = currentTask.getMap();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] != cellButtons.get((i * 10) + j).getCell()) {
					cellButtons.get((i * 10) + j).setCell(map[i][j]);
					cellButtons.get((i * 10) + j).reset();
				}
				if (currentTask instanceof FirstTask && currentTask.getCurrentChamp() instanceof RavenclawWizard
						&& currentTask.isTraitActivated()) {
					// cellButton.get((i * 10) + j).reset();
					Point point = new Point(i, j);

					for (Point markedCell : markedCells) {
						String path = "";
						Cell cell = cellButtons.get((i * 10) + j).getCell();
						if (markedCell.equals(point)) {
							if (i == 4 && j == 4)
								path = "/Tiles/MarkedCellEgg.png";
							else if (cell instanceof ObstacleCell)
								path = "/Tiles/MarkedCellObstacle.png";
							else if (cell instanceof ChampionCell) {
								if (((ChampionCell) cell).getChamp() instanceof GryffindorWizard)
									path = "/Tiles/MarkedCellGryf.png";
								else if (((ChampionCell) cell).getChamp() instanceof SlytherinWizard)
									path = "/Tiles/MarkedCellSlyth.png";
								else if (((ChampionCell) cell).getChamp() instanceof RavenclawWizard)
									path = "/Tiles/MarkedCellRaven.png";
								else if (((ChampionCell) cell).getChamp() instanceof HufflepuffWizard)
									path = "/Tiles/MarkedCellHuffle.png";
							} else
								path = "/Tiles/MarkedCellEmpty.png";
							ImageIcon icon = new ImageIcon(getClass().getResource(path));
							Image img = icon.getImage();
							Image newimg = img.getScaledInstance(cellButtons.get((i * 10) + j).width,
									cellButtons.get((i * 10) + j).height, java.awt.Image.SCALE_SMOOTH);
							icon = new ImageIcon(newimg);
							cellButtons.get((i * 10) + j).setIcon(icon);
						}

					}

				}
			}

		}

	}

	public void updateCellButtonNewTask() {
		Cell[][] map = currentTask.getMap();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				cellButtons.get((i * 10) + j).setCell(map[i][j]);
				cellButtons.get((i * 10) + j).reset();
			}
		}
	}

	public void update() {
		if (tournament.getTaskIndicator() == 0) {
			gui.switchView("Over");
			return;
		}
		if (tournament.getTaskIndicator() == 4) {
			gui.switchView("Win");
			//hzsg
		}
		// System.out.println(oldTaskIndicator+"//"+tournament.getTaskIndicator()
		// );
		if (oldTaskIndicator != tournament.getTaskIndicator() && tournament.getTaskIndicator() == 2) {

			currentTask = tournament.getSecondTask();

			currentTask.setGame(this);
			allChampsPanel.generateAllChampsPanel();
			UpdateTaskChampsInfo();
			updateCellButtonNewTask();
			sidePanel.panelUpdate();
			oldTaskIndicator = tournament.getTaskIndicator();
		}
		if (oldTaskIndicator != tournament.getTaskIndicator() && tournament.getTaskIndicator() == 3) {
			currentTask = tournament.getThirdTask();
			currentTask.setGame(this);
			allChampsPanel.generateAllChampsPanel();
			UpdateTaskChampsInfo();
			updateCellButtonNewTask();
			sidePanel.panelUpdate();
			oldTaskIndicator = tournament.getTaskIndicator();
		}
		updateCellButton();
		allChampsPanel.generateAllChampsPanel();
		sidePanel.panelUpdate();
		if (tournament.getTaskIndicator() == 1 && red) {
			fire(markedCells);
			red = false;
		}
		if (tournament.getTaskIndicator() == 2 && blue) {
			merperson(merCells);
			blue = false;
		}
		if (tournament.getTaskIndicator() == 1)
			markedCells = tournament.getFirstTask().getMarkedCells();

	}

	public void initTournament() {
		try {
			tournament.beginTournament();

			FirstTask firstTask = tournament.getFirstTask();
			firstTask.setGame(this);
			for (Champion champion : firstTask.getChampions()) {
				((Wizard) champion).setGame(this);
			}

			markedCells = tournament.getFirstTask().getMarkedCells();
			currentTask = tournament.getFirstTask();
			oldTaskIndicator = tournament.getTaskIndicator();

			gui.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent key) {
				}

				@Override
				public void keyPressed(KeyEvent key) {
					try {
						if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
							System.out.println("ESCAPE");
							cancelled = true;
						} else if (key.getKeyCode() == KeyEvent.VK_W) {
							currentTask.moveForward();
						} else if (key.getKeyCode() == KeyEvent.VK_A)
							currentTask.moveLeft();
						else if (key.getKeyCode() == KeyEvent.VK_S)
							currentTask.moveBackward();
						else if (key.getKeyCode() == KeyEvent.VK_D)
							currentTask.moveRight();
						else if (key.getKeyCode() == KeyEvent.VK_Z) {
							Wizard currentWizard = (Wizard) currentTask.getCurrentChamp();
							Spell spell = currentWizard.getSpells().get(0);
							if (spell instanceof HealingSpell)
								currentTask.castHealingSpell((HealingSpell) spell);
							else if (spell instanceof DamagingSpell) {
								Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD,
										Direction.LEFT, Direction.RIGHT };
								Direction d = (Direction) JOptionPane.showInputDialog(gui, "Choose Target Direction",
										"Damaging Spell", JOptionPane.QUESTION_MESSAGE, null, directions,
										directions[0]);
								currentTask.castDamagingSpell((DamagingSpell) spell, d);
							} else if (spell instanceof RelocatingSpell) {
								Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD,
										Direction.LEFT, Direction.RIGHT };
								Direction d1 = (Direction) JOptionPane.showInputDialog(gui, "Choose Target Direction",
										"Relocating Spell 1", JOptionPane.QUESTION_MESSAGE, null, directions,
										directions[0]);
								Direction d2 = (Direction) JOptionPane.showInputDialog(gui,
										"Choose Relocation Direction", "Relocating Spell 2",
										JOptionPane.QUESTION_MESSAGE, null, directions, directions[0]);
								String stringRange = JOptionPane.showInputDialog(gui, "Enter casting range:",
										"Relocating Spell Range", JOptionPane.WARNING_MESSAGE);
								int r = Integer.parseInt(stringRange);
								currentTask.castRelocatingSpell((RelocatingSpell) spell, d1, d2, r);
							}
						} else if (key.getKeyCode() == KeyEvent.VK_X) {
							Wizard currentWizard = (Wizard) currentTask.getCurrentChamp();
							Spell spell = currentWizard.getSpells().get(1);
							if (spell instanceof HealingSpell)
								currentTask.castHealingSpell((HealingSpell) spell);
							else if (spell instanceof DamagingSpell) {
								Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD,
										Direction.LEFT, Direction.RIGHT };
								Direction d = (Direction) JOptionPane.showInputDialog(gui, "Choose Target Direction",
										"Damaging Spell", JOptionPane.QUESTION_MESSAGE, null, directions,
										directions[0]);
								currentTask.castDamagingSpell((DamagingSpell) spell, d);
							} else if (spell instanceof RelocatingSpell) {
								Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD,
										Direction.LEFT, Direction.RIGHT };
								Direction d1 = (Direction) JOptionPane.showInputDialog(gui, "Choose Target Direction",
										"Relocating Spell 1", JOptionPane.QUESTION_MESSAGE, null, directions,
										directions[0]);
								Direction d2 = (Direction) JOptionPane.showInputDialog(gui,
										"Choose Relocation Direction", "Relocating Spell 2",
										JOptionPane.QUESTION_MESSAGE, null, directions, directions[0]);
								String stringRange = JOptionPane.showInputDialog(gui, "Enter casting range:",
										"Relocating Spell Range", JOptionPane.WARNING_MESSAGE);
								int r = Integer.parseInt(stringRange);
								currentTask.castRelocatingSpell((RelocatingSpell) spell, d1, d2, r);
							}
						} else if (key.getKeyCode() == KeyEvent.VK_C) {
							Wizard currentWizard = (Wizard) currentTask.getCurrentChamp();
							Spell spell = currentWizard.getSpells().get(2);
							if (spell instanceof HealingSpell)
								currentTask.castHealingSpell((HealingSpell) spell);
							else if (spell instanceof DamagingSpell) {
								Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD,
										Direction.LEFT, Direction.RIGHT };
								Direction d = (Direction) JOptionPane.showInputDialog(gui, "Choose Target Direction",
										"Damaging Spell", JOptionPane.QUESTION_MESSAGE, null, directions,
										directions[0]);
								currentTask.castDamagingSpell((DamagingSpell) spell, d);
							} else if (spell instanceof RelocatingSpell) {
								Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD,
										Direction.LEFT, Direction.RIGHT };
								Direction d1 = (Direction) JOptionPane.showInputDialog(gui, "Choose Target Direction",
										"Relocating Spell 1", JOptionPane.QUESTION_MESSAGE, null, directions,
										directions[0]);
								Direction d2 = (Direction) JOptionPane.showInputDialog(gui,
										"Choose Relocation Direction", "Relocating Spell 2",
										JOptionPane.QUESTION_MESSAGE, null, directions, directions[0]);
								String stringRange = JOptionPane.showInputDialog(gui, "Enter casting range:",
										"Relocating Spell Range", JOptionPane.WARNING_MESSAGE);
								int r = Integer.parseInt(stringRange);
								currentTask.castRelocatingSpell((RelocatingSpell) spell, d1, d2, r);
							}
						} else if (key.getKeyCode() == KeyEvent.VK_Q) {
							String[] stringPotions = getChampPoitions(currentTask.getCurrentChamp());
							String chosenPotion = (String) JOptionPane.showInputDialog(gui, "Choose a potion:",
									"Potion", JOptionPane.QUESTION_MESSAGE, null, stringPotions, stringPotions[0]);
							if (chosenPotion.equals("Potions")) {
								JOptionPane.showMessageDialog(null, "You need to selected a Potion to use it!",
										"No Potion Selected", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							ArrayList<Collectible> inventory = ((Wizard) currentTask.getCurrentChamp()).getInventory();
							for (Collectible collectible : inventory) {
								Potion potion = (Potion) collectible;
								if (potion.getName().equals(chosenPotion)) {
									currentTask.usePotion(potion);
									break;
								}
							}
						} else if (key.getKeyCode() == KeyEvent.VK_E) {
							currentTask.getCurrentChamp().useTrait();
						}

						update();

					} catch (InvalidTargetCellException e) {
						if (!e.getMessage().equals("Cannot move to WallCell"))
							JOptionPane.showMessageDialog(null, e.getMessage(), "Warning!",
									JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Warning!",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			});
		} catch (

		IOException e) {
			System.exit(1);
		}
	}

	public void fire(ArrayList<Point> markedCells) {
		for (int k = 0; k < markedCells.size(); k++) {
			Point tmp = markedCells.get(k);
			// System.out.println(tmp.x+"//"+tmp.y);
			CellButton fired = getCellButton().get((tmp.x * 10) + tmp.y);
			if (fired.getCell() instanceof ObstacleCell)
				fired.changeImage("/Tiles/fireCellCollectible.png");
			else if (fired.getCell() instanceof ChampionCell) {
				ChampionCell tmpChamp = (ChampionCell) fired.getCell();
				if (tmpChamp.getChamp() instanceof GryffindorWizard) {
					fired.changeImage("/Tiles/fireCellGryf.png");
				}
				if (tmpChamp.getChamp() instanceof SlytherinWizard) {
					fired.changeImage("/Tiles/fireCellSlyth.png");
				}
				if (tmpChamp.getChamp() instanceof HufflepuffWizard) {
					fired.changeImage("/Tiles/fireCellHuffle.png");
				}
				if (tmpChamp.getChamp() instanceof RavenclawWizard) {
					fired.changeImage("/Tiles/fireCellRaven.png");
				}
			}

			else
				fired.changeImage("/Tiles/fireCell.png");

		}
		gui.getTaskView().revalidate();
		gui.getTaskView().repaint();

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int k = 0; k < markedCells.size(); k++) {
					Point tmp = markedCells.get(k);
					// System.out.println(tmp.x+"//"+tmp.y);
					CellButton fired = getCellButton().get((tmp.x * 10) + tmp.y);
					fired.reset();

				}
				gui.getTaskView().revalidate();
				gui.getTaskView().repaint();
				// update();

			}
		});
		timer.start();
		timer.setRepeats(false);
		gui.getTaskView().revalidate();
		gui.getTaskView().repaint();

	}

	public void merperson(ArrayList<Point> merpersonPoints) {

		for (int k = 0; k < merpersonPoints.size(); k++) {
			Point tmp = merpersonPoints.get(k);
			// System.out.println(tmp.x+"//"+tmp.y);
			CellButton fired = getCellButton().get((tmp.x * 10) + tmp.y);
			fired.changeImage("/Tiles/Merperson1.png");
			int damage = ((Merperson) ((ObstacleCell) fired.getCell()).getObstacle()).getDamage();
			JOptionPane.showMessageDialog(null, "A merperson dealt " + damage + " points of damage to you.",
					"Merperson Encountered!", JOptionPane.INFORMATION_MESSAGE);
		}
		gui.getTaskView().revalidate();
		gui.getTaskView().repaint();
		// System.out.println("1");

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int k = 0; k < merpersonPoints.size(); k++) {
					Point tmp = merpersonPoints.get(k);
					// System.out.println(tmp.x+"//"+tmp.y);
					CellButton fired = getCellButton().get((tmp.x * 10) + tmp.y);
					fired.reset();
					// System.out.println("2");
				}
				// update();

			}
		});
		timer.start();
		timer.setRepeats(false);
		gui.getTaskView().revalidate();
		gui.getTaskView().repaint();

	}

	@SuppressWarnings("unchecked")
	public void ravenclawTrait(Object traitObject) {
		if (currentTask instanceof FirstTask)
			markedCells = (ArrayList<Point>) traitObject;
		else if (currentTask instanceof SecondTask) {
			ArrayList<Direction> hint = (ArrayList<Direction>) traitObject;
			String mssg = "Your treasure is ";
			for (Direction d : hint)
				mssg += d + " ";
			JOptionPane.showMessageDialog(null, mssg, "Ravenclaw Trait", JOptionPane.INFORMATION_MESSAGE);
		} else if (currentTask instanceof ThirdTask) {
			ArrayList<Direction> hint = (ArrayList<Direction>) traitObject;
			String mssg = "The cup is ";
			for (Direction d : hint)
				mssg += d + " ";
			JOptionPane.showMessageDialog(null, mssg, "Ravenclaw Trait", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public Direction slythTrait() {
		Direction[] directions = new Direction[] { Direction.FORWARD, Direction.BACKWARD, Direction.LEFT,
				Direction.RIGHT };
		return (Direction) JOptionPane.showInputDialog(gui, "Choose Trait Direction", "Slytherin Trait",
				JOptionPane.QUESTION_MESSAGE, null, directions, directions[0]);
	}

	public void encounterCollectible(Collectible collectible) {
		Potion potion = (Potion) collectible;
		JOptionPane.showMessageDialog(null,
				"You've acquired a potion!\nName: " + potion.getName() + "\nAmount: " + potion.getAmount(),
				"Collectible", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("serial")
	public class CellButton extends JButton {
		private Cell cell;
		private int width;
		private int height;
		private int x, y;
		private int taskIndicator = tournament.getTaskIndicator();

		public CellButton(Cell cell, int x, int y, int width, int height) {
			this.cell = cell;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			reset();
			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);

		}

		public void resetStretch(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void reset() {
			String path = "";
			if (cell instanceof ChampionCell) {
				if (((ChampionCell) cell).getChamp() instanceof GryffindorWizard) {
					if (currentTask instanceof FirstTask)
						path = "/Tiles/GryfSand.png";
					if (currentTask instanceof SecondTask)
						path = "/Tiles/GryfWater.png";
					if (currentTask instanceof ThirdTask)
						path = "/Tiles/GryfGrass.png";

				}
				if (((ChampionCell) cell).getChamp() instanceof HufflepuffWizard) {
					if (currentTask instanceof FirstTask)
						path = "/Tiles/HuffleSand.png";
					if (currentTask instanceof SecondTask)
						path = "/Tiles/HuffleWater.png";
					if (currentTask instanceof ThirdTask)
						path = "/Tiles/HuffleGrass.png";

				}
				if (((ChampionCell) cell).getChamp() instanceof SlytherinWizard) {
					if (currentTask instanceof FirstTask)
						path = "/Tiles/SlythSand.png";
					if (currentTask instanceof SecondTask)
						path = "/Tiles/SlythWater.png";
					if (currentTask instanceof ThirdTask)
						path = "/Tiles/SlythGrass.png";
				}
				if (((ChampionCell) cell).getChamp() instanceof RavenclawWizard) {
					if (currentTask instanceof FirstTask)
						path = "/Tiles/RavenSand.png";
					if (currentTask instanceof SecondTask)
						path = "/Tiles/RavenWater.png";
					if (currentTask instanceof ThirdTask)
						path = "/Tiles/RavenGrass.png";

				}
			} else if (cell instanceof TreasureCell) {
				path = "/Tiles/waterTile.png";
			} else if (cell instanceof CollectibleCell) {
				if (currentTask instanceof FirstTask)
					path = "/Tiles/sand.png";
				if (currentTask instanceof SecondTask)
					path = "/Tiles/waterTile.png";
				if (currentTask instanceof ThirdTask)
					path = "/Tiles/grass.png";
			} else if (cell instanceof ObstacleCell) {
				if (((ObstacleCell) cell).getObstacle() instanceof PhysicalObstacle) {
					if (currentTask instanceof FirstTask)
						path = "/Tiles/Obstacle.png";
					else if (currentTask instanceof ThirdTask)
						path = "/Tiles/GrassCollectible.png";
					Obstacle obstacle = ((ObstacleCell) cell).getObstacle();
					setToolTipText("HP: " + obstacle.getHp());
				} else if (((ObstacleCell) cell).getObstacle() instanceof Merperson) {
					path = "/Tiles/Merperson0.png";
					Merperson mer = (Merperson) (((ObstacleCell) cell).getObstacle());
					setToolTipText("HP: " + mer.getHp() + "\n Damage: " + mer.getDamage());
				}
			} else if (cell instanceof CupCell) {
				path = "/Tiles/grass.png";
			} else if (cell instanceof WallCell) {
				path = "/Tiles/wall.png";
			} else if (x == 4 && y == 4 && cell instanceof EmptyCell && currentTask instanceof FirstTask) {
				path = "/Tiles/sand eggs.png";
			} else if (cell instanceof EmptyCell) {
				// System.out.println(taskIndicator);
				if (currentTask instanceof FirstTask)
					path = "/Tiles/sand.png";
				if (currentTask instanceof SecondTask)
					path = "/Tiles/waterTile.png";
				if (currentTask instanceof ThirdTask)
					path = "/Tiles/grass.png";

			}

			ImageIcon icon = new ImageIcon(getClass().getResource(path));
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			setIcon(icon);
			taskIndicator = tournament.getTaskIndicator();
			// System.out.println(taskIndicator);
		}

		public void changeImage(String path) {
			ImageIcon icon = new ImageIcon(getClass().getResource(path));
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			setIcon(icon);
		}

		public Cell getCell() {
			return cell;
		}

		public void setCell(Cell cell) {
			this.cell = cell;
		}

	}

	@SuppressWarnings("serial")
	class DMGSpellButton extends JButton {
		private DamagingSpell spell;

		public DMGSpellButton(DamagingSpell spell) {

			this.spell = spell;

		}

		public DamagingSpell getSpell() {
			return spell;
		}

		public void setSpell(DamagingSpell spell) {
			this.spell = spell;
		}
	}

	// class DirectionSelectionBox extends JComboBox<String> {
	// public DirectionSelectionBox() {
	//
	// super(new String[] { "Choose Direction", "Forward", "Backward", "Left",
	// "Right" });
	//
	// }
	//
	// }

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public String[] getChampPoitions(Champion champ) {
		ArrayList<Collectible> potions = ((Wizard) champ).getInventory();
		String[] PotionStringArray = new String[potions.size() + 1];
		PotionStringArray[0] = "Potions";
		for (int i = 0; i < potions.size(); i++) {
			PotionStringArray[i + 1] = potions.get(i).getName();
		}
		return PotionStringArray;
	}

	@SuppressWarnings("serial")
	class SidePanel extends JPanel {
		private Game game;
		private Wizard currentChampion;
		private JLabel playerName;
		private JLabel playerIcon;
		private JLabel playerHealth;
		private JLabel playerIp;
		private JLabel playerAllowedMoves;
		private JLabel playertraitActivated;
		private JLabel TraitCoolLabel;

		private ArrayList<Spell> currentChampSpells;

		private ArrayList<JLabel> SpellInfo;
		private ArrayList<JLabel> SpellTypeInfo;
		private ArrayList<JLabel> SpellDamageAmountLabel;
		private ArrayList<JLabel> SpellCostInfo;
		private ArrayList<JLabel> SpellCoolDown;
		private PotionSelectionBox potionBox;

		public SidePanel(Game game) {
			this.game = game;
			// JPanel characterPanel = new JPanel();
			// characterInformation(characterPanel);
			// setLayout(null);
			// setPreferredSize(new Dimension(200, 800));
			//
			// characterPanel.setBounds(0, 10, 280, 790);
			// add(characterPanel);\characterInformation(characterPanel);
			characterInformation(this);
			setPreferredSize(new Dimension(200, 800));
			setMaximumSize(getPreferredSize());

		}

		public void panelUpdate() {
			currentChampion = (Wizard) currentTask.getCurrentChamp();
			playerName.setText(currentChampion.getName());
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
			int health = currentChampion.getHp();
			String healthString = "Health : " + String.valueOf(health);
			playerHealth.setText(healthString);

			int ip = currentChampion.getIp();
			String Iptring = "Ip : " + String.valueOf(ip);
			playerIp.setText(Iptring);

			int AllowedMoves = currentTask.getAllowedMoves();
			String AllowedMovestring = "Allowed Moves : " + String.valueOf(AllowedMoves);
			playerAllowedMoves.setText(AllowedMovestring);

			boolean traitActivated = currentTask.isTraitActivated();
			String traitActivatedString = "Not Activated";
			if (traitActivated)
				traitActivatedString = "Activated";
			AllowedMovestring = "Trait : " + String.valueOf(traitActivatedString);
			playertraitActivated.setText(AllowedMovestring);

			int TraitCool = currentChampion.getTraitCooldown();
			String TraitCoolString = "TraitCoolDown : " + String.valueOf(TraitCool);
			TraitCoolLabel.setText(TraitCoolString);

			currentChampSpells = currentChampion.getSpells();

			for (int i = 0; i < currentChampSpells.size(); i++) {
				Spell tmpSpell = currentChampSpells.get(i);
				String spellInfoString = tmpSpell.getName();
				SpellInfo.get(i).setText(spellInfoString);

				if (tmpSpell instanceof DamagingSpell) {
					String SpellType = "Damaging Spell";
					SpellTypeInfo.get(i).setText(SpellType);

					String SpellDamageAmount = "Damage: " + ((DamagingSpell) tmpSpell).getDamageAmount();
					SpellDamageAmountLabel.get(i).setText(SpellDamageAmount);

					String SpellCost = "Cost: " + ((DamagingSpell) tmpSpell).getCost();
					SpellCostInfo.get(i).setText(SpellCost);

					String SpellCool = " coolDown: " + ((DamagingSpell) tmpSpell).getCoolDown();
					SpellCoolDown.get(i).setText(SpellCool);

				} else if (tmpSpell instanceof HealingSpell) {
					String SpellType = "Healing Spell";
					SpellTypeInfo.get(i).setText(SpellType);

					String SpellDamageAmount = "Healing amount: " + ((HealingSpell) tmpSpell).getHealingAmount();
					SpellDamageAmountLabel.get(i).setText(SpellDamageAmount);

					String SpellCost = "Cost: " + ((HealingSpell) tmpSpell).getCost();
					SpellCostInfo.get(i).setText(SpellCost);

					String SpellCool = " coolDown: " + ((HealingSpell) tmpSpell).getCoolDown();
					SpellCoolDown.get(i).setText(SpellCool);
				}

				else {
					String SpellType = "Relocating Spell";
					SpellTypeInfo.get(i).setText(SpellType);

					String SpellDamageAmount = "Relocating range: " + ((RelocatingSpell) tmpSpell).getRange();
					SpellDamageAmountLabel.get(i).setText(SpellDamageAmount);

					String SpellCost = "Cost: " + ((RelocatingSpell) tmpSpell).getCost();
					SpellCostInfo.get(i).setText(SpellCost);

					String SpellCool = " coolDown: " + ((RelocatingSpell) tmpSpell).getCoolDown();
					SpellCoolDown.get(i).setText(SpellCool);

				}
			}
			potionBox.setFocusable(false);
			potionBox.update(getChampPoitions(game.getTournament().getFirstTask().getCurrentChamp()));
			potionBox.setFocusable(false);
		}

		public void characterInformation(SidePanel pnl) {
			pnl.removeAll();
			pnl.setOpaque(false);
			pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
			currentChampion = (Wizard) currentTask.getCurrentChamp();
			playerName = new JLabel(currentChampion.getName());
			pnl.add(Box.createRigidArea(new Dimension(0, 20)));
			playerIcon = new JLabel();
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

			int health = currentChampion.getHp();
			String healthString = "Health : " + String.valueOf(health);
			playerHealth = new JLabel(healthString);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playerHealth.setForeground(Color.BLACK);
			playerHealth.setAlignmentX(CENTER_ALIGNMENT);
			playerHealth.setFont(new Font("Algerian", Font.BOLD, 20));
			pnl.add(playerHealth);

			int ip = currentChampion.getIp();
			String Iptring = "Ip : " + String.valueOf(ip);
			playerIp = new JLabel(Iptring);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playerIp.setForeground(Color.BLACK);
			playerIp.setAlignmentX(CENTER_ALIGNMENT);
			playerIp.setFont(new Font("Algerian", Font.BOLD, 20));
			pnl.add(playerIp);

			int AllowedMoves = currentTask.getAllowedMoves();
			String AllowedMovestring = "Allowed Moves : " + String.valueOf(AllowedMoves);
			playerAllowedMoves = new JLabel(AllowedMovestring);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playerAllowedMoves.setForeground(Color.BLACK);
			playerAllowedMoves.setAlignmentX(CENTER_ALIGNMENT);
			playerAllowedMoves.setFont(new Font("Algerian", Font.BOLD, 18));
			pnl.add(playerAllowedMoves);

			boolean traitActivated = currentTask.isTraitActivated();
			String traitActivatedString = "Not Activated";
			if (traitActivated)
				traitActivatedString = "Activated";
			AllowedMovestring = "Trait : " + String.valueOf(traitActivatedString);
			playertraitActivated = new JLabel(AllowedMovestring);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			playertraitActivated.setForeground(Color.BLACK);
			playertraitActivated.setAlignmentX(CENTER_ALIGNMENT);
			playertraitActivated.setFont(new Font("Algerian", Font.BOLD, 15));
			pnl.add(playertraitActivated);

			int TraitCool = currentChampion.getTraitCooldown();
			String TraitCoolString = "TraitCoolDown : " + String.valueOf(TraitCool);
			TraitCoolLabel = new JLabel(TraitCoolString);
			pnl.add(Box.createRigidArea(new Dimension(0, 5)));
			TraitCoolLabel.setForeground(Color.BLACK);
			TraitCoolLabel.setAlignmentX(CENTER_ALIGNMENT);
			TraitCoolLabel.setFont(new Font("Algerian", Font.BOLD, 15));
			pnl.add(TraitCoolLabel);

			currentChampSpells = currentChampion.getSpells();

			pnl.add(Box.createRigidArea(new Dimension(0, 10)));
			SpellInfo = new ArrayList<JLabel>();
			SpellTypeInfo = new ArrayList<JLabel>();
			SpellDamageAmountLabel = new ArrayList<JLabel>();
			SpellCostInfo = new ArrayList<JLabel>();
			SpellCoolDown = new ArrayList<JLabel>();

			for (int i = 0; i < currentChampSpells.size(); i++) {
				Spell tmpSpell = currentChampSpells.get(i);
				String spellInfoString = tmpSpell.getName();
				SpellInfo.add(new JLabel(spellInfoString));
				pnl.add(Box.createRigidArea(new Dimension(0, 5)));
				SpellInfo.get(i).setForeground(Color.BLACK);
				SpellInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
				SpellInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
				pnl.add(SpellInfo.get(i));

				if (tmpSpell instanceof DamagingSpell) {
					String SpellType = "Damaging Spell";
					SpellTypeInfo.add(new JLabel(SpellType));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellTypeInfo.get(i).setForeground(Color.BLACK);
					SpellTypeInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellTypeInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellTypeInfo.get(i));

					String SpellDamageAmount = "Damage: " + ((DamagingSpell) tmpSpell).getDamageAmount();
					SpellDamageAmountLabel.add(new JLabel(SpellDamageAmount));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellDamageAmountLabel.get(i).setForeground(Color.BLACK);
					SpellDamageAmountLabel.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellDamageAmountLabel.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellDamageAmountLabel.get(i));

					String SpellCost = "Cost: " + ((DamagingSpell) tmpSpell).getCost();
					SpellCostInfo.add(new JLabel(SpellCost));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellCostInfo.get(i).setForeground(Color.BLACK);
					SpellCostInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellCostInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellCostInfo.get(i));

					String SpellCool = " coolDown: " + ((DamagingSpell) tmpSpell).getCoolDown();
					SpellCoolDown.add(new JLabel(SpellCool));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellCoolDown.get(i).setForeground(Color.BLACK);
					SpellCoolDown.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellCoolDown.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellCoolDown.get(i));
				} else if (tmpSpell instanceof HealingSpell) {
					String SpellType = "Healing Spell";
					SpellTypeInfo.add(new JLabel(SpellType));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellTypeInfo.get(i).setForeground(Color.BLACK);
					SpellTypeInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellTypeInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellTypeInfo.get(i));

					String SpellDamageAmount = "Healing amount: " + ((HealingSpell) tmpSpell).getHealingAmount();
					SpellDamageAmountLabel.add(new JLabel(SpellDamageAmount));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellDamageAmountLabel.get(i).setForeground(Color.BLACK);
					SpellDamageAmountLabel.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellDamageAmountLabel.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellDamageAmountLabel.get(i));

					String SpellCost = "Cost: " + ((HealingSpell) tmpSpell).getCost();
					SpellCostInfo.add(new JLabel(SpellCost));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellCostInfo.get(i).setForeground(Color.BLACK);
					SpellCostInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellCostInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellCostInfo.get(i));

					String SpellCool = " coolDown: " + ((HealingSpell) tmpSpell).getCoolDown();
					SpellCoolDown.add(new JLabel(SpellCool));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellCoolDown.get(i).setForeground(Color.BLACK);
					SpellCoolDown.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellCoolDown.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellCoolDown.get(i));
				}

				else {
					String SpellType = "Relocating Spell";
					SpellTypeInfo.add(new JLabel(SpellType));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellTypeInfo.get(i).setForeground(Color.BLACK);
					SpellTypeInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellTypeInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellTypeInfo.get(i));

					String SpellDamageAmount = "Relocating range: " + ((RelocatingSpell) tmpSpell).getRange();
					SpellDamageAmountLabel.add(new JLabel(SpellDamageAmount));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellDamageAmountLabel.get(i).setForeground(Color.BLACK);
					SpellDamageAmountLabel.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellDamageAmountLabel.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellDamageAmountLabel.get(i));

					String SpellCost = "Cost: " + ((RelocatingSpell) tmpSpell).getCost();
					SpellCostInfo.add(new JLabel(SpellCost));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellCostInfo.get(i).setForeground(Color.BLACK);
					SpellCostInfo.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellCostInfo.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellCostInfo.get(i));

					String SpellCool = " coolDown: " + ((RelocatingSpell) tmpSpell).getCoolDown();
					SpellCoolDown.add(new JLabel(SpellCool));
					pnl.add(Box.createRigidArea(new Dimension(0, 5)));
					SpellCoolDown.get(i).setForeground(Color.BLACK);
					SpellCoolDown.get(i).setAlignmentX(CENTER_ALIGNMENT);
					SpellCoolDown.get(i).setFont(new Font("Algerian", Font.BOLD, 15));
					pnl.add(SpellCoolDown.get(i));
				}
				pnl.add(Box.createRigidArea(new Dimension(0, 10)));
			}
			pnl.add(Box.createRigidArea(new Dimension(0, 15)));
			potionBox = new PotionSelectionBox(
					getChampPoitions((game.getTournament().getFirstTask().getCurrentChamp())));
			potionBox.setMaximumSize(potionBox.getPreferredSize());
			potionBox.setFocusable(false);
			pnl.add(potionBox);
		}

		class PotionSelectionBox extends JComboBox<String> {
			public PotionSelectionBox(String[] potions) {
				super(potions);
			}

			public void update(String[] potions) {
				removeAllItems();
				for (String s : potions) {
					addItem(s);
				}
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/BoxSpell.jpg")), 0, 0, getWidth(),
						getHeight(), this);
			} catch (Exception e) {
				System.exit(0);
			}
		}

		public ArrayList<Point> getMarkedCells() {
			return game.tournament().getFirstTask().getMarkedCells();
		}

	}

	public GameView gui() {
		return gui;
	}

	public void setGui(GameView gui) {
		this.gui = gui;
	}

	public void setCellButton(ArrayList<CellButton> cellButton) {
		this.cellButtons = cellButton;
	}

	public ArrayList<CellButton> getCellButton() {
		return cellButtons;
	}

	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public boolean isBlue() {
		return blue;
	}

	public void setBlue(boolean blue) {
		this.blue = blue;
	}

	public JPanel generateControlsFirstTask() {

		return sidePanel;
	}

	public JPanel generateChampsInfo() {
		allChampsPanel = new AllChampsPanel();
		allChampsPanel.setGame(this);
		allChampsPanel.CharacterInformationInit(allChampsPanel);
		return allChampsPanel;
	}

	public void UpdateTaskChampsInfo() {
		// allChampsPanel = new AllChampsPanel();
		// allChampsPanel.setGame(this);
		allChampsPanel.CharacterInformationInit(allChampsPanel);
		gui.getTaskView().revalidate();
		gui.getTaskView().repaint();
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public ArrayList<Point> getMerCells() {
		return merCells;
	}

	public void setMerCells(ArrayList<Point> merCells) {
		this.merCells = merCells;
	}

}