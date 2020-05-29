package net.novaplay.networking.player;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.IPlayerPacket;
import net.novaplay.bcproxy.networking.netty.packet.Packet;
import net.novaplay.library.utils.ByteBufUtils;

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
		player = ByteBufUtils.readString(byteBuf);
		type = ByteBufUtils.readString(byteBuf);
		message = ByteBufUtils.readString(byteBuf);
		handled = byteBuf.readBoolean();
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		ByteBufUtils.writeString(byteBuf,player);
		ByteBufUtils.writeString(byteBuf,type);
		ByteBufUtils.writeString(byteBuf,message);
		byteBuf.writeBoolean(handled);
	}

}
