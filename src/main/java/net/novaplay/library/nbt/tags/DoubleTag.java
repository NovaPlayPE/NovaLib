package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleTag extends Tag {

	private double value;
	
	public DoubleTag(String name, double value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeDouble(this.value);
	}

}
