package player;

import actions.Direction;
import actions.Directions.Cardinal;

public class Walking extends PlayerAction {
	private PlayerDirections directions;

	public Walking(PlayerActions pas) {
		super(pas);
		setEnabled(true);
		directions = new PlayerDirections(pas.getPlayer(),
				"resources/player/actions/walking.json");
	}

	public Cardinal getCurrentDirection() {
		return directions.getCurrentDirection();
	}

	public void update(long elapsed) {
		boolean checker = false;
		if (isEnabled())
			for (Direction dir : directions) {
				PlayerDirection direction = (PlayerDirection) dir;
				if (getActions().checkKeys(direction.getKeys())) {
					boolean isSame = directions.getCurrentDirection().equals(
							direction.getCardinality());
					keyPress((PlayerDirection) direction, isSame);
					checker = true;
					break;
				}
			}
		if (!checker)
			setActive(false);
	}

	public void keyPress(PlayerDirection direction, boolean isSame) {
		directions.setCurrentDirection(direction.getCardinality());
		System.out.println(direction.getCardinality());
		direction.changeCharacter(!isActive() || !isSame, 100);
		setActive(true);
	}
}