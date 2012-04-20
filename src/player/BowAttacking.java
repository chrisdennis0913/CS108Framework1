package player;

import inventory.MakeItems;
import inventory.Weapon;

public class BowAttacking extends Attacking {
    MakeItems MI = new MakeItems(this.player.getGame());
    public BowAttacking (PlayerActions pas) {
        super(pas, "resources/player/actions/bowattacking.json");
    }


    public boolean isEnabled () {
        if (super.isEnabled()) {
            Weapon wp= (Weapon) player.getEquipped();
            if (wp.getWeaponType().equalsIgnoreCase("BowAndArrows")) {
                return true;
            }
        }
        return false;
    }
}
