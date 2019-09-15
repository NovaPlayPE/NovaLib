package net.novaplay.api.command;

import java.util.ArrayList;

public class CommandLine {

	public ArrayList<CommandArgument> args = new ArrayList<CommandArgument>();
	
	public CommandLine() {}
	
	public void registerNewArguments(CommandArgument argument) {
		if(!args.contains(argument)) {
			args.add(argument);
		}
	}
	
	public ArrayList<CommandArgument> getArguments(){
		return args;
	}

	public int getId() {
		return 0;
	}
}
