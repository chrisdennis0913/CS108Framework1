package player;

import inventory.MakeItems;
import inventory.Weapon;

public class BowAttacking extends Attacking {
    MakeItems MI = new MakeItems(getPlayer().getGame());
    
    public BowAttacking (Player player) {
        super(player, "resources/player/actions/bowattacking.json");
    }


    public boolean isEnabled () {
        if (super.isEnabled()) {
            Weapon wp= (Weapon) getPlayer().getEquipped();
            if (wp.getWeaponType().equalsIgnoreCase("BowAndArrows")) {
                return true;
            }
        }
        return false;
    }
}
