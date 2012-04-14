package ai;

import player.Player;
import app.RPGGame;

public abstract class AbstractBehaviorModifier {

	RPGGame game;
	Player player;
	
	public AbstractBehaviorModifier(RPGGame game){
		this.game = game;
		this.player = game.getPlayer();
	}
	
	public abstract void setUp(long elapsedTime);
	public abstract boolean unsetUp(long elapsedTime);
	
	public void deregister(){
		player.deregisterBehaviorModifier(this);
	}
	
}
