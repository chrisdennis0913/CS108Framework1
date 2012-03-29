package level;

import java.awt.Graphics2D;

import enemy.Snake;

import scenery.Scenery;
import app.Main;

public class End extends Level {

	public End(Main game) {
		super(game);
	}

	protected void addNPCs() {
		
	}

	protected void addScenery() {
		Scenery shrubs = new Scenery(game, "resources/scenery/shrub.gif");

		int row_size = game.getBG().getHeight() / 5;
		int bush_count = 0;
		for (int i = 0; i < 5; i++) {
			int y = row_size * i + shrubs.getImage().getHeight() / 2;
			if (i % 2 == 0)
				bush_count = 4;
			else
				bush_count = 3;
			for (int j = 0; j < bush_count; j++) {
				int x = (game.getBG().getWidth() / bush_count) * j
						+ shrubs.getImage().getWidth() / 4;
				int[] loc = new int[] { x, y };
				shrubs.add(loc, 0);
			}
		}

		scenery.put("shrubs", shrubs);
	}

	protected void addItems() {

	}

	public void render(Graphics2D g) {
		if (game.getDialog().getMessage().equals(""))
			game.getDialog().setMessage(
					"Press \"enter\" to talk to NPCs and pick up objects.");
		if (getLevelTime() < 1500)
			game.getDialog().showMessage(g);
	}

	public void nextLevel() {
		game.finish();
	}

	protected void addEnemies() {
		Snake snake = new Snake(game, "snake");
		snake.add(new int[]{100, 100}, 4);
		enemies.put("snake", snake);
	}

}
