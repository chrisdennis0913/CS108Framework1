package player;

import inventory.Weapon;

public class SwordAttacking extends Attacking {

    public SwordAttacking (Player player) {
        super(player, "resources/player/actions/swordattacking.json");
    }


    public boolean isEnabled () {
        if (super.isEnabled()) {
            Weapon wp = (Weapon) getPlayer().getEquipped();
            
            if (wp.getWeaponType()=="sword") {
                return true;
            }
        }
        return false;
    }

}
