package level;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import npc.Priest;
import saving_loading.AttributeContainer;
import app.Dialog;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;
import com.google.gson.Gson;
import enemy.Snake;


/**
 * Arrow key	: navigate
 * Space		: switch lower/upper tile
 * Page down	: next tile
 * Page up		: prev tile
 * End			: fast next tile
 * Home			: fast prev tile
 * Right click	: select tile
 * Click		: put tile
 * S		: save
 */
public class LevelEditor extends Game {

	GameFont		font;
	public static final int PICKING = 0, PRIEST = 1, ENEMY = 2;
	int gameState = PICKING;
	List<String> jsonStrings = new ArrayList<String>();
	public Gson gson = new Gson();

	BufferedImage	bg;
	BufferedImage	arrow;
	BufferedImage	priest, enemy;
	Dialog			askx,asky;
	int				x,y;
	int 			start = 0;

	int				option;

	boolean			blink;
	Timer			blinkTimer = new Timer(400);


	public LevelEditor() {
		super();
	}


	public void initResources() {
		bg = getImage("resources/bg.jpg", false);
		priest = getImage("resources/npc/priest.gif", false);
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
						gameState = PRIEST;

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
			case KeyEvent.VK_S:
			 try {
			    	FileWriter f1 = new FileWriter("savedmaps/map00.json"); 
			    	for(String str : jsonStrings){
			    		f1.write(str);
			    		f1.write("\n");
			    	}
			    	f1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
				System.out.println("Level Saved!");
				finish();
				break;
			}
			
			break;
		case PRIEST:
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
		if (start ==0)
			g.drawImage(bg, 0, 0, null);
		
		if (gameState == PRIEST)
		{
			Scanner in = new Scanner(System.in);
						
			while(x<0){
				System.out.println("Enter x coordinate:");
				x = in.nextInt();
			}
			while(y<0){
				System.out.println("Enter y coordinate:");
				y = in.nextInt();
			}
			in.close();
			switch (bsInput.getKeyPressed()) {
			case KeyEvent.VK_1 :
				x = 10; y =10;
				break;
			case KeyEvent.VK_2 :
				x = 20; y =20;
				break;
			case KeyEvent.VK_3 :
				x = 30; y =30;
				break;
			case KeyEvent.VK_4 :
				x = 40; y =40;
				break;
			case KeyEvent.VK_5 :
				x = 50; y =50;
				break;
			case KeyEvent.VK_6 :
				x = 60; y =60;
				break;
			case KeyEvent.VK_7 :
				x = 70; y =70;
				break;
			case KeyEvent.VK_8 :
				x = 80; y =80;
				break;
			case KeyEvent.VK_9 :
				x = 90; y =90;
				break;
		}
			
			g.drawImage(priest, x, y, null);
			gameState = PICKING;
			
			Priest.numPriests++;
			AttributeContainer priestAttributes = new AttributeContainer();
			priestAttributes.put("name", "priest_"+Priest.numPriests);
			priestAttributes.put("type", "priest");
			priestAttributes.put("location", new int[]{x,y});		    
			jsonStrings.add(priestAttributes.asJsonString());
		}
		
		if (gameState == ENEMY)
		{
			Scanner in = new Scanner(System.in);
			
			int x =-1;
			int y=-1;
			while(x<0){
				System.out.println("Enter x coordinate:");
				x = in.nextInt();
			}
			while(y<0){
				System.out.println("Enter y coordinate:");
				y = in.nextInt();
			}
			in.close(); 
			
			switch (bsInput.getKeyPressed()) {
				case KeyEvent.VK_1 :
					x = 10; y =10;
					break;
				case KeyEvent.VK_2 :
					x = 20; y =20;
					break;
				case KeyEvent.VK_3 :
					x = 30; y =30;
					break;
				case KeyEvent.VK_4 :
					x = 40; y =40;
					break;
				case KeyEvent.VK_5 :
					x = 50; y =50;
					break;
				case KeyEvent.VK_6 :
					x = 60; y =60;
					break;
				case KeyEvent.VK_7 :
					x = 70; y =70;
					break;
				case KeyEvent.VK_8 :
					x = 80; y =80;
					break;
				case KeyEvent.VK_9 :
					x = 90; y =90;
					break;
			}
			
			g.drawImage(enemy, x, y, null);
			gameState = PICKING;
			
			Snake.numSnakes++;
			AttributeContainer snakeAttributes = new AttributeContainer();
			snakeAttributes.put("name", "snake_"+Snake.numSnakes);
			snakeAttributes.put("type", "snake");
			snakeAttributes.put("location", new int[]{x,y});
		    jsonStrings.add(snakeAttributes.asJsonString());		    
		}
		
		g.drawString("Add priest", 325, 300);
		g.drawString("Add enemy", 325, 320);
		if (!blink) {
			g.drawImage(arrow, 309, 290+(option*20), null);
		}
	}

}
