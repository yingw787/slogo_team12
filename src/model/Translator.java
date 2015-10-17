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
	
	public Translator(List<ExpressionNode> commands, Controller controller) {
		myCommandList = commands;
		myController = controller;
		myCommandFactory = new CommandFactory();
		myVariables = new HashMap<String,Double>();
	}
	
	/**
	 * Iterates through queue of commands and executes each command
	 */
	public void executeCommands() {
		Queue<Command> commandQueue = translateParseTree();
		for (Command command: commandQueue) {
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
				.map(c -> executeNestedCommands(c));
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
	private Queue<Command> translateParseTree() {
		Queue<Command> commandQueue = new LinkedList<Command>();
		for (ExpressionNode command: myCommandList) {
			commandQueue.add(translate(command));
		}
		return commandQueue;
	}
	
	/**
	 * Takes a single expression tree and translates each node into a command object
	 * @return a single Command object (containing any nested commands)
	 */
	//think about special cases: variable, user defined functions, list, 
	private Command translate(ExpressionNode node) {
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

	private Command initializeCommandObject(ExpressionNode node) {
		Command command = myCommandFactory.getCommand(node.getCommand());
		command.setValue(node.getExpression());
		command.setVariableMap(myVariables);
		//if the command type requires the controller, give it the controller
		if (commandRequiresController(command)) {
			command.setController(myController);
		}
		return command;
	}
	
	private boolean commandRequiresController(Command command) {
		return command.getCommandType().equals(BackEndProperties.TURTLE_COMMAND) || 
				command.getCommandType().equals(BackEndProperties.TURTLE_QUERY) ||
				command.getCommandType().equals(BackEndProperties.SPECIAL_FORM);
	}

}
