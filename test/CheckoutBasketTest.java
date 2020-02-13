import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CheckoutBasketTest {
	
	@Test
	public void WhenAnItemIsScannedTotalIncreasedByUnitPrice()
	{
		CheckoutBasket basket = new CheckoutBasket();
		Item myItem = new Item();
		
		myItem.unitprice = 1.0;
		
		basket.scan(myItem);
		
		assertEquals("1", basket.getTotal());

	}

}
