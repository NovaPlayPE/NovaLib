package net.novatech.library.crypto;

public class NativeProcessor {
	
	static native long createNewContext(boolean encryptionModeToggle);
	static native void enableCrypto(long context, byte[] key, byte[] iv);
	static native void destroyCotext(long ctx);
	static native void debug(long context, boolean debug);
	static native void preallocSize(long ctx, int size);
	static native SizedMemoryPointer process(long context, SizedMemoryPointer pointer);
	
}