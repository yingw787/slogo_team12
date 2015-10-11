package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import commands.Command;

public class ParseModel {
	public static final String VARIABLE = "Variable";
	public static final String CONSTANT = "Constant";
	public static final String LIST_START = "ListStart";
	public static final String LIST_END = "ListEnd";
	public static final String GROUP_START = "GroupStart";
	public static final String GROUP_END = "GroupEnd";
	public static final String WHITESPACE = "\\s+";
	public static final String[] BRACES = { "(", ")", "[", "]" };
	
	private RegExUtil myRegExUtil;
	private ArrayList<String> myInput;
	private Map<String,Command> myCommands;
	private List<ExpressionNode> myCommandList;
	
	public ParseModel(String input, Map<String,Command> functionMap, String languageFile) {
		myInput = initInput(input);
		myCommands = functionMap;
		myRegExUtil = new RegExUtil(languageFile);
	}
	
	public List<ExpressionNode> createParseModel() {
		myCommandList = createSubParseModel(myInput);
		return myCommandList;
	}
	
	private List<ExpressionNode> createSubParseModel(List<String> input) {
		List<ExpressionNode> commandList = new ArrayList<ExpressionNode>();
		while (input.size() > 0) {
			ExpressionNode nextNode = readNextNode(input);
			buildSubTree(input, nextNode);
			commandList.add(nextNode);
		}
		return commandList;
	}

	private ExpressionNode buildSubTree(List<String> input, ExpressionNode parentNode) {
		Command parentCommand = myCommands.get(parentNode.getCommand());
		int numChildren = getNumChildren(parentNode, parentCommand);
		if (myInput.size() == 0 || parentNode.getChildren().size() == numChildren) {
			return parentNode;
		}
		while (parentNode.getChildren().size() < numChildren) {
			ExpressionNode nextNode = readNextNode(input);
			parentNode.addChild(buildSubTree(input, nextNode));
		}
		return parentNode;
	}
	
	private int getNumChildren(ExpressionNode parentNode, Command parentCommand) {
		String command = parentNode.getCommand();
		if (myRegExUtil.getTurtleCommandKeys().contains(command)) {
			return parentCommand.getNumParameters();
		} else {
			switch (command) {
			case GROUP_START:
				findEndBrace(parentNode, ")");
				readNextNode(myInput);
				return 0;
			case LIST_START:
				findEndBrace(parentNode, "]");
				readNextNode(myInput);
				return 0;
			case CONSTANT:
				return 0;
			case VARIABLE:
				return 1;
			}
		}
		return 0;
	}
	
	private void findEndBrace(ExpressionNode parentNode, String endBrace) {
		int endBraceIndex = myInput.indexOf(endBrace);
		List<String> listSegment = myInput.subList(0, endBraceIndex);
		parentNode.setChildren(createSubParseModel(listSegment));
	}

	private ExpressionNode readNextNode(List<String> input) {
		String nextExpr = input.get(0);
		input.remove(nextExpr);
		ExpressionNode nextNode = new ExpressionNode(nextExpr, myRegExUtil.matchPattern(nextExpr));
		return nextNode;
	}
	
	/**
	 * Pre-process input before parsing to remove comments and add spaces after beginning braces
	 */
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
		for (String brace: BRACES) {
			input = findBraces(input, brace);
		}
		return new ArrayList(Arrays.asList(input.trim().split(WHITESPACE)));
	}
	
	private String findBraces(String input, String brace) {
		int index = 0;
		int braceIdx = input.indexOf(brace, index);
		while (braceIdx > 0) {
			if (braceIdx == input.length()-1) {
				input = input.substring(0, braceIdx) + " " + input.substring(braceIdx, braceIdx+1);
				break;
			}
			input = input.substring(0, braceIdx) + " " + input.substring(braceIdx, braceIdx+1) + " " + input.substring(braceIdx+1, input.length()); 
			braceIdx = input.indexOf(brace, braceIdx+2);
		}
		return input;
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
