package net.novaplay.api.command;

public interface CommandParameter {

	String getParameterType();
	String getParameterName();
	void setParameterType(String type);
	void setParameterName(String name);
}
