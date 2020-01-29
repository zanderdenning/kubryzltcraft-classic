package io.github.zandyisrad.kubryzltcraft.command;

import javax.annotation.Nonnull;

import io.github.zandyisrad.kubryzltcraft.KWorldSavedData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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
			ItemStack book = new ItemStack(Item.getItemById(387));
			NBTTagList bookPages = new NBTTagList();
			int j = 0;
			String page = "[\"\",";
			for (int i = 0; i < kubryzlts.tagCount(); i ++) {
				
				String color = "black";
				
				if (kubryzlts.getCompoundTagAt(i).getString("team").equals("crimson")) {
					color = "dark_red";
					
				}
				if (kubryzlts.getCompoundTagAt(i).getString("team").equals("gold")) {
					color = "gold";
				}
				
				String name = kubryzlts.getCompoundTagAt(i).getString("name");
				String dimension = kubryzlts.getCompoundTagAt(i).getString("dimension");
				int x = kubryzlts.getCompoundTagAt(i).getInteger("x");
				int y = kubryzlts.getCompoundTagAt(i).getInteger("y");
				int z = kubryzlts.getCompoundTagAt(i).getInteger("z");
				
				String line = "{\"text\":\"" + name + " (" + dimension + ")\",\"color\":\"" + color + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"" + x + " " + y +" " + z + "\"}}";
				
				if (j > 0) {
					page = page.concat(",{\"text\":\"\\n\",\"color\":\"reset\"},");
				}
				
				page = page.concat(line);
				
				j ++;
				if (j >= 14) {
					j = 0;
					page = page.concat("]");
					bookPages.appendTag(new NBTTagString(page));
					page = "[\"\",";
				}
			}
			
			if (!page.contentEquals("")) {
				page = page.concat("]");
				bookPages.appendTag(new NBTTagString(page));
			}
			
			book.setTagInfo("pages", bookPages);
			book.setTagInfo("author", new NBTTagString(""));
			book.setTagInfo("title", new NBTTagString("Kubryzlts"));
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook((EntityPlayer) sender, book, false));
			
		}
		catch (Exception e) {
			sender.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "Error"));
		}
	}
	
}