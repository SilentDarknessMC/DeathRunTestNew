package com.silentdarknessmc.deathrun.Listeners;

import static org.bukkit.ChatColor.*;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.silentdarknessmc.deathrun.Main;
import com.silentdarknessmc.deathrun.Utils.ConfigTools;
import com.silentdarknessmc.deathrun.Utils.GameState;
import com.silentdarknessmc.deathrun.Utils.Methods;
import com.silentdarknessmc.deathrun.Utils.PlayerLists;

public class Signs implements Listener {
	int a1p = PlayerLists.Arena1Players.size();
	int a2p = PlayerLists.Arena2Players.size();
	int a3p = PlayerLists.Arena3Players.size();
	int a4p = PlayerLists.Arena4Players.size();
	int a1mp = ConfigTools.Arena1MaxPlayers;
	int a2mp = ConfigTools.Arena2MaxPlayers;
	int a3mp = ConfigTools.Arena3MaxPlayers;
	int a4mp = ConfigTools.Arena4MaxPlayers;
	
	@EventHandler
	public void onSignCreate(SignChangeEvent e) {
		Player player = e.getPlayer();
		if(e.getLine(0).equalsIgnoreCase("[Deathrun]") && player.hasPermission("deathrun.admin")) {
			Sign sign = (Sign) e.getBlock().getState();
			if(e.getLine(1).equalsIgnoreCase("Arena1")) {
				e.setLine(0, DARK_AQUA + "[" + GOLD + "Deathrun" + DARK_AQUA + "]");
				e.setLine(1, "" + BLUE + ConfigTools.Arena1);
				if(GameState.isState(GameState.IN_LOBBY)) {
					e.setLine(2, GREEN + "In Lobby");
				}
				if(GameState.isState(GameState.IN_INTRO)) {
					e.setLine(2, RED + "Starting");
				}
				if(GameState.isState(GameState.IN_GAME)) {
					e.setLine(2, RED + "In Game");
				}
				if(GameState.isState(GameState.RESETTING)) {
					e.setLine(2, RED + "<< Restarting >>");
				}
				e.setLine(3, "" + PlayerLists.Arena1Players.size() + "/" + ConfigTools.Arena1MaxPlayers);
				Main.instance.getConfig().set("Signs.Arena1.x", player.getLocation().getX());
				Main.instance.getConfig().set("Signs.Arena1.y", player.getLocation().getY());
				Main.instance.getConfig().set("Signs.Arena1.z", player.getLocation().getZ());
				Main.instance.getConfig().set("Signs.Arena1.world", player.getLocation().getWorld().getName());
			}
			if(e.getLine(1).equalsIgnoreCase("Arena2")) {
				e.setLine(0, DARK_AQUA + "[" + GOLD + "Deathrun" + DARK_AQUA + "]");
				e.setLine(1, "" + BLUE + ConfigTools.Arena2);
				if(GameState.isState(GameState.IN_LOBBY)) {
					e.setLine(2, GREEN + "In Lobby");
				}
				if(GameState.isState(GameState.IN_INTRO)) {
					e.setLine(2, RED + "Starting");
				}
				if(GameState.isState(GameState.IN_GAME)) {
					e.setLine(2, RED + "In Game");
				}
				if(GameState.isState(GameState.RESETTING)) {
					e.setLine(2, RED + "<< Restarting >>");
				}
				e.setLine(3, "" + PlayerLists.Arena2Players.size() + "/" + ConfigTools.Arena2MaxPlayers);
			}
			if(e.getLine(1).equalsIgnoreCase("Arena3")) {
				e.setLine(0, DARK_AQUA + "[" + GOLD + "Deathrun" + DARK_AQUA + "]");
				e.setLine(1, "" + BLUE + ConfigTools.Arena3);
				if(GameState.isState(GameState.IN_LOBBY)) {
					e.setLine(2, GREEN + "In Lobby");
				}
				if(GameState.isState(GameState.IN_INTRO)) {
					e.setLine(2, RED + "Starting");
				}
				if(GameState.isState(GameState.IN_GAME)) {
					e.setLine(2, RED + "In Game");
				}
				if(GameState.isState(GameState.RESETTING)) {
					e.setLine(2, RED + "<< Restarting >>");
				}
				e.setLine(3, "" + PlayerLists.Arena3Players.size() + "/" + ConfigTools.Arena3MaxPlayers);
			}
			if(e.getLine(1).equalsIgnoreCase("Arena4")) {
				e.setLine(0, DARK_AQUA + "[" + GOLD + "Deathrun" + DARK_AQUA + "]");
				e.setLine(1, "" + BLUE + ConfigTools.Arena4);
				if(GameState.isState(GameState.IN_LOBBY)) {
					e.setLine(2, GREEN + "In Lobby");
				}
				if(GameState.isState(GameState.IN_INTRO)) {
					e.setLine(2, RED + "Starting");
				}
				if(GameState.isState(GameState.IN_GAME)) {
					e.setLine(2, RED + "In Game");
				}
				if(GameState.isState(GameState.RESETTING)) {
					e.setLine(2, RED + "<< Restarting >>");
				}
				e.setLine(3, "" + PlayerLists.Arena4Players.size() + "/" + ConfigTools.Arena4MaxPlayers);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.hasBlock() && e.getClickedBlock().getState() instanceof Sign) {
			Sign sign = (Sign) e.getClickedBlock().getState();
			if(sign.getLine(0).equalsIgnoreCase(DARK_AQUA + "[" + GOLD + "Deathrun" + DARK_AQUA + "]")) {
				if(sign.getLine(1).equalsIgnoreCase(BLUE + "" + ConfigTools.Arena1)) {
					if(sign.getLine(2).equalsIgnoreCase(RED + "Starting") || sign.getLine(2).equalsIgnoreCase(RED + "In Game") || sign.getLine(2).equalsIgnoreCase(RED + "<< Restarting >>")) {
						e.setCancelled(true);
						player.sendMessage("That Game Has Already Started!");
					}
					if(a1p == a1mp) {
						e.setCancelled(true);
						player.sendMessage("That Game Is Already Full!");
					}
					if(sign.getLine(2).equalsIgnoreCase(GREEN + "In Lobby")) {
						PlayerLists.Arena1Players.add(player);
						//TODO TELEPORT PLAYER TO ARENA LOBBY
						Methods.UpdateSigns();
					}
				}
				if(sign.getLine(1).equalsIgnoreCase(BLUE + "" + ConfigTools.Arena2)) {
					if(sign.getLine(2).equalsIgnoreCase(RED + "Starting") || sign.getLine(2).equalsIgnoreCase(RED + "In Game") || sign.getLine(2).equalsIgnoreCase(RED + "<< Restarting >>")) {
						e.setCancelled(true);
						player.sendMessage("That Game Has Already Started!");
					}
					if(a2p == a2mp) {
						e.setCancelled(true);
						player.sendMessage("That Game Is Already Full!");
					}
					if(sign.getLine(2).equalsIgnoreCase(GREEN + "In Lobby")) {
						PlayerLists.Arena2Players.add(player);
						//TODO TELEPORT PLAYER TO ARENA LOBBY
						Methods.UpdateSigns();
					}
				}
				if(sign.getLine(1).equalsIgnoreCase(BLUE + "" + ConfigTools.Arena3)) {
					if(sign.getLine(2).equalsIgnoreCase(RED + "Starting") || sign.getLine(2).equalsIgnoreCase(RED + "In Game") || sign.getLine(2).equalsIgnoreCase(RED + "<< Restarting >>")) {
						e.setCancelled(true);
						player.sendMessage("That Game Has Already Started!");
					}
					if(a3p == a3mp) {
						e.setCancelled(true);
						player.sendMessage("That Game Is Already Full!");
					}
					if(sign.getLine(2).equalsIgnoreCase(GREEN + "In Lobby")) {
						PlayerLists.Arena3Players.add(player);
						//TODO TELEPORT PLAYER TO ARENA LOBBY
						Methods.UpdateSigns();
					}
				}
				if(sign.getLine(1).equalsIgnoreCase(BLUE + "" + ConfigTools.Arena4)) {
					if(sign.getLine(2).equalsIgnoreCase(RED + "Starting") || sign.getLine(2).equalsIgnoreCase(RED + "In Game") || sign.getLine(2).equalsIgnoreCase(RED + "<< Restarting >>")) {
						e.setCancelled(true);
						player.sendMessage("That Game Has Already Started!");
					}
					if(a4p == a4mp) {
						e.setCancelled(true);
						player.sendMessage("That Game Is Already Full!");
					}
					if(sign.getLine(2).equalsIgnoreCase(GREEN + "In Lobby")) {
						PlayerLists.Arena4Players.add(player);
						//TODO TELEPORT PLAYER TO ARENA LOBBY
						Methods.UpdateSigns();
					}
				}
			}
		}
	}
}