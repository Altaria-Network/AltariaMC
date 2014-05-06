package pl.dyrtcraft.xp.inv;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.events.InfoBookClickEvent;
import pl.dyrtcraft.events.ShopClickEvent;

public class BungeeInventoryListeners implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			BungeeInventory inv = new BungeeInventory(p);
			if(!(e.getInventory().getName().equalsIgnoreCase(inv.getInventory().getName()))) { return; }
			
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "FreeBuild")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.FREEBUILD);
				return;
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "SkyBlock")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.SKYBLOCK);
				return;
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Survival")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.SURVIVAL);
				return;
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + "Serwer Lobby")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.LOBBY);
				return;
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.ITALIC + "Zamknij okno")) {
				p.closeInventory();
				return;
			}
		} catch(NullPointerException ex) {}
	}
	
}
