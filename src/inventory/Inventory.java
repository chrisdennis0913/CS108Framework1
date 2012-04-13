package inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Inventory implements Iterable<ItemSub> {
	protected Map<ItemSub, Integer> myItemMap;

	public Inventory() {
		myItemMap = new TreeMap<ItemSub, Integer>();
	}

	public void add(ItemSub itm) {
		if (!myItemMap.containsKey(itm))
			myItemMap.put(itm, 0);
		// implicitly calls compareTo
		// sorts items by category alphabetically, then by name, then by price
		// done automatically within the TreeMap
	}

	public void add(ItemSub itm, int quantity) {
		int quant = 0;
		if (myItemMap.containsKey(itm)) {
			quant = myItemMap.get(itm);
		}
		myItemMap.put(itm, quant + quantity);
	}

	public void remove(ItemSub itm) {
		if (myItemMap.containsKey(itm))
			myItemMap.remove(itm);
		else
			System.out.println("Inventory does not contain " + itm.getName()
					+ ".");
	}

	public void remove(ItemSub itm, int quantity) {
		int quant = 0;
		if (myItemMap.containsKey(itm)) {
			quant = myItemMap.get(itm);
		}
		quant -= quantity;
		if (quant < 0) {
			System.out.println("That is " + quant * -1 + " too many of "
					+ itm.getName() + ".");
			return;
		}
		myItemMap.put(itm, quant - quantity);
	}

	public boolean contains(ItemSub itm) {
		return myItemMap.containsKey(itm);
	}

	@Override
	public Iterator<ItemSub> iterator() {
		return new MyIterator(this);
	}

	private static class MyIterator implements Iterator<ItemSub> {

		int index;
		private ArrayList<ItemSub> data;

		public MyIterator(Inventory source) {
			index = 0;
			data = new ArrayList<ItemSub>();
			data.addAll(source.myItemMap.keySet());
		}

		public boolean hasNext() {
			if (index >= data.size())
				return false;
			return true;
		}

		public ItemSub next() {
			ItemSub output = data.get(index);
			index++;
			return output;
		}

		public void remove() {
			//
		}

	}
}
