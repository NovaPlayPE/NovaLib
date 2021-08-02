package net.novatech.library.utils;

import java.io.*;
import java.nio.charset.Charset;

public class IOUtils {
	
	public static final String SIZE_B = "bytes";
	public static final String SIZE_KB = "kilobytes";
	public static final String SIZE_MB = "megabytes";
	public static final String SIZE_GB = "gigabytes";

	public static final int DEFAULT_BUFFER_SIZE = 8 * 1024;
	
	public static double getFolderSize(File file, String size) {
		long bytes = getFolderSizeInBytes(file);
		
		return switch(size) {
			case SIZE_B -> (double) bytes;
			case SIZE_KB -> (double) bytes / 1024;
			case SIZE_MB -> (double) bytes / 1024 / 1024;
			case SIZE_GB -> (double) bytes / 1024 / 1024/ 1024;
			default -> throw new IllegalArgumentException("No such size value implemented: " + size);
		};
	}
	
	public static long getFolderSizeInBytes(File file) {
		long size = 0;
		if(file.isDirectory()) {
			if(file.listFiles() != null) {
				for(File f : file.listFiles()) {
					if(f.isDirectory()) {
						size += getFolderSizeInBytes(f);
					} else {
						size += f.length();
					}
				}
			}
		} else if(file.isFile()) {
			size += file.length();
		}
		return size;
	}
	
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
