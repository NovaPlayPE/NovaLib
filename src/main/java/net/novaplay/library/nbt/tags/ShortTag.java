package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortTag extends Tag {

	private short value;
	
	public ShortTag(String name, short value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public Short getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = in.readShort();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeShort(this.value);
	}

}
