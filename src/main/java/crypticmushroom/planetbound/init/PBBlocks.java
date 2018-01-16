package crypticmushroom.planetbound.init;

import java.util.Set;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class PBBlocks extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<Block> {

	// check this: idk why you need materal rock and mapcolor color here if you are initializing it from the main class.
	// those two things are what is causing an error, because you aren't giving the class anything to work with.
	// - Jonathan
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
	
	 /**
     * Returns the Block in the blockRegistry with the specified name.
     */
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

}
