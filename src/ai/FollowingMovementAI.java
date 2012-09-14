package ai;

import player.Player;

public class FollowingMovementAI extends AbstractMovementAI {

    public FollowingMovementAI (Player player) {
    }

    @Override
    public double[] getVelocities () {
        /*
         * double xSpeed = follow.getMaxXSpeed(); double ySpeed =
         * follow.getMaxYSpeed(); return new double[] { xSpeed, ySpeed };
         */

        return new double[] { 0, 0 };

    }
}
