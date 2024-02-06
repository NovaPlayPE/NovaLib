package net.novatech.library.io;

import java.math.BigInteger;
import java.util.*;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;

import io.netty.buffer.ByteBuf;

public class ByteBufUtils {
	
	public static void writeUnsignedShort(ByteBuf buf, int value) {
        Preconditions.checkArgument(value >= 0 && value <= 0xFFFF, "Value must be between 0 and 65535");
        buf.writeShort((short) value);
    }

    public static int readUnsignedShort(ByteBuf buf) {
        return buf.readUnsignedShort();
    }

    public static void writeUnsignedInt(ByteBuf buf, long value) {
        Preconditions.checkArgument(value >= 0 && value <= 0xFFFFFFFFL, "Value must be between 0 and 4294967295");
        buf.writeInt((int) value);
    }

    public static long readUnsignedInt(ByteBuf buf) {
        return buf.readUnsignedInt();
    }

    public static void writeUnsignedLong(ByteBuf buf, BigInteger value) {
        Preconditions.checkArgument(value.signum() >= 0 && value.bitLength() <= 64, "Value must be between 0 and 2^64-1");
        byte[] bytes = value.toByteArray();
        int length = bytes.length;
        Preconditions.checkArgument(length <= 8, "Value must fit into 8 bytes");
        buf.writeBytes(bytes);
    }

    public static BigInteger readUnsignedLong(ByteBuf buf) {
        byte[] bytes = new byte[8];
        buf.readBytes(bytes);
        return new BigInteger(1, bytes);
    }
	
	public static void writeByteArray(ByteBuf buf, byte[] array) {
		writeUnsignedVarInt(buf, array.length);
		buf.writeBytes(array);
	}
	
	public static byte[] readByteArray(ByteBuf buf) {
		byte[] array = new byte[readUnsignedVarInt(buf)];
		buf.readBytes(array);
		return array;
	}
	
	public static void writeString(ByteBuf buf, String string) {
		buf.writeInt(string.length());
		buf.writeCharSequence(string, Charsets.UTF_8);
	}
	
	public static String readString(ByteBuf buf) {
		int length = buf.readInt();
		return (String) buf.readCharSequence(length,Charsets.UTF_8);
	}
	
	public static void writeUUID(ByteBuf buf, UUID uuid) {
		writeString(buf,uuid.toString());
	}
	
	public static UUID readUUID(ByteBuf buf) {
		String uid = readString(buf);
		try {
			return UUID.fromString(uid);
		} catch(Exception e) {
			return null;
		}
	}
	
	public static void writeSignedVarInt(ByteBuf buf, int integer) {
		writeUnsignedVarInt(buf, (integer << 1) ^ (integer >> 31));
	}
	
	public static int readSignedVarInt(ByteBuf buf) {
		int raw = readUnsignedVarInt(buf);
		int temp = (((raw << 31) >> 31) ^ raw) >> 1;
		return temp ^ (raw & (1 << 31));
	}
	
	public static void writeUnsignedVarInt(ByteBuf buf, int integer) {
		while((integer & 0xFFFFFF80) != 0L) {
			buf.writeByte((integer & 0x7F) | 0x80);
			integer >>>= 7;
		}
		buf.writeByte(integer & 0x7F);
	}
	
	public static int readUnsignedVarInt(ByteBuf buf) {
		int value = 0;
		int i = 0;
		int b;
		while(((b = buf.readByte()) & 0x80) != 0) {
			value |= (b & 0x7F) << i;
			i +=7;
			if(i > 35) {
				System.out.println("ngth is too big");
			}
		}
		return value | (b << i);
	}
	
	public static void writeSignedVarLong(ByteBuf buf, long value) {
		writeUnsignedVarLong(buf, (value << 1) ^ (value >> 63));
	}
	
	public static long readSignedVarLong(ByteBuf buf) {
		long raw = readUnsignedVarLong(buf);
		long temp = (((raw << 63) >> 63) ^ raw) >> 1;
		return temp ^ (raw & (1L << 63));
	}
	
	public static void writeUnsignedVarLong(ByteBuf buf, long value) {
		while((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
			buf.writeByte(((int) value & 0x7F) | 0x80);
			value >>>= 7;
		}
		buf.writeByte((int) value & 0x7F);
	}
	
	public static long readUnsignedVarLong(ByteBuf buf) {
		long value = 0L;
		int i = 0;
		int b;
		while(((b = buf.readByte()) & 0x80) != 0) {
			value |= (b & 0x7F) << i;
			i +=7;
			if(i > 63) {
				System.out.println("ngth is too big");
			}
		}
		return value | (b << i);
	}

}
