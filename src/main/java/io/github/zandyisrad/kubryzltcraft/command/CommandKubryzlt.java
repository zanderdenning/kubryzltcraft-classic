package io.github.zandyisrad.kubryzltcraft.command;

import javax.annotation.Nonnull;

import io.github.zandyisrad.kubryzltcraft.KWorldSavedData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;

public class CommandKubryzlt extends CommandBase {
	public CommandKubryzlt() {
		
	}
	
	@Override
	@Nonnull
	public String getName() {
		return "kubryzlt";
	}
	
	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "kubryzlt";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		try {
			KWorldSavedData data = KWorldSavedData.get(sender.getEntityWorld());
			NBTTagList kubryzlts = data.getKubryzltData().getTagList("kubryzlts", Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < kubryzlts.tagCount(); i ++) {
				TextFormatting color = TextFormatting.WHITE;
				if (kubryzlts.getCompoundTagAt(i).getString("team").equals("crimson")) {
					color = TextFormatting.DARK_RED;
				}
				if (kubryzlts.getCompoundTagAt(i).getString("team").equals("gold")) {
					color = TextFormatting.GOLD;
				}
				TextComponentString text = new TextComponentString(color + kubryzlts.getCompoundTagAt(i).getString("name") + TextFormatting.WHITE + " " + kubryzlts.getCompoundTagAt(i).getString("dimension") + " " + kubryzlts.getCompoundTagAt(i).getInteger("x") + " " + kubryzlts.getCompoundTagAt(i).getInteger("y") + " " + kubryzlts.getCompoundTagAt(i).getInteger("z"));
				sender.sendMessage(text);
			}
		}
		catch (Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "Error"));
		}
	}
	
}