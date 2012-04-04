package collisions;

import app.RPGGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PortalCollision extends BasicCollisionGroup{
	private RPGGame game;
	
	public PortalCollision (RPGGame game) {
		this.game = game;
	}
	
	public void collided(Sprite character, Sprite scenery) {
		game.getField().removeCollisionGroup(this);
		scenery.setActive(false);
		game.getLevel().endLevel();
		relocate();
	}
	
	public void relocate() {
		game.getPlayer().getCharacter().setX(game.getBG().getWidth()/2);
		game.getPlayer().getCharacter().setY(game.getBG().getHeight()/2);
	}
}
