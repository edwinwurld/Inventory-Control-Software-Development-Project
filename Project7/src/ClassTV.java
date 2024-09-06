/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/


public class ClassTV implements Comparable<ClassTV>{
	
		//Creating my string
		private String idNumber;
		private TVType mytvType; //Private instance variable of type TVType
		
		//Setting the string to null
		public ClassTV()
		{
			this.idNumber = null;
			this.mytvType = null;
		}
		
		public ClassTV(String idNumber)
		{
			this.idNumber = idNumber;
			this.mytvType = null;
		}
		
		public ClassTV(String idNumber, TVType mytvType)
		{
			this.idNumber = idNumber;
			this.mytvType = mytvType;
		}
		
		//
		public String getID()
		{
			return idNumber;
		}
		
		public void setID(String idNumber)
		{
			this.idNumber = idNumber;
		}
		
		public TVType getmytvType()
		{
			return mytvType;
		}
		
		public void setmytvType(TVType mytvType)
		{
			this.mytvType = mytvType;
		}
		
		public int compareTo(ClassTV newTV) //My compareTo method
		{
			return this.idNumber.compareTo(newTV.getID());
		}
		
		public String toString()
		{
			String output = String.format("The TV id number is: %s " , idNumber);  //Printing out the TV ID
			if(this.mytvType != null)
			{
				output += String.format("\n%-18s%-15s%2s%.2f", this.mytvType.getBrand(), this.mytvType.getModel(), "$", this.mytvType.getPrice());
				
			}
			
			return output;
		}
		
		
		
	

}
