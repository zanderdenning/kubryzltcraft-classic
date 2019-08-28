package io.github.zandyisrad.kubryzltcraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class KWorldSavedData extends WorldSavedData {
	private static final String DATA_NAME = Kubryzltcraft.MODID + "_DATA";
	private NBTTagCompound kubryzlts;
	
	public KWorldSavedData() {
		super(DATA_NAME);
	}
	
	public KWorldSavedData(String s) {
		super(s);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		kubryzlts = nbt.getCompoundTag("kubryzlts");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("kubryzlts", kubryzlts);
		return compound;
	}
	
	public void setKubryzltData(NBTTagCompound nbt) {
		this.kubryzlts = nbt;
		markDirty();
	}
	
	public NBTTagCompound getKubryzltData() {
		if (kubryzlts != null) {
			return kubryzlts;
		}
		else {
			NBTTagCompound k = new NBTTagCompound();
			k.setTag("kubryzlts", new NBTTagList());
			return k;
		}
	}
	
	public static KWorldSavedData get(World world) {
		MapStorage storage = world.getMapStorage();
		KWorldSavedData instance = (KWorldSavedData) storage.getOrLoadData(KWorldSavedData.class, DATA_NAME);
		
		if (instance == null) {
			instance = new KWorldSavedData();
			storage.setData(DATA_NAME, instance);
		}
		
		return instance;
	}
}