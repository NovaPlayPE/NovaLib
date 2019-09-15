package net.novaplay.api.command;

public class CommandArgument {

	protected String name;
	protected boolean isEmpty = false;
	protected boolean needsParameter = false;
	protected CommandParameter param = null;
	
	public CommandArgument() {
		isEmpty = true;
	}
	
	public CommandArgument(String name) {
		this.name = name;
	}
	
	public void registerParameter(CommandParameter param) {
		this.param = param;
	}
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	public boolean isEmpty() { return this.isEmpty; }
	public void setEmpty(boolean value) { this.isEmpty = value; }
	public boolean needsParameter() { return this.needsParameter; }
	public void setNeed(boolean value) { this.needsParameter = value; }
	public void registerParameter(String a, String b) { registerParameter(new CommandParameter(a,b)); }
	public CommandParameter getCommandParameter() { return this.param; }
}
