/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/


//import java.util.*; 
import java.io.*;
import java.util.ArrayList;


public class Customer implements Serializable, StacksInterface{

	private String CustomerName;
	private String AccountNumber;
	private int NumPurchased;
	private double CostPurchased;
	ArrayList <String> tvID = new ArrayList<String>();
	private TVType TVInfo; //Private instance variable of type TVType
	
	public Customer(String ourCustomerName, String myAccountNumber, TVType TVInfo) //My default constructor
	{	
		this.CustomerName = ourCustomerName;
		this.AccountNumber = myAccountNumber;
		this.TVInfo = null; 
	}
	public Customer(String CustomerName1, String AccountNumber1, int NumPurchased1, ArrayList<String>tvID, TVType TVInfo)
	{
		this.CustomerName = CustomerName1;
		this.AccountNumber = AccountNumber1;
		this.NumPurchased = NumPurchased1;
		this.tvID= tvID;
		this.TVInfo = TVInfo; 
		//CalAmount();
		//CalcTVType();
		
	}
	
	public Customer(String CustomerName1, String AccountNumber1)
	{
		this.CustomerName = CustomerName1;
		this.AccountNumber = AccountNumber1;
	}
	
	public String getCustomerName() //Get the customer name
	{
		return CustomerName;
	}
	
	public void setCustomerName(String Customer_Name) //Set the customer Name
	{
		this.CustomerName = Customer_Name;
	}
	
	public String getAccountNumber() //Get the account number
	{
		return AccountNumber;
	}
	
	public void setAccountNumber(String AccountNumber) //Set the account Number
	{
		this.AccountNumber = AccountNumber;
	}
	
	public int getNumberPurchased() //Get the Number purchased
	{
		return NumPurchased;
	}
	
	public void setNumPurchased(int NumPurchased) //Set the number purchased
	{
		this.NumPurchased = NumPurchased;
		
	}
	
	public ArrayList<String> gettvID() //Get the TV ID
	{
		return tvID;
	}
	
	public void settvID(ArrayList<String>tvID) //Set the TV ID
	{
		this.tvID = tvID;
		
	}
	

	
	//Getter and set Function for Lab 5
	public TVType getTVInfo()  //Getter function for TVInfo
	{
		return TVInfo;
	}
	
	public void setTVInfo(TVType TVInfo)  //Set Function for TVInfo
	{
		this.TVInfo = TVInfo;
	} 
	
	public double CalcTVType(int numofTVpurchased, TVType numbuying) //Calculate how much the customer pays
	{
		double salesTax = (numofTVpurchased * numbuying.getPrice())* mytax;
		double totalprice = salesTax + (numofTVpurchased * numbuying.getPrice());

		return totalprice;
		
			
	}
	//End Get and Set function for lab 5
	
	
	public String toString() //Using the toString method to display the receipt of the sales
	{
		String receipt1, receipt2, receipt3, receipt4;
		
		System.out.println("Checkout Receipt: ");
		
		receipt1 = String.format("Customer Name: %s", CustomerName); 
		receipt2 = String.format("\nAccount Number: %s", AccountNumber);
		receipt1+=receipt2;
		receipt3 = String.format("\nPurchased %s TVs for $%.2f", NumPurchased, CostPurchased);
		receipt1+=receipt3;
		
		for (int i = 0; i < NumPurchased; i++)
		{
			String myID = tvID.get(i);
			receipt4 = String.format("\nTV ID purchased is: %s", myID);
			receipt1 += receipt4;
		}
		
			return String.format(receipt1);
		
	}
}
