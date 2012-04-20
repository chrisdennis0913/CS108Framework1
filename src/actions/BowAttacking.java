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
        double xPos=0;
        double yPos=0;
        double xVel=0;
        double yVel=0;

        if (walking.getDirection().equals(Walking.cardinal.RIGHT)) {
            xPos=10;
            xVel=.5;
            yVel=-.1;
            sendFlameArrow("right", xPos, yPos, xVel, yVel);
        }
        else if (walking.getDirection().equals(Walking.cardinal.LEFT)) {
            xPos=-10;
            xVel=-.5;
            yVel=.1;
            sendFlameArrow("left", xPos, yPos, xVel, yVel);
        }
        else if (walking.getDirection().equals(Walking.cardinal.DOWN)) {
            yPos=10;
            xVel=-.1;
            yVel=.5;
            sendFlameArrow("front",xPos, yPos, xVel, yVel);
        }
        else if (walking.getDirection().equals(Walking.cardinal.UP)) {
            yPos=-10;
            xVel=.1;
            yVel=-.5;
            sendFlameArrow("back",xPos, yPos, xVel, yVel);
        }

        character.getAnimationTimer().setDelay(150); // pull this to superClass
        character.setLoopAnim(true);
        character.setAnimate(true);
        character.setAnimationFrame(0, 2);
        character.setSpeed(0, 0);
    }


    private void sendFlameArrow (String direction, double xPos,double yPos,double xVel,double yVel) {
        player.getCharacter().setImages(images.get(direction));
        Sprite flameArrow =
            new Sprite(this.player.getGame()
                                  .getImage("resources/items/flameArrow.gif"));
        flameArrow.setLayer(0);
        this.player.projectiles.add(flameArrow);
        flameArrow.setLocation(player.getCharacter().getX() + xPos,
                               player.getCharacter().getY() + yPos);
        flameArrow.setSpeed(xVel, yVel);
    }

}
