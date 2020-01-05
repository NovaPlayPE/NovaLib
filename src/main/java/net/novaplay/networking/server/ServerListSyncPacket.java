package net.novaplay.networking.server;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import java.util.*;

public class ServerListSyncPacket extends Packet {

	public ArrayList<String> serverList = new ArrayList<String>();
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		int i = byteBuf.readInt();
		for(int a = 0; a < i; a++) {
			serverList.add(PacketBufferUtils.readString(byteBuf));
		}
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(serverList.size());
		for(String serv : serverList) {
			PacketBufferUtils.writeString(byteBuf,serv);
		}
	}

}
