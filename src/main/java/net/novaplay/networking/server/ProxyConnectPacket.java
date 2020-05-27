package net.novaplay.networking.server;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IServerPacket;

public class ProxyConnectPacket extends Packet implements IServerPacket{

	public String serverId = "";
	public String address = "";
	public String password = "";
	public int port = 19132;
	public boolean success = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		serverId = ByteBufferUtils.readString(byteBuf);
		address = ByteBufferUtils.readString(byteBuf);
		port = byteBuf.readInt();
		password = ByteBufferUtils.readString(byteBuf);
		success = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {	
		ByteBufferUtils.writeString(byteBuf,serverId);
		ByteBufferUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		ByteBufferUtils.writeString(byteBuf,password);
		byteBuf.writeBoolean(success);
	}

}
