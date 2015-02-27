package com.silentdarknessmc.deathrun;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.silentdarknessmc.deathrun.Utils.Methods;


public class Main extends JavaPlugin {
	
	public File Arena;
	public FileConfiguration ArenaConfig;
	public File ArenaSettings;
	public FileConfiguration ArenaSettingConfig;
	public File Messages;
	public FileConfiguration MessagesConfig;
	public File ArenaSigns;
	public FileConfiguration ArenaSignConfig;
	
	public static Main instance;
	
	public void onEnable() {
		Main.instance = this;
		Arena = new File(getDataFolder(), "/Arena/Arenas.yml");
		ArenaSettings = new File(getDataFolder(), "/Arena/ArenaSettings.yml");
		Messages = new File(getDataFolder(), "/Messages/Messages.yml");
		ArenaSigns = new File(getDataFolder(), "/Arena/Signs.yml");
		ArenaConfig = new YamlConfiguration();
		ArenaSettingConfig = new YamlConfiguration();
		MessagesConfig = new YamlConfiguration();
		ArenaSignConfig = new YamlConfiguration();
		Methods.LoadYamls();
		getConfig();
		getConfig().options().copyDefaults(true);
	}
	
	public void onDisable() {
		Methods.SaveYamls();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("deathrun")) {
			Player player = (Player) sender;
			if(args[0].equalsIgnoreCase("Help")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun AdminHelp");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun SetupHelp");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}
			
			if(args[0].equalsIgnoreCase("AdminHelp")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Start <Arena> **USE WITH CAUTION!!!!**");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}
			
			if(args[0].equalsIgnoreCase("SetupHelp")) {
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Create <Arena>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Delete <Arena>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Lobby <Arena>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Runner <Arena>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.GOLD + " /DeathRun Set Death <Arena>");
				player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + ChatColor.YELLOW + " =========================================");
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				if(ArenaConfig.contains("Arenas." + args[1]) && ArenaSettingConfig.contains("Arenas." + args[1])) {
					player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + " That Arena Already Exists!");
				}
				if(!ArenaConfig.contains("Arenas." + args[1]) && !ArenaSettingConfig.contains("Arenas." + args[1])) {
					ArenaConfig.addDefault("Arenas." + args[1], null);
					ArenaSettingConfig.addDefault("Arenas." + args[1], null);
					ArenaSettingConfig.addDefault("Arenas." + args[1] + ".MinPlayers", 10);
					ArenaSettingConfig.addDefault("Arenas." + args[1] + ".MaxPlayers", 20);
					ArenaSettingConfig.addDefault("Arenas." + args[1] + ".Signs", 0);
					ArenaConfig.options().copyDefaults(true);
					ArenaSettingConfig.options().copyDefaults(true);
					Methods.SaveYamls();
					Methods.LoadYamls();
					player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + " You Created The Arena: " + args[1] + "!");
				}
			}
			
			if(args[0].equalsIgnoreCase("delete")) {
				if(!ArenaConfig.contains("Arenas." + args[1]) && !ArenaSettingConfig.contains("Arenas." + args[1])) {
					player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "Deathrun" + ChatColor.DARK_AQUA + "]" + " The Arena: " + args[1] + " Doesn't Exist!");
				}
				if(ArenaConfig.contains("Arenas." + args[1]) && ArenaSettingConfig.contains("Arenas." + args[1])) {
					ArenaConfig.set("Arenas." + args[1], null);
					ArenaSettingConfig.set("Arenas." + args[1], null);
					ArenaSettingConfig.set("Arenas." + args[1] + ".MinPlayers", null);
					ArenaSettingConfig.set("Arenas." + args[1] + ".MaxPlayers", null);
					ArenaSettingConfig.set("Arenas." + args[1] + ".Signs", null);
					Methods.SaveYamls();
					Methods.LoadYamls();
				}
			}
		}
		return false;
	}
}