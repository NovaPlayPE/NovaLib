package net.novaplay.networking.server;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IServerPacket;
import net.novaplay.networking.types.ConnectType;

public class ServerInfoPacket extends Packet implements IServerPacket{
	
	public String serverId = "";
	public String address = "";
	public int port = 19132;
	public ConnectType type = ConnectType.BEDROCK;
	public ArrayList<String> players = new ArrayList<String>();
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		serverId = PacketBufferUtils.readString(byteBuf);
		address = PacketBufferUtils.readString(byteBuf);
		port = byteBuf.readInt();
		type = PacketBufferUtils.readConnectType(byteBuf);
		int c = byteBuf.readInt();
		for(int i = 0; i < c; i++) {
			String p = PacketBufferUtils.readString(byteBuf);
			players.add(p);
		}
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		PacketBufferUtils.writeString(byteBuf,serverId);
		PacketBufferUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		PacketBufferUtils.writeConnectType(byteBuf,type);
		byteBuf.writeInt(players.size());
		for(String pla : players) {
			PacketBufferUtils.writeString(byteBuf,pla);
		}
	}

}
