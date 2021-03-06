package enemy;

import inventory.ItemSub;
import inventory.MakeItems;
import java.util.Random;
import java.util.TreeMap;
import app.RPGGame;

/*
 * EnemyItemGen returns an item to be dropped when an enemy dies.
 * the getDroppedItem method takes an enemy parameter because the
 * state of the enemy may influence the dropped item.
 */

public class EnemyItemGen 
{
	   private static EnemyItemGen instance = null;
	   private TreeMap<String, Double> dropRates;
	   private Random rand;
	   private MakeItems itemMaker;
	   private ValueComparator<String> vC;
	   
	   private EnemyItemGen(RPGGame g)
	   {
		   rand = new Random();
		   itemMaker = new MakeItems(g);
		   vC = new ValueComparator<String>(dropRates);
		   sortMapByValues();
		   new TreeMap<String, Double>(dropRates);
	   }
	   
	   public void changeProb(String descr, double newProb)
	   {
		   dropRates.put(descr, newProb);
	   }
	   
	   public void sortMapByValues()
	   {
		   dropRates = new TreeMap<String,Double>(vC);
	   }
	   
	   public ItemSub getDroppedItem(Enemy en)
	   {
		   double itemProbability = rand.nextDouble();
		   
		   for (String str: dropRates.keySet())
		   {
			   if ( itemProbability > dropRates.get(str))
			   {
				   return itemMaker.parseExpression(str);
			   }
		   }
		   
		   return null; //will change this to return money once it has been implemented
	   }
	   
	   public static EnemyItemGen getInstance(RPGGame g) 
	   {
	      if(instance == null) 
	      {
	         instance = new EnemyItemGen(g);
	      }
	      return instance;
	   }
	}
