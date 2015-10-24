package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import commands.Command;
import commands.CommandFactory;
import commands.UserCommand;
import engine.Controller;

public class Translator {
	private List<ExpressionNode> myCommandList;
	private CommandFactory myCommandFactory;
	private Controller myController;
	private Map<String,Double> myVariables;
	private Queue<TurtleStatus> myTurtleUpdates;
	private Map<String, UserCommand> myUserCommands;
	
	public Translator(List<ExpressionNode> commands, Map<String,UserCommand> userCommands, Controller controller) {
		myCommandList = commands;
		myController = controller;
		myUserCommands = userCommands;
		myCommandFactory = new CommandFactory(myUserCommands);
		myVariables = new HashMap<String,Double>();
		myTurtleUpdates = new LinkedList<TurtleStatus>();
	}
	
	/**
	 * Iterates through queue of commands and executes each command
	 */
	public Queue<TurtleStatus> executeCommands() { 
		Queue<Command> commandQueue = translateParseTree();
		for (Command command: commandQueue) {
			command.executeCommand(c -> executeNestedCommands(c));
		}
		return myTurtleUpdates;
	}
	
	/**
	 * Executes a command by recursively checking for nested commands and executing those first
	 */
	private Command executeNestedCommands(Command command) {
		if (command.getNumParameters() == 0 || command.getCommandType().equals(BackEndProperties.SPECIAL_FORM)) {
			return execute(command);
		} else {
			command.getParameters().stream()
				.map(c -> executeNestedCommands(c))
				.collect(Collectors.toList());
			return execute(command);
		}
	}

	private Command execute(Command command) {
		command.execute();
		return command;
	}
	
	/**
	 * Reads through list of expression trees and translates each tree into a Command object 
	 * @return queue of Command objects
	 */
	
	
	
	private Queue<Command> translateParseTree(){
		Queue<Command> commandQueue = new LinkedList<Command>(); 
		for (ExpressionNode command: myCommandList) { 
			try{
				commandQueue.add(translate(command));
			}
			catch (Exception e){
				System.out.println("I am here");
			}
		}
		return commandQueue;
	}
	
	/**
	 * Takes a single expression tree and translates each node into a command object
	 * @return a single Command object (containing any nested commands)
	 * @throws Exception 
	 */
	private Command translate(ExpressionNode node){	
		// exception handling 
		try{
			Command command = initializeCommandObject(node);
			if (node.getChildren().size() == 0) {
				List<Command> parameters = new ArrayList<Command>();
				command.setParameters(parameters);
				return command;
			}
			List<Command> parameterCommands = node.getChildren().stream()
					.map(child -> translate(child))
					.collect(Collectors.toList());
			command.setParameters(parameterCommands);
			return command;
		}
		catch (Exception e){
			// TODO: exception handling logic goes here 
			// TODO: create an event that can react to a frontend listener in order to create a popup to notify the user that the program cannot compile correctly 
			System.out.println("Exception found");
		}
		return null; 
		
	}
	
	private Command initializeCommandObject(ExpressionNode node) throws Exception{
		try
		{
			Command command = myCommandFactory.getCommand(node.getCommand(), node.getExpression());
			command.setValue(node.getExpression());
			command.setVariableMap(myVariables);
			command.setTurtleUpdates(myTurtleUpdates);
			command.setController(myController);
			command.setUserCommands(myUserCommands);
			return command;
		}
		catch (Exception e)
		{
			System.out.println("I have caught this exception");
		}
		
		return null;
	}
}
