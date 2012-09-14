package level;

import java.awt.Graphics2D;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

import saving_loading.AttributeContainer;
import scenery.Scenery;
import app.RPGGame;
import enemy.Snake;

public class End extends Level {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public End(BaseLoader bsLoader, BaseIO bsIO, RPGGame game2, String levelFilename) {
        super(bsLoader, bsIO, game2, levelFilename);
	}

	protected void addNPCs() {
		
	}
	
	protected void initQuests()
	{
	
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

	public void nextLevel(String next) {
		game.finish();
	}

	protected void addEnemies() {
		AttributeContainer ac = new AttributeContainer();
		ac.put("name", "snake");
		ac.put("type", "snake");
		ac.put("location", new int[]{100,100});
		Snake snake = new Snake(game, ac);
		snake.add( (int[]) ac.getObjectForKey("location", int[].class), 4);
		enemies.put("snake", snake);
	}

	@Override
	public void renderTile(Graphics2D arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

}
