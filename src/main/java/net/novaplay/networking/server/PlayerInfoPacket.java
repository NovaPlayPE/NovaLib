package net.novaplay.networking.server;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IServerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

public class PlayerInfoPacket extends Packet implements IServerPacket{

	public String player = null;
	public UUID uuid;
	public String serverId;
	public boolean online = false;
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = ByteBufUtils.readString(byteBuf);
		uuid = ByteBufUtils.readUUID(byteBuf);
		serverId = ByteBufUtils.readString(byteBuf);
		online = byteBuf.readBoolean();
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufUtils.writeString(byteBuf,player);
		ByteBufUtils.writeUUID(byteBuf,uuid);
		ByteBufUtils.writeString(byteBuf,serverId);
		byteBuf.writeBoolean(online);
		byteBuf.writeBoolean(handled);
	}

}
