package pl.dyrtcraft.xp.portals;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.xp.DyrtCraftXP;

public class PortalListener implements Listener {
	
	private ArrayList<String> blocked = new ArrayList<String>();
	private int task;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(DyrtCraft.getPortals().isPortal(e.getPlayer().getLocation())) {
			e.getPlayer().teleport(Bukkit.getWorld(e.getPlayer().getWorld().getName()).getSpawnLocation());
		}
	}
	
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		if(DyrtCraft.getPortals().isPortal(e.getTo())) {
			Server server = DyrtCraft.getPortals().getDestination(DyrtCraft.getPortals().getPortal(e.getTo()));
			if(!(server == null) && !blocked.contains(e.getPlayer().getName())) {
				blocked.add(e.getPlayer().getName());
				DyrtCraft.getMember(e.getPlayer()).connect(server);
				task = Bukkit.getScheduler().runTaskLater(DyrtCraftXP.getInstance(), new Runnable() {

					@Override
					public void run() {
						blocked.remove(e.getPlayer().getName());
						Bukkit.getScheduler().cancelTask(task);
					}
				} , 200L).getTaskId();
			}
		}
	}
	
}
