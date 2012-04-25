package player;

import java.util.ArrayList;
import inventory.ItemSub;
import actions.Direction;

public class Attacking extends PlayerAction {

    private PlayerDirections directions;
    private long timer = 0;

    public Attacking(Player player, String url) {
		super(player);
		setEnabled(false, true);
        directions =
            new PlayerDirections(getPlayer(), url);
    }


    public boolean isEnabled() {
		ItemSub equipped = getPlayer().getEquipped();
        if (equipped == null)
            return false;
        if (equipped.getCategory().equalsIgnoreCase("weapon"))
            return true;
        return false;
	}


    private void reset () {
        timer = 0;
    }


    public void update (long elapsed) {
    	if (isEnabled()) {
			if (!isActive()) {
				PlayerDirection direction = (PlayerDirection) directions
						.getDirections()[0];
				if (getActions().checkKeys(direction.getKeys()))
					for (Direction dir : directions) {
						if (getActions().getCurrentDirection().equals(
								dir.getCardinality())) {
							dir.changeCharacter(true, 150);
							setActive(true);
							getActions().enableNonAttack(false);
							break;
						}
					}
			}
			else {
				timer += elapsed;
				if (timer > 500) {
					reset();
					setActive(false);
					getActions().enableNonAttack(true);
				}
			}
		}
    }

}
