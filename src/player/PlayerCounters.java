package player;

import counters.BaseCounter;
import counters.BaseCounters;
import enemy.Enemy;
import enemy.IEnemy;

public class PlayerCounters extends BaseCounters {

	Player player;
	HealthCounter health;
	
	public PlayerCounters(Player player) {
		this.player = player;
		health = new HealthCounter(player, 10);
		getCounters().add(health);
	}
	
	public void enemy(IEnemy enemy) {
		for (BaseCounter counter : getCounters()) {
			PlayerCounter pcounter = (PlayerCounter) counter;
			pcounter.enemy(enemy);
		}
	}

	public int getHealth(){
		return health.getCount();
	}
	
	public void addToHealth(int delta){
		health.increase(delta);
	}
}
