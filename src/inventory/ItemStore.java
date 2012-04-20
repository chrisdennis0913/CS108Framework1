package inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.TreeMap;
import inventory.ItemSub;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;

import app.RPGGame;

public class ItemStore {
	private Inventory myInventory;
	private TreeMap<ItemSub, Integer> myPrices;
	private boolean storeOpen = false;
	private RPGGame game;
	private GameFont font;
	private MakeItems MI = new MakeItems(game);

	public ItemStore(RPGGame game2) {
//
		myInventory = new Inventory();
		game = game2;
		font = game.fontManager.getFont(game.getImage("BitmapFont.png"));
//		ItemSub key = MI.parseExpression("Key, key, KeyItem, 5");
//		int[] keyLoc = new int[] { 5, 20 };
//		key.add(keyLoc, 0);
//		myInventory.add(key);
//
	}

	public RPGGame getGame() {
		return game;
	}

	public void addItems(ItemSub itm, int quantity) {
		myInventory.add(itm, quantity);

		ArrayList<ItemSub> IBCArray;
		IBCArray = new ArrayList<ItemSub>();
		IBCArray.add(itm);

	}

	public void removeItems(ItemSub itm, int quantity) {
		myInventory.remove(itm, quantity);
	}

	public void removeItems(ItemSub itm) {
		myInventory.remove(itm);
	}

	// public void adjustPrice(ItemSub item, int amtChange){
	// for (ItemSub i : myInventory){
	// int currentPrice = i.getPrice();
	// int newPrice = currentPrice.adjustPrice();
	// item.setPrice(newPrice);
	// }
	// }

	public void update(long elapsedTime) {
		if (game.keyPressed(KeyEvent.VK_ENTER)) {
			game.unPauseGameForStore();
			storeOpen = false;
		}
	}

	public void showStore(Graphics2D g) {
		if (!storeOpen)
			return;
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 12),
				new Color(255, 255, 255));
		Boxes(g);
		int iter = 0;
		for (ItemSub currentItem : myInventory) {
			if (iter > 5)
				break;
			String currentItemName = currentItem.getName();
			if (currentItemName == null)
				continue;
			if (currentItemName.length() > 12) {
				currentItemName = currentItemName.substring(0, 12) + "...";
			}
			font.drawText(g, currentItemName, SystemFont.LEFT, (iter * 80) + 8,
					345, 70, 2, 0);
			Sprite itemSprite = new Sprite(currentItem.image, iter * 80 + 10,
					360);
			itemSprite.render(g);
			iter++;
		}
	}

	private void Boxes(Graphics2D g) {
		g.setColor(new Color(0));
		g.drawRect(5, 20, 70, 50); // item one
		g.fillRect(5, 20, 70, 50);
		g.drawRect(85, 20, 70, 50); // item two
		g.fillRect(85, 20, 70, 50);
		g.drawRect(165, 20, 70, 50); // item three
		g.fillRect(165, 20, 70, 50);
		g.drawRect(245, 20, 70, 50); // item four
		g.fillRect(245, 20, 70, 50);
		g.drawRect(325, 20, 70, 50); // item five
		g.fillRect(325, 20, 70, 50);
	}

	public void openStore() {
		storeOpen = true;
		game.pauseGameForStore();
	}

	public void renderStore(Graphics2D g) {
		font.drawString(g, "BACK TO GAME PRESS ENTER", 30, 100);
		showStore(g);

	}

	public void updateStore(long elapsed) {
		update(elapsed);
	}

}
