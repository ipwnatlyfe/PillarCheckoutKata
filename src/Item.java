import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
	private BigDecimal unitPrice = new BigDecimal("0.00");
	private String name = "";
	private BigDecimal weight = new BigDecimal("0.00");
	private static int DECIMAL_PLACES = 2;
	private static RoundingMode ROUNDING = RoundingMode.HALF_EVEN;
	
	public BigDecimal roundHundreths(BigDecimal num)
	{
		return num.setScale(DECIMAL_PLACES,ROUNDING);
	}
	
	
	public void setUnitPrice(BigDecimal price)
	{
		this.unitPrice = roundHundreths(price);
	}
	
	// Added a secondary method to set price to accept a string to make it more convenient
	public void setUnitPrice(String price)
	{
		BigDecimal newPrice = new BigDecimal(price);
		this.unitPrice = roundHundreths(newPrice);
	}
	
	public BigDecimal getUnitPrice()
	{
		return this.unitPrice;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setWeight(BigDecimal weight)
	{
		this.weight = roundHundreths(weight);
	}
	
	public BigDecimal getWeight()
	{
		return this.weight;
	}
	
	
}
