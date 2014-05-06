package pl.dyrtcraft.xp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class LoginListener implements Listener {
	
	public static final String[] BANNED = new String[] { "?", "@", "*", "!" };
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		for(int i = 0; i < BANNED.length; i++) {
			if(e.getPlayer().getName().contains(BANNED[i])) {
				ban(e.getAddress().getHostAddress());
				e.disallow(Result.KICK_BANNED, ChatColor.RED + "Auto-ban " + ChatColor.AQUA + ">>" + ChatColor.RED + ChatColor.ITALIC + " Próba otrzymania wszystkich uprawnien!"
						+ ChatColor.RESET + "\n" + ChatColor.AQUA + "Wygasa: " + ChatColor.DARK_RED + "" + ChatColor.UNDERLINE + "Nigdy!");
			}
		}
	}
	
	private void ban(String ip) {
		Server server = Bukkit.getServer();
		if(Bukkit.getServer().getPluginManager().getPlugin("AltariaLobby") != null)
			server.banIP(ip);
	}
	
}
