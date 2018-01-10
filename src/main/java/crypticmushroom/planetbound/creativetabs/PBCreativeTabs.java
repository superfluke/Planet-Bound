package crypticmushroom.planetbound.creativetabs;

import net.minecraft.init.Blocks;
import crypticmushroom.planetbound.common.blocks.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PBCreativeTabs
	{
	    public static PBCreativeTabs[] CREATIVE_TAB_ARRAY = new PBCreativeTabs[3];
	    public static final PBCreativeTabs PLANETCRAFT_BLOCKS = new PBCreativeTabs() {
	    	@SideOnly(Side.CLIENT)
	    	public ItemStack getTabIconItem() {
	    		return new ItemStack(Item.getItemFromBlock(Blocks.VERDANITE_ORE));
	    	}
	    };
	    public static final PBCreativeTabs PLANETCRAFT_ITEMS = new PBCreativeTabs() {
	    	@SideOnly(Side.CLIENT)
	    	public ItemStack getTabIconItem() {
	    		return new ItemStack(Item.VERDANITE_INGOT);
	    	}
	    };
	    public static final PBCreativeTabs PLANETCRAFT_TOOLS = new PBCreativeTabs() {
	    	@SideOnly(Side.CLIENT)
	    	public void getTabIconItem() {
	    	}
	    };

}
