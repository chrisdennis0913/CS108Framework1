package player;

import counters.GraphicalCounter;
import enemy.Enemy;
import enemy.IEnemy;

public abstract class PlayerCounter extends GraphicalCounter{

	private Player player;
	
	public PlayerCounter(Player player, int count) {
		super(count);
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public abstract void enemy(IEnemy enemy);

}
