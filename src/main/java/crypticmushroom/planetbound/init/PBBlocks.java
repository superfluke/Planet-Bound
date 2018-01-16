package crypticmushroom.planetbound.init;

import java.util.Set;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import crypticmushroom.planetbound.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class PBBlocks
{

    public static BlockPBOres ores;
    public static BlockPBRendium rendium;
    // public static BlockPBRendiumOre rendium_ore;
    public static BlockPBVerdanite verdanite;
    public static PBKybriteBlock kybrite_block;
    public static PBKybriteOre kybrite_ore;
    public static PBRendiumBlock rendium_block;
    public static PBRendiumOre rendium_ore;
    public static PBVerdaniteBlock verdanite_block;
    public static PBVerdaniteOre verdanite_ore;

    public static void init()
    {

    }

    /*
	public PBBlocks(Material rock, MapColor color) {
	}

	public static void register(IForgeRegistry<Block> registry) {
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
	}

	public static void registerItemModels() {
	}
	
	public static final Set<Block> CACHE; 
	public static final Block VERDANITE_ORE;
	public static final Block VERDANITE_BLOCK;
	public static final Block KYBRITE_ORE;
	public static final Block KYBRITE_BLOCK;
	public static final Block RENDIUM_ORE; 
	public static final Block LIT_RENDIUM_ORE;
	public static final Block RENDIUM_BLOCK;
	public static final Block LIT_RENDIUM_BLOCK;
	
	// Returns the Block in the blockRegistry with the specified name.
    @Nullable
    private static Block getRegisteredBlock(String blockName)
    {
        Block block = Block.REGISTRY.getObject(new ResourceLocation(blockName));

        if (!CACHE.add(block))
        {
            throw new IllegalStateException("Invalid Block requested: " + blockName);
        }
        else
        {
            return block;
        }
    }
	
	static {
		
		if (!Bootstrap.isRegistered()) {
			
			throw new RuntimeException("Accessed Blocks before Bootstrap!");
		}
		
		else {
			CACHE = Sets.<Block>newHashSet();
			VERDANITE_ORE = getRegisteredBlock("verdanite_ore");
			VERDANITE_BLOCK = getRegisteredBlock("verdanite_block");
			KYBRITE_ORE = getRegisteredBlock("kybrite_ore");
			KYBRITE_BLOCK = getRegisteredBlock("kybrite_block");
			RENDIUM_ORE = getRegisteredBlock("rendium_ore");
			LIT_RENDIUM_ORE = getRegisteredBlock("lit_rendium_ore");
			RENDIUM_BLOCK = getRegisteredBlock("rendium_block");
			LIT_RENDIUM_BLOCK = getRegisteredBlock("lit_rendium_block");
			CACHE.clear();
		}
	}
	*/

}
