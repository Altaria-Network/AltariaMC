package pl.dyrtcraft.xp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.xp.DyrtCraftXP;

public class DcxpCommand implements CommandExecutor {

	private DyrtCraftXP plugin = DyrtCraftXP.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {		
		if(label.equalsIgnoreCase("dcxp")) {
			if(args.length == 0) {
				return erArg(sender, "Nie podano zadnego argumentu!");
			}
			if(args.length == 1) { // /dcxp args[0]
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
					return aboutArg(sender);
				}
				if(args[0].equalsIgnoreCase("inv") || args[0].equalsIgnoreCase("show")) {
					return invArg(sender);
				}
				if(args[0].equalsIgnoreCase("portals")) {
					return erPortalsArg(sender);
				}
				if(args[0].equalsIgnoreCase("reload")) {
					return reloadArg(sender);
				} else {
					return erArg(sender, "Podano bledny argument!");
				}
			}
			if(args.length == 2) { // /dcxp args[0] args[1]
				if(args[0].equalsIgnoreCase("portals")) {
					if(args[1].equalsIgnoreCase("manage")) {
						return new PortalComponent().manage(sender);
					} else {
						return erPortalsArg(sender);
					}
				} else {
					return erArg(sender, "Podano bledny argument!");
				}
			}
			if(args.length == 3) { // /dcxp args[0] args[1] args[2]
				if(args[0].equalsIgnoreCase("portals")) {
					if(args[1].equalsIgnoreCase("create")) {
						return new PortalComponent().create(sender, args[2]);
					}
					if(args[1].equalsIgnoreCase("destination")) {
						return new PortalComponent().destination(sender, args[2], null);
					}
					if(args[1].equalsIgnoreCase("remove")) {
						return new PortalComponent().remove(sender, args[2]);
					} else {
						return erPortalsArg(sender);
					}
				} else {
					return erArg(sender, "Podano bledny argument!");
				}
			}
			if(args.length == 4) { // /dcxp args[0] args[1] args[2] args[3]
				if(args[0].equalsIgnoreCase("portals")) {
					if(args[1].equalsIgnoreCase("destination")) {
						return new PortalComponent().destination(sender, args[2], args[3]);
					} else {
						return erPortalsArg(sender);
					}
				} else {
					return erPortalsArg(sender);
				}
			} else {
				return erArg(sender, "Zbyt duzo argumentów!");
			}
		}
		return false;
	}
	
	protected boolean erArg(CommandSender sender, String er) {
		sender.sendMessage(ChatColor.RED + er);
		sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("dcxp").getUsage());
		return true;
	}
	
	protected boolean erPortalsArg(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "========== Pomoc ==========");
		sender.sendMessage(ChatColor.RED + "/dcxp portals create <ID>");
		sender.sendMessage(ChatColor.RED + "/dcxp portals destination <ID> <serwer>");
		sender.sendMessage(ChatColor.RED + "/dcxp portals manage");
		sender.sendMessage(ChatColor.RED + "/dcxp portals remove <ID>");
		return true;
	}
	
	private boolean aboutArg(CommandSender sender) {
		sender.sendMessage(ChatColor.YELLOW + "Wersja: " + ChatColor.GRAY + plugin.getDescription().getVersion());
		sender.sendMessage(ChatColor.YELLOW + "Autor: " + ChatColor.GRAY + plugin.getDescription().getAuthors());
		sender.sendMessage(ChatColor.YELLOW + "GitHub: " + ChatColor.GRAY + plugin.getDescription().getWebsite());
		sender.sendMessage(ChatColor.YELLOW + "WorldEdit: " + ChatColor.GRAY + DyrtCraftXP.isWeEnabled());
		return true;
	}
	
	private boolean invArg(CommandSender sender) {
		/*if(!(sender.isOp() || sender.hasPermission("dyrtcraft.xp.inv"))) {
		sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
		return true;
		}*/
		if(!(sender instanceof Player)) {
			plugin.getLogger().warning("Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player player = (Player) sender;
		player.sendMessage(ChatColor.GRAY + "Otwieranie okna z wyborem serwerów...");
		player.openInventory(DyrtCraft.getProxy().getServerChooserInventory(player));
		return true;
	}
	
	private boolean reloadArg(CommandSender sender) {
		if(!(sender.isOp() || sender.hasPermission("dyrtcraft.xp.reload"))) {
			sender.sendMessage(ChatColor.RED + "Ojj, brak odpowiednich uprawnien!");
			return true;
		}
		// config.yml
		try {
			plugin.reloadConfig();
		} catch(YAMLException ex) {
			DyrtCraft.getUtils().sendNotify("Nastapil blad z plikiem config.yml (YAMLException)", true);
		} catch(Exception ex) {
			DyrtCraft.getUtils().sendNotify("Nastapil blad z plikiem config.yml (Nie znaleziono pliku). Tworzenie 1 pliku dla Ciebie w folderze", true);
			plugin.saveDefaultConfig();
		}
		sender.sendMessage(ChatColor.DARK_GREEN + "Pomyslnie przeladowano plik config.yml!");
		return true;
	}
	
}
