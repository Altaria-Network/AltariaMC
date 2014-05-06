package pl.dyrtcraft.xp;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;

import pl.dyrtcraft.Database;

public class DyrtDatabase implements Database {
	
	@Override
	public void createNew(@Nonnull Player player) {
		createNew(player.getName());
	}
	
	@Override
	public void createNew(@Nonnull String name) {
		MySQL.get().addNewPlayer(name, DyrtCraftXP.getInstance().getConfig().getString("nazwa-serwera"));
	}
	
}
