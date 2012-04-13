package player;

import counters.BaseCounter;
import counters.BaseCounters;
import enemy.IEnemy;

public class PlayerCounters extends BaseCounters {

	Player player;
	HealthCounter health;
	SpeedCounter speed;
	
	public PlayerCounters(Player player) {
		this.player = player;
		health = new HealthCounter(player, 10);
		getCounters().add(health);
	}
	
	public void reactToEnemy(IEnemy enemy) {
		for (BaseCounter counter : getCounters()) {
			PlayerCounter pcounter = (PlayerCounter) counter;
			pcounter.reactToEnemy(enemy);
		}
	}
	
	public BaseCounter getHealth() {
		return health;
	}
}
