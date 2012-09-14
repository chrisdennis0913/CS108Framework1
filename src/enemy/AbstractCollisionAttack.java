package enemy;

import app.RPGGame;

public abstract class AbstractCollisionAttack extends AbstractAttack {

	protected boolean active = true;
	
	public AbstractCollisionAttack(RPGGame game, IEnemy enemy, String name, boolean startActive) {
		super(game, enemy, name);
		active = startActive;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
}
