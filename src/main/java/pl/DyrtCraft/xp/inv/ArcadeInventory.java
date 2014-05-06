package pl.dyrtcraft.xp.inv;

import java.util.Arrays;
import java.util.List;

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

public class ArcadeInventory {
	
	private Inventory inv;
	private ItemStack ac1, ac2, ac3,	lobby, esc, about, book1, book2, book3, shop;
	
	public ArcadeInventory(Player player) {
		inv = Bukkit.createInventory(player, 18, ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "DyrtCraft" + ChatColor.RESET + ChatColor.DARK_GRAY + " Wybierz serwer");
		
		ac1 = createServer(Server.ARCADE1);
		ac2 = createServer(Server.ARCADE2);
		ac3 = createServer(Server.ARCADE3);
		
		lobby = createLobby();
		esc = createEscape();
		book1 = createInfoBook(InfoBookType.FACEBOOK);
		book2 = createInfoBook(InfoBookType.TEAMSPEAK3);
		book3 = createInfoBook(InfoBookType.WEBSITE);
		
		if(DyrtCraft.getUtils().isInfoBook()) { about = createAbout(); }
		if(DyrtCraft.getUtils().isShop()) { shop = createShop(player); }
		
		inv.setItem(0, ac1);
		inv.setItem(1, ac2);
		inv.setItem(2, ac3);
		
		inv.setItem(7, lobby);
		inv.setItem(8, esc);
		inv.setItem(10, about);
		inv.setItem(12, book1);
		inv.setItem(13, book2);
		inv.setItem(14, book3);
		inv.setItem(16, shop);
	}
	
	public Inventory getInventory() {
		return inv;
	}
	
	private ItemStack createAbout() {
		ItemStack i = new ItemStack(Material.BOOK, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "Informacje o serwerze");
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Wyswietl informacje o serwerze"));
		i.setItemMeta(iMeta);
		return i;
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
		if(type == InfoBookType.FACEBOOK) {
			ItemStack i = new ItemStack(Material.WRITTEN_BOOK, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "Facebook");
			iMeta.setLore(Arrays.asList(ChatColor.GRAY + "https://facebook.com/DyrtCraftNetwork"));
			i.setItemMeta(iMeta);
			return i;
		}
		if(type == InfoBookType.TEAMSPEAK3) {
			ItemStack i = new ItemStack(Material.WRITTEN_BOOK, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "TeamSpeak 3");
			iMeta.setLore(Arrays.asList(ChatColor.GRAY + "dyrtcraft.pl"));
			i.setItemMeta(iMeta);
			return i;
		}
		if(type == InfoBookType.WEBSITE) {
			ItemStack i = new ItemStack(Material.WRITTEN_BOOK, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.ITALIC + "Strona WWW oraz forum");
			iMeta.setLore(Arrays.asList(ChatColor.GRAY + "http://dyrtcraft.pl"));
			i.setItemMeta(iMeta);
			return i;
		} else {
			return new ItemStack(Material.BEDROCK, 1);
		}
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
		String name = "Brak nazwy";
		List<String> description = Arrays.asList("Brak opisu");
		int number = 0;
		if(server == Server.ARCADE1) {
			name = "Arcade 1"; description = Arrays.asList(
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Blitz - TDM,",
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Capture the wool,",
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Destroy the monument,",
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Team-death match",
					ChatColor.GRAY + "Kliknij, aby przejsc na serwer"); number = 1;
		}
		if(server == Server.ARCADE2) {
			name = "Arcade 2"; description = Arrays.asList(
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Paintball,",
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Run Rush",
					ChatColor.GRAY + "Kliknij, aby przejsc na serwer"); number = 2;
		}
		if(server == Server.ARCADE3) {
			name = "Arcade 3"; description = Arrays.asList(
					ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ChatColor.ITALIC + "Castle mod",
					ChatColor.GRAY + "Kliknij, aby przejsc na serwer"); number = 3;
		}
		
		ItemStack i = new SpawnEgg(EntityType.ENDER_CRYSTAL).toItemStack(number);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		iMeta.setLore(description);
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack createShop(Player player) {
		if(DyrtCraft.getProxy().isCurrentServer(Server.LOBBY)) {
			ItemStack i = new ItemStack(Material.EMERALD, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "Sklep globalny");
			iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Otwórz sklep globalny DyrtCraft Network",
					ChatColor.GRAY + "Twoja ilosc XP: " + getXp(player)));
			i.setItemMeta(iMeta);
			return i;
		} else {
			ItemStack i = new ItemStack(Material.EMERALD, 1);
			ItemMeta iMeta = i.getItemMeta();
			iMeta.setDisplayName(ChatColor.GOLD + "Sklep serwera");
			iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Otwórz sklep serwera",
					ChatColor.GRAY + "Twoja ilosc XP: " + getXp(player)));
			i.setItemMeta(iMeta);
			return i;
		}
	}
	
	private int getXp(Player player) {
		try {
			return DyrtCraft.getMember(player).getXp();
		} catch(Exception ex) {
			return -1;
		}
	}
	
}
