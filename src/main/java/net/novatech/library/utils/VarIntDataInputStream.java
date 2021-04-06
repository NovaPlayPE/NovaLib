package net.novatech.library.utils;

import java.io.DataInput;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Charsets;

public class VarIntDataInputStream extends FilterInputStream implements DataInput {

	public VarIntDataInputStream(InputStream in) {
		super(in);
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		this.readFully(b, 0, b.length);

	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		if(len < 0) throw new IndexOutOfBoundsException();
		int read;
		for(int pos = 0; pos < len; pos += read) {
			read = this.in.read(b, off + pos, len - pos);
			if(read < 0) {
				throw new EOFException();
			}
		}

	}

	@Override
	public int skipBytes(int n) throws IOException {
		int total = 0;
		int skipped = 0;
		while(total < n && (skipped = (int) this.in.skip(n - total)) > 0) {
			total += skipped;
		}
		return total;
	}

	@Override
	public boolean readBoolean() throws IOException {
		int val = this.in.read();
		if(val < 0) {
			throw new EOFException();
		}
		return val != 0;
	}

	@Override
	public byte readByte() throws IOException {
		int val = this.in.read();
		if(val < 0) {
			throw new EOFException();
		}
		return (byte) val;
	}

	@Override
	public int readUnsignedByte() throws IOException {
		int val = this.in.read();
		if(val < 0) {
			throw new EOFException();
		}
		
		return val;
	}

	@Override
	public short readShort() throws IOException {
		int b1 = this.in.read();
		int b2 = this.in.read();
		if((b1 | b2) < 0) {
			throw new EOFException();
		}
		return (short) (b1 | (b2 << 8));
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return this.readShort() & 0xFFFF;
	}

	@Override
	public char readChar() throws IOException {
		int b1 = this.in.read();
		int b2 = this.in.read();
		if((b1 | b2) < 0) {
			throw new EOFException();
		}
		return (char) (b1 | (b2 << 8));
	}

	@Override
	public int readInt() throws IOException {
		return VarInt.readVarInt(this.in);
	}

	@Override
	public long readLong() throws IOException {
		return VarInt.readVarLong(this.in);
	}

	@Override
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(this.readInt());
	}

	@Override
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(this.readLong());
	}

	@Override
	@Deprecated
	public String readLine() throws IOException {
		return null;
	}

	@Override
	public String readUTF() throws IOException {
		int length = (int) VarInt.readUnsignedVarInt(this.in);
		byte[] bytes = new byte[length];
		this.in.read(bytes);
		
		return new String(bytes, Charsets.UTF_8);
	}

}
