package app;


import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;


public class Title extends GameObject {


	GameFont		font;

	BufferedImage	title;
	BufferedImage	arrow;

	int				option;

	boolean			blink;
	Timer			blinkTimer = new Timer(400);


	public Title(GameEngine main) {
		super(main);
	}


	public void initResources() {
		title = getImage("Title.png", false);
		arrow = getImage("Arrow.png");

		font = fontManager.getFont(getImage("BitmapFont.png"));
	}


	public void update(long elapsedTime) {
		if (blinkTimer.action(elapsedTime)) {
			blink = !blink;
		}

		switch (bsInput.getKeyPressed()) {
			case KeyEvent.VK_ENTER :
				if (option == 0) {
					// start
					parent.nextGameID = Main.GAME_MODE;
					finish();

				} else if (option == 1) {
					// load

				} else if (option == 2) {
					// end
					finish();
				}
			break;

			case KeyEvent.VK_UP :
				option--;
				if (option < 0) option = 2;
			break;

			case KeyEvent.VK_DOWN :
				option++;
				if (option > 2) option = 0;
			break;

			case KeyEvent.VK_ESCAPE :
				finish();
			break;
		}
	}


	public void render(Graphics2D g) {
		g.drawImage(title, 0, 0, null);
		font.drawString(g, "START", 325, 300);
		font.drawString(g, "LOAD", 325, 320);
		font.drawString(g, "END", 325, 340);

		if (!blink) {
			g.drawImage(arrow, 309, 297+(option*20), null);
		}
	}

}