package net.novaplay.networking.player;

import java.util.UUID;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class LogoutPacket extends Packet implements IPlayerPacket {

	public String username;
	public UUID uuid;
	public String reason;
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		username = PacketBufferUtils.readString(byteBuf);
		uuid = PacketBufferUtils.readUUID(byteBuf);
		reason = PacketBufferUtils.readString(byteBuf);
	}
	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeCharSequence(reason, Charsets.UTF_8);
		PacketBufferUtils.writeString(byteBuf, username);
		PacketBufferUtils.writeUUID(byteBuf,uuid);
		PacketBufferUtils.writeString(byteBuf,reason);
	}
	
}
