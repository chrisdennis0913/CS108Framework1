package inventory;

import java.awt.Graphics2D;
import app.RPGGame;


/**
 * Class for items that attach to the hero and contribute to his stats.
 * Accessories are of the form
 * "Name, gifName, accessory, statToChange, valueOfChange" for example
 * "Charismatic Chainmail, chainmail, accessory, health, 8"
 * 
 * @author Chris Dennis
 */
public class Accessory extends ItemSub {
    private boolean showsOnScreen = true;
    private int statChange;
    private int relX;
    private int relY;
    private String statistic;


    private Accessory () {

    }


    public Accessory (RPGGame game2,
                      String name,
                      String gifName,
                      String stat,
                      int value,
                      int relativeX,
                      int relativeY) {
        super(game2, name, gifName);
        category = "Accessory";
        game2.addItems(this);
        statistic = stat;
        statChange = value;
        relX = relativeX;
        relY = relativeY;
    }


    @Override
    public boolean isThisKindOfItem (String toParse) {
        String myCateg = super.parseCategory(toParse);
        return myCateg.equalsIgnoreCase("Accessory");
    }


    @Override
    public ItemSub parseItem (RPGGame game2, String toParse) {
        return new Accessory(game2,
                             super.parseName(toParse),
                             super.parseGifName(toParse),
                             parseStatCategory(toParse),
                             parseStatValue(toParse),
                             parseXValue(toParse),
                             parseYValue(toParse));
    }


    public String parseStatCategory (String toParse) {
        String[] parseArray = toParse.split(",");
        return parseArray[2].trim();
    }


    private int parseStatValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 3].trim());
    }


    private int parseXValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 2].trim());
    }


    private int parseYValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 1].trim());
    }


    public static ItemFactory getFactory () {
        return new ItemFactory(new Accessory());
    }


    @Override
    public void use () {}


    public int getStatChange () {
        return statChange;
    }


    public int compareTo (ItemSub it) {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        return compareTo((Accessory) it);
    }


    public int compareTo (Accessory ac) {
        if (statistic != ac.getStatistic()) return statistic.compareTo(ac.getStatistic());
        if (statChange != ac.getStatChange()) return statChange -
                                                     ac.getStatChange();
        if (myName != ac.getName()) return myName.compareTo(ac.getName());
        return 0;
    }


    public String getStatistic () {
        return statistic;
    }


    public void render (Graphics2D g) {
        if (!showsOnScreen) return;
        mySprite.setActive(true);
        mySprite.setLayer(10);
        mySprite.setLocation(game.getPlayer().getCharacter().getX() + relX,
                             game.getPlayer().getCharacter().getY() + relY);
        mySprite.render(g);
    }


    public void equip () {
        game.getPlayer().getInventory().equipAcc(this);
        game.getPlayer().getPCs().getHealth().boostTotal(statChange);
        game.getPlayer().getPCs().getHealth().decrease(1);
        game.getPlayer().getPCs().getHealth().increase(statChange + 1);
    }


    public void unequip () {
        game.getPlayer().getInventory().unEquipAcc(this);
        game.getPlayer().getPCs().getHealth().boostTotal(-statChange);
        game.getPlayer().getPCs().getHealth().decrease(statChange);
    }


    public boolean isEquipped () {
        return game.getPlayer().getEquipped() == this;
    }


    @Override
    public void drop () {
        // TODO Auto-generated method stub   
    }
}
