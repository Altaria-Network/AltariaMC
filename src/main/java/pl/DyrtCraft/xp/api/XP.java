package pl.dyrtcraft.xp.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.events.PlayerGetXPEvent;
import pl.dyrtcraft.events.PlayerLoseXPEvent;
import pl.dyrtcraft.xp.DyrtCraftXP;
import pl.dyrtcraft.xp.MySQL;

/**
 * @deprecated USE {@link DyrtCraft}!
 */
@Deprecated
public class XP {

	private static MySQL mySql;
	DyrtCraftXP plugin;
	
	public XP(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
		mySql = DyrtCraftXP.getDB();
	}
	
	/**
	 * @deprecated USE {@link DyrtCraft}!
	 */
	@Deprecated
	public static void addXp(Player player, int xp, String powod) {
		PlayerGetXPEvent event = new PlayerGetXPEvent(player, powod, xp);
		Bukkit.getPluginManager().callEvent(event);
		
		int xpRazem = xp + getXp(player);
		//mySql.setXP(player.toString(), xpRazem);
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Zdobyles " + xp + " XP za: " + powod + ".");
		player.sendMessage(XP.showXp(player.getName()));
	}
	
	/**
	 * @deprecated USE {@link DyrtCraft}!
	 */
	@Deprecated
	public static boolean delXp(Player player, int xp, String powod) {
		PlayerLoseXPEvent event = new PlayerLoseXPEvent(player, powod, xp);
		Bukkit.getPluginManager().callEvent(event);
		
		// TODO Naprawic przy release
		/*int iloscXp = mySql.getXP(player.toString());
		if(iloscXp == xp || iloscXp < xp) {
			// Nie wystarczajacy liczba XP
			return false;
		}
		int xpRazem = mySql.getXP(player.toString())-xp;
		mySql.setXP(player.toString(), xpRazem);*/
		player.sendMessage(ChatColor.LIGHT_PURPLE + "Straciles " + event.getAmount() + " XP za: " + event.getReason() + ".");
		player.sendMessage(XP.showXp(player.getName()));
		return true;
	}
	
	/**
	 * @deprecated USE {@link DyrtCraft}!
	 */
	@Deprecated
	public static int getXp(String player) {
		int xp = -1;
		try {
			//mySql.getXP(player);
		} catch(NullPointerException ex) {}
		return xp;
	}
	
	/**
	 * @deprecated USE {@link DyrtCraft}!
	 */
	@Deprecated
	public static String showXp(String player) {
		int xp1 = getXp(player);
		return ChatColor.LIGHT_PURPLE + "Aktualna ilosc XP gracza " + player + " to " + xp1 + ".";
	}
	
	private static int getXp(Player player) {
		int xp = -1;
		try {
			getXp(player.getName());
		} catch(NullPointerException ex) {}
		return xp;
	}
	
}