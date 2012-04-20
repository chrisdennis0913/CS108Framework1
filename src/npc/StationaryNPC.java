package npc;

import saving_loading.AttributeContainer;
import app.RPGGame;

public abstract class StationaryNPC extends NPC{

	public StationaryNPC(RPGGame game2, AttributeContainer ac) {
		super(game2, ac);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract String getTalk();

	@Override
	public abstract void die();
	
	public abstract void changeLocation(int[] location);
	

}
