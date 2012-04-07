package actions;

public abstract class Direction{
	private Double speedX;
	private Double speedY;
	private Integer key;
	
	public Direction(double speedX, double speedY, int key) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.key = key;
	}
	
	public Double getHorSpeed() {
		return speedX;
	}
	
	public Double getVerSpeed() {
		return speedY;
	}
	
	public Integer getKey() {
		return key;
	}
}
