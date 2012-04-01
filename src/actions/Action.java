package actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;


import app.Player;
import app.RPGGame;

import com.golden.gamedev.engine.timer.SystemTimer;

public abstract class Action {

	protected Player player;

	protected static String[] directions = new String[] { "front", "back",
			"left", "right" };
	protected HashMap<String, BufferedImage[]> images = new HashMap<String, BufferedImage[]>();
	protected String name;
	protected int frames;

	protected boolean messageable = false;
	private boolean actionable = false;
	private boolean acting = false;
	private SystemTimer actionTimer = new SystemTimer();
	private long actionStartTime = 0;

	public Action(Player player, int frames, String name) {
		this.player = player;
		this.frames = frames;
		this.name = name;
		actionTimer.setFPS(100);
		actionTimer.startTimer();
		makeAnimation();
	}

	private void makeAnimation() {
		for (int i = 0; i < directions.length; i++)
			images.put(directions[i], makeActionAnims(directions[i]));
	}

	private BufferedImage[] makeActionAnims(String direction) {
		BufferedImage[] action = new BufferedImage[frames];
		for (int i = 0; i < frames; i++) {
			String path = "resources/player/" + name + "/" + direction + "_"
					+ name + "_" + (i + 1);
			if (new File(path + ".png").exists())
				action[i] = player.getGame().getImage(path + ".png");
			else
				action[i] = player.getGame().getImage(path + ".gif");
		}

		return action;
	}

	public boolean isMessageable() {
		return messageable;
	}
	
	public boolean isActing() {
		return acting;
	}

	public void setActing(boolean acting) {
		this.acting = acting;
		actionStartTime = actionTimer.getTime();
	}

	public boolean isActionable(RPGGame game2) {
		return actionable;
	}

	public void setActionable(boolean actionable) {
		this.actionable = actionable;
	}

	public long getActionTime() {
		return actionTimer.getTime() - actionStartTime;
	}

	public abstract void act(RPGGame game2);

}
