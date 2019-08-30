package io.github.zandyisrad.kubryzltcraft.block;

import io.github.zandyisrad.kubryzltcraft.KWorldSavedData;
import io.github.zandyisrad.kubryzltcraft.Kubryzltcraft;
import io.github.zandyisrad.kubryzltcraft.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockKubryzlt extends Block implements ITileEntityProvider {
	public static final PropertyInteger TEAM = PropertyInteger.create("team", 0, 2);
	
	public BlockKubryzlt() {
		super(Material.ROCK);
		setUnlocalizedName(Kubryzltcraft.MODID + ".kubryzlt");
		setRegistryName("kubryzlt");
		setBlockUnbreakable();
		setLightLevel(1f);
		setResistance(18000000);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new DataTileEntity();
	}
	
	private DataTileEntity getTE(World worldIn, BlockPos pos) {
		return (DataTileEntity) worldIn.getTileEntity(pos);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItemMainhand().getItem().equals(new ItemStack(ModItems.KUBRYZLT_KEY).getItem())) {
			try {
				if (playerIn.getTeam().getName().equals("Crimson") && !(worldIn.getBlockState(pos).getValue(TEAM).equals(1))) {
					String savedName = getTE(worldIn, pos).getName();
					TextComponentString announcement = new TextComponentString(TextFormatting.BLUE + "[CAPTURE]" + TextFormatting.WHITE + " " + playerIn.getName() + " captured " + getTE(worldIn, pos).getName() + " for " + TextFormatting.DARK_RED + "Crimson");
					worldIn.getMinecraftServer().getPlayerList().sendMessage(announcement);
					if (!(playerIn.capabilities.isCreativeMode)) {
						playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, ItemStack.EMPTY);
					}
					worldIn.setBlockState(pos, state.withProperty(TEAM, 1));
					getTE(worldIn, pos).setName(savedName);
					
					KWorldSavedData data = KWorldSavedData.get(worldIn);
					NBTTagCompound newData = new NBTTagCompound();
					NBTTagList newDataList = new NBTTagList();
					NBTTagList kList = data.getKubryzltData().getTagList("kubryzlts", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < kList.tagCount(); i ++) {
						if (kList.getCompoundTagAt(i).getString("name").equals(savedName)) {
							NBTTagCompound newDat = kList.getCompoundTagAt(i);
							newDat.setString("team", "crimson");
							newDataList.appendTag(newDat);
						}
						else {
							newDataList.appendTag(kList.getCompoundTagAt(i));
						}
					}
					newData.setTag("kubryzlts", newDataList);
					data.setKubryzltData(newData);
				}
				if (playerIn.getTeam().getName().equals("Gold") && !(worldIn.getBlockState(pos).getValue(TEAM).equals(2))) {
					String savedName = getTE(worldIn, pos).getName();
					TextComponentString announcement = new TextComponentString(TextFormatting.BLUE + "[CAPTURE]" + TextFormatting.WHITE + " " + playerIn.getName() + " captured " + getTE(worldIn, pos).getName() + " for " + TextFormatting.GOLD + "Gold");
					worldIn.getMinecraftServer().getPlayerList().sendMessage(announcement);
					if (!(playerIn.capabilities.isCreativeMode)) {
						playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, ItemStack.EMPTY);
					}
					worldIn.setBlockState(pos, state.withProperty(TEAM, 2));
					getTE(worldIn, pos).setName(savedName);
					
					KWorldSavedData data = KWorldSavedData.get(worldIn);
					NBTTagCompound newData = new NBTTagCompound();
					NBTTagList newDataList = new NBTTagList();
					NBTTagList kList = data.getKubryzltData().getTagList("kubryzlts", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < kList.tagCount(); i ++) {
						if (kList.getCompoundTagAt(i).getString("name").equals(savedName)) {
							NBTTagCompound newDat = kList.getCompoundTagAt(i);
							newDat.setString("team", "gold");
							newDataList.appendTag(newDat);
						}
						else {
							newDataList.appendTag(kList.getCompoundTagAt(i));
						}
					}
					newData.setTag("kubryzlts", newDataList);
					data.setKubryzltData(newData);
				}
			}
			catch (Exception e) {
				
			}
		}
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TEAM, meta);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TEAM);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TEAM);
	}
}