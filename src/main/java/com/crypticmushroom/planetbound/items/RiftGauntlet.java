package com.crypticmushroom.planetbound.items;

import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RiftGauntlet extends Item
{
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        player.swingArm(hand);
        if (!world.isRemote)
        {
            RayTraceResult result = ForgeHooks.rayTraceEyes(player, 20.0D);
            if (result != null)
            {
                switch (result.typeOfHit)
                {
                    case ENTITY:
                        //TODO do we want a different behaviour when hitting entities?
                    case BLOCK:
                        BlockPos pos = result.getBlockPos();
                        EnumFacing.Axis axis = player.getHorizontalFacing().getAxis();
                        EnumFacing sideToExpandTo;
                        switch (axis)
                        {
                            default:
                            case X: //WEST-EAST
                                sideToExpandTo = isDecimalsLessThanHalf(result.hitVec.z) ? EnumFacing.NORTH : EnumFacing.SOUTH;
                                break;
                            case Z: //NORTH-SOUTH
                                sideToExpandTo = isDecimalsLessThanHalf(result.hitVec.x) ? EnumFacing.WEST : EnumFacing.EAST;
                        }
                        List<BlockPos> portalPoses = getPortalPositions(pos.offset(result.sideHit), sideToExpandTo);
                        if (isAllAir(world, portalPoses))
                        {
                            IBlockState state = PBBlocks.rift.getDefaultState().withRotation(axis == EnumFacing.Axis.X ? Rotation.CLOCKWISE_90 : Rotation.NONE);
                            portalPoses.forEach(blockPos -> world.setBlockState(blockPos, state));
                            player.getCooldownTracker().setCooldown(this, 200);
                            break; //break the large switch so we don't end up with MISS
                        }
                    case MISS:
                        player.getCooldownTracker().setCooldown(this, 20);
                }
            } else player.getCooldownTracker().setCooldown(this, 20);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    private static boolean isDecimalsLessThanHalf(double d)
    {
        return Math.abs(d - (int) d) < 0.5D;
    }

    public static List<BlockPos> getPortalPositions(BlockPos start, EnumFacing sideToExpand)
    {
        List<BlockPos> ret = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            BlockPos toAdd = start.offset(EnumFacing.UP, i);
            ret.add(toAdd);
            ret.add(toAdd.offset(sideToExpand));
        }
        return ret;
    }

    private static boolean isAllAir(World world, List<BlockPos> blocks)
    {
        return blocks.stream().filter(pos -> !world.isAirBlock(pos)).collect(Collectors.toList()).size() == 0;
    }
}