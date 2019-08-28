package io.github.zandyisrad.kubryzltcraft.proxy;

import io.github.zandyisrad.kubryzltcraft.Kubryzltcraft;
import io.github.zandyisrad.kubryzltcraft.block.*;
import io.github.zandyisrad.kubryzltcraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		
	}
	
	public void init(FMLInitializationEvent e) {
		
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemKubryzltKey());
		event.getRegistry().register(new ItemBlock(ModBlocks.KUBRYZLT).setRegistryName(ModBlocks.KUBRYZLT.getRegistryName()));
	}
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new BlockKubryzlt());
		GameRegistry.registerTileEntity(DataTileEntity.class, Kubryzltcraft.MODID + "_kubryzlt");
	}
}