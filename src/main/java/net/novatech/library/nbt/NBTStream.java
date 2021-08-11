package net.novatech.library.nbt;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;

import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;

import lombok.Getter;
import net.novatech.library.varint.VarIntDataInputStream;
import net.novatech.library.varint.VarIntDataOutputStream;

public enum NBTStream {
	
	BIG_ENDIAN(ByteOrder.BIG_ENDIAN),
	LITTLE_ENDIAN(ByteOrder.LITTLE_ENDIAN),
	VAR_INT(ByteOrder.LITTLE_ENDIAN);
	
	NBTStream(ByteOrder order){
		this.order = order;
	}
	
	@Getter
	private ByteOrder order;
	
	public DataOutput getOutputData(OutputStream out) {
		switch(this) {
		case BIG_ENDIAN:
			return new DataOutputStream(out);
		case LITTLE_ENDIAN:
			return new LittleEndianDataOutputStream(out);
		case VAR_INT:
			return new VarIntDataOutputStream(out);
		}
		return null;
	}
	
	public DataInput getInputData(InputStream in) {
		switch(this) {
		case BIG_ENDIAN:
			return new DataInputStream(in);
		case LITTLE_ENDIAN:
			return new LittleEndianDataInputStream(in);
		case VAR_INT:
			return new VarIntDataInputStream(in);
		}
		return null;
	}
	
}