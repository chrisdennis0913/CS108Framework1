package enemy;

import app.RPGGame;

import com.golden.gamedev.object.AnimatedSprite;

public abstract class AbstractAttackVector {

	protected static RPGGame game;
	private AnimatedSprite vectorSprite;
	public AbstractAttackVector(RPGGame game, AbstractVectorAttack owner, double x, double y, double speedX, double speedY){
		AbstractAttackVector.game = game;
		vectorSprite = loadVectorSprite();
		vectorSprite.setLocation(x, y);
		vectorSprite.setSpeed(speedX, speedY);
		owner.registerVectorSprite(vectorSprite);
	}
	
	public abstract AnimatedSprite loadVectorSprite();
}
