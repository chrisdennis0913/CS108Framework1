 package inventory;

import player.PlayerDirection;
import app.RPGGame;
import com.golden.gamedev.object.AnimatedSprite;

public class Sword extends Weapon implements Sellable{
    private Sword(){
        super();
    }
    private int price = 1;
    private Sword (RPGGame game2, String name,
                    String gifName,
                   int damage)
    {
        super(game2, name , gifName, damage);
        weaponType="sword";
    }
    
    @Override
    public boolean isThisKindOfItem (String toParse)
    {    
        String myCateg=super.parseCategory(toParse);
        return (myCateg.equalsIgnoreCase("sword"));
    }
    
    public static ItemFactory getFactory(){
        return new ItemFactory(new Sword());
    }
    @Override
    public ItemSub parseItem (RPGGame game2, String toParse)
    {
        return new Sword(game2, super.parseName(toParse),
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

	@Override
	public boolean isSellable() {
		if(price == 0)
            return false;
        return true;
	}

}
