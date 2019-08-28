package io.github.zandyisrad.kubryzltcraft;

import io.github.zandyisrad.kubryzltcraft.command.CommandKubryzlt;
import io.github.zandyisrad.kubryzltcraft.command.CommandKubryzltCreate;
import io.github.zandyisrad.kubryzltcraft.command.CommandKubryzltRemove;
import io.github.zandyisrad.kubryzltcraft.item.ModItems;
import io.github.zandyisrad.kubryzltcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Mod(modid = Kubryzltcraft.MODID, name = Kubryzltcraft.NAME, version = Kubryzltcraft.VERSION)
public class Kubryzltcraft {
    public static final String MODID = "kubryzltcraft";
    public static final String NAME = "Kubryzltcraft";
    public static final String VERSION = "1.0";
    public static final String MC_VERSION = "[1.12.2]";
    
    @SidedProxy(clientSide = "io.github.zandyisrad.kubryzltcraft.proxy.ClientProxy", serverSide = "io.github.zandyisrad.kubryzltcraft.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance
    public static Kubryzltcraft instance;
    
    public static final Logger LOGGER = LogManager.getLogger(Kubryzltcraft.MODID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandKubryzltCreate());
    	event.registerServerCommand(new CommandKubryzlt());
    	event.registerServerCommand(new CommandKubryzltRemove());
    }
    
    @EventHandler
    public void onEntityDeath(LivingDeathEvent event) {
    	if (event.getEntityLiving() instanceof EntityPlayer) {
    		if (event.getEntity() instanceof EntityPlayer) {
    			event.getEntity().dropItem(ModItems.KUBRYZLT_KEY, 1);
    		}
    	}
    }
}