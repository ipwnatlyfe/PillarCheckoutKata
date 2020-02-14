import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;


public class CheckoutBasketTest {
	
	CheckoutBasket basket;
	
	@Before
	public void setUp()
	{
		basket = new CheckoutBasket();
	}
	
	@Test
	public void whenAnItemIsScannedTotalIncreasedByUnitPrice()
	{
		Item myItem = new Item();
		BigDecimal prescanTotal = new BigDecimal("0.0");
		
		myItem.setUnitPrice("1.0");
		
		prescanTotal = basket.getTotal();
		basket.scan(myItem);
		
		assertEquals("1.00", basket.getTotal().subtract(prescanTotal).toString());

	}
	
	@Test
	public void whenAnItemIsScannedItIsAddedToBasketList()
	{
		Item myItem = new Item();
		myItem.setName("Chicken Soup");
		basket.scan(myItem);
		
		assertEquals(-1, basket.itemInBasket("beef"));
		assertTrue(basket.itemInBasket("Chicken Soup")>=0);
	}
	
	@Test
	public void whenAnItemIsScannedWeightIsUpdated()
	{
		Item myItem = new Item();
		BigDecimal unitWeight = new BigDecimal("2.50");
		
		myItem.setWeight(unitWeight);
		
		assertEquals("2.50", myItem.getWeight().toString());
		
	}
	
	@Test
	public void whenAnItemIsScannedTwiceTotalCorrect()
	{
		Item myItem = new Item();
		
		myItem.setName("soup");
		myItem.setUnitPrice("3.50");
		
		basket.scan(myItem);
		basket.scan(myItem);
		
		assertEquals("7.00", basket.getTotal().toString());
	}
	
	@Test
	public void whenAnItemWithWeightIsScannedTwiceTotalCorrect()
	{
		Item myItem = new Item();
		
		myItem.setName("chicken");
		myItem.setWeight("1.20");
		myItem.setUnitPrice("2.00");
		
		basket.scan(myItem);
		basket.scan(myItem);
		
		assertEquals("4.80", basket.getTotal().toString());
		
	}
	
	@Test
	public void addAnItemToListThenMakeSureItCanBeRemovedAndTotalUpdated()
	{
		Item myItem = new Item();
		
		myItem.setName("beef");
		myItem.setWeight("3.25");
		myItem.setUnitPrice("4.00");
		
		basket.scan(myItem);
		
		assertEquals("13.00", basket.getTotal().toString());
		assertTrue(basket.itemInBasket("beef")>=0);
		
		basket.remove(myItem);
		assertEquals("0.00", basket.getTotal().toString());
		assertEquals(-1,basket.itemInBasket("beef"));
	}
	
	@Test
	public void addMarkdownToNonWeighedItemAndTotalUpdated()
	{
		Item myItem = new Item();
		Item myItem2 = new Item();
		
		myItem.setName("chicken nuggets");
		myItem.setUnitPrice("2.00");
		myItem.setMarkdown("1.00");
		
		myItem2.setName("beef");
		myItem2.setUnitPrice("2.00");
		myItem2.setWeight("2.7");
		myItem2.setMarkdown("1.00");
		
		basket.scan(myItem);
		
		assertEquals("1.00", basket.getTotal().toString());
		
		basket.scan(myItem2);
		
		assertEquals("3.70", basket.getTotal().toString());
		
	}
	
	@Test
	public void buyNItemsGetMAtXPercentOff()
	{
		Item myItem = new Item();
		Item.Special currSpecial = myItem.new Special();
		
		myItem.setName("chicken nuggets");
		myItem.setUnitPrice("2.00");
		
		currSpecial.setType(Item.specialType.BUY_N_GET_M_AT_X_PERCENT_OFF);
		currSpecial.setDiscPercentage(new BigDecimal("50"));
		currSpecial.setNumNeeded(new BigDecimal("3"));
		currSpecial.setNumUpTo(new BigDecimal("3"));

		myItem.setCurrSpecial(currSpecial);
		
		basket.scan(myItem);
		basket.scan(myItem);
		basket.scan(myItem);
		basket.scan(myItem);
		basket.scan(myItem);
		basket.scan(myItem);
		
		//Assuming that to get the discount, the promotion requires that the
		// full N in get N at X% off is required
		
		assertEquals("9.00", basket.getTotal().toString());
		
	}

}
