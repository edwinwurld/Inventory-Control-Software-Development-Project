/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/


public class TVType {
	
	private String Brand;
	private String Model;
	private double Price;
	
	public TVType()  //Default COnstructor
	{
		this.Brand = null;
		this.Model = null;
		this.Price = 0.0;
	}
	
	public TVType(String Brand, String Model, double Price)  //Full Parameter Constructors
	{
		this.Brand = Brand;
		this.Model = Model;
		this.Price = Price;
	}
	
	public String getBrand()  //GetBrand
	{
		return Brand;
	}
	
	public void setBrand(String Brand)  //SetBrand
	{
		this.Brand = Brand;
	}
	
	public String getModel()  //GetModel
	{
		return Model;
	}
	
	public void setModel(String Model)  //SetModel
	{
		this.Model = Model;
	}
	
	public double getPrice()  //GetPrice
	{
		return Price;
	}
	
	public void setPrice(double Price)  //SetPrice
	{
		this.Price = Price;
	}
	
	public String toString()  // My toString
	{
		String TVList = String.format("%20s  %25s  %18s %-18.2f", Brand, Model, "$",Price); //To print and format the TV options
				
		return TVList;	
	}
	
}

