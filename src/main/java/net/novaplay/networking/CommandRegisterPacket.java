package net.novaplay.networking;

import java.util.ArrayList;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import net.novaplay.api.command.*;
import net.novaplay.library.netty.packet.Packet;
import net.novaplay.networking.types.ConnectType;

public class CommandRegisterPacket extends Packet implements IServerPacket {

	
	public String commandName;
	public ConnectType type = ConnectType.JAVA;
	public boolean hasArgs = false;
	public ArrayList<CommandLine> lines;
	
	@Override
	public void read(ByteBuf byteBuf) throws Exception {
		int length;
		length = byteBuf.readInt();
		commandName = (String) byteBuf.readCharSequence(length,Charsets.UTF_8);
		int tip = byteBuf.readInt();
		switch(tip) {
		case 0:
			type = ConnectType.JAVA;
			break;
		case 1:
			type = ConnectType.BEDROCK;
			break;
		}
		hasArgs = byteBuf.readBoolean();
		if(hasArgs) {
			length = byteBuf.readInt(); //command lines
			lines = new ArrayList<CommandLine>(length);
			ArrayList<CommandArgument> args;
			for(int i = 0; i < length; i++) {
				CommandLine line = new CommandLine();
				length = byteBuf.readInt(); //and now command arguments
				args = new ArrayList<CommandArgument>(length);
				for(int j = 0; j < length; j++){
					length = byteBuf.readInt();
					String name = (String) byteBuf.readCharSequence(length,Charsets.UTF_8);
					boolean empty = byteBuf.readBoolean();
					CommandArgument arg = new CommandArgument(name);
					arg.setEmpty(empty);
					if(type == ConnectType.BEDROCK) {
						boolean needs = byteBuf.readBoolean();
						arg.setNeed(needs);
						if(needs) {
							length = byteBuf.readInt();
							String pname = (String) byteBuf.readCharSequence(length,Charsets.UTF_8);
							length = byteBuf.readInt();
							String p = (String) byteBuf.readCharSequence(length,Charsets.UTF_8);
							CommandParameter param = new CommandParameter(pname,p);
							arg.registerParameter(param);
						}
					}
					args.add(arg);
					line.args = args;
				}
				lines.add(line);
			}
		}
	}

	@Override
	public void write(ByteBuf byteBuf) throws Exception {
		byteBuf.writeInt(commandName.length());
		byteBuf.writeCharSequence(commandName,Charsets.UTF_8);
		byteBuf.writeInt(type.getType());
		byteBuf.writeBoolean(hasArgs);
		if(hasArgs) {
			byteBuf.writeInt(lines.size());
			for(CommandLine line : lines) {
				byteBuf.writeInt(line.getArguments().size());
				for(CommandArgument args : line.getArguments()) {
					byteBuf.writeInt(args.getName().length());
					byteBuf.writeCharSequence(args.getName(),Charsets.UTF_8);
					byteBuf.writeBoolean(args.isEmpty());
					if(type == ConnectType.BEDROCK) {
						byteBuf.writeBoolean(args.needsParameter());
						if(args.needsParameter()) {
							byteBuf.writeInt(args.getCommandParameter().getParameterName().length());
							byteBuf.writeCharSequence(args.getCommandParameter().getParameterName(), Charsets.UTF_8);
							byteBuf.writeInt(args.getCommandParameter().getParameterType().length());
							byteBuf.writeCharSequence(args.getCommandParameter().getParameterType(), Charsets.UTF_8);	
						}
					}
				}
			}
		}
	}

}
