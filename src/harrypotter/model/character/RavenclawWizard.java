package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;
import harrypotter.controller.Game;

public class RavenclawWizard extends Wizard implements Champion {

	public RavenclawWizard(String name) {
		super(name, 750, 700);
	}

	public void useTrait() throws InCooldownException {

		if (getListener() != null) {
			Object traitObject = getListener().onRavenclawTrait();
			getGame().ravenclawTrait(traitObject);
		}

	}
}
