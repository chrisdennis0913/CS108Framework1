package player;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



import com.golden.gamedev.object.font.SystemFont;

import enemy.Enemy;


public class HealthCounter extends PlayerCounter {
	
	public HealthCounter(Player player, int count) {
		super(player, count);
	}	

	public void render(Graphics2D g) {
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 20), new Color(255,0,0));
		g.setColor(new Color(0));
		g.drawRect(10, 10, 25, 25);
		g.fillRect(10, 10, 25, 25);
		font.drawText(g, getCount().toString(), SystemFont.LEFT, 12, 12, 25, 2, 0);
	}
	
	public void update(long elapsed) {
		if (isEmpty())
			getPlayer().getGame().finish();
	}

	public void enemy(Enemy enemy) {
		decrease();
	}
	
}
