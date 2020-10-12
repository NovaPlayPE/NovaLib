package net.novatech.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntTag extends Tag {

	private int value;
	
	public IntTag(String name) {
		this(name, 0);
	}
	
	public IntTag(String name, int value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = in.readInt();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.value);

	}

}
