package inventory;

import player.PlayerDirection;
import com.golden.gamedev.object.AnimatedSprite;

public class Sword extends Weapon {
    public void animateCharacter(PlayerDirection pd, boolean animate, int delay) {
        AnimatedSprite character = ItemSub.game.getPlayer().getCharacter();
        character.setSpeed(pd.getHorSpeed(), pd.getVerSpeed());
        if (animate) {
            character.setImages(pd.getImages());
            if (pd.frameCount() == 1) {
                character.setLoopAnim(false);
                character.setAnimate(false);
            } else {
                character.getAnimationTimer().setDelay(delay);
                character.setLoopAnim(true);
                character.setAnimate(true);
                character.setAnimationFrame(0, pd.frameCount() - 1);
            }
        }
        ItemSub.game.getBG().setToCenter(character);
    }

}
