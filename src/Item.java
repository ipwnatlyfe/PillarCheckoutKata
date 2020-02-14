import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
	private double unitPrice = 0.00;
	private String name = "";
	private BigDecimal weight = new BigDecimal("0.00");
	private static int DECIMAL_PLACES = 2;
	private static RoundingMode ROUNDING = RoundingMode.HALF_EVEN;
	
	private BigDecimal roundHundreths(BigDecimal num)
	{
		return num.setScale(DECIMAL_PLACES,ROUNDING);
	}
	
	
	public void setUnitPrice(Double price)
	{
		this.unitPrice = price;
	}
	
	public Double getUnitPrice()
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
