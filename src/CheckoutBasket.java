import java.math.BigDecimal;
import java.util.ArrayList;

public class CheckoutBasket {
	private BigDecimal total = new BigDecimal("0.00");
	private ArrayList<Item> basketList= new ArrayList<Item>();
	
	public void scan(Item scannedItem)
	{
		basketList.add(scannedItem);
		this.total = this.total.add(scannedItem.getUnitPrice());
	}
	
	public BigDecimal getTotal()
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
