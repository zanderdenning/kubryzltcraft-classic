package io.github.zandyisrad.kubryzltcraft.item;

import io.github.zandyisrad.kubryzltcraft.Kubryzltcraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKubryzltKey extends Item {
	public ItemKubryzltKey() {
		setRegistryName("kubryzlt_key");
		setUnlocalizedName(Kubryzltcraft.MODID + ".kubryzlt_key");
		setCreativeTab(CreativeTabs.MISC);
		setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}