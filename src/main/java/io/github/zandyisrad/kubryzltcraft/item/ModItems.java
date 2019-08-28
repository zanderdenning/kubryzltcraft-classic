package io.github.zandyisrad.kubryzltcraft.item;

import io.github.zandyisrad.kubryzltcraft.Kubryzltcraft;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	@GameRegistry.ObjectHolder(Kubryzltcraft.MODID + ":kubryzlt_key")
	public static ItemKubryzltKey KUBRYZLT_KEY;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() {
		KUBRYZLT_KEY.initModel();
	}
}