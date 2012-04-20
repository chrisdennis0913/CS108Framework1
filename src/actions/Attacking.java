package actions;

import java.awt.event.KeyEvent;
import inventory.Weapon;
import player.Player;
import app.RPGGame;


public abstract class Attacking extends Action1 {
    public Attacking (Player player, int frames, String name, RPGGame game2) {
        super(player, frames, name);
        System.out.println("actions attacking constructer");
    }


    public boolean isActionable (String str) { //called from subclasses

        if (player.getEquipped() == null) {
            return false;
            
        }
        if (player.getEquipped().getCategory().equalsIgnoreCase("weapon")) {
            Weapon wp = (Weapon) player.getEquipped();
            if (wp.getWeaponType().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }


    protected void turn (boolean actionSwitch) {
        if (!actionSwitch) {
            player.getAction("grabbing").setActionable(false);
            player.getAction("talking").setActionable(false);
            player.getAction("walking").setActionable(false);
            player.getAction("standing").setActionable(false);
        }
        else {
            player.getAction("walking").setActionable(true);
            player.getAction("standing").setActionable(true);
        }
    }


    protected abstract void animate ();


    public void act (Attacking atk, RPGGame game2) {
        if (isActionable(game2) && !isActing() &&
            player.getGame().keyDown(KeyEvent.VK_SPACE)) {
            turn(false);
            atk.animate();
            setActing(true);
        }

        if (getActionTime() > 450) {
            turn(true);
            setActing(false);
            setActionable(false);
        }
    }
}
