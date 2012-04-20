package player;

import inventory.ItemSub;
import inventory.PlayerInventory;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import actions.Action1;
import actions.SwordAttacking;
import actions.BowAttacking;
import actions.Grabbing;
import actions.Standing;
import actions.Talking;
import actions.Walking;
import ai.AbstractBehaviorModifier;
import app.RPGGame;
import collisions.PlayerBoundaryCollision;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.SpriteGroup;


public class Player implements Cloneable {
    private static final double INITIAL_PLAYER_X_SPEED = 0.1;
    private static final double INITIAL_PLAYER_Y_SPEED = 0.1;

    private RPGGame game;
    private SpriteGroup group = new SpriteGroup("Player");
    
    public SpriteGroup projectiles = new SpriteGroup("Projectiles");
    
    private AnimatedSprite character;
    private String startSprite = "resources/player/start_sprite.gif";
    private PlayerInventory myInventory = new PlayerInventory(game);
    private PlayerCounters pcs = new PlayerCounters(this);
    private HashMap<String, Action1> actions = new HashMap<String, Action1>();
    private HashMap<String, ItemSub> inventoryWithNames =
        new HashMap<String, ItemSub>();
    private LinkedList<AbstractBehaviorModifier> behaviorModifiers =
        new LinkedList<AbstractBehaviorModifier>();
    private double maxXSpeed = INITIAL_PLAYER_X_SPEED;
    private double maxYSpeed = INITIAL_PLAYER_Y_SPEED;


    public Player (RPGGame rpgGame) {
        this.game = rpgGame;
        myInventory = new PlayerInventory(game);
    }


    public PlayerCounters getPCs () {
        return pcs;
    }


    public void generate (int[] location) {
        initCharacter(location);
        initCollisions();
        initActions();
    }


    private void initActions () {
        actions.put("walking", new Walking(this, 6, "walk"));
        actions.put("standing", new Standing(this,
                                             1,
                                             "stand",
                                             (Walking) actions.get("walking")));
        actions.put("talking", new Talking(this, 1, "stand"));
        actions.put("grabbing", new Grabbing(this, 1, "stand"));
        for (ItemSub itm : game.getInventory())
            System.out.println(itm + "in Player");
        actions.put("swordAttacking" + "", new SwordAttacking(this,
                                                              3,
                                                              "attacksword",
                                                              game));
        actions.put("bowAttacking" + "", new BowAttacking(this,
                                                          5,
                                                          "attackbow",
                                                          game));
    }


    private void initCharacter (int[] location) {
        BufferedImage[] image =
            new BufferedImage[] { game.getImage(startSprite) };
        character = new AnimatedSprite(image, location[0], location[1]);
        character.setLayer(10);
        group.add(character);
        game.getField().addGroup(group);
        game.getField().addGroup(projectiles);
    }


    private void initCollisions () {
        game.getField()
            .addCollisionGroup(group,
                               null,
                               new PlayerBoundaryCollision(game.getBG()));
    }


    public void update (long elapsedTime) {
        for (AbstractBehaviorModifier bm : behaviorModifiers)
            bm.setUp(elapsedTime);
        pcs.update(0);
        for (String name : actions.keySet())
            if (actions.get(name).isActionable(game)) actions.get(name)
                                                             .act(game);
        if (game.keyPressed(java.awt.event.KeyEvent.VK_I)) {
            myInventory.toggleShow();
        }
        else if (game.keyPressed(java.awt.event.KeyEvent.VK_O)) {
            myInventory.showFullInventoryMenu();
        }
        
        Iterator<AbstractBehaviorModifier> bmReverse =
            behaviorModifiers.descendingIterator();
        while (bmReverse.hasNext()) {
            if (bmReverse.next().unsetUp(elapsedTime)) bmReverse.remove();
        }

    }


    public void render (Graphics2D g) {
        pcs.render(g);
        for (String name : actions.keySet()) {
            Action1 action = actions.get(name);
            if (action.isActing() && action.isActionable(game) &&
                action.isMessageable()) game.getDialog().showMessage(g);
        }
        myInventory.showLimitedInventory(g);
        myInventory.drawAccessories(g);
    }


    public Action1 getAction (String name) {
        return actions.get(name);
    }


    public HashMap<String, Action1> getActions () {
        return actions;
    }


    public RPGGame getGame () {
        return game;
    }


    public AnimatedSprite getCharacter () {
        return character;
    }


    public SpriteGroup getGroup () {
        return group;
    }


    public PlayerInventory getInventory () {
        return myInventory;
    }


    public void addItem (ItemSub grabItem) {
        inventoryWithNames.put(grabItem.getName(), grabItem);
        myInventory.add(grabItem);
    }


    public ItemSub getEquipped () {
        return myInventory.getEquipped();
    }


    public void setEquipped (ItemSub itm) {
        myInventory.setEquipped(itm);
    }


    public boolean hasItem (ItemSub itm) {
        return myInventory.contains(itm);
    }


    public boolean hasItem (String itemName) {
        if (!inventoryWithNames.containsKey(itemName)) {
            return false;
        }
        return myInventory.contains(inventoryWithNames.get(itemName));
    }


    public double getMaxXSpeed () {
        return maxXSpeed;
    }


    public double getMaxYSpeed () {
        return maxYSpeed;
    }


    public void setMaxXSpeed (double xs) {
        maxXSpeed = xs;
    }


    public void setMaxYSpeed (double ys) {
        maxYSpeed = ys;
    }


    public void registerBehaviorModifier (AbstractBehaviorModifier bm) {
        //default is to insert at end of linked list
        registerBehaviorModifier(bm, false);
    }


    public void registerBehaviorModifier (AbstractBehaviorModifier bm,
                                          boolean insertAtTop) {
        if (insertAtTop) behaviorModifiers.addFirst(bm);
        else behaviorModifiers.addLast(bm);
    }


    public void registerBehaviorModifierExclusive (AbstractBehaviorModifier bm,
                                                   boolean insertAtTop) {
        Iterator<AbstractBehaviorModifier> iter = behaviorModifiers.iterator();
        while (iter.hasNext())
            if (iter.next().getClass() == bm.getClass()) iter.remove();

        registerBehaviorModifier(bm, insertAtTop);
    }


    public void deregisterBehaviorModifier (AbstractBehaviorModifier bm) {
        behaviorModifiers.remove(bm);
    }

}
