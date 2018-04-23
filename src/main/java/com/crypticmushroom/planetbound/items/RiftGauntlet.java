package com.crypticmushroom.planetbound.items;

import com.crypticmushroom.planetbound.networking.PBNetworkHandler;
import com.crypticmushroom.planetbound.networking.packet.PBPacketSpawnRift;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class RiftGauntlet extends Item {
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        RayTraceResult ray = player.rayTrace(10, 0);

        BlockPos pos = ray.getBlockPos();
        BlockPos up = pos.up();

        Block block = world.getBlockState(pos).getBlock();

        if (!block.equals(Blocks.AIR) && world.getBlockState(up).getBlock().equals(Blocks.AIR)) {
            PBNetworkHandler.sendToServer(new PBPacketSpawnRift(pos));
        }

        return super.onItemRightClick(world, player, hand);
    }
}