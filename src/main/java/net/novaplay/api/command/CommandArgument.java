package net.novaplay.api.command;


public interface CommandArgument {

	void registerParameter(String pname,String ptype);
	void registerParameter(CommandParameter param);
	String getName();
	void setName(String name);
	boolean isEmpty();
	void setEmpty(boolean value);
	boolean needsParameter();
	void setNeed(boolean value);
	CommandParameter getCommandParameter();
}
