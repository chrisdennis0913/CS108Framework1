package player;

import actions.Direction;

public class Standing extends PlayerAction {

	private PlayerDirections directions;

	public Standing(Player player) {
		super(player);
		setEnabled(true);
		directions = new PlayerDirections(getPlayer(),
				"resources/player/actions/standing.json");
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			if (!getActions().isWalking()) {
				if (!isActive()) {
					for (Direction dir : directions) {
						if (getActions().getCurrentDirection().equals(
								dir.getCardinality())) {
							dir.changeCharacter(true, 0);
							setActive(true);
							break;
						}
					}
				}
			} else {
				setActive(false);
			}
		}
	}
}
