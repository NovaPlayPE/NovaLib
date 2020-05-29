package net.novaplay.networking.server;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IServerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

public class ServerInfoPacket extends Packet implements IServerPacket{
	
	public String serverId = "";
	public String address = "";
	public int port = 19132;
	public boolean isMain = true;
	public ArrayList<String> players = new ArrayList<String>();
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		serverId = ByteBufUtils.readString(byteBuf);
		address = ByteBufUtils.readString(byteBuf);
		port = byteBuf.readInt();
		isMain = byteBuf.readBoolean();
		int c = byteBuf.readInt();
		for(int i = 0; i < c; i++) {
			String p = ByteBufUtils.readString(byteBuf);
			players.add(p);
		}
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufUtils.writeString(byteBuf,serverId);
		ByteBufUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		byteBuf.writeBoolean(isMain);
		byteBuf.writeInt(players.size());
		for(String pla : players) {
			ByteBufUtils.writeString(byteBuf,pla);
		}
	}

}
