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

}
