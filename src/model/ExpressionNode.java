package model;

import java.util.ArrayList;
import java.util.List;

public class ExpressionNode {
	private String myExpression;
	private String myCommand;
	private List<ExpressionNode> myChildren;
	
	public ExpressionNode() {
		myChildren = new ArrayList<ExpressionNode>();
	}
	
	public ExpressionNode(String s, String p) {
		myExpression = s;
		myCommand = p;
		myChildren = new ArrayList<ExpressionNode>();
	}

	public String getExpression() {
		return myExpression;
	}
	
	public String getCommand() {
		return myCommand;
	}
	
	public List<ExpressionNode> getChildren() {
		return myChildren;
	}
	
	public void addChild(ExpressionNode child) {
		if (child != null) {
			myChildren.add(child);
		}
	}
	
	public void setChild(ExpressionNode child, int index) {
		myChildren.add(index, child);
	}
}
