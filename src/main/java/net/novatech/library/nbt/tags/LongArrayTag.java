package net.novatech.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongArrayTag extends Tag {
	
	private long[] value;
	
	public LongArrayTag(String name) {
		this(name, new long[0]);
	}
	
	public LongArrayTag(String name, long[] value) {
		super(name);
		this.value = value;
	}
	
	@Override
	public long[] getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.value = new long[this.value.length];
		for(int i = 0; i < this.value.length; i++) {
			this.value[i] = in.readLong();
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.value.length);
		for(int i = 0; i < this.value.length; i++) {
			out.writeLong(this.value[i]);
		}
	}

}
