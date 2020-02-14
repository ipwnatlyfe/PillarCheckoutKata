import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
	private BigDecimal unitPrice = roundHundreths(new BigDecimal("0.00"));
	private String name = "";
	private BigDecimal weight = roundHundreths(new BigDecimal("0.00"));
	private BigDecimal quantity = roundHundreths(new BigDecimal("1.00"));
	private BigDecimal markdown = roundHundreths(new BigDecimal("0.00"));
	
	private static int DECIMAL_PLACES = 2;
	private static RoundingMode ROUNDING = RoundingMode.HALF_EVEN;
	
	public Item()
	{
		
	}
	
	public Item(BigDecimal unitPrice, String name, BigDecimal weight, BigDecimal markdown)
	{
		this.unitPrice = roundHundreths(unitPrice);
		this.name = name;
		this.weight = roundHundreths(weight);
		this.markdown = roundHundreths(markdown);
		
	}
	
	public static BigDecimal roundHundreths(BigDecimal num)
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
	
	public void setWeight(String weight)
	{
		BigDecimal newWeight = new BigDecimal(weight);
		this.weight = roundHundreths(newWeight);
	}
	
	public BigDecimal getWeight()
	{
		return this.weight;
	}
	
	public void setQuantity(BigDecimal quantity)
	{
		this.quantity = quantity;
	}
	
	public BigDecimal getQuantity()
	{
		return this.quantity;
	}
	
	public void setMarkdown(BigDecimal markdown)
	{
		this.markdown = markdown;
	}
	
	public void setMarkdown(String markdown)
	{
		BigDecimal newMarkdown = new BigDecimal(markdown);
		this.markdown = roundHundreths(newMarkdown);
	}
	
	public BigDecimal getMarkdown()
	{
		return this.markdown;
	}
	
	
}
