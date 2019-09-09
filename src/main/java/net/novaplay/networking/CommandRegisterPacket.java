package net.novaplay.networking;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.networking.types.ConnectType;

public class CommandRegisterPacket extends Packet implements IServerPacket {

	public String commandName;
	public ConnectType type = ConnectType.JAVA;
	public ArrayList<String> params = new ArrayList<String>();
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
