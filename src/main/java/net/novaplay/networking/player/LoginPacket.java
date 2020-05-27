package net.novaplay.networking.player;

import java.util.UUID;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class LoginPacket extends Packet implements IPlayerPacket{
	
	public String username;
	public UUID uuid;
	public String serverId;
	public boolean handled = false;
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		username = ByteBufferUtils.readString(byteBuf);
		uuid = ByteBufferUtils.readUUID(byteBuf);
		serverId = ByteBufferUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}
	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufferUtils.writeString(byteBuf,username);
		ByteBufferUtils.writeUUID(byteBuf,uuid);
		ByteBufferUtils.writeString(byteBuf,serverId);
		byteBuf.writeBoolean(handled);
	}

}
