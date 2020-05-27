package net.novaplay.networking.player;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class TransferPacket extends Packet implements IPlayerPacket{

	public String player = null;
	public String destination = null;
	public boolean handled = false;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = ByteBufferUtils.readString(byteBuf);
		destination = ByteBufferUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufferUtils.writeString(byteBuf,player);
		ByteBufferUtils.writeString(byteBuf,destination);
		byteBuf.writeBoolean(handled);
	}

}
