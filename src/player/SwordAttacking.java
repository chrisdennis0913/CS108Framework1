package player;

import inventory.Weapon;

public class SwordAttacking extends Attacking {

    public SwordAttacking (PlayerActions pas) {
        super(pas, "resources/player/actions/swordattacking.json");
    }


    public boolean isEnabled () {
        if (super.isEnabled()) {
            Weapon wp= (Weapon) player.getEquipped();
            if (wp.getWeaponType()=="sword") {
                return true;
            }
        }
        return false;
    }

}
