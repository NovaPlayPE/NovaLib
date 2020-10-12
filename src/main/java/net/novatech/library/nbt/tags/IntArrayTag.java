package net.novatech.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntArrayTag extends Tag {
	
	private int[] value;
	
	public IntArrayTag(String name) {
		this(name, new int[0]);
	}
	
	public IntArrayTag(String name, int[] value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public int[] getValue() {
		return this.value;
	}

	public int getValue(int index) {
		return this.value[index];
	}
	
	@Override
	public void read(DataInput in) throws IOException {
		this.value = new int[in.readInt()];
		for(int i = 0; i < this.value.length; i++) {
			this.value[i] = in.readInt();
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.value.length);
		for(int i = 0; i < this.value.length; i++) {
			out.writeInt(this.value[i]);
		}
	}

}
