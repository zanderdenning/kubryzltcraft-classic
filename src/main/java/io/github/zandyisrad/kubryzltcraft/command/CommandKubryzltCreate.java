package io.github.zandyisrad.kubryzltcraft.command;

import javax.annotation.Nonnull;

import io.github.zandyisrad.kubryzltcraft.KWorldSavedData;
import io.github.zandyisrad.kubryzltcraft.block.DataTileEntity;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;

public class CommandKubryzltCreate extends CommandBase {
	public CommandKubryzltCreate() {
		
	}
	
	@Override
	@Nonnull
	public String getName() {
		return "kubryzltcreate";
	}
	
	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "kubryzltcreate <name>";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		if (args.length < 1) {
			return;
		}
		try {
			DataTileEntity tile = (DataTileEntity) sender.getEntityWorld().getTileEntity(sender.getPosition().down());
			tile.setName(args[0]);
			KWorldSavedData data = KWorldSavedData.get(sender.getEntityWorld());
			NBTTagCompound newbryzlt = new NBTTagCompound();
			newbryzlt.setString("name", args[0]);
			newbryzlt.setString("team", "none");
			newbryzlt.setString("dimension", sender.getEntityWorld().provider.getDimensionType().getName());
			newbryzlt.setInteger("x", sender.getPosition().getX());
			newbryzlt.setInteger("y", sender.getPosition().down().getY());
			newbryzlt.setInteger("z", sender.getPosition().getZ());
			NBTTagList kList = data.getKubryzltData().getTagList("kubryzlts", Constants.NBT.TAG_COMPOUND);
			kList.appendTag(newbryzlt);
			NBTTagCompound returnTag = new NBTTagCompound();
			returnTag.setTag("kubryzlts", kList);
			data.setKubryzltData(returnTag);
			sender.sendMessage(new TextComponentString("Kubryzlt successfully registered"));
		}
		catch (Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "Error"));
		}
	}
}