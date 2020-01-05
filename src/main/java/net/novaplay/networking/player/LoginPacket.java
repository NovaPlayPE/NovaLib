package net.novaplay.networking.player;

import java.util.UUID;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class LoginPacket extends Packet implements IPlayerPacket{
	
	public String username;
	public UUID uuid;
	public String serverId;
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		username = PacketBufferUtils.readString(byteBuf);
		uuid = PacketBufferUtils.readUUID(byteBuf);
		serverId = PacketBufferUtils.readString(byteBuf);
	}
	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		PacketBufferUtils.writeString(byteBuf,username);
		PacketBufferUtils.writeUUID(byteBuf,uuid);
		PacketBufferUtils.writeString(byteBuf,serverId);
	}

}
