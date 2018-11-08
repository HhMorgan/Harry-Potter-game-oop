package harrypotter.view;

import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TasksView extends JPanel{
	private GameView gameView;
	public TasksView(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	public JPanel generateMap(){
			return gameView.getGame().generateMapFirstTask();
	}
	public JPanel generateActions(){
		return gameView.getGame().generateControlsFirstTask();
	}
	public JPanel generateAllChampsInfo(){
			return gameView.getGame().generateChampsInfo();
	}
	public void initialization() {
		gameView.getGame().initTournament();
		add(generateAllChampsInfo());
		add(generateMap());
		add(generateActions());

	}
	
	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
		initialization();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(ImageReader.class.getResource("/Tiles/bgG.jpg")), 0, 0, getWidth(),
					getHeight(), this);
		} catch (Exception e) {
			System.exit(0);
		}
	}
}
