package collisions;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class PlayerBoundaryCollision extends CollisionBounds {

	public PlayerBoundaryCollision(Background bg) {
		super(bg);
	}

	public void collided(Sprite character) {
		character.setSpeed(character.getHorizontalSpeed() * (double) -1,
				character.getVerticalSpeed() * (double) -1);
		character.setX(character.getOldX());
		character.setY(character.getOldY());
	}

}
