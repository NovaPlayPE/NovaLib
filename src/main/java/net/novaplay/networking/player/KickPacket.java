package net.novaplay.networking.player;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IPlayerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

public class KickPacket extends Packet implements IPlayerPacket{
	
	public String player;
	public String reason = "unknown";
	public String type = "kick"; //there is also ban
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = ByteBufUtils.readString(byteBuf);
		reason = ByteBufUtils.readString(byteBuf);
		type = ByteBufUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufUtils.writeString(byteBuf,player);
		ByteBufUtils.writeString(byteBuf,reason);
		ByteBufUtils.writeString(byteBuf,type);
		byteBuf.writeBoolean(handled);
	}

}
