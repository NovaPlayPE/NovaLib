package net.novaplay.api.command;

import java.util.ArrayList;

public class CommandLine {

	public ArrayList<CommandArgument> args = new ArrayList<CommandArgument>();
	protected int id = 0;
	public CommandLine() {this(0);}
	public CommandLine(int id) { this.id = id; }
	
	public void registerNewArguments(CommandArgument argument) {
		if(!args.contains(argument)) {
			args.add(argument);
		}
	}
	
	public ArrayList<CommandArgument> getArguments(){
		return args;
	}

	public int getId() {
		return id;
	}
}
