package ai;

import player.Player;
import app.RPGGame;

public class FollowingMovementAI extends AbstractMovementAI{
	
	private Player follow;
	
	public FollowingMovementAI(Player player){
		follow = player;
	}

	@Override
	public double[] getVelocities() {
		double xSpeed = follow.getMaxXSpeed();
		double ySpeed = follow.getMaxYSpeed();
		return new double[] {xSpeed, ySpeed};
		
	}

}
