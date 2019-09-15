package net.novaplay.api.command;

public class CommandParameter {

	protected String paramType = "string";
	protected String paramName;
	
	public CommandParameter(String name, String type) {
		this.paramType = type;
		this.paramName = name;
	}
	
	public String getParameterType(){ return this.paramType; }	
	public String getParameterName() { return this.paramName; }


}
