import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
	private BigDecimal unitPrice = roundHundreths(new BigDecimal("0.00"));
	private String name = "";
	private BigDecimal weight = roundHundreths(new BigDecimal("0.00"));
	private BigDecimal quantity = roundHundreths(new BigDecimal("1.00"));
	private BigDecimal markdown = roundHundreths(new BigDecimal("0.00"));
	private Special currSpecial = new Special();
	
	

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
	
	public Special getCurrSpecial() {
		return currSpecial;
	}

	public void setCurrSpecial(Special currSpecial) {
		this.currSpecial = currSpecial;
	}
	
	enum specialType {
		NONE, BUY_N_GET_M_AT_X_PERCENT_OFF, BUY_N_ITEMS_FOR_X_DOLLARS, BUY_N_GET_M_AT_X_PERCENT_OFF_WEIGHT
	}
	
	public class Special {
		specialType type = specialType.NONE;
		private BigDecimal numNeeded = new BigDecimal("0.00");
		private BigDecimal numUpTo = new BigDecimal("0.00");
		private BigDecimal discPercentage = new BigDecimal("0.00");
		private BigDecimal specValue = new BigDecimal("0.00");
		private BigDecimal itemLimit = new BigDecimal("0.00");
		
		
		public BigDecimal getItemLimit() {
			return itemLimit;
		}
		public void setItemLimit(BigDecimal itemLimit) {
			this.itemLimit = itemLimit;
		}
		
		public BigDecimal getSpecValue() {
			return specValue;
		}
		public void setSpecValue(BigDecimal specValue) {
			this.specValue = specValue;
		}
		public BigDecimal getNumNeeded() 
		{
			return numNeeded;
		}
		public void setNumNeeded(BigDecimal numNeeded) 
		{
			this.numNeeded = numNeeded;
		}
		public BigDecimal getNumUpTo() 
		{
			return numUpTo;
		}
		public void setNumUpTo(BigDecimal numUpTo) 
		{
			this.numUpTo = numUpTo;
		}
		public BigDecimal getDiscPercentage() 
		{
			return discPercentage;
		}
		public void setDiscPercentage(BigDecimal discPercentage) 
		{
			this.discPercentage = discPercentage;
		}
		
		
		public void setType(specialType type)
		{
			this.type = type;
		}
		public specialType getType()
		{
			return this.type;
		}
		
		
	}
	
	
}
