package net.novatech.library.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import net.novatech.library.utils.NativeLibraryLoader;

public class Processor {
	
	static {
		NativeLibraryLoader loader = new NativeLibraryLoader();
		if(loader.supports("amd64") || loader.supports("arm") || loader.supports("aarch64")) {
			loader.load("crypto", Processor.class.getClassLoader());
		} else {
			throw new RuntimeException("Couldn't load crypto native library");
		}
	}
	
	private final long context;
	
	public Processor(boolean encrypted) {
		this.context = NativeProcessor.createNewContext(encrypted);
	}
	
	public void enableCrypto(byte[] key, byte[] iv) {
		NativeProcessor.enableCrypto(this.context, key, iv);
	}
	
	public void debug(boolean debug) {
		NativeProcessor.debug(this.context, debug);
	}
	
	public ByteBuf process(ByteBuf data) {
		try {
			long pointer = data.memoryAddress() + data.readerIndex();
			int size = data.readableBytes();
			
			SizedMemoryPointer dataPointer = new SizedMemoryPointer(pointer, size);
			SizedMemoryPointer processedDataPointer = NativeProcessor.process(this.context, dataPointer);
			
			if(processedDataPointer.getAddress() == 0 || processedDataPointer.getSize() == 0) {
				return PooledByteBufAllocator.DEFAULT.directBuffer();
			}
			
			return Unpooled.wrappedBuffer(processedDataPointer.getAddress(), processedDataPointer.getSize(), true);
		} finally {
			data.release();
		}
	}
	
	public void preallocSize(int size) {
		NativeProcessor.preallocSize(this.context, size);
	}
	
}