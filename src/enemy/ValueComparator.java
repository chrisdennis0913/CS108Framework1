package enemy;

import java.util.Comparator;
import java.util.Map;
/*
 * Sorts a map based upon its values, in descending order
 */

@SuppressWarnings("hiding")
public class ValueComparator<String> implements Comparator<String> {

	  Map<String, Double> toSort;
	  
	  public ValueComparator(Map<String, Double> map) 
	  {
	      toSort = map;
	  }

	  public int compare(String a, String b) {

	    if((Double)toSort.get(a) < (Double)toSort.get(b))
	    {
	      return 1;
	    } 
	    else 
	    if((Double)toSort.get(a) == (Double)toSort.get(b)) 
	    {
	      return 0;
	    } 
	    else
	    {
	      return -1;
	    }
	  }
	}

