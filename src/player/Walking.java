package player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Walking extends PlayerAction{
	public int[] directions = {
		KeyEvent.VK_UP,
		KeyEvent.VK_DOWN,
		KeyEvent.VK_LEFT,
		KeyEvent.VK_RIGHT
	};
	private int direction = 0;
	
	public Walking(Player player) {
		super(player);
		setActive(true);
	}

	public void update(long elapsed) {
		for (int direction : directions) {
			System.out.println(direction);	
		}
	}

	public void render(Graphics2D g) {
		
	}
}
