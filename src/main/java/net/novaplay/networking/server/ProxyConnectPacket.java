package net.novaplay.networking.server;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.types.ConnectType;

public class ProxyConnectPacket extends Packet{

	public String serverId = "";
	public String address = "";
	public String password = "";
	public int port = 25565;
	public ConnectType type = ConnectType.JAVA;
	public boolean success = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		serverId = PacketBufferUtils.readString(byteBuf);
		address = PacketBufferUtils.readString(byteBuf);
		port = byteBuf.readInt();
		int tip = byteBuf.readInt();
		switch(tip) {
		case 0:
			type = ConnectType.JAVA;
			break;
		case 1:
			type = ConnectType.BEDROCK;
			break;
		}
		password = PacketBufferUtils.readString(byteBuf);
		success = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {	
		PacketBufferUtils.writeString(byteBuf,serverId);
		PacketBufferUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		byteBuf.writeInt(type.getType());
		PacketBufferUtils.writeString(byteBuf,password);
		byteBuf.writeBoolean(success);
	}

}
