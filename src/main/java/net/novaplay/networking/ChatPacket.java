package net.novaplay.networking;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;

public class ChatPacket extends Packet implements IPlayerPacket {

	public String player;
	public String message;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		int length;
		length = byteBuf.readInt();
		player = (String) byteBuf.readCharSequence(length, Charsets.UTF_8);
		length = byteBuf.readInt();
		message = (String) byteBuf.readCharSequence(length, Charsets.UTF_8);
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(player.length());
		byteBuf.writeCharSequence(player, Charsets.UTF_8);
		byteBuf.writeInt(message.length());
		byteBuf.writeCharSequence(message, Charsets.UTF_8);
	}

}
