package net.novaplay.networking.server;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IServerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

import java.util.*;

public class ServerListSyncPacket extends Packet implements IServerPacket{

	public ArrayList<String> serverList = new ArrayList<String>();
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		int i = byteBuf.readInt();
		for(int a = 0; a < i; a++) {
			serverList.add(ByteBufUtils.readString(byteBuf));
		}
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(serverList.size());
		for(String serv : serverList) {
			ByteBufUtils.writeString(byteBuf,serv);
		}
	}

}
