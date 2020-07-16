package net.novaplay.library.nbt;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;
import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;

import net.novaplay.library.nbt.tags.CompoundTag;
import net.novaplay.library.nbt.tags.Tag;

public class NBTIO {
	
	private static ArrayList<NbtType> nbtTypes = new ArrayList<NbtType>();
	
	static {
		nbtTypes.add(NbtType.BYTE);
		nbtTypes.add(NbtType.SHORT);
		nbtTypes.add(NbtType.INT);
		nbtTypes.add(NbtType.LONG);
		nbtTypes.add(NbtType.FLOAT);
		nbtTypes.add(NbtType.DOUBLE);
		nbtTypes.add(NbtType.BYTE_ARRAY);
		nbtTypes.add(NbtType.STRING);
		nbtTypes.add(NbtType.LIST);
		nbtTypes.add(NbtType.COMPOUND);
		nbtTypes.add(NbtType.INT_ARRAY);
		nbtTypes.add(NbtType.LONG_ARRAY);
	}
	
	public static CompoundTag read(String path) throws IOException {
		return read(new File(path));
	}
	
	public static CompoundTag read(File file) throws IOException{
		return read(file, ByteOrder.LITTLE_ENDIAN);
	}
	
	public static CompoundTag read(File file, ByteOrder order) throws IOException {
		return read(file,order,true);
	}
	
	public static CompoundTag read(File file, ByteOrder order, boolean compress) throws IOException {
		InputStream in = new FileInputStream(file);
		if(compress) {
			in = new ZstdInputStream(in);
		}
		Tag tag = readTag(in,order);
		if(tag instanceof CompoundTag) {
			return (CompoundTag)tag;
		}
		throw new IOException("Root tag is not CompoundTag");
	}
	
	public static void write(CompoundTag tag, String path) throws IOException {
		write(tag, new File(path));
	}
	
	public static void write(CompoundTag tag, File file) throws IOException {
		write(tag, file, ByteOrder.LITTLE_ENDIAN);
	}
	
	public static void write(CompoundTag tag, File file, ByteOrder order) throws IOException {
		write(tag, file, order, true);
	}
	
	public static void write(CompoundTag tag, File file, ByteOrder order, boolean compress) throws IOException {
		if(!file.exists()) {
			if(file.getParentFile() != null && !file.getParentFile().exists()) {
				file.mkdirs();
			}
			file.createNewFile();
		}
		
		OutputStream out = new FileOutputStream(file);
		if(compress) {
			out = new ZstdOutputStream(out);
		}
		writeTag(out, tag, order);
		out.close();
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
		int type = in.readUnsignedByte();
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
	
	public static void writeTag(OutputStream out, Tag tag, ByteOrder order) throws NullPointerException, IOException {
		DataOutput output;
		if(order == ByteOrder.LITTLE_ENDIAN) {
			output = new LittleEndianDataOutputStream(out);
		} else {
			output = new DataOutputStream(out);
		}
		writeTag(output, tag);
	}
	
	public static void writeTag(DataOutput out, Tag tag) throws NullPointerException, IOException {
		if(tag == null) {out.writeByte(0);return;}
		
		NbtType t = matchForTag(tag);
		out.writeByte(t.getId());
		out.writeUTF(tag.getName());
		tag.write(out);
		
	}
	
	public static NbtType matchForClass(Class<? extends Tag> clazz) {
		for(NbtType type : nbtTypes) {
			if(type.getClassInstance() == clazz) {
				return type;
			}
		}
		return null;
	}
	
	public static NbtType matchForTag(Tag tag) {
		for(NbtType type : nbtTypes) {
			if(type.getClassInstance() == tag.getClass()) {
				return type;
			}
		}
		return null;
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