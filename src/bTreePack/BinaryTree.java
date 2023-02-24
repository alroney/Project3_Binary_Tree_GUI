package bTreePack;

/**
 * Author: Andrew Roney
 * Date: 09/12/2022
 * Description: This class constructs a Binary Tree by taking the string from a user input, turns it into an array based on the parenthesis.
 * It then loops through the array using the Stack and adds children that fall within the parent parenthesis.
 */

/*
 * NOTE: rec = recursive
 */

import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {
	Node parent;
	Node child;
	
	
	public BinaryTree(String input) throws InvalidTreeSyntax {
		Stack<Node> nodeStack = new Stack<>();
		String[] inputArray = input.substring(1, input.length()-1).split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");//RegularExpression: Removes first and last parenthesis.Splits string into an array
		parent = new Node(inputArray[0]);
		
		for(int i = 1; i < inputArray.length - 1; i++) {//For every node, it will have 2 parenthesis.
			
			if(inputArray[i].equals("(")){
				nodeStack.push(parent);
				
				if(child != null) {
					parent = child;//Assign the current parent as the child of one on stack
				}
			}
			else if(inputArray[i].equals(")")){
				try {
					child = parent;
					parent = nodeStack.pop();
				}
				catch(EmptyStackException e) {
					throw new InvalidTreeSyntax("Incorret parenthesis!");
				}
			}
				
			else {
				child = new Node(inputArray[i]);
				if(parent != null) {
					parent.addChild(child);
				}
			}
		}//End for loop
		
		if(this.recNodes(parent) * 3 != input.length()) {
			throw new InvalidTreeSyntax("Incorrect Syntax");
		}
	}
	
	
//Method: Calls recursive method (below), which also calls recursive height method.
	public boolean isBalanced() {
		return recIsBalanced(this.parent);
	}
	
//Method: Determines if the absolute difference between branches is at most 1.
	private boolean recIsBalanced(Node root) {
		if(root == null) {
			return true;
		}
		return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1) && (recIsBalanced(root.left) && recIsBalanced(root.right));//If the absolute difference is at most 1 return true.
	}
	
//Method: Calls recursive method (below), which also calls recursive height method.
	public boolean isFull() {
		return recIsFull(this.parent, recHeight(this.parent), 0);
	}
	
//Method: Determines if the binary tree has the maximum amount of nodes for the height or not.	
	private boolean recIsFull(Node root, int height, int index) {
		if(root == null) {
			return true;
		}
		if(root.left == null && root.right == null) {
			return (height == index + 1);
		}
		
		if(root.left == null || root.right == null) {
			return false;
		}
		
		return recIsFull(root.left, height, index + 1) && recIsFull(root.right, height, index + 1);
	}
	

//Method: Calls recursive method (below), which also calls recursive height method.
	public boolean isProper() {
		return recIsProper(this.parent);
	}
	

//Method: Determines if all the nodes in the binary tree has either 0 or 2 children.
	private boolean recIsProper(Node root) {
		if(root == null) {
			return true;
		}
		
		return ((root.left != null || root.right == null) && (root.left == null || root.right != null)) && (recIsProper(root.left) && recIsProper(root.right));
	}
	
	
//Method: Calls recursive method (below), uses the algorithm left > root > right.
	public String inOrder() {
		return recInOrder(this.parent);
	}
	
	
//Method: Prints the information on the nodes in the binary tree in order.
	private String recInOrder(Node root) {
		return (root == null) ? "" : "(" + recInOrder(root.left) + root.info + recInOrder(root.right) + ")";
	}
	
	
//Method: Calls recursive method (below), which adds 1 to the larger of left or right.
	public int height() {
		return recHeight(this.parent)- 1; 
	}
	
//Method: Finds the height of the binary tree, where the root node is 0.
	private int recHeight(Node root) {
		return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));//If root == null then add 0 to the nodes instead of 1. ? = Ternary Operator, a compact conditional statement. TRUE:FALSE = values to use from, this : that.
	}
	
	
//Method: Calls recursive method (below), adds 1 for every node of left and right subtree, 0 if null.
	public int nodes() {
		return recNodes(this.parent);
	}
	
	
//Method: Finds the amount of nodes in the binary tree.
	private int recNodes(Node root) {
		return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);//If root == null then add 0 to the nodes instead of 1. ? = compact conditional statement. : = values to use from, this : that.
	}
	
//Method: Calls the root node toString.
	@Override
	public String toString() {
		return parent.toString();
	}
	
	
//CLASS: Creates nodes to be used in the binary tree and creates the methods to act on them.
	public static class Node {
		private String info;
		private Node left;
		private Node right;
		
	//Constructor: Defines the default for info.
		public Node(String info) {
			this.info = info;
		}
		
	//Method: Conditions for nodes to only have a maximum of 2 children.
		private void addChild(Node child) throws InvalidTreeSyntax {
			if(this.left == null) {
				this.setLeft(child);
			}
			
			else if(this.right == null) {
				this.setRight(child);
			}
			
			else {
				throw new InvalidTreeSyntax("Nodes can only have 2 children");
			}
		}
		
	//Method: Sets the value for the left node from the given value.
		private void setLeft(Node newLeft) {
			left = newLeft;
		}
		
	//Method: Sets the value for the right node from the given value.
		private void setRight(Node newRight) {
			right = newRight;
		}
		
	//Method: Calls the recursive method.
		@Override
		public String toString() {
			return toString(this);
		}
		
	//Method: Recursively prints out the nodes
		public static String toString(Node root) {
			return (root == null) ? "" : "(" + root.info + toString(root.left)+ toString(root.right) + ")";  
		}
	}
}


