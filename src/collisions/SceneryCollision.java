package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class SceneryCollision extends BasicCollisionGroup {

	public void collided(Sprite character, Sprite scenery) {
		double maxsep = scenery.getImage().getHeight() - character.getImage().getHeight()/3*2;
		double separation = character.getY() - scenery.getY();
		
		if (separation <= maxsep) {
			character.setX(character.getOldX());
			character.setY(character.getOldY());
		}
	}

}
