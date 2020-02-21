package PillarCheckoutKata;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
		BigDecimal tempItemLimit = new BigDecimal("0.00");
		BigDecimal viableItems = new BigDecimal("0.00");
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
			tempItemLimit = this.basketList.get(i).getCurrSpecial().getItemLimit();
			
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
			if(tempSpecial.getType()== Item.specialType.BUY_N_GET_M_AT_X_PERCENT_OFF && tempWeight.compareTo(BigDecimal.ZERO) <= 0)
			{
				viableItems = tempQuantity;
				// If the number of items is greater than the number of viable items for the special, and the lmit is non-zero
				// set to the quantity available to special to the limit
				
				if(tempQuantity.compareTo(tempItemLimit)>0 && tempItemLimit.compareTo(BigDecimal.ZERO) > 0)
				{
					viableItems = tempItemLimit;
				}
				// Viable items / (N+M) is the total amount of fully utilized specials
				BigDecimal fullSpecials = viableItems.divide(tempSpecial.getNumNeeded().add(tempSpecial.getNumUpTo()),0, RoundingMode.FLOOR);
				
				// Get the integer value of fullSpecials
				fullSpecials.setScale(0,RoundingMode.FLOOR);
				
				// Subtract the total complete specials. (complete specials) * (number of discounted items per special) * (discount percentage) * (unit price)
				total = total.subtract(fullSpecials.multiply(HelperUtils.percentage(tempSpecial.getNumUpTo(), tempSpecial.getDiscPercentage()).multiply(tempPrice)));
				
				// THere could still be a few unit items that weren't counted if they weren't part a few specials, subtract those next e.g. 
				// if you buy N and get M and X percent off, if you get a number L < M , they should still get the discount
				
				viableItems = viableItems.subtract(fullSpecials.multiply(tempSpecial.getNumNeeded().add(tempSpecial.getNumUpTo())));
				
				if (viableItems.compareTo(tempSpecial.getNumNeeded()) >= 0 )
				{
					total = total.subtract(HelperUtils.percentage(viableItems.remainder(tempSpecial.getNumNeeded()), tempSpecial.getDiscPercentage()).multiply(tempPrice));
				}
				

			}
			
			else if(tempSpecial.getType()== Item.specialType.BUY_N_ITEMS_FOR_X_DOLLARS && tempWeight.compareTo(BigDecimal.ZERO) <= 0)
			{
				viableItems = tempQuantity;
				// If the number of items is greater than the number of viable items for the special, and the lmit is non-zero
				// set to the quantity available to special to the limit
				
				if(tempQuantity.compareTo(tempItemLimit)>0 && tempItemLimit.compareTo(BigDecimal.ZERO) > 0)
				{
					viableItems = tempItemLimit;
				}
				 
				BigDecimal fullSpecials = viableItems.divide(tempSpecial.getNumNeeded(),RoundingMode.DOWN);
				fullSpecials.setScale(0,RoundingMode.FLOOR);
				
				
				if((viableItems.compareTo(tempSpecial.getNumNeeded()) >= 0))
				{
					//Subtract the full price items
					total = total.subtract(fullSpecials.multiply(tempPrice).multiply(tempSpecial.getNumNeeded()));
					
					//Add the special price
					total = total.add(tempSpecial.getSpecValue());
					
				}
				
			}
			
			else if(tempSpecial.getType()== Item.specialType.BUY_N_GET_M_AT_X_PERCENT_OFF_WEIGHT && tempWeight.compareTo(BigDecimal.ZERO) > 0)
			{
				viableItems = tempWeight;
				// If the number of items is greater than the number of viable items for the special, and the lmit is non-zero
				// set to the quantity available to special to the limit
				
				if(tempQuantity.compareTo(tempItemLimit)>0 && tempItemLimit.compareTo(BigDecimal.ZERO) > 0)
				{
					viableItems = tempItemLimit;
				}
				// Viable items / (N+M) is the total amount of fully utilized specials
				BigDecimal fullSpecials = viableItems.divide(tempSpecial.getNumNeeded().add(tempSpecial.getNumUpTo()),0, RoundingMode.FLOOR);
				
				// Get the integer value of fullSpecials
				fullSpecials.setScale(0,RoundingMode.FLOOR);
				
				// Subtract the total complete specials. (complete specials) * (number of discounted items per special) * (discount percentage) * (unit price)
				total = total.subtract(fullSpecials.multiply(HelperUtils.percentage(tempSpecial.getNumUpTo(), tempSpecial.getDiscPercentage()).multiply(tempPrice)));
				
				// THere could still be a few unit items that weren't counted if they weren't part a few specials, subtract those next e.g. 
				// if you buy N and get M and X percent off, if you get a number L < M , they should still get the discount
				
				viableItems = viableItems.subtract(fullSpecials.multiply(tempSpecial.getNumNeeded().add(tempSpecial.getNumUpTo())));
				
				if (viableItems.compareTo(tempSpecial.getNumNeeded()) >= 0 )
				{
					total = total.subtract(HelperUtils.percentage(viableItems.remainder(tempSpecial.getNumNeeded()), tempSpecial.getDiscPercentage()).multiply(tempPrice));
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
	
	//TODO: Add a remove method that takes in a string
	public void remove(Item myItem)
	{
		int index = itemInBasket(myItem.getName());
		
		if(index != -1)
		{
			
			
			basketList.get(index).setQuantity(basketList.get(index).getQuantity().subtract(new BigDecimal("1")));
			
			if(basketList.get(index).getQuantity().compareTo(BigDecimal.ZERO) == 0)
			{
				basketList.remove(index);
			}
			
			this.total = calcBasketTotal();
		}
	}
	
	public static void main(String[] args)
	{
		
	}
	
	

}

