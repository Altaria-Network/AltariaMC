package pl.dyrtcraft.xp.signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.dyrtcraft.xp.inv.BungeeInventory;

public class ServersSign implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase("") &&
						s.getLine(1).equalsIgnoreCase("Lista serwerów") &&
						s.getLine(2).equalsIgnoreCase(ChatColor.UNDERLINE + "" + ChatColor.BOLD + "Altaria") &&
						s.getLine(3).equalsIgnoreCase("")) {
					BungeeInventory inv = new BungeeInventory(e.getPlayer());
					e.getPlayer().openInventory(inv.getInventory());
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
