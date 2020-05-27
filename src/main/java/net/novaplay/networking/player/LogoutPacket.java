package net.novaplay.networking.player;

import java.util.UUID;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class LogoutPacket extends Packet implements IPlayerPacket {

	public String username;
	public UUID uuid;
	public String reason;
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		username = ByteBufferUtils.readString(byteBuf);
		uuid = ByteBufferUtils.readUUID(byteBuf);
		reason = ByteBufferUtils.readString(byteBuf);
	}
	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufferUtils.writeString(byteBuf, username);
		ByteBufferUtils.writeUUID(byteBuf,uuid);
		ByteBufferUtils.writeString(byteBuf,reason);
	}
	
}
