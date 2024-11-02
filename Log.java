package bank;

import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class Log {
	protected final String author = "Brady L Kerins";
	private DefaultMutableTreeNode node_1;
	private JTree tree;
	private double sumTotal;
	private String history;
	protected String name;
	private double maxTotal;
	protected final String messages[] = {
			"\n","Withdrawn: $"," Transfered: $" ,"'s Transfer failed."
	};
	public Log(String name) {
			this.name = name;
			this.node_1 = null;
			this.tree = null;
			this.sumTotal = 0.0;
			this.history = "";
			this.maxTotal = 10000;
	}
	public Log(String name, DefaultMutableTreeNode node_1, JTree tree) {
			this.name = name;
			this.node_1 = node_1;
			this.tree = tree;
			this.sumTotal = 0.0;
			this.history = "";
			this.maxTotal = 10000;
	}//FrontEnd
	private final void removeNodeChild(DefaultMutableTreeNode node_1) {
			try {
				MutableTreeNode last = (MutableTreeNode)node_1.getLastChild();
				node_1.remove(last);
				sumTotal = Double.parseDouble(last.toString());		
			}catch(Exception e) {
				System.out.println(e.getCause());
			}
	}
	private final void updateTreeNode() {	
			if(tree !=null) {
				if(node_1 !=null) {	
					removeNodeChild(node_1);
					node_1.add(new DefaultMutableTreeNode(Double.toString(sumTotal)));
					tree.updateUI();
				}
			}
	}
	protected final void withdrawl(double subtraction, String failMessage) {
			if(sumTotal<maxTotal) {
				if(this.sumTotal>0) {			
					this.sumTotal -=subtraction;
					updateTreeNode();
					history += (this.messages[0]+this.name+ this.messages[1]+ subtraction);
				}else {
					history += (failMessage);
				}
			}
	}
	protected final void deposit(double addition, String message) {	
			if(sumTotal<maxTotal) {
				this.sumTotal +=addition;					
				updateTreeNode();
				history += (this.messages[0]+this.name+ message + addition);
			}
	}
	protected final void transfer(double lump, Log recipient) {
			if(this.sumTotal<this.maxTotal) {
				if(sumTotal>0) {
					this.withdrawl(lump);
					recipient.deposit(lump);
					history += (this.messages[0]+this.name+this.messages[2]+ lump);
				}else {
					history += (this.messages[0]+this.name+this.messages[3]);
				}
			}
	}//BackEnd
	private final void withdrawl(double subtraction) {
			if(sumTotal<maxTotal) {
				if(this.sumTotal>0) {					
					this.sumTotal -=subtraction;
					updateTreeNode();		
				}
			}
	}
	private final void deposit(double addition) {
			if(sumTotal<maxTotal) {			
				this.sumTotal +=addition;	
				updateTreeNode();
			}
	}
	protected final void updateHistory(JTextArea textArea) {
			textArea.setText(history);
	}
	public double getMaxTotal() {
			return maxTotal;
	}
	public void maxSum(Log Sender) {
		this.sumTotal = this.maxTotal;
		updateTreeNode();
		Sender.withdrawl(this.sumTotal);
		history +="Balance maxed";
	}
}
