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

public class ArcadeInventoryListeners implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			BungeeInventory inv = new BungeeInventory(p);
			if(!(e.getInventory().getName().equalsIgnoreCase(inv.getInventory().getName()))) { return; }
			
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Arcade 1")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.ARCADE1);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Arcade 2")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.ARCADE2);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Arcade 3")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.ARCADE3);
				return;
			}
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + "Serwer Lobby")) {
				p.closeInventory();
				DyrtCraft.getMember(p).connect(Server.LOBBY);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.ITALIC + "Zamknij okno")) {
				p.closeInventory();
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Informacje o serwerze")) {
				p.closeInventory();
				InfoBookClickEvent event = new InfoBookClickEvent(p);
				Bukkit.getPluginManager().callEvent(event);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Sklep globalny")
					|| e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Sklep serwera")) {
				p.closeInventory();
				ShopClickEvent event = new ShopClickEvent(p);
				Bukkit.getPluginManager().callEvent(event);
				return;
			}
		} catch(NullPointerException ex) {}
	}
	
}
