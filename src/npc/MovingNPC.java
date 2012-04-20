package npc;

import java.util.Arrays;

import saving_loading.AttributeContainer;
import ai.AbstractMovementAI;
import app.RPGGame;

public abstract class MovingNPC extends NPC{
	
	RPGGame game;
	int[] velocity;
	AbstractMovementAI motion;

	public MovingNPC(RPGGame game2, AttributeContainer ac) {
		super(game2, ac);
	}

	public int[] getVelocity(){
		return Arrays.copyOf(velocity, velocity.length);
	}
	
	public void update(){
		
	}

	@Override
	public abstract String toJson();

	@Override
	public abstract String getTalk();

	@Override
	public abstract void die();

}
