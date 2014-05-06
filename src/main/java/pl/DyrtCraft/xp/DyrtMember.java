package pl.dyrtcraft.xp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Member;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.events.PlayerChangeServerEvent;
import pl.dyrtcraft.xp.api.XP;

@SuppressWarnings("deprecation")
public class DyrtMember implements Member {
	
	private Player player;
	
	public DyrtMember(Player member) {
		player = member;
	}
	
	@Override
	public void addXp(@Nonnull int amount, @Nonnull String reason) {
		XP.addXp(player, amount, reason);
	}
	
	@Override
	public void connect(@Nonnull Server server) {
		PlayerChangeServerEvent event = new PlayerChangeServerEvent(player, server);
		Bukkit.getPluginManager().callEvent(event);
		
		if(!event.isCancelled()) {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			player.sendMessage(event.getMessage());
			try {
				out.writeUTF("Connect");
				out.writeUTF(DyrtCraft.getProxy().getServerAddress(event.getServer()));
			} catch(IOException ex) {}
			Bukkit.getLogger().info("Przelaczanie gracza " + player.getName() + " na serwer " + DyrtCraft.getProxy().getServerName(server) + "...");
			player.sendPluginMessage(DyrtCraftXP.getInstance(), "BungeeCord", b.toByteArray());
		}
	}
	
	@Override
	public boolean delXp(@Nonnull int amount, @Nonnull String reason) {
		XP.delXp(player, amount, reason);
		return false;
	}
	
	@Override
	public int getDeads() {
		// TODO
		return -1;
	}
	
	@Override
	public int getKills() {
		// TODO
		return -1;
	}
	
	@Override
	public SimpleDateFormat getLastLogoutTime() {
		// TODO
		return null;
	}
	
	@Override
	public Server getLastServer() {
		return DyrtCraft.getProxy().getServer(MySQL.get().getLastServer(player.getName()));
	}
	
	@Override
	public int getXp() {
		return XP.getXp(player.getName());
	}
	
	@Override
	public void setDeads(int deads) {
		// TODO
	}
	
	@Override
	public void setKills(int kills) {
		// TODO
	}
	
	@Override
	public void setLastLogoutTime(@Nonnull SimpleDateFormat time) {
		// TODO
	}
	
	@Override
	public void setLastServer(@Nonnull Server server) {
		MySQL.get().setLastServer(player.getName(), DyrtCraft.getProxy().getServerName(server));
	}
	
	@Override
	public String showXp() {
		return XP.showXp(player.getName());
	}
	
}
