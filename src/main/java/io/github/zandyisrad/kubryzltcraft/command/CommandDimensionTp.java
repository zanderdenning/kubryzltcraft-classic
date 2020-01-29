package io.github.zandyisrad.kubryzltcraft.command;

import javax.annotation.Nonnull;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandDimensionTp extends CommandBase {
	public CommandDimensionTp() {
		
	}
	
	@Override
	@Nonnull
	public String getName() {
		return "dimensiontp";
	}
	
	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "dimensiontp <id> <x> <y> <z>";
	}
	
	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		if(args.length < 4) {
			return;
		}
		String s = args[0];
		@SuppressWarnings("unused")
		int dim;
		try {
			dim = Integer.parseInt(s);
		}
		catch(Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid ID"));
			return;
		}
		
		String _x = args[1];
		@SuppressWarnings("unused")
		int x;
		try {
			x = Integer.parseInt(_x);
		}
		catch(Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid X coordinate"));
			return;
		}
		
		String _y = args[2];
		@SuppressWarnings("unused")
		int y;
		try {
			y = Integer.parseInt(_y);
		}
		catch(Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid Y coordinate"));
			return;
		}
		
		String _z = args[3];
		@SuppressWarnings("unused")
		int z;
		try {
			z = Integer.parseInt(_z);
		}
		catch(Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid Z coordinate"));
			return;
		}
		
		if(sender instanceof EntityPlayer) {
			TeleporterDimensionTp.teleportToDimension((EntityPlayer) sender, dim, x, y, z);
		}
	}
	
}