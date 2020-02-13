public class Item {
	private double unitPrice = 0.0;
	private String name;
	
	
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
}
