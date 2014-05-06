package pl.dyrtcraft.xp.inv;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.Server;

public class BungeeInventory {
	
	private Inventory inv;
	//private ItemStack hc, ac, mz, sb,	lobby, esc, about, book1, book2, book3, shop;
	private ItemStack fb, lobby, sb, sv, esc, book;
	
	public BungeeInventory(Player player) {
		inv = Bukkit.createInventory(player, 18, ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Altaria" + ChatColor.RESET + ChatColor.DARK_GRAY + " Wybierz serwer");
		
		//hc = createServer(Server.APOCALYPTO_HARDCORE);
		//ac = createArcade();
		//mz = createServer(Server.MINEZ);
		fb = createServer(Server.FREEBUILD);
		sb = createServer(Server.SKYBLOCK);
		sv = createServer(Server.SURVIVAL);
		
		lobby = createLobby();
		esc = createEscape();
		book = createInfoBook(InfoBookType.TEAMSPEAK3);
		
		inv.setItem(0, fb);
		inv.setItem(1, sb);
		//inv.setItem(2, sv);
		
		inv.setItem(7, lobby);
		inv.setItem(8, esc);
		inv.setItem(13, book);
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	private ItemStack createEscape() {
		ItemStack i = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "Zamknij okno");
		iMeta.setLore(Arrays.asList(ChatColor.GRAY + "Kliknij, aby zamknac okno"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack createInfoBook(InfoBookType type) {
		ItemStack i = new ItemStack(Material.WRITTEN_BOOK, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "TeamSpeak 3");
		iMeta.setLore(Arrays.asList(ChatColor.GRAY + "ts.altariamc.eu"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack createLobby() {
		ItemStack i = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "Serwer Lobby");
		iMeta.setLore(Arrays.asList(ChatColor.GRAY + "Kliknij, aby wrócic na serwer Lobby"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack createServer(Server server) {
		String name = "Brak nazwy", description = "Brak opisu";
		EntityType entity = EntityType.SHEEP;
		if(server == Server.FREEBUILD) {
			name = "FreeBuild"; description = "Wejdz i zobacz jak wyglada FreeBuild"; entity = EntityType.VILLAGER;
		}
		if(server == Server.SKYBLOCK) {
			name = "SkyBlock"; description = "Czas na SkyBlock w chmurach!"; entity = EntityType.SHEEP;
		}
		if(server == Server.SURVIVAL) {
			name = "Survival"; description = "W budowie ;D"; entity = EntityType.SQUID;
		}
		
		ItemStack i = new SpawnEgg(entity).toItemStack(1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + description, ChatColor.GRAY + "Kliknij, aby przejsc na serwer"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	/*private ItemStack createShop(Player player) {
		if(DyrtCraft.getProxy().isCurrentServer(Server.LOBBY)) {
			ItemStack i = new ItemStack(Material.EMERALD, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "Sklep globalny");
			iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Otw�rz sklep globalny DyrtCraft Network",
					ChatColor.GRAY + "Twoja ilosc XP: " + getXp(player)));
			i.setItemMeta(iMeta);
			return i;
		} else {
			ItemStack i = new ItemStack(Material.EMERALD, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "Sklep serwera");
			iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Otw�rz sklep serwera",
					ChatColor.GRAY + "Twoja ilosc XP: " + getXp(player)));
			i.setItemMeta(iMeta);
			return i;
		}
	}
	
	private String count(Server server) {
		return ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + ChatColor.ITALIC + DyrtCraft.getProxy().getOnlineCount(server) + "/" + DyrtCraft.getProxy().getSlots(server);
	}
	
	private int getXp(Player player) {
		try {
			return DyrtCraft.getMember(player).getXp();
		} catch(Exception ex) {
			return -1;
		}
	}*/
	
}
