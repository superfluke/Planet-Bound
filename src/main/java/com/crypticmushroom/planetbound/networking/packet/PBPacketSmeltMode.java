package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class PBPacketSmeltMode extends PBPacket<PBPacketSmeltMode> {
    private int mode;
    private int x;
    private int y;
    private int z;

    public PBPacketSmeltMode() {
    }

    public PBPacketSmeltMode(TileEntityInventorsForge tileEntity) {
        BlockPos pos = tileEntity.getPos();

        mode = tileEntity.getField(4) == 0 ? 1 : 0;
        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        mode = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(mode);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public void handleClient(PBPacketSmeltMode message, EntityPlayer player) {

    }

    @Override
    public void handleServer(PBPacketSmeltMode message, EntityPlayer player) {
        TileEntity tileEntity = player.world.getTileEntity(new BlockPos(message.x, message.y, message.z));

        if (tileEntity instanceof TileEntityInventorsForge) {
            ((TileEntityInventorsForge) tileEntity).setField(4, message.mode);
        }
    }
}