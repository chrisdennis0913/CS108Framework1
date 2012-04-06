package actions;


import java.awt.event.KeyEvent;
import java.util.TreeSet;

import player.Player;
import app.RPGGame;

import com.golden.gamedev.object.AnimatedSprite;

import inventory.ItemSub;


public class Attacking extends Action1
{
    private TreeSet<ItemSub> myWeaponList = new TreeSet<ItemSub>();
    private RPGGame myGame;


    public Attacking (Player player, int frames, String name, RPGGame game2)
    {
        super(player, frames, name);
        myGame = game2;
        for (ItemSub itm : myGame.getInventory()){
            myWeaponList.add(itm);
        }
    }


    public boolean isActionable (RPGGame game2)
    {
        for (ItemSub itm : game2.getInventory()){
            myWeaponList.add(itm);
        }
        for (ItemSub itm : myWeaponList)
        {
            if (player.hasItem(itm))
            {
                return true;
            }
        }
        return false;
    }


    private void turn (boolean actionSwitch)
    {
        if (!actionSwitch)
        {
            player.getAction("grabbing").setActionable(false);
            player.getAction("talking").setActionable(false);
            player.getAction("walking").setActionable(false);
            player.getAction("standing").setActionable(false);
        }
        else
        {
            player.getAction("walking").setActionable(true);
            player.getAction("standing").setActionable(true);
        }
    }


    private void animate ()
    {
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


    public void act (RPGGame game2)
    {
        for (ItemSub itm : myGame.getInventory())
        {
            myWeaponList.add(itm);
        }
        if (isActionable(game2) && !isActing() &&
            player.getGame().keyDown(KeyEvent.VK_SPACE))
        {
            turn(false);
            animate();
            setActing(true);
        }

        if (getActionTime() > 450)
        {
            turn(true);
            setActing(false);
            setActionable(false);
        }
    }

}
