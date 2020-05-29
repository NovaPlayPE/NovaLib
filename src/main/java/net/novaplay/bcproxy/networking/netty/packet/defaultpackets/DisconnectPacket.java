package net.novaplay.bcproxy.networking.netty.packet.defaultpackets;

import io.netty.buffer.ByteBuf;
import net.novaplay.bcproxy.networking.netty.packet.Packet;

import java.io.IOException;

public class DisconnectPacket extends Packet {
    @Override
    public void read(ByteBuf byteBuf) throws IOException {
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
    }
}