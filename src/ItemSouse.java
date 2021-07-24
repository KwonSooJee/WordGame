import java.util.HashMap;

public class ItemSouse {
	
	public HashMap<String,Integer> item =new HashMap<String,Integer>();
	
	public ItemSouse() {
		item.put("bomb", 2);
		item.put("balloon", 2);
		item.put("slingshot", 2);
		item.put("swap", 2);
	}
	
	public void increase(String itemName) {
		int itemCount=item.get(itemName)+1;
		item.put(itemName, itemCount);
	}
	
	public void decrease(String itemName) {
		int itemCount=item.get(itemName)-1;
		item.put(itemName, itemCount);
	}

}
