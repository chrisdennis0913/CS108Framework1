package enemy;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import app.RPGGame;

public class ShootingAttack extends AbstractVectorAttack{

	Timer timer;

	public ShootingAttack(RPGGame game, IEnemy enemy, String name) {
		super(game, enemy, name);
		timer = new Timer(1000);
		vectorSpeedX = 0.1;
		vectorSpeedY = 0.1;
	}

	@Override
	public void launchVector(double x, double y, double speedX, double speedY) {
		new ShotVector(game, this, x, y, speedX, speedY);
	}

	@Override
	public void onCollision(Sprite vector, Sprite player) {
		vector.setActive(false);
		game.getPlayer().getPCs().reactToEnemy(enemy);
	}

	@Override
	public boolean isAttackValid(long elapsedTime) {
		return timer.action(elapsedTime);
		// return true;
	}

	@Override
	public void performAttack(long elapsedTime) {
		if (isAttackValid(elapsedTime))
			launchVector();
	}

}
