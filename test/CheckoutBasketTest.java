import static org.junit.Assert.assertEquals;

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
		Double prescanTotal = 0.0;
		
		myItem.setUnitPrice(1.0);
		
		prescanTotal = basket.getTotal();
		basket.scan(myItem);
		
		assertEquals("1.0", Double.toString(basket.getTotal() - prescanTotal));

	}
	
	@Test
	public void whenAnItemIsScannedItIsAddedToBasketList()
	{
		Item myItem = new Item();
		myItem.setName("Chicken Soup");
		basket.scan(myItem);
		
		assertEquals(false, basket.itemInBasket("beef"));
		assertEquals(true, basket.itemInBasket("Chicken Soup"));
	}

}
