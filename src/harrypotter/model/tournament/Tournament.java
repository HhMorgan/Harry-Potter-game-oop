package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tournament implements TaskListener {

	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private FirstTask firstTask;
	private SecondTask secondTask;
	private ThirdTask thirdTask;
	//M4
	private int taskIndicator;
	private Champion winner;
	//
	public Tournament() throws IOException {

		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");

	}

	public ArrayList<Champion> getChampions() {
		return champions;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public SecondTask getSecondTask() {
		return secondTask;
	}

	public ThirdTask getThirdTask() {
		return thirdTask;
	}

	public void addChampion(Champion c) {
		champions.add(c);
	}

	private void loadSpells(String filePath) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {

			String[] content = line.split(",");
			switch (content[0]) {

			case "DMG":
				spells.add(new DamagingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "HEL":
				spells.add(new HealingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "REL":
				spells.add(new RelocatingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			}

			line = br.readLine();

		}

		br.close();

	}

	public void beginTournament() throws IOException {
		//M4
		taskIndicator=1;
		//
		//onFinishingFirstTask(champions);
		//
		firstTask = new FirstTask(champions);
		firstTask.setListener(this);

	}

	public void onFinishingFirstTask(ArrayList<Champion> winners)
			throws IOException {
		
		if (!winners.isEmpty()) {
			//M4
			taskIndicator=2;
			//
			secondTask = new SecondTask(winners);
			secondTask.setListener(this);
		}
		else taskIndicator=0;

	}

	public void onFinishingSecondTask(ArrayList<Champion> winners)
			throws IOException {
		
		if (!winners.isEmpty()) {
			//M4
			taskIndicator=3;
			//
			thirdTask = new ThirdTask(winners);
			thirdTask.setListener(this);
		}
		else taskIndicator=0;
	}

	public void onFinishingThirdTask(Champion winner) {
		taskIndicator=4;
		this.setWinner(winner);
		// TODO: M4

	}

	
	//M4
	
	public int getTaskIndicator() {
		return taskIndicator;
	}

	public Champion getWinner() {
		return winner;
	}

	public void setWinner(Champion winner) {
		this.winner = winner;
	}
	
}
