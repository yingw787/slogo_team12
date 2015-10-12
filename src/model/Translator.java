package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import commands.Command;
import commands.CommandFactory;
import engine.Controller;

public class Translator {
	private static final String TURTLE_COMMAND = "TurtleCommand";
	public static final String BASIC_SYNTAX = "BasicSyntax";
	
	private List<ExpressionNode> myCommandList;
	private CommandFactory myCommandFactory;
	private Controller myController;
	
	public Translator(List<ExpressionNode> commands, Controller controller) {
		myCommandList = commands;
		myController = controller;
		myCommandFactory = new CommandFactory();
	}
	
	public Queue<Command> translateParseTree() {
		Queue<Command> commandQueue = new LinkedList<Command>();
		for (ExpressionNode command: myCommandList) {
			commandQueue.add(translate(command));
		}
		return commandQueue;
	}
	
	//think about special cases: variable, user defined functions, list, 
	private Command translate(ExpressionNode node) {
		Command command = myCommandFactory.getCommand(node.getCommand());
		command.setValue(node.getExpression());
		//if the command type requires the controller, give it the controller
		if (command.getCommandType().equals(TURTLE_COMMAND)) {
			command.setController(myController);
		}
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

}
