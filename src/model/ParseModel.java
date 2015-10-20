package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import commands.Command;
import commands.CommandFactory;
import commands.UserCommand;

public class ParseModel {
	public static final String VARIABLE = "Variable";
	public static final String CONSTANT = "Constant";
	public static final String LIST_START = "ListStart";
	public static final String LIST_END = "ListEnd";
	public static final String GROUP_START = "GroupStart";
	public static final String GROUP_END = "GroupEnd";
	public static final String COMMAND = "Command";
	public static final String MAKE_USER_INSTRUCTION = "MakeUserInstruction";
	public static final String WHITESPACE = "\\s+";
	public static final String[] BRACES = { "(", ")", "[", "]" };
	
	private RegExUtil myRegExUtil;
	private ArrayList<String> myInput;
	private CommandFactory myCommandFactory;
	private List<ExpressionNode> myCommandList;
	private Map<String,UserCommand> myUserCommands;
	
	public ParseModel(String input, String languageFile) {
		myInput = initInput(input);
		myUserCommands = new HashMap<String,UserCommand>();
		myCommandFactory = new CommandFactory(myUserCommands);
		myRegExUtil = new RegExUtil(languageFile);
	}
	
	public List<ExpressionNode> createParseModel() {
		myCommandList = createSubParseModel(myInput);
		return myCommandList;
	}
	
	public Map<String,UserCommand> getUserCommands() {
		return myUserCommands;
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
		
		//TODO put in try catch here?
		try {
			Command parentCommand = myCommandFactory.getCommand(parentNode.getCommand(), parentNode.getExpression());
			int numChildren = getNumChildren(input, parentNode, parentCommand);
			if (myInput.size() == 0 || parentNode.getChildren().size() == numChildren) {
				return parentNode;
			}
			while (parentNode.getChildren().size() < numChildren) {
				ExpressionNode nextNode = readNextNode(input);
				parentNode.addChild(buildSubTree(input, nextNode));
			}
			
			return parentNode;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private int getNumChildren(List<String> input, ExpressionNode parentNode, Command parentCommand) {
		String command = parentNode.getCommand();
		switch (command) {
		case GROUP_START:
			findEndBrace(input, parentNode, "(", ")");
			readNextNode(input);
			return 0;
		case LIST_START:
			findEndBrace(input, parentNode, "[", "]");
			readNextNode(input);
			return 0;
		case CONSTANT:
			return 0;
		case VARIABLE:
			return 0;
		case MAKE_USER_INSTRUCTION:
			int numVariables = input.indexOf("]") - 2;
			//TODO check for nested lists to throw exception ?
			myUserCommands.put(input.get(0), new UserCommand(input.get(0), numVariables));
			parentNode.addChild(readNextNode(input));
			return 3;
		case COMMAND:
			return myUserCommands.get(parentNode.getExpression()).getNumParameters();
		}
		if (myRegExUtil.getTurtleCommandKeys().contains(command)) {
			return parentCommand.getNumParameters();
		}
		return 0;
	}
	
	private void findEndBrace(List<String> input, ExpressionNode parentNode, String startBrace, String endBrace) {
		String[] inputArray = new String[input.size()];
		inputArray = input.toArray(inputArray);
		int endBraceIndex = getEndBraceIndex(inputArray, startBrace, endBrace);
		List<String> listSegment = input.subList(0, endBraceIndex);
		parentNode.setChildren(createSubParseModel(listSegment));
	}
	
	private int getEndBraceIndex(String[] input, String startBrace, String endBrace) {
		Stack<String> braces = new Stack<String>();
		for (int i=0; i < input.length; i++) {
			String s = input[i];
			if (s.equals(startBrace)) {
				braces.push(s);
			} else if (s.equals(endBrace)) {
				if (braces.isEmpty()) {
					return i;
				}
				braces.pop();
			}
		}
		return -1;
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
		ArrayList<String> cleanedInput = new ArrayList<String>(Arrays.asList(input.trim().split(WHITESPACE)));
		System.out.println("Input" + cleanedInput);
		return cleanedInput;
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
