package level;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Scanner;

import app.Dialog;
import app.Main;
import app.Player;
import app.RPGGame;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.*;


/**
 * Arrow key	: navigate
 * Space		: switch lower/upper tile
 * Page down	: next tile
 * Page up		: prev tile
 * End			: fast next tile
 * Home			: fast prev tile
 * Right click	: select tile
 * Click		: put tile
 * Ctrl + S		: save
 */
public class LevelEditor extends GameObject {

	GameFont		font;
	public static final int PICKING = 0, BUSH = 1, ENEMY = 2;
	int gameState = PICKING;

	BufferedImage	bg;
	BufferedImage	arrow;
	BufferedImage	bush, enemy;
	Dialog			askx,asky;
	int				x,y;

	int				option;

	boolean			blink;
	Timer			blinkTimer = new Timer(400);


	public LevelEditor(GameEngine main) {
		super(main);
	}


	public void initResources() {
		bg = getImage("resources/bg.jpg", false);
		bush = getImage("resources/scenery/shrub.gif", false);
		enemy = getImage("resources/npc/snake_1.gif", false);
		arrow = getImage("Arrow.png");

		font = fontManager.getFont(getImage("BitmapFont.png"));
	}


	public void update(long elapsedTime) {
		if (blinkTimer.action(elapsedTime)) {
			blink = !blink;
		}

		switch (gameState) {

		case PICKING:

			switch (bsInput.getKeyPressed()) {
				case KeyEvent.VK_ENTER :
					if (option == 0) {
						gameState = BUSH;

				} else if (option == 1) {
						gameState = ENEMY;
				}
				break;

			case KeyEvent.VK_UP :
				option--;
				if (option < 0) option = 1;
				break;

			case KeyEvent.VK_DOWN :
				option++;
				if (option > 1) option = 0;
				break;

			case KeyEvent.VK_ESCAPE :
				finish();
				break;
			}
			break;
		case BUSH:
			if (keyPressed(KeyEvent.VK_SPACE)) {
				gameState = PICKING;
			}
			break;
		case ENEMY:
			if (keyPressed(KeyEvent.VK_SPACE)) {
				gameState = PICKING;
			}
			break;
		}
	}


	public void render(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
		if (gameState == BUSH)
		{
			Scanner in = new Scanner(System.in);
			
				System.out.println("Enter x coordinate:");
				x = in.nextInt();
				System.out.println("Enter y coordinate:");
				y = in.nextInt();
				in.close();
			g.drawImage(bush, x, y, null);
		}
		
		if (gameState == ENEMY)
		{
			Scanner in = new Scanner(System.in);
			
			System.out.println("Enter x coordinate:");
			x = in.nextInt();
			System.out.println("Enter y coordinate:");
			y = in.nextInt();
			in.close();
			g.drawImage(enemy, x, y, null);
		}
		
		g.drawString("Add bush", 325, 300);
		g.drawString("Add enemy", 325, 320);
		if (!blink) {
			g.drawImage(arrow, 309, 290+(option*20), null);
		}
	}

}
