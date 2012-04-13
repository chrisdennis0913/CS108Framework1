package actions;

import java.awt.image.BufferedImage;

import actions.Directions.Cardinal;

public abstract class Direction{
	private Double speedX;
	private Double speedY;
	private BufferedImage[] images;
	private Cardinal cardinal;
	
	
	public Direction(double speedX, double speedY, Cardinal cardinal, BufferedImage[] images) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.images = images;
		this.cardinal = cardinal;
	}
	
	public Double getHorSpeed() {
		return speedX;
	}
	
	public Double getVerSpeed() {
		return speedY;
	}
	
	public Cardinal getCardinality() {
		return cardinal;
	}
	
	public BufferedImage[] getImages() {
		return images;
	}
	
	public int frameCount() {
		return images.length;
	}
	
	public abstract void changeCharacter(boolean animate, int delay);
}
