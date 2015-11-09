package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import commands.Command;
import commands.UserCommand;
import engine.Controller;
import exceptions.CommandNotFoundException;
import exceptions.NotEnoughParametersException;
import exceptions.PopupError;
import masterpiece.CommandFactory;

// Translator extends Observable as exception handling results must be forwarded to the view 
public class Translator {
	private List<ExpressionNode> myCommandList;
	private CommandFactory myCommandFactory;
	private Controller myController;
	private Map<String,Double> myVariables;
	private Queue<TurtleStatus> myTurtleUpdates;
	private List<Integer> myActiveTurtles;
	private Map<String, UserCommand> myUserCommands;
	
	public Translator(List<ExpressionNode> commands, Map<String,UserCommand> userCommands, List<Integer> activeTurtles, Map<String,Double> variablesMap, Controller controller) {
		myCommandList = commands;
		myController = controller;
		myUserCommands = userCommands;
		myActiveTurtles = activeTurtles;
		myVariables = variablesMap;
		myCommandFactory = new CommandFactory(myUserCommands);
		myTurtleUpdates = new LinkedList<TurtleStatus>();
	}
	
	/**
	 * Iterates through queue of commands and executes each command
	 */
	public Queue<TurtleStatus> executeCommands() throws CommandNotFoundException { 
		myController.setVariablesMap(myVariables);
		Queue<Command> commandQueue = translateParseTree();
		for (Command command: commandQueue) {
			if(command == null){
				throw new CommandNotFoundException(); 
			}
			try {
				command.executeCommandOverActiveTurtles();
			} catch (NotEnoughParametersException e) {
				PopupError popup = new PopupError(); 
				popup.generateError("Not enough parameters");
//				System.out.println("I am in Translator.java");
			}
		}
		return myTurtleUpdates;
	}
	
	/**
	 * Reads through list of expression trees and translates each tree into a Command object 
	 * @return queue of Command objects
	 */
	private Queue<Command> translateParseTree(){
		Queue<Command> commandQueue = new LinkedList<Command>(); 
		for (ExpressionNode command: myCommandList) { 
//			try{
				commandQueue.add(translate(command));
			}
//			catch (Exception e){
//				System.out.println("I am here");
//			}
//		}
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
		catch (CommandNotFoundException e){
			// TODO: exception handling logic goes here 
			// TODO: create an event that can react to a frontend listener in order to create a popup to notify the user that the program cannot compile correctly 
			// TODO: halt execution of program; 
			PopupError popup = new PopupError(); 
			popup.generateError("Exception found");
			
		}
		return null; 
		
	}
	
	private Command initializeCommandObject(ExpressionNode node) throws CommandNotFoundException{
//		try
//		{
			Command command = myCommandFactory.getCommand(node.getCommand(), node.getExpression());
			
			if(command == null){
				throw new CommandNotFoundException(); 
			}
			command.setValue(node.getExpression());
			command.setVariableMap(myVariables);
			command.setTurtleUpdates(myTurtleUpdates);
			command.setActiveTurtles(myActiveTurtles);
			command.setController(myController);
			command.setUserCommands(myUserCommands);
			return command;
//		}
//		catch (Exception e)
//		{
//			System.out.println("I have caught this exception");
//		}
//		
//		return null;
	}
}
