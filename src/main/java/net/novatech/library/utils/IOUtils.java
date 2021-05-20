package net.novatech.library.utils;

import java.io.*;
import java.nio.charset.Charset;

public class IOUtils {

	public static final int DEFAULT_BUFFER_SIZE = 8 * 1024;
	
	public static byte[] readFullyWithoutClosing(InputStream stream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		copyTo(stream, result);
		return result.toByteArray();
	}

	public static ByteArrayOutputStream readFully(InputStream stream) throws IOException {
		try (InputStream is = stream) {
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			copyTo(is, result);
			return result;
		}
	}

	public static byte[] readFullyAsByteArray(InputStream stream) throws IOException {
		return readFully(stream).toByteArray();
	}

	public static String readFullyAsString(InputStream stream) throws IOException {
		return readFully(stream).toString();
	}

	public static String readFullyAsString(InputStream stream, Charset charset) throws IOException {
		return readFully(stream).toString(charset.name());
	}

	public static void write(String text, OutputStream outputStream) throws IOException {
		write(text.getBytes(), outputStream);
	}

	public static void write(byte[] bytes, OutputStream outputStream) throws IOException {
		copyTo(new ByteArrayInputStream(bytes), outputStream);
	}

	public static void copyTo(InputStream src, OutputStream dest) throws IOException {
		copyTo(src, dest, new byte[DEFAULT_BUFFER_SIZE]);
	}

	public static void copyTo(InputStream src, OutputStream dest, byte[] buf) throws IOException {
		while (true) {
			int len = src.read(buf);
			if (len == -1)
				break;
			dest.write(buf, 0, len);
		}
	}

}
