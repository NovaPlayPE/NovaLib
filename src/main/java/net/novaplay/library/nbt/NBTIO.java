package net.novaplay.library.nbt;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import com.google.common.io.LittleEndianDataInputStream;

import net.novaplay.library.nbt.tags.CompoundTag;
import net.novaplay.library.nbt.tags.Tag;

public class NBTIO {
	
	private static ArrayList<NbtType> nbtTypes = new ArrayList<NbtType>();
	
	static {
		nbtTypes.add(NbtType.COMPOUND);
	}
	
	public static CompoundTag read(File file, ByteOrder order) throws IOException {
		return read(file,order,true);
	}
	
	public static CompoundTag read(File file, ByteOrder order, boolean compress) throws IOException {
		InputStream in = new FileInputStream(file);
		if(compress) {
			in = new GZIPInputStream(in);
		}
		Tag tag = readTag(in,order);
		if(tag instanceof CompoundTag) {
			return (CompoundTag)tag;
		}
		throw new IOException("Root tag is not CompoundTag");
	}
	
	public static Tag readTag(InputStream in, ByteOrder order) throws NullPointerException, IOException {
		DataInput input;
		if(order == ByteOrder.LITTLE_ENDIAN) {
			input = new LittleEndianDataInputStream(in);
		} else {
			input = new DataInputStream(in);
		}
		return readTag(input);
		
	}
	
	public static Tag readTag(DataInput in) throws NullPointerException, IOException {
		int type = in.readByte();
		if(type == 0) return null;
		
		String name = in.readUTF();
		NbtType t = matchForType(type);
		Tag tag = null;
		Class<? extends Tag> clazz = t.getClassInstance(); 
		try {
			Constructor<? extends Tag> constructor = clazz.getDeclaredConstructor(String.class);
			constructor.setAccessible(true);
			tag = constructor.newInstance(name);
		} catch(Exception e) {
			
		}
		return tag;
	}
	
	public static NbtType matchForType(int type) {
		for(NbtType t : nbtTypes) {
			if(t.getId() == type) {
				return t;
			}
		}
		return null;
	}
	
}