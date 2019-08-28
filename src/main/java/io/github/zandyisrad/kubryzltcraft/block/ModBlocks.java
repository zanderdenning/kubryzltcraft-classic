package io.github.zandyisrad.kubryzltcraft.block;

import io.github.zandyisrad.kubryzltcraft.Kubryzltcraft;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	@GameRegistry.ObjectHolder(Kubryzltcraft.MODID + ":kubryzlt")
	public static BlockKubryzlt KUBRYZLT;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() {
		KUBRYZLT.initModel();
	}
}