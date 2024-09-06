/**
@author Edwin Ekeinde
* Topic: Lab 6 - Heaps
* Date: 4/11/2024
* Description: In this program I created a Stack software project that takes inventory control in a TV store warehouse. 
*The Program also allow the user to perform multiple activities with the television such as restocking the shelves, updating inventory, and writing report updates.
*The program also uses queues to keep track of customers that are in line to checkout there our and performing calculations of the TV the bought.
*/

public class BinaryTree {
	
	private Node RootTree;
	private static boolean SearchElement;
	public int counter = 1;
	
	public BinaryTree()
	{
		this.RootTree = null;
	}
	
	public Node getRootTree()  //Get the root Tree
	{
		return RootTree;
	}
	
	public void setRootTree(Node RootTree)  //Set the rootTree
	{
		this.RootTree = RootTree;
	}
	
	public void resetSearchElement()  //Reset the static boolean flag to false 
	{
		this.SearchElement = false;
	}

	public void newNode(TVType TVadd)
	{
		if(RootTree == null)
		{
			RootTree = new Node(TVadd);
			
		}
		else
		{
			if(TVadd.getPrice() < RootTree.getTVInfo().getPrice()) //Set the lower cost of the television to the right side of the tree
			{
				if(RootTree.getLeftBranch()  == null)
				{
					this.RootTree.setLeftBranch(new Node (TVadd));
				}
				else
				{
					newNode(TVadd, RootTree.getLeftBranch()); //do Recursive call
				}
			}
			else
			{
				if(RootTree.getRightBranch() == null)
				{
					this.RootTree.setRightBranch(new Node(TVadd));
				}
				else
				{
					newNode(TVadd, RootTree.getRightBranch()); //do Recursive call
				}
			}	
		}
	}
	
	public void newNode(TVType TVadd, Node Nodetoadd)
	{
		if(TVadd.getPrice() < Nodetoadd.getTVInfo().getPrice())
		{
			if(Nodetoadd.getLeftBranch()  == null) //Check if the Left Branch is null
			{
				Nodetoadd.setLeftBranch(new Node (TVadd));
			}
			else
			{
				newNode(TVadd, Nodetoadd.getLeftBranch()); //do Recursive call

			}
		}
		else
		{
			if(Nodetoadd.getRightBranch() == null) //Check if the Right Branch is null
			{
				Nodetoadd.setRightBranch(new Node(TVadd));
			}
			else
			{
				newNode(TVadd, Nodetoadd.getRightBranch()); //do Recursive call
			}
		}
	}
	
	public void Traversal(Node tree) //the tree traversal method
	{
		
		if(tree != null)
		{
			Traversal(tree.getLeftBranch());
			String Inordertraversal = String.format("\n%d", counter++);
			System.out.printf(Inordertraversal);
			System.out.print(tree.getTVInfo());
			Traversal(tree.getRightBranch());
		}	
	}
	

	public TVType SearchTV(Node TVSearch, String TVBrand, String TVModel) //Searching for the tree method
	{
		SearchElement = false;
		TVType mytvtype = null;
		
		if(RootTree == null) //If the tree is empty
		{
			System.out.println(" The Tree is empty");
			return null;
		}
		else
		{
			mytvtype = TVSearch.getTVInfo();
			
			if((TVSearch.getTVInfo().getBrand().equals(TVBrand)) && (TVSearch.getTVInfo().getModel().equals(TVModel))) //Check to see if the TV entered is in the tree
			{
				SearchElement = true; //Reset searchElement
				return mytvtype;
			}
			else
			{
				mytvtype = null;
			}
				if(TVSearch.getLeftBranch() != null && SearchElement == false)  //Search Left Branch of the tree
			{
				mytvtype = SearchTV(TVSearch.getLeftBranch(), TVBrand, TVModel); //Recursive call
			}
				if(TVSearch.getRightBranch() != null && SearchElement == false) //Search Right Branch of the tree
			{
				mytvtype = SearchTV(TVSearch.getRightBranch(), TVBrand, TVModel);  //Recursive call.
			}	
		}
		return mytvtype;
	}
	
}


