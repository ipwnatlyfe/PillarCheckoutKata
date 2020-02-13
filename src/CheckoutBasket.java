import java.util.ArrayList;

public class CheckoutBasket {
	private double total = 0.0;
	private ArrayList<Item> basketList= new ArrayList<Item>();
	
	public void scan(Item scannedItem)
	{
		basketList.add(scannedItem);
		this.total = this.total + scannedItem.getUnitPrice();
	}
	
	public Double getTotal()
	{
		return this.total;
	}

	public Boolean itemInBasket(String itemName) {
		
		for(int i=0; i<basketList.size(); i++)
		{
			if(basketList.get(i).getName() != null && basketList.get(i).getName().equals(itemName))
			{
				return true;
			}
		}
		return false;
	}
	
	

}
