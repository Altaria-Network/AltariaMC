package pl.dyrtcraft.xp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.xp.DyrtCraftXP;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class PortalComponent {
	
	public boolean create(CommandSender sender, String id) {
		if(!checkPerm(sender))
			return true;
		
		if(!(sender instanceof Player)) {
			Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
			return true;
		}
		
		// WorldEdit selection
		Selection region = null;
		try {
			region = DyrtCraftXP.getWE().getSelection((Player) sender);
		} catch(NoClassDefFoundError e) {
			sender.sendMessage(ChatColor.RED + "Do wykonania tej komendy wymagany jest plugin WorldEdit!");
			return true;
		} catch(ClassNotFoundException e) {
			sender.sendMessage(ChatColor.RED + "Do wykonania tej komendy wymagany jest plugin WorldEdit!");
			return true;
		}
		if(region == null) {
			sender.sendMessage(ChatColor.RED + "Nie zaznaczono obszaru mapy!");
			sender.sendMessage(ChatColor.GRAY + "Obszar zaznaczasz uzywajac //wand oraz klikajac przyciskiem myszy.");
			return true;		
		}
		
		if(DyrtCraft.getPortals().getPortals().contains(id.toLowerCase())) {
			sender.sendMessage(ChatColor.RED + "Portal o tym ID juz istnieje!");
			return true;
		}
		DyrtCraft.getPortals().create(id.toLowerCase(), region.getWorld(), region.getMaximumPoint(), region.getMinimumPoint(), Server.UNKNOWN);
		sender.sendMessage(ChatColor.GREEN + "Pomyslnie stworzono portal o ID " + id.toLowerCase());
		sender.sendMessage(ChatColor.GRAY + "Nie zapomnij stworzyc /dcxp portals destination " + id.toLowerCase() + " <serwer>!");
		return true;
	}
	
	public boolean destination(CommandSender sender, String id, String server) {
		if(!checkPerm(sender))
			return true;
		
		if(!DyrtCraft.getPortals().getPortals().contains(id.toLowerCase())) {
			sender.sendMessage(ChatColor.RED + "Nie znaleziono portalu o ID \"" + id.toLowerCase() + "\"!");
			return true;
		}
		if(server == null) {
			sender.sendMessage(ChatColor.RED + "Destination: " + DyrtCraft.getPortals().getDestination(id.toLowerCase()));
			return true;
		}
		
		Server serverEnum = null;
		World world = Bukkit.getWorld(DyrtCraftXP.getInstance().getConfig().getString("portale." + id.toLowerCase() + ".world"));
		Location point1 = new Location(null,
				DyrtCraftXP.getInstance().getConfig().getInt("portale." + id.toLowerCase() + ".location.x-max"),
				DyrtCraftXP.getInstance().getConfig().getInt("portale." + id.toLowerCase() + ".location.y-max"),
				DyrtCraftXP.getInstance().getConfig().getInt("portale." + id.toLowerCase() + ".location.z-max"));
		Location point2 = new Location(null,
				DyrtCraftXP.getInstance().getConfig().getInt("portale." + id.toLowerCase() + ".location.x-min"),
				DyrtCraftXP.getInstance().getConfig().getInt("portale." + id.toLowerCase() + ".location.y-min"),
				DyrtCraftXP.getInstance().getConfig().getInt("portale." + id.toLowerCase() + ".location.z-min"));
		
		try {
			serverEnum = Server.valueOf(server.toUpperCase());
		} catch(Exception ex) { // FIXME
			sender.sendMessage(ChatColor.RED + "Nie znaleziono serwera \"" + server.toUpperCase() + "\"!");
			return true;
		}
		DyrtCraft.getPortals().create(id, world, point1, point2, serverEnum);
		sender.sendMessage(ChatColor.GREEN + "Pomyslnie stworzono portal do " + DyrtCraft.getProxy().getServerName(serverEnum) + "!");
		return true;
	}
	
	public boolean manage(CommandSender sender) {
		if(!checkPerm(sender))
			return true;
		
		StringBuilder str = new StringBuilder();
		for(String portal : DyrtCraftXP.getInstance().getConfig().getStringList("portale.lista-portali")) {
			str.append(ChatColor.GRAY + portal);
			str.append(", ");
		}
		sender.sendMessage(ChatColor.GOLD + "Obecne portale: " + str.toString());
		return true;
	}
	
	public boolean remove(CommandSender sender, String id) {
		if(!checkPerm(sender))
			return true;
		
		if(!DyrtCraft.getPortals().getPortals().contains(id.toLowerCase())) {
			sender.sendMessage(ChatColor.RED + "Nie znaleziono portalu o ID \"" + id.toLowerCase() + "\"!");
			return true;
		}
		String dest = DyrtCraft.getProxy().getServerName(DyrtCraft.getPortals().getDestination(id.toLowerCase()));
		DyrtCraft.getPortals().remove(id);
		sender.sendMessage(ChatColor.GREEN + "Pomyslnie usunieto portal do " + dest + " o ID " + id.toLowerCase() + "!");
		return true;
	}
	
	private boolean checkPerm(CommandSender sender) {
		if(sender.hasPermission("dyrtcraft.xp.portals")) {
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return false;
		}
	}
	
}
