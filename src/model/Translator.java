package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import commands.Command;
import commands.CommandFactory;

public class Translator {
	List<ExpressionNode> myCommandList;
	CommandFactory myCommandFactory;
	
	public Translator(List<ExpressionNode> commands) {
		myCommandList = commands;
		myCommandFactory = new CommandFactory();
	}
	
	public Queue<Command> translateParseTree() {
		Queue<Command> commandQueue = new LinkedList<Command>();
		for (ExpressionNode command: myCommandList) {
			commandQueue.add(translate(command));
		}
		return commandQueue;
	}
	
	private Command translate(ExpressionNode node) {
		Command command = myCommandFactory.getCommand(node.getCommand());
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
