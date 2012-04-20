package actions;

import com.golden.gamedev.object.AnimatedSprite;
import player.Player;
import app.RPGGame;

public class SwordAttacking extends Attacking {

    public SwordAttacking (Player player, int frames, String name, RPGGame game2) {
        super(player, frames, name, game2);
    }
    
    public boolean isActionable (RPGGame game2) {
        return super.isActionable("sword");
    }
    
    public void act (RPGGame game2) {
        super.act(this, game2);
    }
    protected void animate() {
        AnimatedSprite character = player.getCharacter();
        Walking walking = (Walking) player.getAction("walking");

        if (walking.getDirection().equals(Walking.cardinal.RIGHT)) character.setImages(images.get("right"));
        else if (walking.getDirection().equals(Walking.cardinal.LEFT)) character.setImages(images.get("left"));
        if (walking.getDirection().equals(Walking.cardinal.DOWN)) character.setImages(images.get("front"));
        else if (walking.getDirection().equals(Walking.cardinal.UP)) character.setImages(images.get("back"));

        character.getAnimationTimer().setDelay(150);
        character.setLoopAnim(true);
        character.setAnimate(true);
        character.setAnimationFrame(0, 2);
        character.setSpeed(0, 0);
    }

}
