package bTreePack;


/**
 * Author: Andrew Roney
 * Date: 09/12/2022
 * Description: This class is where the GUI is created and the program is ran from.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class RunBinaryTree extends JFrame{
	private JTextField input = new JTextField(20);
	private JTextField output = new JTextField(20);
	private static BinaryTree inputTree;
	
	public static void main(String[] args) {
		RunBinaryTree treeFrame = new RunBinaryTree();
		treeFrame.setVisible(true);
		
	}
	
//Constructor: Titles and calls the setup to form the complete Interface.
	public RunBinaryTree() {
		super("Binary Tree");//Frame title
		setupFrame();
	}
	
//Method: Creates the entire layout and features of the GUI.
	public void setupFrame() {
		setSize(700, 175);
		setLocationRelativeTo(null);//Start at center of screen.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Stop program when window is closed. 
		setLayout(new GridLayout(3, 1));//For organization.
		
		JComponent[] inputComponents = {//Array of components containing 1 JLabel.
				new JLabel("Enter Expression"), input
		};
		JComponent[] outputComponents = {//Array of components containing 1 JLabel.
				new JLabel("Output"), output
		};
		
		JButton[] buttonComponents = {//Array of buttons
				new JButton("Make Tree"),
				new JButton("Is Balanced?"),
				new JButton("Is Full?"),
				new JButton("Is Proper?"),
				new JButton("Height"),
				new JButton("Nodes"),
				new JButton("Inorder")
		};
		
		makeFlowPanel(inputComponents);
		makeFlowPanel(buttonComponents);
		makeFlowPanel(outputComponents);
		
		addActionListeners(buttonComponents);
		output.setEditable(false);
		setResizable(true);
	}
	
//Method: This is the layout scheme for the components that were added.
	private void makeFlowPanel(JComponent[] components) {
		JPanel panel = new JPanel(new FlowLayout());
		for(Component component : components) {//For each components there are listed, place the component on the GUI.
			add(component);
		}
		add(panel);
	}
	
	
//Method: Assigns each button to an action listener.
	private void addActionListeners (JButton[] buttons) {
		for(JButton button : buttons) {
			button.addActionListener(treeListener);
		}
	}
	
	
//Method: Gives the button ActionListener and event.
	private final ActionListener treeListener = event -> {
		try {
			switch((event.getActionCommand())) {
			case "Make Tree":
				inputTree = new BinaryTree(input.getText());
				output.setText(inputTree.toString());
				break;
			
			case "Is Balanced?":
				output.setText((inputTree.isBalanced())? "Is Balanced":"Not Balanced");
				break;
			
			case "Is Full?":
				output.setText((inputTree.isFull())? "Is Full":"Not Full");
				break;
				
			case "Is Proper?":
				output.setText((inputTree.isProper())? "Is Proper":"Not Proper");
				break;
				
			case "Height":
				output.setText(String.valueOf(inputTree.height()));
				break;
				
			case "Nodes":
				output.setText(String.valueOf(inputTree.nodes()));
				break;
				
			case "Inorder":
				output.setText(String.valueOf(inputTree.inOrder()));
				break;
			}
		}
		catch(InvalidTreeSyntax e) {
			JOptionPane.showMessageDialog(getParent(),  e.getMessage());
		}
		catch(IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(getParent(),  "No input given!");
		}
	};
}
