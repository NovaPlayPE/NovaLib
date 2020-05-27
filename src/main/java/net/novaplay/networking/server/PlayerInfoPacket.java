package net.novaplay.networking.server;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IServerPacket;

public class PlayerInfoPacket extends Packet implements IServerPacket{

	public String player = null;
	public UUID uuid;
	public String serverId;
	public boolean online = false;
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = ByteBufferUtils.readString(byteBuf);
		uuid = ByteBufferUtils.readUUID(byteBuf);
		serverId = ByteBufferUtils.readString(byteBuf);
		online = byteBuf.readBoolean();
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufferUtils.writeString(byteBuf,player);
		ByteBufferUtils.writeUUID(byteBuf,uuid);
		ByteBufferUtils.writeString(byteBuf,serverId);
		byteBuf.writeBoolean(online);
		byteBuf.writeBoolean(handled);
	}

}
