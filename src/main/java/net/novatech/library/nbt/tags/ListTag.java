package net.novatech.library.nbt.tags;

import java.util.List;
import java.util.ArrayList;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Constructor;

import lombok.NonNull;
import net.novatech.library.nbt.NBTIO;
import net.novatech.library.nbt.NbtType;

public class ListTag extends Tag {

	private List<Tag> value;
	private Class<? extends Tag> clazz = null;
	
	public ListTag(String name) {
		super(name);
		this.value = new ArrayList<Tag>();
	}
	
	public ListTag(String name, ArrayList<Tag> value) throws IllegalArgumentException {
		super(name);
		init(value);
	}
	
	private void init(ArrayList<Tag> value) throws IllegalArgumentException {
		this.clazz = null;
		this.value.clear();
		for(Tag tag : value) {
			add(tag);
		}
	}
	
	public ListTag add(@NonNull Tag tag) throws IllegalArgumentException {
		if(this.clazz == null) {
			this.clazz = tag.getClass();
		} else if(this.clazz != tag.getClass()) {
			throw new IllegalArgumentException("Tried to add " + tag.getClass().getName() + " to a list defined by " + clazz.getName());
		}
		
		this.value.add(tag);
		return this;
	}
	
	@Override
	public List<Tag> getValue() {
		return this.value;
	}

	@Override
	public void read(DataInput in) throws IOException {
		this.clazz = null;
		this.value.clear();
		
		int id = in.readUnsignedByte();
		if(id == 0) return;
		
		NbtType t = NBTIO.matchForType(id);
		
		int size = in.readInt();
		for(int i = 0; i < size; i++) {
			Tag tag = null;
			Class<? extends Tag> tagClazz = t.getClassInstance(); 
			try {
				Constructor<? extends Tag> constructor = tagClazz.getDeclaredConstructor(String.class);
				constructor.setAccessible(true);
				tag = constructor.newInstance("");
				tag.read(in);
				add(tag);
			} catch(Exception e) {}
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		if(this.clazz == null) {out.writeByte(0); return;}
		
		NbtType type = NBTIO.matchForClass(this.clazz);
		out.writeByte(type.getId());
		out.writeInt(this.value.size());
		this.value.forEach(tag -> {
			try {
				tag.write(out);
			} catch (IOException e) {}
		});
	}

}
