package collisions;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import app.RPGGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import enemy.IEnemy;


public class EnemyCollision extends BasicCollisionGroup {

    private RPGGame game;
    private IEnemy enemy;
    private Player player;

    private List<CollisionObserver> collisionObservers = new ArrayList<CollisionObserver>();

    public EnemyCollision (RPGGame game, Player player, String enemyname) {
        this.game = game;
        this.player = player;
        this.enemy = game.getLevel().getEnemy(enemyname);
    }


    public void collided (Sprite character, Sprite scenery) {
        overlap(character, scenery);
        
        for(CollisionObserver co : collisionObservers)
        	co.notifyOfCollision();
        
        if (player.getActions().isAttacking()) {
            enemy.addToHealth(-1);
            if (enemy.getHealth() < 1) {
                enemy.die();
                game.getField().removeCollisionGroup(this);
            }
            jump(character, scenery);
        }
        else {
            jump(character, scenery);
            enemy.onCollision();
        }
    }


    protected void jump (Sprite character, Sprite scenery) {
        scenery.setX(character.getX() - 100);
        scenery.setY(character.getY() - 100);
    }


    protected void overlap (Sprite character, Sprite scenery) {
        double maxsep =
            scenery.getImage().getHeight() - character.getImage().getHeight() /
                    6 * 5;
        double separation = character.getY() - scenery.getY();

        if (separation <= maxsep) {
            character.setX(character.getOldX());
            character.setY(character.getOldY());
        }
    }

    public void registerCollisionObserver(CollisionObserver co){
    	collisionObservers.add(co);
    }
    
    public void deregisterCollisionObserver(CollisionObserver co){
    	collisionObservers.remove(co);
    }
}
