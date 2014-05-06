package pl.dyrtcraft.xp.api;

import pl.dyrtcraft.xp.DyrtCraftXP;
import pl.dyrtcraft.xp.MySQL;

/**
 * @since Alpha 1.1.5_2
 */
@Deprecated
public class Database {

	static DyrtCraftXP plugin;
	static MySQL mySql;
	static XP xp;
	
	@Deprecated
	public Database(DyrtCraftXP dyrtCraftXP) {
		plugin = dyrtCraftXP;
	}
	
	/**
	 * Zdobadz ostatni czas wylogowania z serwerowni
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick gracza
	 * @return String Czas ostatniego wylogowania
	 */
	@Deprecated
	public static String getLastLogout(String player) {
		String lastLogout = mySql.getLastLogout(player);
		return lastLogout;
	}
	
	/**
	 * Ustaw ostatni czas wylogowania
	 * 
	 * @author TheMolkaPL
	 * @since Alpha 1.6
	 * 
	 * @param player Nick gracza
	 * @param time Czas wylogowania
	 */
	@Deprecated
	public static void setLastLogout(String player, String time) {
		mySql.setLastLogout(player, time);
	}
	
}
