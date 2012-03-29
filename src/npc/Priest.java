package npc;

import java.awt.image.BufferedImage;

import scenery.Portal;
import app.Main;

public class Priest extends NPC {

	public Priest(Main game, String name) {
		super(game, name);
		setCanDie(true);
	}

	public String getTalk() {
		if (game.getPlayer().hasItem("sword"))
			return getScript()[1];
		return getScript()[0];
	}

	public void die() {
		if (!isDead()) {
			BufferedImage death = game.getImage("resources/npc/priest_dead.png");
			getGroup().getActiveSprite().setImage(death);
			setDead(true);
			game.getLevel().addSceneryObject(new Portal(game, "resources/scenery/portal.gif"));
		}
	}

}
