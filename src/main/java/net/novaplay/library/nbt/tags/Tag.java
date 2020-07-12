package net.novaplay.library.nbt.tags;

import lombok.NonNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import lombok.Getter;

public abstract class Tag implements Cloneable{
	
	@Getter
	private String name;
	
	public Tag(@NonNull String name) {
		this.name = name;
	}
	
	public abstract Object getValue();
	public abstract void read(DataInput in) throws IOException;
	public abstract void write(DataOutput out) throws IOException;
}