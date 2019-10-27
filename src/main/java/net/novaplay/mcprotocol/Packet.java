package net.novaplay.mcprotocol;

public interface Packet {
	
	void write(Buffer buf);
	void read(Buffer buf);

}
