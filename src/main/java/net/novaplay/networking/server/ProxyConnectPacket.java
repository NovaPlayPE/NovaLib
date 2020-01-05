package net.novaplay.networking.server;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IServerPacket;
import net.novaplay.networking.types.ConnectType;

public class ProxyConnectPacket extends Packet implements IServerPacket{

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
		type = PacketBufferUtils.readConnectType(byteBuf);
		password = PacketBufferUtils.readString(byteBuf);
		success = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {	
		PacketBufferUtils.writeString(byteBuf,serverId);
		PacketBufferUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		PacketBufferUtils.writeConnectType(byteBuf,type);
		PacketBufferUtils.writeString(byteBuf,password);
		byteBuf.writeBoolean(success);
	}

}
