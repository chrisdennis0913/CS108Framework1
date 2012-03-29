package collisions;

import app.Main;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PortalCollision extends BasicCollisionGroup{
	private Main game;
	
	public PortalCollision (Main game) {
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
