package enemy;

import java.awt.image.BufferedImage;

import app.RPGGame;

import com.golden.gamedev.object.AnimatedSprite;

public class ShotVector extends AbstractAttackVector {

	private static final String imagePath = "resources/enemy/projectile.png";
	
	public ShotVector(RPGGame game, AbstractAttackWithVector owner, double x, double y, double speedX, double speedY) {
		super(game, owner,x,y, speedX, speedY);
	}

	@Override
	public AnimatedSprite loadVectorSprite() {
		BufferedImage[] image = new BufferedImage[] { game
				.getImage(imagePath) };
		return new AnimatedSprite(image);
	}

}
