package enemy;

import collisions.AttackVectorCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import app.RPGGame;

public abstract class AbstractAttackWithVector extends AbstractAttack {
	
	private SpriteGroup vectors;
	
	protected double vectorSpeedX = 0;
	protected double vectorSpeedY = 0;

	public AbstractAttackWithVector(RPGGame game, IEnemy enemy) {
		super(game, enemy, null);
		vectors = new SpriteGroup(getClass().getName());
		game.getField().addGroup(vectors);
		initCollisions();
	}

	public final void launchVector(){
		launchVector(getAttackerX(),getAttackerY(), vectorSpeedX, vectorSpeedY);
	}
	
	public abstract void launchVector(double x, double y, double speedX, double speedY);

	public void registerVectorSprite(Sprite s){
		vectors.add(s);
	}
	
	public void initCollisions(){
		game.getField().addCollisionGroup(vectors, target.getGroup(),
				new AttackVectorCollision(this));
	}
	
	public abstract void onCollision(Sprite vector, Sprite player);
	
	public void setSpeed(double speedX, double speedY){
		vectorSpeedX = speedX;
		vectorSpeedY = speedY;
	}
}
