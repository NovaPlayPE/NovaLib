package net.novatech.library.utils;

public class BitsUtils {
	
	static byte[] result = new byte[4];
	
	public static byte addValue(byte original, byte value) {
		return original |= value;
	}
	
	public static boolean checkValue(byte original, byte value) {
		return (original & value) != 0;
	}
	
	public static int packInt(short left, short right) {
		return (left << 16) | (right & 0xFFF);
	}

	public static long packLong(int x, int y) {
		return (((long) x) << 32) | (y & 0xffffffffL);
	}

	/** Packs two bytes with values 0-15 into one byte. */
	public static byte packByte(byte left, byte right) {
		return (byte) ((left << 4) | right);
	}

	public static byte getLeftByte(byte value) {
		return (byte) ((value >> 4) & (byte) 0x0F);
	}

	public static byte getRightByte(byte value) {
		return (byte) (value & 0x0F);
	}

	public static int getLeftShort(int field) {
		return field >>> 16;
	}

	public static int getRightShort(int field) {
		return field & 0xFFF;
	}

	public static byte getLeftByte(short field) {
		return (byte) (field >> 8);
	}

	public static byte getRightByte(short field) {
		return (byte) field;
	}

	public static short packShort(byte left, byte right) {
		return (short) ((left << 8) | (right & 0xFF));
	}

	/** The same array instance is returned each call. */
	public static byte[] getBytes(int i) {
		result[0] = (byte) (i >> 24);
		result[1] = (byte) (i >> 16);
		result[2] = (byte) (i >> 8);
		result[3] = (byte) (i /* >> 0 */);

		return result;
	}

	public static int packInt(byte b1, byte b2, byte b3, byte b4) {
		return ((0xFF & b1) << 24) | ((0xFF & b2) << 16) | ((0xFF & b3) << 8) | (0xFF & b4);
	}

	/** Packs 4 bytes into an int. */
	public static int packInt(byte[] array) {
		return ((0xFF & array[0]) << 24) | ((0xFF & array[1]) << 16) | ((0xFF & array[2]) << 8) | (0xFF & array[3]);
	}

}