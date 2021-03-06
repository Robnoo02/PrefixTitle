package me.robnoo02.prefixtitle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	/**
	 * Saves config and registers events and commands.
	 */
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
		Bukkit.getPluginManager().registerEvents(new GuiUtil(), this);
		getCommand("prefixtitle").setExecutor(new PrefixTitleCmd());
		GuiFactory.setDefaultItems();
	}
	
	/**
	 * @return instance of Main class.
	 */
	public static Main getInstance() {
		return JavaPlugin.getPlugin(Main.class);
	}
	
}
