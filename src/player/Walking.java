package player;

import actions.Direction;
import actions.Directions.Cardinal;

public class Walking extends PlayerAction {
	private PlayerDirections directions;

	public Walking(Player player) {
		super(player);
		setEnabled(true, true);
		directions = new PlayerDirections(player,
				"resources/player/actions/walking.json");
	}

	public Cardinal getCurrentDirection() {
		return directions.getCurrentDirection();
	}

	public void update(long elapsed) {
		boolean checker = false;
		if (isEnabled())
			for (Direction dir : directions) {
				PlayerDirection direction = (PlayerDirection) dir;
				if (getActions().checkKeys(direction.getKeys())) {
					boolean isSame = directions.getCurrentDirection().equals(
							direction.getCardinality());
					keyPress((PlayerDirection) direction, isSame);
					checker = true;
					break;
				}
			}
		if (!checker)
			setActive(false);
		player.getGame().getBG().setToCenter(player.getCharacter());
	}

	public void keyPress(PlayerDirection direction, boolean isSame) {
		directions.setCurrentDirection(direction.getCardinality());
		direction.changeCharacter(!isActive() || !isSame, 100);
		setActive(true);
	}
	
	public void setSpeed(double[] speed){
		for(Direction d : directions.getDirections())
			if(d.getCardinality().equals(directions.getCurrentDirection())){
				d.setHorSpeed(speed[0]);
				d.setVerSpeed(speed[1]);
			}
	}
	
	public double[] getSpeed(){
		double[] speed = new double[2];
		for(Direction d : directions.getDirections())
			if(d.getCardinality().equals(directions.getCurrentDirection())){
				speed[0] = d.getHorSpeed();
				speed[1] = d.getVerSpeed();
			}
		return speed;
	}
}