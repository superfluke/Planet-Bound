package com.crypticmushroom.planetbound.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//do NOT make the entire interface side only!
public interface ICustomModelProvider {

    @SideOnly(Side.CLIENT)
    void registerItemModel();
}
