package pl.dyrtcraft.xp;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import pl.dyrtcraft.BungeeCord;
import pl.dyrtcraft.Server;
import pl.dyrtcraft.xp.inv.BungeeInventory;

public class DyrtBungeeCord implements BungeeCord {
	
	private Server currentServer = Server.UNKNOWN;
	
	@Override
	public boolean isCurrentServer(Server server) {
		if(currentServer == server) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Server getCurrentServer() {
		return currentServer;
	}
	
	@Override
	public int getOnlineCount(@Nonnull Server server) {
		return 0;
	}
	
	@Override
	public Server getServer(@Nonnull String address) {
		switch(address) {
		case "apocalypto": return Server.APOCALYPTO_HARDCORE;
		case "arcade01": return Server.ARCADE1;
		case "arcade02": return Server.ARCADE2;
		case "arcade03": return Server.ARCADE3;
		case "lobby": return Server.LOBBY;
		case "minez": return Server.MINEZ;
		case "sb": return Server.SKYBLOCK;
		case "fb": return Server.FREEBUILD;
		case "sv": return Server.SURVIVAL;
		case "rpg": return Server.RPG;
		case "test": return Server.TEST;
		default: return Server.UNKNOWN;
		}
	}
	
	@Override
	public String getServerAddress(@Nonnull Server server) {
		switch(server) {
		case APOCALYPTO_HARDCORE: return "apocalypto";
		case ARCADE1: return "arcade01";
		case ARCADE2: return "arcade02";
		case ARCADE3: return "arcade03";
		case LOBBY: return "lobby";
		case MINEZ: return "minez";
		case SKYBLOCK: return "sb";
		case FREEBUILD: return "fb";
		case SURVIVAL: return "sv";
		case RPG: return "rpg";
		case TEST: return "test";
		default: return server.toString().toLowerCase();
		}
	}
	
	@Override
	public Inventory getServerChooserInventory(@Nonnull Player player) {
		BungeeInventory inv = new BungeeInventory(player);
		return inv.getInventory();
	}
	
	@Override
	public String getServerName(@Nonnull Server server) {
		switch(server) {
		case APOCALYPTO_HARDCORE: return "Apocalypto - Hardcore";
		case ARCADE1: return "Arcade 1";
		case ARCADE2: return "Arcade 2";
		case ARCADE3: return "Arcade 3";
		case LOBBY: return "Lobby";
		case MINEZ: return "MineZ";
		case SKYBLOCK: return "SkyBlock";
		case FREEBUILD: return "FreeBuild";
		case SURVIVAL: return "Survival";
		case RPG: return "RPG";
		case TEST: return "Serwer Testowy";
		default: return "Unknown";
		}
	}
	
	@Override
	public int getSlots(@Nonnull Server server) {
		return 0;
	}
	
	@Override
	public void setCurrentServer(Server server) {
		currentServer = server;
	}
	
}
