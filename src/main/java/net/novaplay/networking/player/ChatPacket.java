package net.novaplay.networking.player;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.library.netty.packet.ByteBufferUtils;
import net.novaplay.networking.IPlayerPacket;

public class ChatPacket extends Packet implements IPlayerPacket {

	public String player;
	/**
	 * chat
	 * tip
	 * popup
	 * title
	 * subtitle
	 * actionbar
	 */
	public String type = CHAT;
	public String message;
	public boolean handled = false;
	
	public static String CHAT = "chat";
	public static String TIP = "tip";
	public static String POPUP = "popup";
	public static String TITLE = "title";
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		player = ByteBufferUtils.readString(byteBuf);
		type = ByteBufferUtils.readString(byteBuf);
		message = ByteBufferUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufferUtils.writeString(byteBuf,player);
		ByteBufferUtils.writeString(byteBuf,type);
		ByteBufferUtils.writeString(byteBuf,message);
		byteBuf.writeBoolean(handled);
	}

}
