package counters;

import enemy.Enemy;
import app.Player;

public abstract class PlayerCounter extends GraphicalCounter{

	private Player player;
	
	public PlayerCounter(Player player, int count) {
		super(count);
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public abstract void enemy(Enemy enemy);

}
