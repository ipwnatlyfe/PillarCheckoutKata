import java.math.BigDecimal;
import java.util.ArrayList;

public class CheckoutBasket {
	private BigDecimal total = new BigDecimal("0.00");
	private ArrayList<Item> basketList= new ArrayList<Item>();
	
	private BigDecimal calcBasketTotal()
	{
		BigDecimal total = Item.roundHundreths(new BigDecimal("0.00"));
		if(this.basketList.size() == 0)
		{
			return Item.roundHundreths(total);
		}
		
		
		for(int i=0; i<this.basketList.size(); i++)
		{
			
			if(this.basketList.get(i).getWeight().equals( Item.roundHundreths(BigDecimal.ZERO)))
			{
				
				
				total = total.add(this.basketList.get(i).getUnitPrice().multiply(this.basketList.get(i).getQuantity()));
			}
			else
			{
				total = total.add(this.basketList.get(i).getUnitPrice().multiply(this.basketList.get(i).getWeight()));
			}
						
		}
		
		
		// Have to reset the scale to two decimal points after doing multiplication
		return Item.roundHundreths(total);
	}
	
	public void scan(Item scannedItem)
	{
		int itemIndex = -1;
		itemIndex = itemInBasket(scannedItem.getName());
		
		// Need to make sure the same reference isn't added multiple times	
		if(itemIndex == -1)
		{
			Item newItem = new Item(scannedItem.getUnitPrice(),scannedItem.getName() ,scannedItem.getWeight());
			basketList.add(newItem);
		}
		else
		{
			// If a weight is defined on the scanned item, update the total weight, else update
			// the quantity
			
			if(scannedItem.getWeight() != null && scannedItem.getWeight().intValue() != 0)
			{
				basketList.get(itemIndex).setWeight(scannedItem.getWeight().add(basketList.get(itemIndex).getWeight()));
			}
			else
			{
				basketList.get(itemIndex).setQuantity(scannedItem.getQuantity().add(basketList.get(itemIndex).getQuantity()));
			}
			
			
		}
		
		this.total = calcBasketTotal();
	}
	
	public BigDecimal getTotal()
	{
		return this.total;
	}

	public int itemInBasket(String itemName) 
	{
		
		for(int i=0; i<basketList.size(); i++)
		{
			if(basketList.get(i).getName() != null && basketList.get(i).getName().equals(itemName))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void remove(Item myItem)
	{
		int index = itemInBasket(myItem.getName());
		
		if(index != -1)
		{
			basketList.remove(index);
			this.total = calcBasketTotal();
		}
	}
	
	

}
