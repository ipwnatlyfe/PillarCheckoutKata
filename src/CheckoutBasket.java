import java.math.BigDecimal;
import java.util.ArrayList;

public class CheckoutBasket {
	private BigDecimal total = new BigDecimal("0.00");
	private ArrayList<Item> basketList= new ArrayList<Item>();
	
	//Iterate through entire arraylist of items and add up the items
	private BigDecimal calcBasketTotal()
	{
		BigDecimal tempWeight = new BigDecimal("0.00");
		BigDecimal tempQuantity = new BigDecimal("0.00");
		BigDecimal tempPrice = new BigDecimal("0.00");
		BigDecimal tempMarkdown = new BigDecimal("0.00");
		Item.Special tempSpecial;
		
		BigDecimal total = Item.roundHundreths(new BigDecimal("0.00"));
		
		// If there is nothing in the basket, return total immediately
		// Should be zero
		if(this.basketList.size() == 0)
		{
			return Item.roundHundreths(total);
		}
		
		
		for(int i=0; i<this.basketList.size(); i++)
		{
			tempWeight = this.basketList.get(i).getWeight();   
			tempQuantity = this.basketList.get(i).getQuantity();
			tempPrice = this.basketList.get(i).getUnitPrice();
			tempMarkdown = this.basketList.get(i).getMarkdown();  
			tempSpecial = this.basketList.get(i).getCurrSpecial();
			
			//Modify price to reflect the markdown (assuming per unit or per pound prices)
			tempPrice = tempPrice.subtract(tempMarkdown);
			
			//Price can't go below 0, so if the markdown makes it negative, set
			// the price to 0
			if(tempPrice.compareTo(BigDecimal.ZERO) < 0)
			{
				tempPrice = Item.roundHundreths(BigDecimal.ZERO);
			}
			
			
			//If the weight for the selected item is 0, then add another count to the quantity of the item
			//If the weight is nonzero, then use the weight as the quantifier instead.
			if(tempWeight.equals( Item.roundHundreths(BigDecimal.ZERO)))
			{
				total = total.add(tempPrice.multiply(tempQuantity));
			}
			else
			{
				total = total.add(tempPrice.multiply(tempWeight));
			}
			
			//After having the unmodified (other than markdown) check if there is a special
			if(tempSpecial.getType()== Item.specialType.BUY_N_GET_M_AT_X_PERCENT_OFF)
			{
				if((tempQuantity.compareTo(tempSpecial.getNumNeeded()) >= 0) && ((tempQuantity.subtract(tempSpecial.getNumNeeded())).compareTo(tempSpecial.getNumUpTo()) >= 0))
				{
					BigDecimal tempValue = new BigDecimal("100");
					
					//Divide the disc by 100 to convert into a percentage, eventually
					// need to throw this in it's own function in a helper class
					tempValue = tempSpecial.getDiscPercentage().divide(tempValue);
					
					
					//unit price * percentage discount * number of items for promotion
					tempValue = tempValue.multiply(tempPrice.multiply(tempSpecial.getNumUpTo()));
					
					
					
					total = total.subtract(tempValue);
				}
				
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
			Item newItem = new Item(scannedItem.getUnitPrice(),scannedItem.getName() ,scannedItem.getWeight(), scannedItem.getMarkdown());
			newItem.setCurrSpecial(scannedItem.getCurrSpecial());
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
			
			basketList.get(itemIndex).setCurrSpecial(scannedItem.getCurrSpecial());
			
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
