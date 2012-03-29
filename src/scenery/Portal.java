package scenery;

import app.Main;
import collisions.PortalCollision;

public class Portal extends Scenery {

	public Portal(Main game, String imageURL) {
		super(game, imageURL);
	}
	
	public void setCollision() {
		PortalCollision collision = new PortalCollision(game);
		collision.pixelPerfectCollision = true;
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}

}
