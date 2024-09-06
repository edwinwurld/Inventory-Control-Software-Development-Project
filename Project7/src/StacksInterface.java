/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/

public interface StacksInterface {

		public static final int STOCK_SHELVES = 1;
		public static final int FILLWEB = 2;
		public static final int RESTOCK_RETURN = 3;
		public static final int RESTOCK_INVEN = 4;
		public static final int CUSTOMER_UPDATE = 5;
		public static final int CUSTOMER_PURCHASE = 6;
		public static final int CHECKOUT = 7;
		public static final int DISPLAY_DELIVERY = 8;
		public static final int DISPLAY_INVEN = 9;
		public static final int END = 10;
		
		//Updating the customers information
		public static final int ADD_CUSTOMER = 1;
		public static final int DELETE_CUSTOMER = 2;
		public static final int CHANGE_CUSTOMER = 3;
		public static final int SAVE_CHANGES = 4;
		public static final int DISPLAY_CUSTOMER_LIST = 5;
		public static final int RETURN_MAIN = 6;
		
		public static final int TVSize = 6;
		public static final String NewID= "ABC123-";
		
		public static final double mytvcost = 199.95; 
		public static final double mytax = 0.06;
		
		public static final int NUMOFDELIVERY = 25;
		
	//	C:\Users\SCSLOANL-934U\Downloads\stack.txt
	
	//	C:\Users\SCSLOANL-934U\Downloads\CustFile.txt
		
		//C:\Users\SCSLOANL-934U\Downloads\tv.txt
		
		//C:\Users\SCSLOANL-934U\Downloads\delinfo.txt
		
		//C:\Users\SCSLOANL-934U\Downloads\tvsold.txt
}

 