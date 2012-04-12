package enemy;

import app.RPGGame;

import com.golden.gamedev.object.AnimatedSprite;

public abstract class AbstractAttackVector {

	protected static RPGGame game;
	private AnimatedSprite vectorSprite;
	private AbstractAttackWithVector owner;
	
	public AbstractAttackVector(RPGGame game, AbstractAttackWithVector owner, double x, double y, double speedX, double speedY){
		this.game = game;
		this.owner = owner;
		vectorSprite = loadVectorSprite();
		vectorSprite.setLocation(x, y);
		vectorSprite.setSpeed(speedX, speedY);
		owner.registerVectorSprite(vectorSprite);
	}
	
	public abstract AnimatedSprite loadVectorSprite();
}
