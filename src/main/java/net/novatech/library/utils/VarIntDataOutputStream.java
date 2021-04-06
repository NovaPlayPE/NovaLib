package net.novatech.library.utils;

import java.io.DataOutput;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class VarIntDataOutputStream extends FilterOutputStream implements DataOutput{
	
	
	public VarIntDataOutputStream(OutputStream stream) {
		super(stream);
	}

	@Override
	public void write(int b) throws IOException {
		this.out.write(b);
		
	}

	@Override
	public void write(byte[] b) throws IOException {
		this.out.write(b);
		
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		this.out.write(b, off, len);
		
	}

	@Override
	public void writeBoolean(boolean v) throws IOException {
		this.out.write(v ? 1 : 0);
		
	}

	@Override
	public void writeByte(int v) throws IOException {
		this.out.write(v);
		
	}

	@Override
	public void writeShort(int v) throws IOException {
		this.out.write(v & 0xFF);
		this.out.write((v >>> 8) & 0xFF);
		
	}

	@Override
	public void writeChar(int v) throws IOException {
		this.out.write(v & 0xFF);
		this.out.write((v >>> 8) & 0xFF);
		
	}

	@Override
	public void writeInt(int v) throws IOException {
		VarInt.writeVarInt(this.out, v);
		
	}

	@Override
	public void writeLong(long v) throws IOException {
		VarInt.writeVarLong(this.out, v);
		
	}

	@Override
	public void writeFloat(float v) throws IOException {
		this.writeInt(Float.floatToIntBits(v));
		
	}

	@Override
	public void writeDouble(double v) throws IOException {
		this.writeLong(Double.doubleToLongBits(v));
		
	}

	@Override
	public void writeBytes(String s) throws IOException {
		int len = s.length();
		for(int i = 0; i < len; i++) {
			this.out.write((byte) s.charAt(i));
		}
	}

	@Override
	public void writeChars(String s) throws IOException {
		int len = s.length();
		for(int i = 0; i < len; i++) {
			char c = s.charAt(i);
			this.out.write(c & 0xFF);
			this.out.write((c >>> 8) & 0xFF);
		}
		
	}

	@Override
	public void writeUTF(String s) throws IOException {
		byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
		VarInt.writeUnsignedVarInt(this.out, bytes.length);
		this.out.write(bytes);
		
	}

}
