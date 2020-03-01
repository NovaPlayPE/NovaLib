package net.novaplay.networking.server;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IServerPacket;

public class ProxyConnectPacket extends Packet implements IServerPacket{

	public String serverId = "";
	public String address = "";
	public String password = "";
	public int port = 19132;
	public boolean success = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		serverId = PacketBufferUtils.readString(byteBuf);
		address = PacketBufferUtils.readString(byteBuf);
		port = byteBuf.readInt();
		password = PacketBufferUtils.readString(byteBuf);
		success = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {	
		PacketBufferUtils.writeString(byteBuf,serverId);
		PacketBufferUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		PacketBufferUtils.writeString(byteBuf,password);
		byteBuf.writeBoolean(success);
	}

}
