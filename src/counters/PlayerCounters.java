package counters;

import enemy.Enemy;
import app.Player;

public class PlayerCounters extends BaseCounters {

	Player player;
	HealthCounter health;
	
	public PlayerCounters(Player player) {
		this.player = player;
		health = new HealthCounter(player, 10);
		getCounters().add(health);
	}
	
	public void enemy(Enemy enemy) {
		for (BaseCounter counter : getCounters()) {
			PlayerCounter pcounter = (PlayerCounter) counter;
			pcounter.enemy(enemy);
		}
	}

}
