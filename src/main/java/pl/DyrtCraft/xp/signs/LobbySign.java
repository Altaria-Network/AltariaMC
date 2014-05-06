package pl.dyrtcraft.xp.signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Server;

public class LobbySign implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if(s.getLine(0).equalsIgnoreCase(ChatColor.UNDERLINE + "LOBBY") &&
						s.getLine(1).equalsIgnoreCase(ChatColor.DARK_GREEN + "do") &&
						s.getLine(2).equalsIgnoreCase(ChatColor.DARK_GREEN + "Altaria") &&
						s.getLine(3).equalsIgnoreCase(ChatColor.DARK_GREEN + "Lobby serwer")) {
					DyrtCraft.getMember(e.getPlayer()).connect(Server.LOBBY);
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
