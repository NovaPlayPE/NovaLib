package net.novaplay.api.command;

import java.util.ArrayList;


public interface CommandLine {

	int getId();
	void registerNewArguments(CommandArgument argument);
	ArrayList<CommandArgument> getArguments();
	void setArguments(ArrayList<CommandArgument> args);
}
