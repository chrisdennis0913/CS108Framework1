package npc;

import saving_loading.AttributeContainer;
import app.RPGGame;

public abstract class MovingNPC extends NPC{
	
	int xVelocity;
	int yVelocity;

	public MovingNPC(RPGGame game2, AttributeContainer ac) {
		super(game2, ac);
	}
	
	//TODO: implement this with different algorithms
	public void getMovementStrategy(){};

	public int[] getVelocity(){
		return new int[] {xVelocity, yVelocity};
	}

	@Override
	public abstract String toJson();

	@Override
	public abstract String getTalk();

	@Override
	public abstract void die();

}
