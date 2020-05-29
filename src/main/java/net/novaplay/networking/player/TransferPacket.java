package net.novaplay.networking.player;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IPlayerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

public class TransferPacket extends Packet implements IPlayerPacket{

	public String player = null;
	public String destination = null;
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = ByteBufUtils.readString(byteBuf);
		destination = ByteBufUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufUtils.writeString(byteBuf,player);
		ByteBufUtils.writeString(byteBuf,destination);
		byteBuf.writeBoolean(handled);
	}

}
