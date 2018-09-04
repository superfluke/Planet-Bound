package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.items.*;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = PBCore.MOD_ID)
@GameRegistry.ObjectHolder(PBCore.MOD_ID)
public class PBItems
{
    //Armor Materials
    public static ItemArmor.ArmorMaterial ARMOR_HALKIR = EnumHelper.addArmorMaterial("HALKIR", "halkir", 16, new int[]{3, 6, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static ItemArmor.ArmorMaterial ARMOR_RONNE_LEATHER = EnumHelper.addArmorMaterial("RONNE_LEATHER", "ronne_leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    //Tool Materials
    public static Item.ToolMaterial TOOL_EMBERWOOD = EnumHelper.addToolMaterial("EMBERWOOD", 0, 59, 2.0F, 0.0F, 15);
    public static Item.ToolMaterial TOOL_HALKIR = EnumHelper.addToolMaterial("HALKIR", 2, 250, 6.0F, 2.0F, 14);

    private static final List<Item> items = new ArrayList<>();

    public static final Item kybrite_ingot = registerItem(new PBItem(), "kybrite_ingot");
    public static final Item verdanite_ingot = registerItem(new PBItem(), "verdanite_ingot");
    public static final Item rendium_chunk = registerItem(new PBItem(), "rendium_chunk");
    public static final Item rendium_crystal = registerItem(new PBItem(), "rendium_crystal");
    public static final Item core_frame = registerItem(new PBItem(), "core_frame");
    public static final Item rendium_core = registerItem(new PBItem(), "rendium_core");
    public static final Item rift_gauntlet = registerItem(new RiftGauntlet(), "rift_gauntlet");
    public static final Item gauntlet_shell = registerItem(new PBItem(), "gauntlet_shell");
    public static final Item fortium_ingot = registerItem(new PBItem(), "fortium_ingot");
    public static final Item halkir_ingot = registerItem(new PBItem(), "halkir_ingot");
    public static final Item bloodstone_shard = registerItem(new PBItem(), "bloodstone_shard");
    public static final Item emberwood_stick = registerItem(new PBItem(), "emberwood_stick");

    //Tools
    public static final Item emberwood_shovel = registerItem(new PBShovel(TOOL_EMBERWOOD), "emberwood_shovel");
    public static final Item emberwood_pickaxe = registerItem(new PBPickaxe(TOOL_EMBERWOOD), "emberwood_pickaxe");
    public static final Item emberwood_axe = registerItem(new PBAxe(TOOL_EMBERWOOD, 4.0F, -3.2F), "emberwood_axe");
    public static final Item emberwood_bucket = registerItem(new PBBucket(), "emberwood_bucket");
    public static final Item halkir_sword = registerItem(new PBSword(TOOL_HALKIR), "halkir_sword");
    public static final Item halkir_shovel = registerItem(new PBShovel(TOOL_HALKIR), "halkir_shovel");
    public static final Item halkir_pickaxe = registerItem(new PBPickaxe(TOOL_HALKIR), "halkir_pickaxe");
    public static final Item halkir_hoe = registerItem(new PBHoe(TOOL_HALKIR), "halkir_hoe");
    public static final Item halkir_axe = registerItem(new PBAxe(TOOL_HALKIR, 8.0F, -3.2F), "halkir_axe");
    public static final Item halkir_shears = registerItem(new PBShears(), "halkir_shears");
    public static final Item halkir_bucket = registerItem(new PBBucket(), "halkir_bucket");

    //WIP
    public static final Item emberwood_basin = registerItem(new PBItem(), "emberwood_basin");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(PBItems.getItems());
        PBLogDev.printInfo("Registered PlanetBound items...");
    }

    protected static <T extends Item> T registerItem(T item, String name)
    {
        return registerItem(item, name, PBCreativeTabs.TAB_ITEMS);
    }

    protected static <T extends Item> T registerItem(T item, String name, CreativeTabs tab)
    {
        Validate.notNull(item, "item cannot be null");
        Validate.notNull(name, "name cannot be null");

        if (tab != null)
        {
            item.setCreativeTab(tab);
        }

        item.setTranslationKey(PBCore.MOD_ID + "." + name);
        item.setRegistryName(PBCore.MOD_ID, name);

        items.add(item);

        return item;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        for (Item item : items)
        {
            if(item instanceof ICustomModelProvider) ((ICustomModelProvider) item).registerItemModel();
            else registerModel(item);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item)
    {
        registerModel(item, 0);
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item, int metadata)
    {
        Validate.notNull(item, "item cannot be null");
        ModelLoader.setCustomModelResourceLocation(item, metadata,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static Item[] getItems()
    {
        return items.toArray(new Item[]{});
    }
}