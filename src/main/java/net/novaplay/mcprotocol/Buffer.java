package net.novaplay.mcprotocol;
import java.util.UUID;
public interface Buffer {
	
	void writeByte(int b);
	void writeBoolean(boolean b);
	void writeInt(int i);
	void writeVarInt(int i);
	void writeShort(int s);
	void writeLong(long l);
	void writeVarLong(long l);
	void writeDouble(double d);
	void writeFloat(float f);
	void writeChar(char c);
	void writeBytes(byte b[]);
	void writeBytes(byte b[], int l);
	void writeInts(int[] i);
	void writeInt(int[] i, int l);
	void writeShorts(short[] s);
	void writeShorts(short[] s, int l);
	void writeDoubles(double[] d);
	void writeDoubles(double[] d, int l);
	void writeFloats(float[] f);
	void writeFloats(float[] f, int l);
	void writeString(String s);
	void writeUUID(UUID uuid);

}
