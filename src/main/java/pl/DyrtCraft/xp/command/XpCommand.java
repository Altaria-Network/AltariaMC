package pl.dyrtcraft.xp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.xp.DyrtCraftXP;

/**
 * Komendy zwiazane z doswiadczeniem DyrtCraftXP
 * 
 * @author TheMolkaPL
 * @see XP
 */
public class XpCommand implements CommandExecutor {
	
	int xp;
	
	public XpCommand(DyrtCraftXP dyrtCraftXP) {}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		// Komenda /xp
		/*if(label.equalsIgnoreCase("xp")) {
			// Liczba argumentow - 0
			if(args.length==0) {
				// Jezeli wyslano z konsoli
				if(!(sender instanceof Player)) {
					Bukkit.getLogger().warning("Nie mozesz wykonywac tego polecenia z poziomu konsoli!");
					return true;
				}
				// Jezeli wyslal gracz
				Player p = (Player) sender;
				// Pokaz ilosc XP
				sender.sendMessage(DyrtCraft.getMember(p).showXp());
				return true;
			}
			// Liczba argumentow - 1
			if(args.length==1) {
				// Pobieranie gracza z listy graczy online
				Player gracz = Bukkit.getServer().getPlayer(args[0]);
				// Jezeli sender nie jest operatorem serwera
				if(!(sender.isOp())) {
					sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
					return true;
				}
				// Jezeli pobrany gracz nie jest online na serwerze
				if(gracz == null) {
		        	sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" nie jest obecnie na serwerze!");
		        	return true;
		        }
				// Jezeli wyslal gracz
				// Pokaz ilosc XP
		        sender.sendMessage(DyrtCraft.getMember(gracz).showXp());
		        return true;
			}
			// Liczba argumentow - 2
			if(args.length==2) {
				// Jezeli sender nie jest konsola
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Ojj, komenda musi zostac wykonana z konsoli!");
					return true;
				}
				// Argument 1: create
				if(args[1].equalsIgnoreCase("create")) {
					DyrtCraft.getDatabase().createNew(args[0]);
					DyrtCraft.getUtils().sendNotify(sender.getName() + " stworzyl nowe konto dla gracza " + args[0], true);
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Popelniono blad! Spr�buj jeszcze raz!");
					sender.sendMessage(ChatColor.RED + "/xp [gracz] [[create]del|give|set <liczba>]");
					return true;
				}
			}
			// Liczba argumentow - 3
			if(args.length==3) {
				// Jezeli sender nie jest konsola
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Ojj, komenda musi zostac wykonana z konsoli!");
					return true;
				}
				// Argument 1: del
				if(args[1].equalsIgnoreCase("del")) {
					try {
						if(!(Bukkit.getPlayer(args[0]).isOnline())) {
							sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" musi byc online na serwerze!");
							return true;
						}
						xp = Integer.parseInt(args[2]);
						Player player = Bukkit.getPlayer(args[0]);
						if(DyrtCraft.getMember(player).delXp(xp, "Usunieto " + xp + " przez " + sender.getName())) {
							sender.sendMessage(ChatColor.RED + "Gracz " + player.getName() + " nie posiada wystarczajacej ilosci XP lub/i wystapil blad z baza MySQL.");
							return true;
						}
						DyrtCraft.getUtils().sendNotify(sender.getName() + " usunal graczowi " + args[0] + xp + " XP", true);
						return true;
					} catch(NumberFormatException ex) {
						sender.sendMessage(ChatColor.RED + "Argumentem \"" + args[2] + "\" musi byc liczba!");
						return true;
					}
				}
				// Argument 1: give
				if(args[1].equalsIgnoreCase("give")) {
					try {
						if(!(Bukkit.getPlayer(args[0]).isOnline())) {
							sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" musi byc online na serwerze!");
							return true;
						}
						xp = Integer.parseInt(args[2]);
						Player player = Bukkit.getPlayer(args[0]);
						if(DyrtCraft.getMember(player).delXp(xp, "Dodano " + xp + " XP przez " + sender.getName())) {
							sender.sendMessage(ChatColor.RED + "Gracz " + player.getName() + " nie posiada wystarczajacej ilosci XP lub/i wystapil blad z baza MySQL.");
							return true;
						}
						DyrtCraft.getUtils().sendNotify(sender.getName() + " dodal graczowi " + args[0] + xp + " XP", true);
						return true;
					} catch(NumberFormatException ex) {
						sender.sendMessage(ChatColor.RED + "Argumentem \"" + args[2] + "\" musi byc liczba!");
						return true;
					}
				}
				// Argument 1: set
				if(args[1].equalsIgnoreCase("set")) {
					try {
						if(!(Bukkit.getPlayer(args[0]).isOnline())) {
							sender.sendMessage(ChatColor.RED + "Gracz \"" + args[0] + "\" musi byc online na serwerze!");
							return true;
						}
						xp = Integer.parseInt(args[2]);
						Player player = Bukkit.getPlayer(args[0]);
						if(DyrtCraft.getMember(player).delXp(DyrtCraft.getMember(player).getXp(), "")) {
							sender.sendMessage(ChatColor.RED + "Wystapil blad z baza MySQL.");
							return true;
						}
						DyrtCraft.getMember(player).addXp(xp, "Ustawiono " + xp + "XP przez " + sender.getName());
						DyrtCraft.getUtils().sendNotify(sender.getName() + " ustawil graczowi " + args[0] + xp + " XP", true);
						return true;
					} catch(NumberFormatException ex) {
						sender.sendMessage(ChatColor.RED + "Argumentem \"" + args[2] + "\" musi byc liczba!");
						return true;
					}
				// Zaden z argumentow nie zostal spelniony
				} else {
					sender.sendMessage(ChatColor.RED + "Popelniono blad! Spr�buj jeszcze raz!");
					sender.sendMessage(ChatColor.RED + "/xp [gracz] [[create]|del|give|set <liczba>]");
					return true;
				}
		    // Liczba argumentow nie zostala spelniona
			} else {
				sender.sendMessage(ChatColor.RED + "Popelniono blad! Spr�buj jeszcze raz!");
				sender.sendMessage(ChatColor.RED + "/xp [gracz] [[create]del|give|set <liczba>]");
				return true;
			}
		}
		// Jezeli komenda nie jest /xp*/
		return false;
	}
	
}
