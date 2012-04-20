package player;

import inventory.Weapon;
import actions.Direction;


public class Attacking extends PlayerAction {

    private PlayerDirections directions;
    private long timer = 0;
    protected Player player;

    public Attacking (PlayerActions pas, String url) {
        super(pas);
        setEnabled(false);
        directions =
            new PlayerDirections(pas.getPlayer(),
                                 url);
        System.out.println("player's attacking constructer");
    }


    public boolean isEnabled () {
        player = getActions().getPlayer();
        if (player.getEquipped()==null){
            return false;
        }
        System.out.println("player's attacking");
        if (player.getEquipped().getCategory().equals("weapon")){
            return true;
        }
        return false;
    }


    @SuppressWarnings("unused")
    private void reset () {
        timer = 0;
    }


    public void update (long elapsed) {
        System.out.println(elapsed);
        if (isEnabled()) {
            if (!isActive()) {
                PlayerDirection direction =
                    (PlayerDirection) directions.getDirections()[0];
                timer += elapsed;
                Weapon currentWeapon=(Weapon) player.getEquipped();
                if (getActions().checkKeys(direction.getKeys())) for (Direction dir : directions) {
                    if (getActions().getCurrentDirection()
                                    .equals(dir.getCardinality())) {
                        dir.changeCharacter(true, 0);
                        setActive(true);
                        break;
                    }
                }
            }
        }
    }
}
