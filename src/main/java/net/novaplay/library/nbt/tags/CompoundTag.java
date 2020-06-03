package net.novaplay.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Map;

public class CompoundTag extends Tag {
	public CompoundTag(String name, Map<String, Tag> map) {
		super(name);
		this.value = map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Tag> getValue() {
		return (Map<String,Tag>)this.value;
	}

	@Override
	public void read(DataInput in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(DataOutput out) {
		// TODO Auto-generated method stub
		
	}

}
