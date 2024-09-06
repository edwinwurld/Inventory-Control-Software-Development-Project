/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/


import java.util.*; 
import java.io.*;
//import java.util.ArrayList;

public class CustomerData implements Iterable<Customer>, Serializable{

	public List <Customer> myCustomerList;
	
	public CustomerData() //My default constructor that instantiates a LinkedList of type customer
	{
		this.myCustomerList = new LinkedList<>();
	}
	
	public Iterator<Customer> iterator() //My iterator that returns list as an Iterable list
	{
		return myCustomerList.iterator();
	}
	
	public void AddCustomer(Customer CustomerName) //Adding customer
	{
		if(CustomerName == null)
		{
			System.out.println("Error - please try again");
		}
		else
		{
			myCustomerList.add(CustomerName);
		}
		
	}
	
	public void RemoveCustomer(Customer myCustomerName) //Removing Customer
	{
		if(myCustomerList.size() < 1)
		{
			System.out.println("Error - Please try again");
		}
		else
		{
			myCustomerList.remove(myCustomerName);
		}
	}
	
	public void UpdateCustomer(String AccountNumber, String NewName)  //Updating Customer
	{
		for(Customer CustomerName : myCustomerList)
		{
			if(CustomerName.getAccountNumber().equals(AccountNumber)) //Use the .equals commend when comparing the values in java
			{
				CustomerName.setCustomerName(NewName);	
			}
		}
	}
	
	public Customer FindCustomerNumber(String AccountNumber) //Finding Customer
	{
		for(Customer CustomerName : myCustomerList)
		{
			if(CustomerName.getAccountNumber().equals(AccountNumber))
			{
				return CustomerName;
			}
				
		}
		return null;
	}
	
	public int returnsize()
	{
		return myCustomerList.size();
	}
	
	public String toString()  //My toString
	{
		int keeptrack = 1;
		String output1;
		output1 = String.format("\nCustomer List:\n----------");
		
		for (Customer CustomerName : myCustomerList)
		{
			output1 += String.format("\n%-10s %2d: %-20s %10s: %5s", "Customer", keeptrack, CustomerName.getCustomerName(), "Account Number", CustomerName.getAccountNumber());
			System.out.println("\n");
			keeptrack++;		
		}
		
		return output1;
	}
	
	public Customer[] toArray()  //Converts customer dataList
	{
		
		Customer[] CustomerArray = myCustomerList.toArray(new Customer[myCustomerList.size()]);  //Set CustomerArray to myCustomerList
		for(int i = 0; i < CustomerArray.length; i++) //Loop through customer object 
		{
			CustomerArray[i] = (Customer) CustomerArray[i]; //Cast the object to a type of customer
		}
		
		return CustomerArray; //return CustomerArray	
	}
}

