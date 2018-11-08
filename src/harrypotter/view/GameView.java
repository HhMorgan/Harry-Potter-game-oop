package harrypotter.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import harrypotter.controller.Game;
import harrypotter.model.character.GryffindorWizard;

@SuppressWarnings("serial")
public class GameView extends JFrame {

	// Controller
	private Game game;
	// View
	private MainMenuView mainMenu;
	private CharacterSelectionView charSelect;
	// private TaskView taskView;
	private TasksView taskView;
	private Sound sound1;
	private Sound sound;
	// initialize champs
	private ArrayList<ArrayList<String>> chosenSpells;
	private ArrayList<String> Name;
	private ArrayList<String> Houses;
	private String[] spells;
	private GameOverScreen gameOverScreen;
	private HelpView helpView;
	private WinningScreen winningScreen;

	public GameView(String[] spells) {
		this.spells = spells;
		chosenSpells = new ArrayList<ArrayList<String>>();
		Name = new ArrayList<String>();
		Houses = new ArrayList<String>();
		gameOverScreen = new GameOverScreen();
		helpView = new HelpView();
		winningScreen=new WinningScreen();
		winningScreen.setGameView(this);
	}

	public void initMain() {
		mainMenu = new MainMenuView();
		mainMenu.setGameView(this);
		sound1 = new Sound();
		sound = new Sound();
		setTitle("Harry Potter and The Return of The Triwizard Tournament");
		setSize(1280, 800);
		ImageIcon tmp = new ImageIcon("res\\Tiles\\icon.png");
		setIconImage(tmp.getImage());
		setResizable(false);
		setFocusable(true);
		setLocationRelativeTo(null);
		add(mainMenu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		// the first song :) and how to initiate a song with a delay as the
		// second parameter for the song to loop
		sound.play("res/Music/title.wav", 122405);
		// sound1.play("res/Music/second.wav", 81000);

	}

	public void initChar() {
		charSelect = new CharacterSelectionView(spells);
		charSelect.setGameView(this);
	}

	public void initFirstTask() {
		getGame().createChamps(chosenSpells, Name, Houses);
		taskView = new TasksView();
		taskView.setGameView(this);
	}

	public void initChamps(ArrayList<ArrayList<String>> chosenSpells, ArrayList<String> Name,
			ArrayList<String> Houses) {
		this.chosenSpells = chosenSpells;
		this.Name = Name;
		this.Houses = Houses;

	}

	public void switchView(String s) {
		switch (s) {
		case "Select":
			remove(mainMenu);
			add(charSelect);
			// pack();
			// just add the next line when you want to end the music to add new
			// one :)
			// sound.stop();
			revalidate();
			repaint();
			break;
		case "Help":
			remove(charSelect);
			// iniTor();
			add(helpView);
			// pack();
			// just add the next line when you want to end the music to add new
			// one :)
			// sound1.stop();

			revalidate();
			repaint();
			break;
		case "first":
			remove(helpView);
			// iniTor();
			add(taskView);
			// pack();
			// just add the next line when you want to end the music to add new
			// one :)
			// sound1.stop();

			revalidate();
			repaint();
			// sound1.play("res/Music/second.wav", 81000);
			break;
		case "Over":
			remove(taskView);
			add(gameOverScreen);
			sound.stop();
			revalidate();
			repaint();
			Timer timer = new Timer(5000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(1);
				}
			});
			timer.start();
			timer.setRepeats(false);

			break;
		case "Win":
			remove(taskView);
			// iniTor();
			add(winningScreen);
			// pack();
			// just add the next line when you want to end the music to add new
			// one :)
			// sound1.stop();

			revalidate();
			repaint();
			Timer timer1 = new Timer(5000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(1);
				}
			});
			timer1.start();
			timer1.setRepeats(false);
			// sound1.play("res/Music/second.wav", 81000);
			break;
		}

	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
		initMain();
	}

	public MainMenuView getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenuView mainMenu) {
		this.mainMenu = mainMenu;
	}

	public CharacterSelectionView getCharSelect() {
		return charSelect;
	}

	public TasksView getTaskView() {
		return taskView;
	}

}
