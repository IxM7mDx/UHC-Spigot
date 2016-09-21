package de.alphahelix.uhc.instances;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.alphahelix.uhc.Registery;
import de.alphahelix.uhc.UHC;
import de.popokaka.alphalibary.file.SimpleFile;
import de.popokaka.alphalibary.item.ItemBuilder;

public abstract class EasyFile extends SimpleFile {

	private UHC uhc;
	private Registery register;
	private Logger log;
	private String name;

	public EasyFile(String name, UHC uhc) {
		super("plugins/UHC", name);
		setUhc(uhc);
		setRegister(getUhc().getRegister());
		setLog(getUhc().getLog());
		setName(name);
		getRegister().getEasyFiles().add(this);
	}
	
	public EasyFile(String path, String name, UHC uhc) {
		super("plugins/UHC/"+path, name);
		setUhc(uhc);
		setRegister(getUhc().getRegister());
		setLog(getUhc().getLog());
		setName(name);
		getRegister().getEasyFiles().add(this);
	}

	public abstract void addValues();

	public void loadValues() {
	};

	public void register(EasyFile easy) {
		easy.addValues();
		easy.loadValues();
	}
	
	public Material getMaterial(String path) {
		return Material.getMaterial(getString(path).replace(" ", "_").toUpperCase());
	}

	public SimpleFile getFile() {
		return new SimpleFile("plugins/UHC", name);
	}

	public ItemBuilder getItemBuilder(String path) {
		String[] data = getString(path).split(";");
		return new ItemBuilder(Material.getMaterial(data[0])).setAmount(Integer.parseInt(data[1]))
				.setDamage(Short.parseShort(data[2]));
	}

	public void setItem(String path, ItemStack item) {
		set(path, item.getType() + ";" + item.getAmount() + ";" + item.getDurability());
		save();
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> toList(T... args) {
		ArrayList<T> toReturn = new ArrayList<>();
		for(T type : args) {
			toReturn.add(type);
		}
		return toReturn;
	}

	public UHC getUhc() {
		return uhc;
	}

	public void setUhc(UHC uhc) {
		this.uhc = uhc;
	}

	public Registery getRegister() {
		return register;
	}

	public void setRegister(Registery register) {
		this.register = register;
	}

	public Logger getLog() {
		return log;
	}

	private void setLog(Logger log) {
		this.log = log;
	}

	private void setName(String filename) {
		this.name = filename;
	}
}