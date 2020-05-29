package net.novaplay.networking.player;

import java.util.UUID;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IPlayerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

public class LogoutPacket extends Packet implements IPlayerPacket {

	public String username;
	public UUID uuid;
	public String reason;
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		username = ByteBufUtils.readString(byteBuf);
		uuid = ByteBufUtils.readUUID(byteBuf);
		reason = ByteBufUtils.readString(byteBuf);
	}
	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufUtils.writeString(byteBuf, username);
		ByteBufUtils.writeUUID(byteBuf,uuid);
		ByteBufUtils.writeString(byteBuf,reason);
	}
	
}
