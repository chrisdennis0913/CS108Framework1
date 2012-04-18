package actions;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import player.Player;
import app.RPGGame;


public class BowAttacking extends Attacking {

    public BowAttacking (Player player, int frames, String name, RPGGame game2) {
        super(player, frames, name, game2);
    }


    public boolean isActionable (RPGGame game2) {
        return super.isActionable("BowAndArrows");
    }


    public void act (RPGGame game2) {
        super.act(this, game2);
    }


    protected void animate () {
        AnimatedSprite character = player.getCharacter();
        Walking walking = (Walking) player.getAction("walking");

        if (walking.getDirection().equals(Walking.cardinal.RIGHT)) {
            character.setImages(images.get("right"));
            Sprite flameArrow =
                new Sprite(this.player.getGame()
                                      .getImage("resources/items/flameArrow.gif"));
            flameArrow.setLayer(0);
            this.player.projectiles.add(flameArrow);
            flameArrow.setLocation(player.getCharacter().getX()+5, player.getCharacter().getY());
            flameArrow.setSpeed(.5, -.1);
        }
        else if (walking.getDirection().equals(Walking.cardinal.LEFT)) {
            character.setImages(images.get("left"));
            Sprite flameArrow =
                    new Sprite(this.player.getGame()
                                          .getImage("resources/items/flameArrow.gif"));
                flameArrow.setLayer(0);
                this.player.projectiles.add(flameArrow);
                flameArrow.setLocation(player.getCharacter().getX()-5, player.getCharacter().getY());
                flameArrow.setSpeed(-.5, .1);
        }
        else if (walking.getDirection().equals(Walking.cardinal.DOWN)) {
            character.setImages(images.get("front"));
            Sprite flameArrow =
                    new Sprite(this.player.getGame()
                                          .getImage("resources/items/flameArrow.gif"));
                flameArrow.setLayer(0);
                this.player.projectiles.add(flameArrow);
                flameArrow.setLocation(player.getCharacter().getX(), player.getCharacter().getY()+5);
                flameArrow.setSpeed(-.1, .5);
        }
        else if (walking.getDirection().equals(Walking.cardinal.UP)) {
            character.setImages(images.get("back"));
            Sprite flameArrow =
                    new Sprite(this.player.getGame()
                                          .getImage("resources/items/flameArrow.gif"));
                flameArrow.setLayer(0);
                this.player.projectiles.add(flameArrow);
                flameArrow.setLocation(player.getCharacter().getX(), player.getCharacter().getY()-5);
                flameArrow.setSpeed(.1, -.5);
        }

        character.getAnimationTimer().setDelay(150);
        character.setLoopAnim(true);
        character.setAnimate(true);
        character.setAnimationFrame(0, 2);
        character.setSpeed(0, 0);
    }

}
