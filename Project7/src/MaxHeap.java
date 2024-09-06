/**
@author Edwin Ekeinde
* Topic: Lab 7 - Sets and Maps
* Date: 5/2/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/
public class MaxHeap implements StacksInterface {
	
	private Delinfo[] heap; //Array of Delinfo objects
	private int Maxheapsize; 
	private int ActualheapSize;
	private static final int FRONT = 1;
	
	public MaxHeap(int heapsize) //My Constructor
	{
		this.Maxheapsize = heapsize;
		this.ActualheapSize = 0;
		heap = new Delinfo[this.Maxheapsize]; //Array if Delinfo Object
		//heap[0] = Integer.MIN_VALUE;
		
	}
	
	public int getactualheap()
	{
		return ActualheapSize;
	}
	
	private int getParentPosition(int position) //Parent position
	{
		return (position-1)/2;
	}
	
	private int LeftChild(int position) //Left Child position
	{
		return (2 * position);
	}
	
	private int RightChild(int position)
	{
		return (2 * position) + 1;
	}
	
	private boolean CheckLeaf(int position) //Check if the current position is a leaf
	{
		if(position > (ActualheapSize / 2 ) && (position <=(ActualheapSize)))
		{
			return true;	
		}
		return false;
	}
	
	public void swap(int first, int second) //Swap the position of the two nodes inside the heap
	{
		Delinfo temp = heap[first];
		heap[first] = heap[second];
		heap[second] = temp;
	}
	
	public void insertNode(Delinfo myDeliveryinfo) //a method to insert a node into the heap
	{
		if(ActualheapSize >= Maxheapsize)
		{
			return;
		}
		heap[ActualheapSize] = myDeliveryinfo;
		
		int current = ActualheapSize;
		
		//System.out.println(heap[current]);
		while(heap[current].getDeliTVPurchased() > heap[getParentPosition(current)].getDeliTVPurchased())
		{
			swap(current, getParentPosition(current));
			current = getParentPosition(current);
		}
		ActualheapSize++;
	}
	
	public void CheckBalance(int position) //Check to assure the heap is always balanced
	{
		if(!CheckLeaf(position))
		{
			if(heap[position].getDeliTVPurchased()< heap[LeftChild(position)].getDeliTVPurchased() ||
					heap[position].getDeliTVPurchased() < heap[RightChild(position)].getDeliTVPurchased())
			{
				if(heap[LeftChild(position)].getDeliTVPurchased() > heap[RightChild(position)].getDeliTVPurchased())
				{
					swap(position, LeftChild(position));
					CheckBalance(LeftChild(position));
				}
				else
				{
					swap(position, RightChild(position));
					CheckBalance(RightChild(position));
				}
			}
		}
	}
	
	public void displayHeap()
	{
		//System.out.println("PARENT NODE" + "\t" + "LEFT NODE" + "\t" + "RIGHT NODE");
		for(int i = 1; i <= ActualheapSize ; i++)
		{
			//Delinfo data = myMaxHeap.RemoveRoot();
			System.out.print(" " + heap[i] + "\t\t" + heap[2 * i] + "\t\t" + heap[2 * i + 1]);
			System.out.println();
		}
	}
	
	public void MaxHeap()
	{
		for(int position = 0; position < (ActualheapSize / 2); position++)
		{
			CheckBalance(position);
		}
	}
	
	public Delinfo RemoveRoot() //Remove the root node from the heap
	{
		Delinfo Removeinfo = heap[FRONT-1];
		heap[FRONT-1] = heap[--ActualheapSize];
		CheckBalance(FRONT-1);
		return Removeinfo;	
	}
	
	
	
	
	
}
