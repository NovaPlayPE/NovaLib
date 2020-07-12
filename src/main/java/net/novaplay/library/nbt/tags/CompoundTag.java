package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.util.Map;

import net.novaplay.library.nbt.NBTIO;

public class CompoundTag extends Tag {
	
	private Map<String, Tag> value;
	
	public CompoundTag(String name, Map<String, Tag> map) {
		super(name);
		this.value = map;
	}
	
	@Override
	public Map<String, Tag> getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException{
		try {
			Tag tag;
			while((tag = NBTIO.readTag(in)) != null) {
				getValue().put(tag.getName(), tag);
			}
		} catch(EOFException e) {
			throw new IOException("Did not received EndTag...");
		}
	}

	@Override
	public void write(DataOutput out) throws IOException{
		getValue().forEach((str, tag) -> {
			try {
				NBTIO.writeTag(out, tag);
			} catch (IOException e) {}
		});
		out.writeByte(0);
		
	}

}
