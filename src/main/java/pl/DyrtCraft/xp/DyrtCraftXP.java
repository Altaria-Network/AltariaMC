package pl.dyrtcraft.xp;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.xp.command.DcxpCommand;
import pl.dyrtcraft.xp.command.LobbyCommand;
import pl.dyrtcraft.xp.command.XpCommand;
import pl.dyrtcraft.xp.inv.ArcadeInventoryListeners;
import pl.dyrtcraft.xp.inv.BungeeInventoryListeners;
import pl.dyrtcraft.xp.portals.PortalListener;
import pl.dyrtcraft.xp.signs.LobbySign;
import pl.dyrtcraft.xp.signs.ServersSign;
import pl.dyrtcraft.xp.signs.SignCreator;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class DyrtCraftXP extends JavaPlugin {
	
	private static DyrtCraft api;
	private static DyrtCraftXP instance;
	private static MySQL sql;
	private static boolean we;
	private static WorldEditPlugin wePlugin;
	
	// Dane do SQL
	/* adres */ String address;
	/* login */ String login;
	/* haslo */ String password;
	// Koniec danych do SQL
	
	@Override
	public void onEnable() {
		getLogger().info("Ladowanie " + getDescription().getFullName() + "...");
		long loadTime = System.currentTimeMillis();
		
		saveDefaultConfig();
		
		//******************************************************************************//
		
		if(getServer().getPluginManager().getPlugin("WorldEdit") == null) {
			we = false;
			getLogger().warning("Nie wykryto pluginu WorldEdit! Nie wszystkie funkcje pluginu sa dostepne!");
		} else {
			we = true;
			Plugin worldEdit = getServer().getPluginManager().getPlugin("WorldEdit");
			wePlugin = (WorldEditPlugin) worldEdit;
			getLogger().info("Pomyslnie polaczono z pluginem WorldEdit v" + worldEdit.getDescription().getVersion() + "!");
		}
		
		instance = this;
		api = new DyrtCraft();
		
		address = getConfig().getString("sql.address");
		login = getConfig().getString("sql.login");
		password = getConfig().getString("sql.password");
		
		//******************************************************************************//
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		//******************************************************************************//
		
		registerCommands();
		registerListeners();
		
		//******************************************************************************//
		
		getLogger().info("Ladowanie MySQL.class...");
		sql = new MySQL(this, address, login, password);
		
		//******************************************************************************//
		
		long finLoadTime = System.currentTimeMillis() - loadTime;
		getLogger().info("Zaladowano " + getDescription().getFullName() + " w " + finLoadTime + " ms!");
	}
	
	@Override
	public void onDisable() {
		saveConfig();
	}
	
	/**
	 * @deprecated USE {@link DyrtCraft}!
	 */
	@Deprecated
	public static DyrtCraft getApi() {
		return api;
	}
	
	public static DyrtCraftXP getInstance() {
		return instance;
	}
	
	public static MySQL getDB() {
		return sql;
	}
	
	public static WorldEditPlugin getWE() throws ClassNotFoundException {
		return wePlugin;
	}
	
	public static boolean isWeEnabled() {
		return we;
	}
	
	public void registerCommands() {
		getCommand("dcxp").setExecutor(new DcxpCommand());
		getCommand("lobby").setExecutor(new LobbyCommand(this));
		//getCommand("xp").setExecutor(new XpCommand(this));
	}
	
	public void registerListeners() {
		getLogger().info("Rejestrowanie listener�w...");
		getServer().getPluginManager().registerEvents(new ArcadeInventoryListeners(), this);
		getServer().getPluginManager().registerEvents(new BungeeInventoryListeners(), this);
		getServer().getPluginManager().registerEvents(new LobbySign(), this);
		getServer().getPluginManager().registerEvents(new PortalListener(), this);
		getServer().getPluginManager().registerEvents(new ServersSign(), this);
		getServer().getPluginManager().registerEvents(new SignCreator(), this);
		getServer().getPluginManager().registerEvents(new LoginListener(), this);
		getLogger().info("Zarejestrowano listenery!");
	}
	
}
