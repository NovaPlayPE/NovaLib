package net.novaplay.networking.player;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.PacketBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class KickPacket extends Packet implements IPlayerPacket{
	
	public String player;
	public String reason = "unknown";
	public String type = "kick"; //there is also ban
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = PacketBufferUtils.readString(byteBuf);
		reason = PacketBufferUtils.readString(byteBuf);
		type = PacketBufferUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		PacketBufferUtils.writeString(byteBuf,player);
		PacketBufferUtils.writeString(byteBuf,reason);
		PacketBufferUtils.writeString(byteBuf,type);
		byteBuf.writeBoolean(handled);
	}

}