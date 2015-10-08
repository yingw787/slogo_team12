package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import commands.Forward;

public class ParseModel {
	public static final String WHITESPACE = "\\s+";
	public static final String COMMENT = "Comment";
	public static final String CONSTANT = "Constant";
	public static final String VARIABLE = "Variable";
	public static final String COMMAND = "Command";
	public static final String LIST_START = "ListStart";
	public static final String LIST_END = "ListEnd";
	public static final String GROUP_START = "GroupStart";
	public static final String GROUP_END = "GroupEnd";
	
	private RegExUtil myRegEx;
	private ArrayList<String> myInput;
	private Map<String,Forward> myFunctions;
	private ExpressionNode myRoot;
	
	public ParseModel(String input, Map<String,Forward> functionMap) {
		myInput = initInput(input);
		myFunctions = functionMap;
		myRegEx = new RegExUtil();
		initRoot();
		//System.out.println(myRoot.getExpression());
	}
	
	public ExpressionNode createParseModel() {
		parse(myRoot);
		return myRoot;
	}
	
	private ExpressionNode parse(ExpressionNode parentNode) {
		if (myInput.size() == 0) {
			return null;
		}
		String curExpr = myInput.get(0);
        if (curExpr.trim().length() > 0) {
            try {
            	String token = myRegEx.matchPattern(curExpr);
            	ExpressionNode curNode = new ExpressionNode(curExpr, token);
            	parseToken(parentNode, curNode);
                return curNode;
            } catch (NullPointerException e) {
            	//TODO change this exception
            	//unrecognized syntax
            	e.printStackTrace();
            }
        }
        return null;
	}
	
	private void parseToken(ExpressionNode parentNode, ExpressionNode curNode) {
		myInput.remove(0);
		//System.out.println(curNode.getExpression());
		switch (parentNode.getRegEx()) {
		case VARIABLE:
			parentNode.addChild(parse(parentNode));
			break;
		case COMMAND:
			for (int i=0; i < myFunctions.get(parentNode.getExpression()).getNumParameters(); i++) {
				parentNode.addChild(parse(parentNode));
			}
			break;
		case LIST_START:
			lookForEndBrace(parentNode, curNode, LIST_END);
			break;
		case GROUP_START:
			lookForEndBrace(parentNode, curNode, GROUP_END);
			break;
		}
		parse(curNode);
	}
	
	private void lookForEndBrace(ExpressionNode parentNode, ExpressionNode curNode, String endBrace) {
		if (myInput.size() == 0) {
			//throw exception
		}
		while (!curNode.getRegEx().equals(endBrace)) {
			parentNode.addChild(parse(parentNode));
		}
	}
	
	private void initRoot() {
		try {
			myRoot = new ExpressionNode(myInput.get(0), myRegEx.matchPattern(myInput.get(0)));
			myInput.remove(myRoot.getExpression());
			//TODO maybe add check here to make myRoot is command
		} catch (NullPointerException e) {
			//TODO change this exception
			//no string passed in
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> initInput(String input) {
		while (input.length() > 0) {
			int idx = input.indexOf("#");
			if (idx < 0) {
				break;
			}
			if (idx == 0 || ((input.charAt(idx-1)+"").equals(" ") || (input.charAt(idx-1)+"").equals("\n"))) {
				int newLine = input.indexOf("\n", idx);
				int upperBound = newLine == -1 ? input.length() : newLine;
				input = input.substring(0, idx) + input.substring(upperBound, input.length());
			}
		}
		//System.out.println(Arrays.asList(input.split(WHITESPACE)).toString());
		return new ArrayList(Arrays.asList(input.split(WHITESPACE)));
	}
	
	//FOR DEBUGGING
	public void printParseModel() {
		printParseModelHelper(myRoot, 0);
	}
	
	private void printParseModelHelper(ExpressionNode curNode, int count) {
		if (curNode != null) {
			if (curNode.getChildren().size() != 0) {
				System.out.println(curNode.getExpression() + " " + count+"");
				for (ExpressionNode child: curNode.getChildren()) {
					printParseModelHelper(child, count++);
				}
			}
		}
	}
}
