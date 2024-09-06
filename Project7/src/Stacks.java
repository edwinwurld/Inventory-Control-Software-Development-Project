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


public class Stacks implements StacksInterface {
	
	public static void main(String[] args) //The Main Program
	{
		Stack<ClassTV> myTVStack = new Stack<>(); //My Stack Inventory
		Queue<Customer> customerQueue = new LinkedList<>(); //Creating my customer Queue
		CustomerData myCustomerList = new CustomerData();			
		BinaryTree myTVModel = new BinaryTree(); //My Binary Tree
		MaxHeap myMaxHeap = null;
		
		Scanner in = new Scanner(System.in);
		myTVStack = readfile(in);  //Calling my ReadFile function
		myCustomerList =  ReadcustomerData(in);
		boolean done = false;
		myCustomerList = sortCustomerData(myCustomerList);
		
		myTVModel = ReadBinaryData(in);  //Read file in
		
		int idNumber = myTVStack.size() - 1; //
		
		Welcome();  //Calling my welcome and menu function
		
		do
		{
			Menu();	
		
			//Asking the user to enter in their menu choice.
			System.out.println("\nPlease enter the menu choice: ");
			int choice = in.nextInt();
		
		try 
		{
			//The switch-statement and also using the case statement this makes the program break into the line based on what is selected.
			switch(choice)
			{
			
			//My stock shelves case
			case STOCK_SHELVES:
				Stockshelves(myTVStack);
				break;
				
			//My Fill web order case
			case FILLWEB:
				fillweb(myTVStack);
				break;
			
			//My Restock return case
			case RESTOCK_RETURN:
				idNumber = restockreturn(myTVStack, idNumber, in );
				break;
				
			//My Restocks Inventory case	
			case RESTOCK_INVEN:
				idNumber = restockinventory(myTVStack, idNumber);
				break;
				
			//My Customer Update case
			case CUSTOMER_UPDATE:
				myCustomerList = sortCustomerData(myCustomerList);
				UpdateCustomerFunction(myCustomerList, in); //Update Customer Call with all of the subMenu
				break;
				
			//My Customer Purchase case
			case CUSTOMER_PURCHASE:
				myCustomerList = sortCustomerData(myCustomerList);
				customer_purchase(myTVStack, customerQueue, idNumber, in, myCustomerList, myTVModel);
				break;
				
			//My Customer Checkout case
			case CHECKOUT:
				checkout(customerQueue);
				break;
				
			//My Customer Delivery
			case DISPLAY_DELIVERY:
				myMaxHeap = ReadDeliveryInfo(in);
				
			//	System.out.println();
				System.out.println(myMaxHeap.getactualheap());
				int actualheapSize = myMaxHeap.getactualheap();
				for(int i = 0; i < actualheapSize ; i++)
				{
					Delinfo data = myMaxHeap.RemoveRoot();
					//System.out.print(" " + heap[i] + "\t\t" + heap[2 * i] + "\t\t" + heap[2 * i + 1]);
					//System.out.printf("\n\nDelivery Stop #%d", i+1);
					System.out.print(data);
					//System.out.println();
				}
				
				
				break;
				
				
			//My display Inventory case	
			case DISPLAY_INVEN:
				displayinventory(myTVStack);
				break;
			
			//Ending my program
			case END:
				done = endprogram(customerQueue, myTVStack);
				break;
				
			//The default function for my error message
			default:
				System.out.println("Error - Invalid entry please reenter!!");
				break;
			}
			
			}catch (InputMismatchException e)
				{
					System.out.println("Error - Invalid Input please try again"); //For invalid entry
				}	
		
		}while(!done);
			
	}
	
	public static void UpdateCustomerFunction(CustomerData myCustomerList, Scanner in)
	{
		boolean done1 = false;
		boolean savechangestofile = false;
		boolean savetodatabase = false;
		//System.out.println(myCustomerList);
		do
		{
			
				switch(UpdateMenu(in))
					{
						case ADD_CUSTOMER: //Add customer
							AddCustomer(myCustomerList, in);
							savetodatabase= true;
							break;
							
						case DELETE_CUSTOMER: //Delete customer
							RemoveCustomer(myCustomerList, in);
							savetodatabase = true;
							break;
							
						case CHANGE_CUSTOMER: //Change customer
							
							ChangeCustomerName(myCustomerList, in);
							savetodatabase = true;
							break;
							
						case SAVE_CHANGES: //Save customer
							myCustomerList = sortCustomerData(myCustomerList);
							SaveChanges(myCustomerList, in);
							savechangestofile = true;
							break;
							
						case DISPLAY_CUSTOMER_LIST:  //Display customer list
							myCustomerList = sortCustomerData(myCustomerList);
							System.out.print(myCustomerList);
							break;
							
						case RETURN_MAIN:  //Return to the main menu
							myCustomerList = sortCustomerData(myCustomerList);
						done1 = ReturnMain(savetodatabase, savechangestofile, in);
							break;
							
							//The default function for my error message
						default:
							System.out.println("Error - Invalid entry please reenter!!");
							break;
					}
				
		}while(done1 == false);
	}
	
	//Printing my the welcome function
	public static void Welcome()
	{
		System.out.println("\nLab 7 - Sets and Maps");
		System.out.println("Copyright 2024 - Howard Community College All rights reserved; Unauthorized Duplication Prohibited");
		System.out.println("\nCMSY 265 TV Inventory Control Program");
	}

	//My Function to read the file
	public static Stack <ClassTV> readfile(Scanner in)
	{
		boolean done = false;
		String file = "";
		Stack <ClassTV> myStack = new Stack<ClassTV>();
		
		do
		{
			try {
				
				System.out.println("Please enter the Stack TV file path: "); //Asking the user to enter the file path
				file = in.next();
				File myfile = new File(file);
				Scanner readmyfile = new Scanner(myfile);
				
				while(readmyfile.hasNextLine())
				{
					String alldata = readmyfile.nextLine();
					ClassTV mytelevision = new ClassTV(alldata);
					myStack.push(mytelevision);
				}
				readmyfile.close();
				done = true;
			}catch(FileNotFoundException e)
			{
				System.out.println("Error, please enter the correct file path"); //the error message for wrong file path
			}
			
		}while(done == false);
		
		return myStack;
	}

	//Displaying the menu option for the user   
	public static void Menu()
	{
		System.out.println("\nMenu Options");
		System.out.printf("%d - Stock Shelves", STOCK_SHELVES);
		System.out.printf("\n%d - Fill Web Order", FILLWEB);
		System.out.printf("\n%d - Restock Return", RESTOCK_RETURN);
		System.out.printf("\n%d - Restock Inventory", RESTOCK_INVEN);
		System.out.printf("\n%d - Customer Update", CUSTOMER_UPDATE);
		System.out.printf("\n%d - Customer Purchase", CUSTOMER_PURCHASE);
		System.out.printf("\n%d - Customer Checkout", CHECKOUT);
		System.out.printf("\n%d - Display Delivery List", DISPLAY_DELIVERY);
		System.out.printf("\n%d - Display Inventory", DISPLAY_INVEN);
		System.out.printf("\n%d - End Program", END); 
	}
	
	//Updating the customer menu
	public static  int UpdateMenu(Scanner in)
	{
		int choice = 0;
		boolean done = false;
		
		System.out.println("\n\nCustomer Update Menu Options");
		System.out.printf("%d - Add a Customer", ADD_CUSTOMER);
		System.out.printf("\n%d - Delete a Customer", DELETE_CUSTOMER);
		System.out.printf("\n%d - Change Customer Name", CHANGE_CUSTOMER);
		System.out.printf("\n%d - Save Changes", SAVE_CHANGES);
		System.out.printf("\n%d - Display Customer List", DISPLAY_CUSTOMER_LIST);
		System.out.printf("\n%d - Return to Main", RETURN_MAIN);
		do
		{
			try {
				
					System.out.println("\nPlease enter the menu choice: ");
					choice = in.nextInt();
					done = true;
			}catch (InputMismatchException e)
			{
				System.out.println("Error - Invalid submenu input please try again");  //Error message For invalid input
				in.nextLine();
			}
		}while(done == false);
		
		return choice;
		}
	
	//#1 My Stock Shelves function
	public static void Stockshelves(Stack<ClassTV> myTVStack)
	{
		try {
				
			if(myTVStack.size() >= TVSize)
			{
				System.out.println("The following TV's have been placed on the floor for sale:"); //Tv's that have been placed for sales
				for(int i = 0; i < TVSize; i++)
				{
					System.out.println(myTVStack.pop()) ;
				}
				//Printing out the TV that are left in inventory
				System.out.println("\nThere are " + myTVStack.size() + " TV's are left in inventory:");
			}
			
			else
			{
				System.out.println("Error - Insufficient TV available please try again");
			}
		}catch(Exception e) {
			System.out.println("Error please try again");
		}
	}
	
	//#2 This function helps to fill web orders
	public static void fillweb(Stack<ClassTV> myTVStack)
	{
		try {
			if(!myTVStack.isEmpty()) //Using the isEmpty to display the amount of TV that has been shipped
			{
				System.out.println("The following TV's has been shipped: "); //Printing out all tv's that have been shipped
				System.out.println(myTVStack.pop()) ;		
			}
			
			System.out.println("There are " + myTVStack.size() + " TV's left in inventory");
		}catch(Exception e) {
			System.out.println("Error please try again");
		}	
	}
	
	//#3 This function helps me Restock the item that is returned
	public static int restockreturn(Stack<ClassTV> myTVStack, int idNumber, Scanner in)
	{
		
				idNumber++;
				String tvID = NewID+String.valueOf(idNumber);
				ClassTV Alltv = new ClassTV(tvID);
				
				//Lab 7
				boolean returndone = false;
				String ReturnID = null;
				TVReturn HashMap = new TVReturn();
				ClassTV ReturnTV = null;
				double calculation;
				boolean done = false;
				//String file = "";
				
				in.nextLine();
				do
				{
					System.out.println("Please enter the id number of the returned TV: ");
					ReturnID = in.nextLine();
					ReturnTV = HashMap.returnTV(ReturnID);
					if(ReturnTV == null)
					{
						System.out.println("Item not found in the TV sold list. Please re-enter!");
					}
					else
					{
						System.out.println("Found Item:");
						System.out.println(ReturnTV);
						calculation = (ReturnTV.getmytvType().getPrice()+(ReturnTV.getmytvType().getPrice()*mytax));
						System.out.printf("\nThe customer should receive credit for: $%.2f", calculation);
						
						returndone = true;
					}
					
				}while(returndone == false);
				
				
				myTVStack.push(Alltv);
				System.out.println("\n\nThere are " + myTVStack.size() + " TV's left in inventory");
				return idNumber;
	}
	
	//#4 This function helps me restock my inventory
	public static int restockinventory(Stack<ClassTV> myTVStack, int idNumber)
	{
			for (int i = 1; i < TVSize; i++)
			{
				idNumber++;
				String tvID = NewID + String.valueOf(idNumber);
				ClassTV Alltv = new ClassTV(tvID);
				myTVStack.push(Alltv); 
			}
			
			//The PrintLn to print out the items left in the inventory
			System.out.println("There are " + myTVStack.size() + " TV's left in inventory");
			return idNumber;
	}
	
	//Update Customer
	
	//#6 This function helps me take the customer information.
	public static void customer_purchase(Stack<ClassTV>myTVStack, Queue<Customer> customerQueue, int idNumber, Scanner in, CustomerData myCustomerList, BinaryTree myTVModel)
	{
		ArrayList<String>TVpurchased = new ArrayList<>(); //My ArrayList
		boolean flag = false;
		int NumPurchased = 0;
		Customer CheckList = null;
		String CustomerName = null;
		boolean SavingtoFile = false;
	
		
		System.out.print(myCustomerList);
		if (myTVStack.isEmpty()) // if the inventory is empty, display error error
		{
			System.out.println("Error - TV is not available");
		}
		else {
				
					System.out.println("\nPlease enter the customer account number or none: "); //Asking for the customer account number
					String AccountNumber = in.next();
					CheckList = myCustomerList.FindCustomerNumber(AccountNumber);
					if(CheckList != null)
					{
						CustomerName = CheckList.getCustomerName();
						System.out.printf("Customer is: %s", CheckList.getCustomerName());
						
					}
					else
					{
						//String SearchID = null;
						//boolean notfound = false;
						System.out.println("Customer was not found");
		
						in.nextLine();
						
						System.out.println("\nPlease enter the customer name: "); //Asking for the customer name
						CustomerName = in.nextLine();
			
						SavingtoFile = true;
						Customer SearchCustomer = new Customer(CustomerName, AccountNumber);
						myCustomerList.AddCustomer(SearchCustomer); 
					}
						
					//Lab 5 Add on begins
					
					TVInformation(myTVModel);
					
					boolean done4 = false;
					TVType myTVtype = null;
					String TVBrand = null;
					String TVModel = null;
					
					in.nextLine();
				do
					{
						
						System.out.print("\n\nPlease enter the brand: "); //Get the brand form the customer
						TVBrand = in.nextLine();
						
						//in.nextLine();
						System.out.print("Please enter the model: "); //Get the model from the customer
						TVModel = in.nextLine();
						
						//TVType TVInfo = BinaryTree.Search(Brand, Model); //Search for TV in the binary tree
						myTVtype = myTVModel.SearchTV(myTVModel.getRootTree(), TVBrand, TVModel);
						
						if(myTVtype == null)
						{
							System.out.print("TV Not Found, Please Reenter!");	
						}
						else
						{
							done4 = true;
							//System.out.println(myTVtype);
							System.out.printf("\n%-22s %-20s $%-30.2f", myTVtype.getBrand(), myTVtype.getModel(), myTVtype.getPrice());
						
						}	
					}while (done4 == false); 
					
				//End of lab 5 add on.
						
				do
				{
					try
					{
						System.out.println("\n\nPlease enter the number of TV purchased: "); //Number of tv purchased
						NumPurchased = in.nextInt();
						
						if(NumPurchased <= 0 ) //Checking to see if the number of TV entered is less than  zero
						{
							System.out.println("Error - you cannot enter in a negative value of TVs to purchase, please reenter");
							//return ;
						}
						else if (NumPurchased > myTVStack.size())
						{
							System.out.println("Error - You can not buy more than what is in stock");
						}
						else
						{
							flag = true;
						}
						
					}catch(InputMismatchException e )
					{
						System.out.println("Error - Input must be a Number. Please try again");
						in.nextLine();
					}
						
				}while(!flag);
				
				System.out.printf("Customer %s Purchased the following TVs:\n", CustomerName); //Tv's that have been placed for sales
				for(int i =0; i < NumPurchased; i++)
				{
					ClassTV tvID = myTVStack.pop();
					System.out.println(tvID);
					String myID = tvID.getID();
					TVpurchased.add(myID); //Adding TV to the array List
				}
				
				boolean done =false, validinput = false;
				
				//Lab 6 Begins
				
					do
					{
						System.out.println("\n\nDoes the customer want TVs Delivered? (y or n) "); //Asking the user to enter in y or n
						char choice = in.next().charAt(0);
						if(choice =='y' | choice == 'Y') // If/else statement to check
						{
							DeliveryInfo(in, CustomerName, AccountNumber, NumPurchased); //Creating my delivery function for the file 
							System.out.println("Successfully wrote to the file.");
							done = true;
							validinput = true;
						}
						else if(choice == 'n' | choice == 'N')
						{
							validinput = true;	
						}
						else
						{
							System.out.println("Error - Invalid Input. Please try again. Thank you!!"); //Invalid input prompt
						}
						
					}while(validinput == false);
						
			//Lab 6 Ends
						
				//The PrintLn to print out the items left in the inventory
				System.out.println("There are " + myTVStack.size() + " TV's left in inventory");
				if(SavingtoFile == true)
				{
					System.out.println("\nCustomer Data has been updated, you must save now!!\n");
					SaveChanges(myCustomerList, in); //Saving changes and adding the customer to the file.
				}
					
		Customer myCustomer = new Customer(CustomerName, AccountNumber, NumPurchased, TVpurchased, myTVtype); 
		customerQueue.add(myCustomer);
		}
		
	}
	
	//#7 This Function helps the customers to checkout
	public static void checkout(Queue<Customer> customerQueue)
	{
		if (customerQueue.isEmpty())
		{

		}
		
		else
		{
			Customer CustomerCheckout= customerQueue.remove();
			System.out.print(CustomerCheckout);
			System.out.printf("\n\nThere are %d customer(s) left in the checkout ", customerQueue.size());
		}	
	}
	
	//#8 This function helps me display my inventory
	public static void displayinventory(Stack<ClassTV> myTVStack)
	{
		try {
			//The PrintLn to print out the items left in the inventory
			System.out.println("The following " + myTVStack.size() + " TV's are left in inventory\n");
			for (ClassTV Alltv : myTVStack)
			{
				System.out.println(Alltv); 
			}
		
		}catch(Exception e) 
			{
				System.out.println("Error - please try again");
			}
	}	
	
	//#9 Ending the program
	public static boolean endprogram(Queue<Customer> customerQueue, Stack<ClassTV>myTVStack)
	{
		boolean done = false;
		if(!customerQueue.isEmpty())
		{
			System.out.println("There is still customer who have not checked out.");
			System.out.println("Please make sure all customers are processed before ending the program");
			done = false;
		}
		else
		{
			displayinventory(myTVStack); //Displaying the inventory that is left at end program
			System.out.println("Thank you for using the program");
			done = true;
		}
		return done;
	}
	
	//Reading the Customer Data From the file
	public static   CustomerData  ReadcustomerData(Scanner in)
	{
		boolean done = false;
		String file = "";
		CustomerData myCustomerList = new CustomerData ();
		
		do
		{
			try {
				
				System.out.println("Please enter the Customer List file paths: "); //Asking the user to enter the file path
				file = in.next();
				File myfile = new File(file);
				Scanner readmyfile = new Scanner(myfile);
				
				while(readmyfile.hasNextLine())
				{
					String Readname = readmyfile.nextLine(); //Read the file
					String ReadID = readmyfile.nextLine(); //Read the file
					Customer myCustomerData = new Customer(Readname, ReadID);
					
					myCustomerList.AddCustomer(myCustomerData);
				}
				readmyfile.close(); //Close the file
				done = true;
			}catch(FileNotFoundException e)
				{
					System.out.println("Error, please enter the correct file path"); //the error message for wrong file path
				}
			
		}while(done == false);
		
		return myCustomerList;
	}
	
	//#1 Adding New Customer to the program
	public static void AddCustomer(CustomerData DataList, Scanner in)
	{
		in.nextLine();
		boolean AccountNumberExist = false;
		String numbers = null;
		Customer FindingAccountNum = null;
		
		System.out.println("Add a new customer to the system");
		System.out.println("Please enter the customer name: "); //Getting the customer name to add
		String name = in.nextLine();
		
		do
		{
			System.out.println("Please enter the customer account number: ");  //Getting the account number to add
			numbers = in.nextLine();
			FindingAccountNum = DataList.FindCustomerNumber(numbers);
			if(FindingAccountNum != null) //If the customer already exist, print out error message
			{
				System.out.println("ERROR - This account already exist. Please try again");
			}
			else
			{
				AccountNumberExist = true;
			}
			
		}while(AccountNumberExist == false);
		Customer NewCustomer = new Customer(name, numbers);
		DataList.AddCustomer(NewCustomer);  //New customer added
		System.out.println("Customer has been added to the program");
		
		//Customer myNewCustomer = new Customer(name, FindingAccountNum);
		
	}
	
	//#2 Removing a customer from the list using their ID number
	public static void RemoveCustomer(CustomerData DataList, Scanner in)
	{
		boolean done = false;
		Customer RemoverFromCustomerList = null;  //Remove the customer from the file
		System.out.println("Remove a customer from the System");
		do
		{
			System.out.println("Please enter the customer ID number: ");
			String AccountNumber = in.next(); //Enter the customer ID number
			RemoverFromCustomerList= DataList.FindCustomerNumber(AccountNumber);
			if(RemoverFromCustomerList == null) //if not found, display the error message
			{
				System.out.println("ERROR - This account does not exist. Please try again");
			}
			else
			{
				done = true;
			}
			
		}while(done == false);
		
		DataList.RemoveCustomer(RemoverFromCustomerList);
		System.out.println("Customer Account has been removed");
	}
	
	//#3 Updating the Customer Name in the Program
	public static void ChangeCustomerName(CustomerData DataList, Scanner in)
	{
		boolean done = false;
		String AccountNumber =null;
		System.out.println("Change the name of a customer in the system");
		in.nextLine();
		
		do
		{
			System.out.println("Please enter the Customer existing ID number: "); //Enter existing number
			AccountNumber = in.nextLine();
			
			if(DataList.FindCustomerNumber(AccountNumber) == null) //Find the customer account number
			{
				System.out.println("ERROR - This account does not exist. Please try again");
			}
			else
			{
				done = true;
				
			}
		}while(done == false);
		
		System.out.println("Please enter the new customer name: ");
		String newName = in.nextLine();
		DataList.UpdateCustomer(AccountNumber, newName); //Update customer name and account number
		System.out.println("Customer Name Updated");
	}
	
	//#4 Saving any changes that have been made in the system 
	public static void SaveChanges(CustomerData DataList, Scanner in)
	{
		String filename = null;
		boolean done = false;
		in.nextLine();
		do
		{
			
			try
			{
				System.out.println("Please enter the name of the file to save: "); //Asking the user for the customer file
				filename = in.nextLine();
				FileWriter writetoFile = new FileWriter(filename);
				BufferedWriter writingtoFile = new BufferedWriter(writetoFile); //Using my BufferedWrited to write to the file
				
				for(Customer SavingtoFile : DataList)
				{
					writingtoFile.write(SavingtoFile.getCustomerName()); //Saving the customer name to the file
					writingtoFile.write("\n");
					
					writingtoFile.write(SavingtoFile.getAccountNumber()); //Saving the account number to the file
					writingtoFile.write("\n");
				}
				
				writingtoFile.close(); //Close the file
				done = true;
				System.out.println("File Created: CustFile.txt");
				System.out.println("Successfully wrote to the file.");
				
			}catch(IOException e)
				{
					System.out.println("ERROR - Please try again");
				}
		}while(done == false);
	}
	
	//#6 Returning the program back to main
	public static boolean ReturnMain(boolean savetodatabase, boolean savechangestofile, Scanner in)
	{
	
		boolean done =false, validinput = false;
		
		if(savetodatabase == true && savechangestofile == false)
		{
			do
			{
				System.out.println("\n\nWarning - you have not saved any changes. Do you want to return to the main menu? (y or n) "); //Asking the user to enter in y or n
				char choice = in.next().charAt(0);
				if(choice =='y' | choice == 'Y') // If/else statement to check
				{
					System.out.println("Program returned to Main Menu");
					done = true;
					validinput = true;
				}
				else if(choice == 'n' | choice == 'N')
				{
					validinput = true;	
				}
				else
				{
					System.out.println("Error - Invalid Input. Please try again. Thank you!!"); //Invalid input prompt
				}
				
			}while(validinput == false);
		}
		else
		{
			done = true;
		}
		
		return done;
	} 
	
	//Lab 4 Begins: Recursion
	
	//Customer Sort Function
	public static CustomerData sortCustomerData(CustomerData DataList)
	{
		Customer[] customerArray = DataList.toArray();
		CustomerData sortDataList = new CustomerData(); //store the data
		int myArray = customerArray.length; //Determine the number of times calling the recursive function
		insertionSort(customerArray, myArray);  //Insertion sort function call
		for(int i = 0; i < customerArray.length; i++) //Loop through the CustomerArray
		{
			sortDataList.AddCustomer(customerArray[i]); //Add the customer to the customer array
		}
		return sortDataList; //return my sorted data
	}
	
	//Insertion Sort
	public static void insertionSort(Customer[] DataList, int myArray)
	{
		if(myArray <= 1)
		{
			return; //to end the recursion
		}
		insertionSort(DataList, myArray - 1);  //performs the recursion sort
		String customernumber = DataList[myArray - 1].getAccountNumber();
		Customer key = DataList[myArray - 1];
		int position = myArray - 2; //Because it need to Grap the one before [myArray - 1]
		while(position >= 0 && DataList[position].getAccountNumber().compareTo(customernumber) > 0 ) //Compare to the customer number
		{
			DataList[position + 1] = DataList[position];
			position --;
		}
		DataList[position + 1] = key;
		
	}  //Lab 4 Ends. Thank you
	
	//Lab 5  - Tree
	public static BinaryTree ReadBinaryData(Scanner in)
	{
		boolean don = false;
		String filename = "";
		
		BinaryTree myBinary = new BinaryTree();
		in.nextLine();
		do
		{
			try {
				System.out.println("Please enter the name of the binary tree: "); //Asking the user for the customer file
				filename = in.nextLine();
		
				File myfile = new File(filename);
				Scanner readmyfile = new Scanner(myfile);
				
				while(readmyfile.hasNext())
				{
					String Brand = readmyfile.nextLine(); //Read the file
					String Model = readmyfile.nextLine();
					String Price = readmyfile.nextLine();
					
					double ConvertPrice = Double.parseDouble(Price); //Convert the price to double
					TVType ConvertTV = new TVType (Brand, Model, ConvertPrice);
					myBinary.newNode(ConvertTV);
					//System.out.println(ConvertTV);
				}
				
				readmyfile.close();
				don = true;
			}catch(FileNotFoundException e) {
				System.out.println("Error - Please try again");
			}
		}while(don == false);
		
		return myBinary;
	}
	
	//
	public static void TVInformation(BinaryTree TVInDataBase)  //Display the TV Information
	{
		System.out.printf("\n\n\nTV Options\n");
		System.out.printf("\n%-15s %-25s %-25s %-25s%n", "Item", "Brand", "Model", "Cost");
		System.out.printf("%-15s %-25s %-25s %-25s%n", "-----", "------", "------" , "-----");
		TVInDataBase.Traversal(TVInDataBase.getRootTree()); //Traversal call	
	}
	
	//Lab 5 ENDS

	//Lab 6 Begins: Heaps
	public static MaxHeap ReadDeliveryInfo(Scanner in)
	{
		boolean done = false;
		
		MaxHeap myheap = new MaxHeap(NUMOFDELIVERY);
		
		in.nextLine();
		do
		{
			try
			{
				System.out.print("Please enter in the delivery info file path: "); //File entry
				String newfile = in.nextLine();
				File mydeliveryfile = new File(newfile);
				
				Scanner deliveryfile =  new Scanner(mydeliveryfile);
				while(deliveryfile.hasNext())
				{
					String Customername = deliveryfile.nextLine();
					String CustomerAddress = deliveryfile.nextLine();
					String CustomerAccountNum = deliveryfile.nextLine();
					String NumTVPurchased = deliveryfile.nextLine();
					int ConvertNumPurchased = Integer.parseInt(NumTVPurchased); //Convert String NumTVPurchased to integer
					
					Delinfo mynewDelivery = new Delinfo(Customername, CustomerAccountNum, CustomerAddress, ConvertNumPurchased);
					
					myheap.insertNode(mynewDelivery);
				}
				
				deliveryfile.close();
				done = true;
				
			}
			catch(FileNotFoundException e)
			{
				System.out.print("Error - Please try again");
			}
			
		}while(done == false);
		
		myheap.MaxHeap(); //Call my maxHeap
		//for(int displayCounter = 0; displayCounter < )
	
		return myheap;
	}
	
	//Delivery Information
	public static void DeliveryInfo(Scanner in, String DeliveryName, String ID, int TVpurchased) //Creating my delivery function for the file 
	{
		boolean check = false;
		String CustomerAddress = null;
		String filepath;
		
		in.nextLine();
		System.out.println("Please enter in the customer Address: "); //Ask for the customer address
		CustomerAddress = in.nextLine();
		
		do
		{
			try
			{
				System.out.println("Please enter in the delivery file path: "); //Ask for the file path
				filepath = in.nextLine();
				
				File deliveryfile = new File(filepath);
				//System.out.println("File already exists");  //if the file already exist.
				if(deliveryfile.isFile() == true)
				{
					//System.out.println("File already exists");  //if the file already exist.
					FileWriter myfile = new FileWriter(filepath, true);
					BufferedWriter myfile2 = new BufferedWriter(myfile); // Create the buffer writer.
					
					myfile2.append(DeliveryName); //Append the customer name
					myfile2.newLine();
					
					myfile2.append(CustomerAddress);  //Append the customer address
					myfile2.newLine();
					
					myfile2.append(ID); //Append the customer ID
					myfile2.newLine();
					
					myfile2.append(String.valueOf(TVpurchased));  //Append the number of TV purchased
					myfile2.newLine();
					
					myfile2.close();
					
				}
				else
				{
					System.out.print("Error - the file path does not exist, Please try again");
				}
					
				check = true;
			}catch(IOException e)
			{
				System.out.println("File path is invalid, Please try again"); //Error message
			}
		}while(check == false);
	}
	
	//Lab 6 ENDS 	
}





