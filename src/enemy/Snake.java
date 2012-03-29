package enemy;

import java.awt.image.BufferedImage;

import scenery.Portal;
import app.Main;

import com.golden.gamedev.engine.timer.SystemTimer;

public class Snake extends Enemy {

	private SystemTimer timer = new SystemTimer();
	private boolean acting = false;
	private long actionStartTime = 0;

	public Snake(Main game, String name) {
		super(game, name);
		timer.setFPS(100);
		timer.startTimer();
		setActing(false);
		health = 5;
	}

	public void setActing(boolean acting) {
		this.acting = acting;
		actionStartTime = timer.getTime();
	}

	public boolean isAction() {
		return acting;
	}

	public long getActionTime() {
		return timer.getTime() - actionStartTime;
	}

	public void die() {
		BufferedImage death = game.getImage("resources/npc/snake_dead.gif");
		getGroup().getActiveSprite().setImage(death);
		setDead(true);
		game.getLevel().addSceneryObject(
				new Portal(game, "resources/scenery/portal.gif"));
	}

	public void act(long elapsed) {
		if (!isDead())
			getSprite().moveTo(elapsed, game.getPlayer().getCharacter().getX(),
				game.getPlayer().getCharacter().getY(), 0.1);
	}

}
