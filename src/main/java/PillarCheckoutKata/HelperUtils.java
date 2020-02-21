package PillarCheckoutKata;
import java.math.BigDecimal;

public class HelperUtils {
	
	public static BigDecimal percentage(BigDecimal base, BigDecimal percent)
	{
		return base.multiply(percent).divide(new BigDecimal(100));
	}


}
