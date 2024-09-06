/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/


public class Node {
	
	private TVType TVInfo; //Private instant variables;
	private Node LeftBranch;
	private Node RightBranch;
	
	public Node(TVType TVInfo) //Single Constructor that takes the TVtype as a parameter.
	{
		this.TVInfo = TVInfo;
		this.LeftBranch = null;
		this.RightBranch = null;			
	}
	
	public TVType getTVInfo() //Get TV type
	{
		return TVInfo;
	}
	
	public void setTVInfo(TVType TVInfo)  //Set TVtype
	{
		this.TVInfo = TVInfo;
	}
	
	public Node getLeftBranch()  //Get Left Branch
	{
		return LeftBranch;
	}
	
	public void setLeftBranch(Node LeftBranch) // Set Left Branch
	{
		this.LeftBranch = LeftBranch;
	}
	
	public Node getRightBranch()  //Get Right Branch
	{
		return RightBranch;
	}
	
	public void setRightBranch(Node RightBranch)  //Set Right Branch
	{
		this.RightBranch = RightBranch;
	}
}


