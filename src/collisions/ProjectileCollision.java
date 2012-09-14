package collisions;

import npc.NPC;
import player.Player;
import app.RPGGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import enemy.IEnemy;


public class ProjectileCollision extends BasicCollisionGroup {
    private RPGGame game;
    private IEnemy enemy;
    private NPC npc;


    public ProjectileCollision (RPGGame game, Player player, String enemyname) {
        this.game = game;
        this.enemy = game.getLevel().getEnemy(enemyname);
        this.npc = game.getLevel().getNPC(enemyname);
//        game.getImage(".gif");
    }


    @Override
    public void collided (Sprite playerProjectile, Sprite badGuy) {
        if (enemy != null) {
            enemy.addToHealth(-1);
            if (enemy.getHealth() < 1) {
                enemy.die();
                game.getField().removeCollisionGroup(this);
            }
        }
        else npc.die();
        playerProjectile.setActive(false);
    }
}
