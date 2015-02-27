package com.silentdarknessmc.deathrun.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Sign;

import com.silentdarknessmc.deathrun.Main;

public class Methods {
	public static void SaveYamls() {
		try {
			Main.instance.ArenaConfig.save(Main.instance.Arena);
			Main.instance.ArenaSettingConfig.save(Main.instance.ArenaSettings);
			Main.instance.MessagesConfig.save(Main.instance.Messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void LoadYamls() {
		try {
			Main.instance.ArenaConfig.save(Main.instance.Arena);
			Main.instance.ArenaSettingConfig.save(Main.instance.ArenaSettings);
			Main.instance.MessagesConfig.save(Main.instance.Messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void UpdateSigns() {
		for(Location loc : getSigns()) {
			if((loc.getBlock().getState() instanceof Sign)) {
				Sign
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Location> getSigns() {
		List locs = new ArrayList();
		for(int count = 1; Main.instance.ArenaSettingConfig.contains("Signs." + ConfigTools.Arena1 + "." + count + ".x"); count++) {
			locs.add(ConfigTools.Arena1Sign);
		}
		return locs;
	}
}