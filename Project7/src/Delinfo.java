/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/


public class Delinfo {

	private String DeliName;
	private String DeliAccountNum;
	private String Address;
	private int DeliTVPurchased;
	private static int DeliveryCounter = 1;
	private static boolean firstrun = false; 
	
	public Delinfo() //My Constructor
	{
		this.DeliName = null;
		this.DeliAccountNum = null;
		this.Address = null;
		this.DeliTVPurchased = 0;
	}
	
	public Delinfo(String DeliName1, String DeliAccountNum1, String Address1, int DeliTVPurchased1) //Full Parameter Constructor
	{
		this.DeliName = DeliName1;
		this.DeliAccountNum = DeliAccountNum1;
		this.Address = Address1;
		this.DeliTVPurchased = DeliTVPurchased1;
	}
	
	public String getName()
	{
		return DeliName;
	}
	
	public void setName(String name)
	{
		this.DeliName = name;
	}
	
	public String getAccountNum()
	{
		return DeliAccountNum;
	}
	
	public void setAccountNum(String DeliAccount_Num)
	{
		this.DeliAccountNum = DeliAccount_Num;
	}
	
	public String getAddress()
	{
		return Address;
	}
	
	public void setAddress(String address)
	{
		this.Address = address;
	}
	
	public int getDeliTVPurchased()
	{
		return DeliTVPurchased;
	}
	
	public void setDeliTVPurchased(int TVPurchased)
	{
		this.DeliTVPurchased = TVPurchased;
	}
	
	public void ResetDeliveryCount()
	{
		firstrun = false;
		DeliveryCounter = 1;
	}
	public String toString()
	{
		
		String output1 = "";
		
		if (firstrun == false)
		{
			output1 = String.format("\nDelivery Report");
			output1 += String.format("\n--------------------");
			firstrun = true;
		}
		
		output1 += String.format("\n%-10s %1d ", "Delivery Stop #",DeliveryCounter,":");
		output1 += String.format("\nName: %-25s", DeliName);
		output1 += String.format("Account Number: %-5s",DeliAccountNum);
		output1 += String.format("\nAddress: %s", Address);
		output1 += String.format("\nNumber of TVs: %d\n", DeliTVPurchased);
		
		DeliveryCounter++;
		return output1;
	}
}
