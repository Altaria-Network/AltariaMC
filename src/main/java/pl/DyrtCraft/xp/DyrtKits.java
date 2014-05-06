package pl.dyrtcraft.xp;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.dyrtcraft.Kits;

public class DyrtKits implements Kits {
	
	@Override
	public ItemStack getServerChooserItem() {
		ItemStack i = new ItemStack(Material.COMPASS, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Serwery Altaria Network");
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Kliknij, aby wybrac serwer!"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	@Override
	public ItemStack getLobbyItem() {
		ItemStack i = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Powrót na serwer Lobby");
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Kliknij, aby przejsc do Lobby!"));
		i.setItemMeta(iMeta);
		return i;
	}
	
}
