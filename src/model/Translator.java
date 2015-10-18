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
import engine.Controller;

public class Translator {
	private List<ExpressionNode> myCommandList;
	private CommandFactory myCommandFactory;
	private Controller myController;
	private Map<String,Double> myVariables;
	private Queue<TurtleStatus> myTurtleUpdates;
	
	public Translator(List<ExpressionNode> commands, Controller controller) {
		myCommandList = commands;
		myController = controller;
		myCommandFactory = new CommandFactory();
		myVariables = new HashMap<String,Double>();
		myTurtleUpdates = new LinkedList<TurtleStatus>();
	}
	
	/**
	 * Iterates through queue of commands and executes each command
	 */
	public void executeCommands() { // I thought the executing of commands would be done by the frontend; the point of the queue would be to pass it to the frontend and let them pop commands as needed 
		Queue<Command> commandQueue = translateParseTree();
		for (Command command: commandQueue) {
//			System.out.println(myVariables.toString());
//			System.out.println(command.returnDoubleValue());
			executeNestedCommands(command);
		}
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
		Queue<Command> commandQueue = new LinkedList<Command>(); // ANY REASON WHY THIS IS LINKED LIST
		for (ExpressionNode command: myCommandList) { // what the parse model class gives to translator 
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
			System.out.println(command.getExpression());
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
		
		if(node.getCommand().equals("Command")){ 
			// TODO: check for a user-defined function in the developed map 
			// TODO: only if it is not in the user-defined function map, do you throw the exception 
			throw new Exception("Command not found in dictionary of legal commands");
		}
		
		Command command = myCommandFactory.getCommand(node.getCommand());
//			System.out.println(command.getCommandType()); // 
		command.setValue(node.getExpression());
//			System.out.println(command.getExpression()); // 

		command.setVariableMap(myVariables);
		command.setTurtleUpdates(myTurtleUpdates);
		command.setController(myController);
		return command;
		
		
	}
}