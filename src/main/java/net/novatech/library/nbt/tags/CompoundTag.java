package net.novatech.library.nbt.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.util.Map;

import net.novatech.library.nbt.NBTIO;

import java.util.HashMap;

public class CompoundTag extends Tag {
	
	private Map<String, Tag> value;
	
	public CompoundTag(String name) {
		super(name);
		this.value = new HashMap<String, Tag>();
	}
	
	public CompoundTag(String name, Map<String, Tag> value) {
		super(name);
		this.value = value;
	}
	
	public CompoundTag add(Tag tag) {
		this.value.put(tag.getName(), tag);
		return this;
	}
	
	@Override
	public Map<String, Tag> getValue() {
		return this.value;
	}
	
	public Tag getValue(String key) {
		return getValue().get(key);
	}
	
	public StringTag getStringValue(String key) {
		if(getValue(key) instanceof StringTag) {
			return (StringTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a String tag");
	}
	
	public ByteTag getByteValue(String key) {
		if(getValue(key) instanceof ByteTag) {
			return (ByteTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a Byte tag");
	}
	public IntTag getIntValue(String key) {
		if(getValue(key) instanceof IntTag) {
			return (IntTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a Int tag");
	}
	public FloatTag getFloatValue(String key) {
		if(getValue(key) instanceof FloatTag) {
			return (FloatTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a Float tag");
	}
	
	public DoubleTag getDoubleValue(String key) {
		if(getValue(key) instanceof DoubleTag) {
			return (DoubleTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not Double tag");
	}
	
	public ShortTag getShortValue(String key) {
		if(getValue(key) instanceof ShortTag) {
			return (ShortTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a Short tag");
	}
	
	public CompoundTag getCompoundValue(String key) {
		if(getValue(key) instanceof CompoundTag) {
			return (CompoundTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a Compound tag");
	}
	
	public ListTag getListValue(String key) {
		if(getValue(key) instanceof ListTag) {
			return (ListTag)getValue(key);
		}
		throw new IllegalArgumentException("Current tag with this key is not a List tag");
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
		getValue().values().forEach(tag -> {
			try {
				NBTIO.writeTag(out, tag);
			} catch (IOException e) {}
		});
		out.writeByte(0);
		
	}

}
