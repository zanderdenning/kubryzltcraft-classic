package io.github.zandyisrad.kubryzltcraft;

import io.github.zandyisrad.kubryzltcraft.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class KeyDropHandler {
	@SubscribeEvent
	public static void onEvent(LivingDropsEvent event) {
		Entity entity = event.getEntity();
		Entity source = event.getSource().getTrueSource();
		if (entity instanceof EntityPlayer || entity instanceof EntityPlayerMP) {
			if (source instanceof EntityPlayer || source instanceof EntityPlayerMP) {
				entity.dropItem(ModItems.KUBRYZLT_KEY, 1);
			}
		}
	}
}