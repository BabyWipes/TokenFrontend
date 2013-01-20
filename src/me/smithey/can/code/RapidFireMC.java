/*
 * Add command to give tokens
 * Add a top list with the most tokens
 * Add a across plugin api to allow use of add tokens and Take tokens
 * Add Take tokens public void
 * Add Admin Commands
 * Wipe account command
 * 
 * Add Check other users tokens
 * 
 * */
package me.smithey.can.code;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import code.husky.TokenAPI;

public class RapidFireMC extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");

	TokenAPI api = new TokenAPI();

	public void onEnable(){
		this.log.info("RapidFireMC has been Enabled");
	}

	public void onDisable() {
		this.log.info("RapidFireMC has been Enabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("arcade")){
			if(args.length == 0) {
				p.sendMessage(ChatColor.RED + "===================== " + ChatColor.GREEN + "Arcade" + ChatColor.RED + " =====================");
				p.sendMessage(ChatColor.AQUA + "/Arcade help " + ChatColor.WHITE + "- Displays this Help message");
				p.sendMessage(ChatColor.AQUA + "/Tokens " + ChatColor.WHITE + "- Views your Token balance");
				p.sendMessage(ChatColor.AQUA + "/Tokens help" + ChatColor.WHITE + "- Displays information about tokens");
				p.sendMessage(ChatColor.AQUA + "/Arcade join " + ChatColor.WHITE + "- Allows you to join a game");
				p.sendMessage(ChatColor.AQUA + "/Arcade leave " + ChatColor.WHITE + "- Leaves the current game you are in");
				p.sendMessage(ChatColor.AQUA + "/Arcade Admin " + ChatColor.WHITE + "- Displays Help for Players with the Admin Permission");
				p.sendMessage(ChatColor.RED + "                           " + ChatColor.DARK_RED + "Made by Smithey");
				p.sendMessage(ChatColor.RED + "====================================================");
			}else if(args[0].equalsIgnoreCase("help" )){
				p.sendMessage(ChatColor.RED + "===================== " + ChatColor.GREEN + "Arcade" + ChatColor.RED + " =====================");
				p.sendMessage(ChatColor.AQUA + "/Arcade help " + ChatColor.WHITE + "- Displays this Help message");
				p.sendMessage(ChatColor.AQUA + "/Tokens " + ChatColor.WHITE + "- Views your Token balance");
				p.sendMessage(ChatColor.AQUA + "/Tokens help" + ChatColor.WHITE + "- Displays information about tokens");
				p.sendMessage(ChatColor.AQUA + "/Arcade join " + ChatColor.WHITE + "- Allows you to join a game");
				p.sendMessage(ChatColor.AQUA + "/Arcade leave " + ChatColor.WHITE + "- Leaves the current game you are in");
				p.sendMessage(ChatColor.AQUA + "/Arcade Admin " + ChatColor.WHITE + "- Displays Help for Players with the Admin Permission");
				p.sendMessage(ChatColor.RED + "                           " + ChatColor.DARK_RED + "Made by Smithey");
				p.sendMessage(ChatColor.RED + "====================================================");
			}
		}else if(cmd.getName().equalsIgnoreCase("tokens") || cmd.getName().equalsIgnoreCase("token")){
			if(args.length == 0) {
				p.sendMessage(ChatColor.GREEN + "You have " + api.getTokens(p) + " tokens!");
			}else if(args[0].equalsIgnoreCase("help")){
				p.sendMessage(ChatColor.RED + "===================== " + ChatColor.GREEN + "Tokens" + ChatColor.RED + " =====================");
				p.sendMessage(ChatColor.AQUA + "/Tokens " + ChatColor.WHITE + "- Views your Token balance");
				p.sendMessage(ChatColor.AQUA + "/Tokens [Player]" + ChatColor.WHITE + "- Views another players Token balance");
				p.sendMessage(ChatColor.AQUA + "/Tokens send [Player] [Amount]" + ChatColor.WHITE + "- Send Tokens to another player");
				p.sendMessage(ChatColor.AQUA + "/Tokens About" + ChatColor.WHITE + "- Explains what tokens are and how to get them");
				p.sendMessage(ChatColor.AQUA + "/Tokens help" + ChatColor.WHITE + "- Displays information about tokens");
				p.sendMessage(ChatColor.RED + "                           " + ChatColor.DARK_RED + "Made by Smithey");
				p.sendMessage(ChatColor.RED + "====================================================");
			}else if(args[0].equalsIgnoreCase("about")){
				p.sendMessage(ChatColor.RED + "===================== " + ChatColor.GREEN + "Tokens" + ChatColor.RED + " =====================");
				p.sendMessage(ChatColor.WHITE + "Tokens are the currency on the server, they are used to play games and when you win games you will recieve tokens. Some games require more or less tokens than others. You can gain toeksn on our website by purchasing them or you can trade tokens with other players. Use /tokens to view your toekn balance.");
				p.sendMessage(ChatColor.RED + "                           " + ChatColor.DARK_RED + "Made by Smithey");
				p.sendMessage(ChatColor.RED + "====================================================");
			}else if(args[0].equalsIgnoreCase("add")){
				if(p.hasPermission("MineCade.Admin") || p.hasPermission("MineCade.*")){
					String givee = args[0];
					String amount = args[1];
					if(args.length == 1){
						p.sendMessage(ChatColor.RED + "Usage: /tokens add [Player] [Amount]");
					} else {
						int am = Integer.valueOf(amount);
						Player gi = getServer().getPlayer(givee);
						if(api.getTokens(gi) <= am) {
							api.addToken(p, am);
							api.removeToken(gi, am);
						} else {
							p.sendMessage(ChatColor.RED + "You need more tokens to do that.");
						}
					}
				} else {
					p.sendMessage(ChatColor.RED + "No permission for that");
				}
			} else {
				Player to = getServer().getPlayer(args[0]);
				p.sendMessage(ChatColor.GREEN + args[0] + " has " + api.getTokens(to) + "!");
			}
		}
		return true;

	}

}
