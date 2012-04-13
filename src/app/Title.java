package app;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;

import saving_loading.LoadFileFilter;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;


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
					//LoadFileFilter lwrFilter = new LoadFileFilter(new String[]{".lwr"});
				    //LoadFileFilter uprFilter = new LoadFileFilter(new String[]{".upr"});
				    LoadFileFilter jsonFilter = new LoadFileFilter(new String[]{".json"});
					
					//JFileChooser lwrChooser = new JFileChooser();
					//JFileChooser uprChooser = new JFileChooser();
					JFileChooser jsonChooser = new JFileChooser();
				    /*	
				     * TO BE IMPLEMENTED FULLY WHEN SCENERY IS CHANGED    
				    lwrChooser.setFileFilter(lwrFilter);
				    lwrChooser.setDialogTitle("Choose your lower map file");
				    int lwrReturnVal = lwrChooser.showOpenDialog(lwrChooser);
				    
				    if(lwrReturnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            lwrChooser.getSelectedFile().getName());
				       RPGGame.lwrFilename = lwrChooser.getSelectedFile().getName();
				    }
				    
				    uprChooser.setFileFilter(uprFilter);
				    uprChooser.setDialogTitle("Choose your upper map file");
				    int uprReturnVal = lwrChooser.showOpenDialog(lwrChooser);
				    if(uprReturnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            uprChooser.getSelectedFile().getName());
				       RPGGame.uprFilename = uprChooser.getSelectedFile().getName();
				    }
				    */
				    jsonChooser.setFileFilter(jsonFilter);
				    jsonChooser.setDialogTitle("Choose your json level file");
				    jsonChooser.setCurrentDirectory(new File("savedmaps/"));
				    int jsonReturnVal = jsonChooser.showOpenDialog(jsonChooser);
				    if(jsonReturnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            jsonChooser.getSelectedFile().getName());
				       RPGGame.startLevelFileName = jsonChooser.getSelectedFile().getName();
				    }
					

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