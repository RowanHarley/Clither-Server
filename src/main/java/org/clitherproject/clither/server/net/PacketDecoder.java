/**
 * This file is part of Clither.
 *
 * Clither is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Clither is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Clither.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.clitherproject.clither.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import java.nio.ByteOrder;
import java.util.List;

import org.clitherproject.clither.server.ClitherServer;
import org.clitherproject.clither.server.net.packet.Packet;
import org.clitherproject.clither.server.net.packet.PacketRegistry;

public class PacketDecoder extends MessageToMessageDecoder<WebSocketFrame> {

	@SuppressWarnings("deprecation")
	@Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> out) throws Exception {
        ByteBuf buf = frame.content().order(ByteOrder.BIG_ENDIAN);
        if (buf.capacity() < 1) {
            // Discard empty messages
            return;
        }

        buf.resetReaderIndex();
        int packetId = buf.readUnsignedByte();
        Packet packet = PacketRegistry.SERVERBOUND.constructPacket(packetId);

        if (packet == null) {
            return;
        }

        ClitherServer.log.finest("Received packet " + " (" + packet.getClass().getSimpleName() + ") from " + ctx.channel().remoteAddress());

        packet.readData(buf);
        out.add(packet);
    }

}
