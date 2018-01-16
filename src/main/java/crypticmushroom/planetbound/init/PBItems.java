package crypticmushroom.planetbound.init;

import javax.annotation.Nullable;

import crypticmushroom.planetbound.creativetabs.PBCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class PBItems extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<Item> {
	
	private static Block registerItem(Item item) {
		return null;
	}

	public static void register(IForgeRegistry<Item> registry) {
		
	}

	// make sure your stuff works before committing it. that's my number 1 rule - Jonathan
	public static void registerItemModel() {
		
	}

	    // you all got some broken stuff over here
	    public static void registerItems() {
	    	registerItemBlock(Blocks.VERDANITE_ORE);
	    	registerItemBlock(Blocks.KYBRITE_ORE);
	    	registerItemBlock(Blocks.RENDIUM_ORE);
	    	registerItemBlock(Blocks.LIT_RENDIUM_ORE);
	    	
	    	registerItem(new Item()).setUnlocalizedName("ingotVerdanite").setCreativeTab(CreativeTabs.PLANETCRAFT_BLOCKS);
	    	registerItem(new Item()).setUnlocalizedName("ingotKybrite").setCreativeTab(CreativeTabs.PLANETCRAFT_BLOCKS);
	    	registerItem(new Item()).setUnlocalizedName("chunkRendium").setCreativeTab(CreativeTabs.PLANETCRAFT_BLOCKS);
	    	registerItem(new Item()).setUnlocalizedName("gauntletRift").setCreativeTab(CreativeTabs.PLANETCRAFT_TOOLS); 
	    	registerItem(new Item()).setUnlocalizedName("gauntletShell").setCreativeTab(CreativeTabs.PLANETCRAFT_ITEMS);
	    	registerItem(new Item()).setUnlocalizedName("framePowerCore").setCreativeTab(CreativeTabs.PLANETCRAFT_ITEMS);
	    	registerItem(new Item()).setUnlocalizedName("powerCoreRendium").setCreativeTab(CreativeTabs.PLANETCRAFT_ITEMS);
	    }
	    
	    /**
	     * Register the given Item as the ItemBlock for the given Block.
	     */
	    protected static void registerItemBlock(Block blockIn, Item itemIn)
	    {
	        registerItem(Block.getIdFromBlock(blockIn), Block.REGISTRY.getNameForObject(blockIn), itemIn);
	        BLOCK_TO_ITEM.put(blockIn, itemIn);
	    }

	    private static void registerItem(int id, ResourceLocation textualID, Item itemIn)
	    {
	        REGISTRY.register(id, textualID, itemIn);
	    }

	    @SideOnly(Side.CLIENT)
	    public ItemStack getDefaultInstance()
	    {
	        return new ItemStack(this);
	    }

}
