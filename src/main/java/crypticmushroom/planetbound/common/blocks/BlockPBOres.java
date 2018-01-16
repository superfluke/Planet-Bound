package crypticmushroom.planetbound.common.blocks;

import java.util.Random;

import crypticmushroom.planetbound.creativetabs.PBCreativeTabs;
import crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockPBOres extends PBBlocks {

	public BlockPBOres()
    {
		 this(Material.ROCK.getMaterialMapColor());
    }

    public BlockPBOres(MapColor color) {
    	super(); // ???
        this.setCreativeTab(PBCreativeTabs.PLANETCRAFT_BLOCKS);
	}

    private void setCreativeTab(PBCreativeTabs planetcraftBlocks) {
    	
	}

	/**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == hold on) {
        	return Items.RENDIUM_CHUNK;
        }
    }
}
