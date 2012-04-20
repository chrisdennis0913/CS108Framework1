package enemy;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;

import collisions.EnemyCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public interface IEnemy {

	public abstract void add(int[] location, int layer);

	public abstract Sprite getSprite();

	public abstract int getHealth();

	@Deprecated
	public abstract void reduceHealth();

	public abstract void addToHealth(int delta);

	public abstract void generate();

	public abstract void setDead(boolean dead);

	public abstract boolean isDead();

	public abstract BufferedImage getImage();

	public abstract void act(long elapsed);

	public abstract void die();

	public abstract void setCollision();

	public abstract EnemyCollision getCollision();

	public abstract SpriteGroup getGroup();

	public abstract void attack(AbstractAttack a, long elapsedTime);

	public abstract HashMap<String, AbstractAttack> getSpontaneousAttacks();

	public abstract void onCollision();

	public abstract HashMap<String, AbstractAttack> getReactiveAttacks();

	public abstract double getMaxXSpeed();

	public abstract double getMaxYSpeed();

}