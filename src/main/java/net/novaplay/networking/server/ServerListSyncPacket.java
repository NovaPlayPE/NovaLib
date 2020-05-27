package net.novaplay.networking.server;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IServerPacket;

import java.util.*;

public class ServerListSyncPacket extends Packet implements IServerPacket{

	public ArrayList<String> serverList = new ArrayList<String>();
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		int i = byteBuf.readInt();
		for(int a = 0; a < i; a++) {
			serverList.add(ByteBufferUtils.readString(byteBuf));
		}
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(serverList.size());
		for(String serv : serverList) {
			ByteBufferUtils.writeString(byteBuf,serv);
		}
	}

}
