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

public class TVReturn {
	private HashMap<String, ClassTV>tvset; //Instance variable to hold the set. HashMap chosen because it gives more efficient and convenient way to manage the TV sales and the TV that is returned. 
	private static String Filepath; //Instance variable to hold the FilePath
	private static boolean done = false;
	private static Scanner filereader;
	public TVReturn()
	{
		tvset = new HashMap<String, ClassTV>();  //HashMap chosen because it gives more efficient and convenient way to manage the TV sales
		boolean done1 = false;
		Scanner in = new Scanner(System.in);
		
		do
		{
			try
			{
				if(done == false)
				{
					System.out.println("Please enter previous the TV sales filepath: "); //Get file path way
					Filepath = in.nextLine();
					//done = true;
				}
				
				File salefileObject = new File(Filepath); //Create a new sales file object
				filereader = new Scanner(salefileObject); //Read in the file
				
				while(filereader.hasNext())
				{
					String IDNumber = filereader.nextLine();
					String TVBrand = filereader.nextLine();
					String TVModel = filereader.nextLine();
					String TVPrice = filereader.nextLine();
					
					double myTVPrice = Double.parseDouble(TVPrice);
					TVType ReturnTV = new TVType(TVBrand, TVModel, myTVPrice);
					ClassTV myID = new ClassTV(IDNumber , ReturnTV);
					
					tvset.put(IDNumber, myID);
				}
				
				filereader.close();
				done1 = true;
				done = true;
				
			}catch(FileNotFoundException e)
			{
				System.out.println("Error - Please try again");
			}
		}while(done1 == false);
	}
	

	public ClassTV returnTV(String myreturn) //Return the found element
	{
		ClassTV myTVReturn = null;
		//String refundprice = null;
		if(tvset.containsKey(myreturn))
		{
			myTVReturn = tvset.get(myreturn);
			tvset.remove(myreturn);
			
			try
			{
				FileWriter myfile = new FileWriter(Filepath);
				BufferedWriter myfile2 = new BufferedWriter(myfile); // Create the buffer writer.
				
				for(ClassTV mynewTV : tvset.values())
				{
					//Retrive TVID, Brand, Model, Price
					String newID = mynewTV.getID();
					String newBrand = mynewTV.getmytvType().getBrand();
					String newModel = mynewTV.getmytvType().getModel();
					String newPrice = String.valueOf(mynewTV.getmytvType().getPrice());
					
					//Writing to the file
					myfile2.write(newID);
					myfile2.newLine();
					
					myfile2.write(newBrand);
					myfile2.newLine();
					
					myfile2.write(newModel);
					myfile2.newLine();
					
					myfile2.write(newPrice);
					myfile2.newLine();
				
				}
				
				myfile2.close(); //Close the file
			}catch(IOException e)
			{
				System.out.println("Error - Please try again");
			}
		}
	
		return myTVReturn;
	}
	
	
}
