package pl.dyrtcraft.xp;

import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.dyrtcraft.Util;

public class DyrtUtils implements Util {
	
	private boolean infoBook = false;
	private boolean shop = false;
	
	@Override
	public boolean isInfoBook() {
		return infoBook;
	}
	
	@Override
	public boolean isShop() {
		return shop;
	}
	
	@Override
	public void sendNotify(@Nonnull String message, @Nonnull boolean priority) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player.isOp() || player.hasPermission("dyrtcraftxp.notify")) {
				if(priority == false) {
					player.sendMessage(ChatColor.AQUA + "* " + message);
				}
				if(priority == true) {
					player.sendMessage(ChatColor.RED + "** " + message + "!");
				}
			}
		}
		Bukkit.getLogger().log(Level.WARNING, ChatColor.RED + "REP: " + message);
	}
	
	@Override
	public void setInfoBook(boolean infoBook) {
		this.infoBook = infoBook;
	}
	
	@Override
	public void setShop(boolean shop) {
		this.shop = shop;
	}
	
}
