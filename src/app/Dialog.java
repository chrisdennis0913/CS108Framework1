package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.object.font.SystemFont;

public class Dialog {
	private String message = "";

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void showMessage(Graphics2D g) {
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 12), new Color(255,255,255));
		drawDialogBox(g);
		font.drawText(g, message, SystemFont.LEFT, 15, 345, 330, 2, 0);
	}
	
	private void drawDialogBox(Graphics2D g) {
		g.setColor(new Color(0));
		g.drawRect(10, 340, 380, 50);
		g.fillRect(10, 340, 380, 50);
	}
}
