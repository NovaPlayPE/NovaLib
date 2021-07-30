package net.novatech.library.crypto;

import lombok.Getter;

@Getter
public class SizedMemoryPointer {
	
	private final long address;
	private final int size;
	
	public SizedMemoryPointer(long addres, int size) {
		this.address = addres;
		this.size = size;
	}
	
}