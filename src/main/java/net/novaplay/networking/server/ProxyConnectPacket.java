package net.novaplay.networking.server;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IServerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

public class ProxyConnectPacket extends Packet implements IServerPacket{

	public String serverId = "";
	public String address = "";
	public String password = "";
	public int port = 19132;
	public boolean success = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		serverId = ByteBufUtils.readString(byteBuf);
		address = ByteBufUtils.readString(byteBuf);
		port = byteBuf.readInt();
		password = ByteBufUtils.readString(byteBuf);
		success = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {	
		ByteBufUtils.writeString(byteBuf,serverId);
		ByteBufUtils.writeString(byteBuf,address);
		byteBuf.writeInt(port);
		ByteBufUtils.writeString(byteBuf,password);
		byteBuf.writeBoolean(success);
	}

}
