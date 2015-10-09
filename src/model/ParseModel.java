package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import commands.Command;

public class ParseModel {
	public static final String WHITESPACE = "\\s+";
	
	private RegExUtil myRegEx;
	private ArrayList<String> myInput;
	private Map<String,Command> myCommands;
	private ExpressionNode myRoot;
	private List<ExpressionNode> myCommandList;
	
	public ParseModel(String input, Map<String,Command> functionMap, String languageFile) {
		myInput = initInput(input);
		myCommands = functionMap;
		myRegEx = new RegExUtil(languageFile);
		initRoot();
		//System.out.println(myRoot.getExpression());
	}
	
	public ExpressionNode createParseModel() {
		parse();
		return myRoot;
	}
	
	private void parse() {
		myCommandList = new ArrayList<ExpressionNode>();
		buildSubTree(myRoot);
		myCommandList.add(myRoot);
		while (myInput.size() > 0) {
			ExpressionNode nextNode = readNextNode();
			buildSubTree(nextNode);
			myCommandList.add(nextNode);
		}
	}

	private ExpressionNode buildSubTree(ExpressionNode parentNode) {
		Command parentCommand = myCommands.get(parentNode.getCommand());
		if (myInput.size() == 0) {
			return parentNode;
		} else if (parentNode.getChildren().size() == parentCommand.getNumParameters()) {
			return parentNode;
		}
		while (parentNode.getChildren().size() < parentCommand.getNumParameters()) {
			ExpressionNode nextNode = readNextNode();
			parentNode.addChild(buildSubTree(nextNode));
		}
		return null;
	}

	private ExpressionNode readNextNode() {
		String nextExpr = myInput.get(0);
		myInput.remove(nextExpr);
		ExpressionNode nextNode = new ExpressionNode(nextExpr, myRegEx.matchPattern(nextExpr));
		return nextNode;
	}
	
//	private ExpressionNode parse(ExpressionNode parentNode) {
//		if (myInput.size() == 0) {
//			return null;
//		}
//		String curExpr = myInput.get(0);
//        if (curExpr.trim().length() > 0) {
//            try {
//            	String token = myRegEx.matchPattern(curExpr);
//            	ExpressionNode curNode = new ExpressionNode(curExpr, token);
//            	parseToken(parentNode, curNode);
//                return curNode;
//            } catch (NullPointerException e) {
//            	//TODO change this exception
//            	//unrecognized syntax
//            	e.printStackTrace();
//            }
//        }
//        return null;
//	}
//	
//	private void parseToken(ExpressionNode parentNode, ExpressionNode curNode) {
//		myInput.remove(0);
//		//System.out.println(curNode.getExpression());
//		switch (parentNode.getRegEx()) {
//		case VARIABLE:
//			parentNode.addChild(parse(parentNode));
//			break;
//		case COMMAND:
//			for (int i=0; i < myFunctions.get(parentNode.getExpression()).getNumParameters(); i++) {
//				parentNode.addChild(parse(parentNode));
//			}
//			break;
//		case LIST_START:
//			lookForEndBrace(parentNode, curNode, LIST_END);
//			break;
//		case GROUP_START:
//			lookForEndBrace(parentNode, curNode, GROUP_END);
//			break;
//		}
//		parse(curNode);
//	}
//	
//	private void lookForEndBrace(ExpressionNode parentNode, ExpressionNode curNode, String endBrace) {
//		if (myInput.size() == 0) {
//			//throw exception
//		}
//		while (!curNode.getRegEx().equals(endBrace)) {
//			parentNode.addChild(parse(parentNode));
//		}
//	}
	
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
		return new ArrayList(Arrays.asList(input.split(WHITESPACE)));
	}
	
	//FOR DEBUGGING
	public void printParseModel() {
		for (int i=0; i < myCommandList.size(); i++) {
			printTree(myCommandList.get(i), 0);
		}
	}
	
	private void printTree(ExpressionNode curNode, int count) {
		if (curNode != null) {
			System.out.println(curNode.getExpression() + " " + count+"");
			if (curNode.getChildren().size() != 0) {
				count++;
				for (ExpressionNode child: curNode.getChildren()) {
					printTree(child, count);
				}
			}
		}
	}
}
