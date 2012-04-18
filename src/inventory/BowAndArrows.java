package inventory;

import player.PlayerDirection;
import app.RPGGame;
import com.golden.gamedev.object.AnimatedSprite;

public class BowAndArrows extends Weapon {
    private BowAndArrows(){
        super();
    }
    
    private BowAndArrows (RPGGame game2, String name,
                    String gifName,
                   int damage)
    {
        super(game2, name, gifName, damage);
        weaponType="BowAndArrows";
    }
    
    @Override
    public boolean isThisKindOfItem (String toParse)
    {    
        String myCateg=super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("BowAndArrows")) return true;
        return false;
    }
    
    public static ItemFactory getFactory(){
        return new ItemFactory(new BowAndArrows ());
    }
    @Override
    public ItemSub parseItem (RPGGame game2, String toParse)
    {
        return new BowAndArrows(game2, super.parseName(toParse),
                          super.parseGifName(toParse),
                          parseDamage(toParse));
    }
    
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
