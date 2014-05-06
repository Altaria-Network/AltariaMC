package pl.dyrtcraft.xp.signs;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignCreator implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[DyrtCraftXP]")) {
			if(!e.getPlayer().isOp()) {
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
				return;
			}
			if(e.getLine(1).equalsIgnoreCase("")) {
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.RED + "Nie podano zadnego argumentu.");
				e.getPlayer().sendMessage(ChatColor.GRAY + "Dostepne argumenty: lobby, servers");
				return;
			}
			
			if(e.getLine(1).equalsIgnoreCase("lobby")) {
				e.setLine(0, ChatColor.UNDERLINE + "LOBBY");
				e.setLine(1, ChatColor.DARK_GREEN + "do");
				e.setLine(2, ChatColor.DARK_GREEN + "Altaria");
				e.setLine(3, ChatColor.DARK_GREEN + "Lobby serwer");
				e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Pomyslnie utworzono tabliczke \"lobby\"!");
				return;
			}
			if(e.getLine(1).equalsIgnoreCase("servers")) {
				e.setLine(0, "");
				e.setLine(1, "Lista serwerów");
				e.setLine(2, ChatColor.UNDERLINE + "" + ChatColor.BOLD + "Altaria");
				e.setLine(3, "");
				e.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Pomyslnie utworzono tabliczke \"servers\"!");
				return;
			} else {
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.RED + "Nie znaleziono \"" + e.getLine(1) + "\".");
				e.getPlayer().sendMessage(ChatColor.GRAY + "Dostepne argumenty: lobby, servers");
				return;
			}
		}
	}
	
}
