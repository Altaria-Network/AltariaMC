package pl.dyrtcraft.xp.portals;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import pl.dyrtcraft.PortalsManager;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.xp.DyrtCraftXP;

public class DyrtPortalsManager implements PortalsManager {
	
	@Override
	public void create(@Nonnull String id, @Nonnull World world, @Nonnull Location locMax, @Nonnull Location locMin, @Nullable Server destination) {
		List<String> portals = DyrtCraftXP.getInstance().getConfig().getStringList("portale.lista-portali");
		portals.remove(id.toLowerCase());
		portals.add(id.toLowerCase());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".destination", destination.toString());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".location.x-max", locMax.getBlockX());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".location.y-max", locMax.getBlockY());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".location.z-max", locMax.getBlockZ());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".location.x-min", locMin.getBlockX());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".location.y-min", locMin.getBlockY());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".location.z-min", locMin.getBlockZ());
		DyrtCraftXP.getInstance().getConfig().set("portale." + id + ".world", world.getName());
		DyrtCraftXP.getInstance().getConfig().set("portale.lista-portali", portals);
		DyrtCraftXP.getInstance().saveConfig();
	}
	
	@Override
	public boolean isPortal(@Nonnull Location loc) {
		if(getPortal(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isPortal(@Nonnull World world, @Nonnull int x, @Nonnull int y, @Nonnull int z) {
		if(getPortal(world, x, y, z) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Server getDestination(@Nonnull String id) {
		String server = DyrtCraftXP.getInstance().getConfig().getString("portale." + id + ".destination");
		if(server == null) {
			return null;
		}
		try {
			return Server.valueOf(server.toUpperCase());
		} catch(Exception ex) { // FIXME
			return null;
		}
	}
	
	@Override
	public String getPortal(@Nonnull Location loc) {
		return getPortal(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}
	
	@Override
	public String getPortal(@Nonnull World world, @Nonnull int x, @Nonnull int y, @Nonnull int z) {
		FileConfiguration config = DyrtCraftXP.getInstance().getConfig();
		for(String portal : config.getStringList("portale.lista-portali")) {
			if(world.getName().equals(config.getString("portale." + portal + ".world"))) {
				if(x <= config.getInt("portale." + portal + ".location.x-max") && x >= config.getInt("portale." + portal + ".location.x-min")) {
					if(y <= config.getInt("portale." + portal + ".location.y-max") && y >= config.getInt("portale." + portal + ".location.y-min")) {
						if(z <= config.getInt("portale." + portal + ".location.z-max") && z >= config.getInt("portale." + portal + ".location.z-min")) {
							return portal;
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public List<String> getPortals() {
		return DyrtCraftXP.getInstance().getConfig().getStringList("portale.lista-portali");
	}
	
	@Override
	public boolean remove(@Nonnull String id) {
		if(getPortals().contains(id)) {
			List<String> portals = getPortals();
			portals.remove(id);
			DyrtCraftXP.getInstance().getConfig().set("portale.lista-portali", portals);
			DyrtCraftXP.getInstance().getConfig().set("portale." + id, null);
			DyrtCraftXP.getInstance().saveConfig();
			return true;
		} else {
			return false;
		}
	}
	
}
