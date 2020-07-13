package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteTag extends Tag {

	private byte value;
	
	public ByteTag(String name) {
		this(name, (byte)0);
	}
	
	public ByteTag(String name, byte value) {
		super(name);
		this.value = value;
	}

	@Override
	public Byte getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = in.readByte();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeByte((int)this.value);
	}

}
