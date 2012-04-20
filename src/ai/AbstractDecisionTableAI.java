package ai;

import app.RPGGame;
import enemy.AbstractAttack;
import enemy.IEnemy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public abstract class AbstractDecisionTableAI extends AbstractAI{

	HashMap<String,HashMap<AbstractGameHeuristic,Integer>> decisionTable;
	
	public AbstractDecisionTableAI(RPGGame game, IEnemy enemy) {
		super(game, enemy);
	}

	@Override
	public void act(long elapsedTime) {
		if(!enemy.isDead()){
			move(elapsedTime);
			if (shouldAttack())
				enemy.attack(pickBestSpontaneousAttack(),elapsedTime);
		}
	}

	@Override
	public void move(long elapsedTime) {
		enemy.getSprite().moveTo(elapsedTime, game.getPlayer().getCharacter().getX(),
				game.getPlayer().getCharacter().getY(), (enemy.getMaxXSpeed() + enemy.getMaxYSpeed())/2);
	}

	@Override
	public boolean shouldAttack() {
		//This AI is aggressive: we should always attack
		return true;
	}

	public AbstractAttack pickBestAttack(HashMap<String,AbstractAttack> attackMap){
		PriorityQueue<AbstractAttack> attackQ = new PriorityQueue<AbstractAttack>(10,new AttackComparator(this));
		for(String attackName : decisionTable.keySet()){
			if(attackMap.containsKey(attackName))
				attackQ.add(attackMap.get(attackName));
		}
		return attackQ.poll();
	}
	
	@Override
	public AbstractAttack pickBestSpontaneousAttack() {
		return pickBestAttack(enemy.getSpontaneousAttacks());
	}

	@Override
	public AbstractAttack pickBestReactiveAttack() {
		return pickBestAttack(enemy.getReactiveAttacks());
	}

	@Override
	public void onCollision() {
		AbstractAttack at = pickBestReactiveAttack();
		System.err.println(at);
		at.performAttack(0);
		//pickBestReactiveAttack().performAttack(0);
	}
	
	public static class AttackComparator implements Comparator<AbstractAttack>{

		AbstractDecisionTableAI myAI;
		
		public AttackComparator(AbstractDecisionTableAI ai){
			myAI = ai;
		}
		
		@Override
		public int compare(AbstractAttack a1, AbstractAttack a2) {
			return - evaluateAttackValue(a1) + evaluateAttackValue(a2);	//higher score means first
		}
		
		public int evaluateAttackValue(AbstractAttack at){
			HashMap<AbstractGameHeuristic,Integer> attackConditions = myAI.decisionTable.get(at.getName());
			int totalValue = 0;
			for(AbstractGameHeuristic cond: attackConditions.keySet()){
				if (cond.getHeuristicBool()){
					System.err.println(at.getName() + cond + attackConditions.get(cond));
					totalValue += attackConditions.get(cond);
				}
			}
			return totalValue;
		}
		
	}

	
}
