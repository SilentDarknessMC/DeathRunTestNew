package com.silentdarknessmc.deathrun.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.silentdarknessmc.deathrun.Main;

public class ConfigTools {
	public static Double Arena1 = Main.instance.getConfig().getDouble("Arenas.Arena1");
	public static Double Arena2 = Main.instance.getConfig().getDouble("Arenas.Arena2");
	public static Double Arena3 = Main.instance.getConfig().getDouble("Arenas.Arena3");
	public static Double Arena4 = Main.instance.getConfig().getDouble("Arenas.Arena4");
	
	public static int Arena1MinPlayers = Main.instance.ArenaSettingConfig.getInt("Arena1MinPlayers");
	public static int Arena2MinPlayers = Main.instance.ArenaSettingConfig.getInt("Arena2MinPlayers");
	public static int Arena3MinPlayers = Main.instance.ArenaSettingConfig.getInt("Arena3MinPlayers");
	public static int Arena4MinPlayers = Main.instance.ArenaSettingConfig.getInt("Arena4MinPlayers");
	
	public static int Arena1MaxPlayers = Main.instance.ArenaSettingConfig.getInt("Arena1MaxPlayers");
	public static int Arena2MaxPlayers = Main.instance.ArenaSettingConfig.getInt("Arena2MaxPlayers");
	public static int Arena3MaxPlayers = Main.instance.ArenaSettingConfig.getInt("Arena3MaxPlayers");
	public static int Arena4MaxPlayers = Main.instance.ArenaSettingConfig.getInt("Arena4MaxPlayers");
	
	public static Double Arena1Signx = Main.instance.ArenaSignConfig.getDouble("Signs." + Arena1 + ".x");
	public static Double Arena1Signy = Main.instance.ArenaSignConfig.getDouble("Signs." + Arena1 + ".y");
	public static Double Arena1Signz = Main.instance.ArenaSignConfig.getDouble("Signs." + Arena1 + ".z");
	public static String Arena1Signworld = Main.instance.ArenaSignConfig.getString("Signs." + Arena1 + ".world");
	public static World Arena1Signworld2 = Bukkit.getServer().getWorld(Arena1Signworld);
	public static Location Arena1Sign = new Location(Arena1Signworld2, Arena1Signx, Arena1Signy, Arena1Signz);
}