package model;

import java.util.ArrayList;
import java.util.List;

public class ExpressionNode {
	private String myExpression;
	private String myRegEx;
	private List<ExpressionNode> myChildren;
	
	public ExpressionNode() {
		myChildren = new ArrayList<ExpressionNode>();
	}
	
	public ExpressionNode(String s, String p) {
		myExpression = s;
		myRegEx = p;
		myChildren = new ArrayList<ExpressionNode>();
	}

	public String getExpression() {
		return myExpression;
	}
	
	public String getRegEx() {
		return myRegEx;
	}
	
	public List<ExpressionNode> getChildren() {
		return myChildren;
	}
	
	public void addChild(ExpressionNode child) {
		myChildren.add(child);
	}
	
	public void setChild(ExpressionNode child, int index) {
		myChildren.add(index, child);
	}
}
