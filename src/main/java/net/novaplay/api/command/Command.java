package net.novaplay.api.command;

public interface Command {

	CommandLine getCommandLine(int index);
	CommandLine createNewCommandLine(CommandLine line);
}
