package io.github.zandyisrad.kubryzltcraft.command;

import javax.annotation.Nonnull;

import io.github.zandyisrad.kubryzltcraft.KWorldSavedData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;

public class CommandKubryzltRemove extends CommandBase {
	public CommandKubryzltRemove() {
		
	}
	
	@Override
	@Nonnull
	public String getName() {
		return "kubryzltremove";
	}
	
	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "kubryzltremove <name>";
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
			KWorldSavedData data = KWorldSavedData.get(sender.getEntityWorld());
			NBTTagList kList = data.getKubryzltData().getTagList("kubryzlts", Constants.NBT.TAG_COMPOUND);
			NBTTagList newDataList = new NBTTagList();
			NBTTagCompound newData = new NBTTagCompound();
			for (int i = 0; i < kList.tagCount(); i ++) {
				if (!(kList.getCompoundTagAt(i).getString("name").equals(args[0]))) {
					newDataList.appendTag(kList.getCompoundTagAt(i));
				}
			}
			newData.setTag("kubryzlts", newDataList);
			data.setKubryzltData(newData);
			sender.sendMessage(new TextComponentString("Kubryzlt successfully deleted"));
		}
		catch (Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "Error"));
		}
	}
}