package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatTag extends Tag {

	private float value;
	
	public FloatTag(String name) {
		this(name, 0F);
	}
	
	public FloatTag(String name, float value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public Float getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = in.readFloat();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeFloat(this.value);
	}

}
