package enemy;

import app.Player;
import app.RPGGame;

public abstract class AbstractAttack {
	private static RPGGame game;
	private String name;
	private Player target;
	
	public AbstractAttack (RPGGame game, String name){
		AbstractAttack.game = game;
		this.name = name;
		setPlayerAsTarget();
	}
	
	public void setPlayerAsTarget(){
		this.target = game.getPlayer();
	}
	
	public void performAttack(){
		
	}
	
	
}
