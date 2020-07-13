package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteArrayTag extends Tag {
	
	private byte[] value;
	
	public ByteArrayTag(String name) {
		super(name);
		this.value = new byte[0];
	}
	
	public ByteArrayTag(String name, byte[] value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public byte[] getValue() {
		return this.value;
	}
	
	public byte getValue(int index) {
		return this.value[index];
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = new byte[in.readInt()];
		in.readFully(this.value);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.value.length);
		out.write(this.value);
	}

}
