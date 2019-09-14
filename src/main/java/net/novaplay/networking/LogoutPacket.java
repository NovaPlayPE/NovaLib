package net.novaplay.networking;

import java.util.UUID;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;

public class LogoutPacket extends Packet implements IPlayerPacket {

	public String username;
	public UUID uuid;
	public String reason;
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		int length;
		length = byteBuf.readInt();
		username = (String) byteBuf.readCharSequence(length,Charsets.UTF_8);
		length = byteBuf.readInt();
		uuid = getUuidFromString((String) byteBuf.readCharSequence(length,Charsets.UTF_8));
		length = byteBuf.readInt();
		reason = (String) byteBuf.readCharSequence(length,Charsets.UTF_8);
	}
	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(username.length());
		byteBuf.writeCharSequence(username, Charsets.UTF_8);
		byteBuf.writeInt(getStringFromUuid(this.uuid).length());
		byteBuf.writeCharSequence(getStringFromUuid(this.uuid), Charsets.UTF_8);
		byteBuf.writeInt(reason.length());
		byteBuf.writeCharSequence(reason, Charsets.UTF_8);
	}
	
	public UUID getUuidFromString(String string) {
		try {
			return UUID.fromString(string);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getStringFromUuid(UUID uuid) {
		return uuid.toString();
	}
	
}
