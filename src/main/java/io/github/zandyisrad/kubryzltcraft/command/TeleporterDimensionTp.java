package io.github.zandyisrad.kubryzltcraft.command;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDimensionTp extends Teleporter {
	public TeleporterDimensionTp(WorldServer world, double x, double y, double z) {
		super(world);
		this.worldServer = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private final WorldServer worldServer;
	private double x;
	private double y;
	private double z;
	
	@Override
	public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
		this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));
		entity.setPosition(this.x, this.y, this.z);
		entity.motionX = 0.0f;
		entity.motionY = 0.0f;
		entity.motionZ = 0.0f;
	}
	
	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
		int oldDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		player.addExperienceLevel(0);
		
		if(worldServer == null || worldServer.getMinecraftServer() == null) {
			throw new IllegalArgumentException("That dimension does not exist");	
		}
		
		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new TeleporterDimensionTp(worldServer, x, y, x));
		player.setPositionAndUpdate(x, y, z);
		if(oldDimension == 1) {
			player.setPositionAndUpdate(x, y, z);
			worldServer.spawnEntity(player);
			worldServer.updateEntityWithOptionalForce(player, false);
		}
	}
}