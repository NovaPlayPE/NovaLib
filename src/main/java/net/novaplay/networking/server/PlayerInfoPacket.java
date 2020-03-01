package net.novaplay.networking.server;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IServerPacket;

public class PlayerInfoPacket extends Packet implements IServerPacket{

	public String player = null;
	public UUID uuid;
	public String serverId;
	public boolean online = false;
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = PacketBufferUtils.readString(byteBuf);
		uuid = PacketBufferUtils.readUUID(byteBuf);
		serverId = PacketBufferUtils.readString(byteBuf);
		online = byteBuf.readBoolean();
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		PacketBufferUtils.writeString(byteBuf,player);
		PacketBufferUtils.writeUUID(byteBuf,uuid);
		PacketBufferUtils.writeString(byteBuf,serverId);
		byteBuf.writeBoolean(online);
		byteBuf.writeBoolean(handled);
	}

}
