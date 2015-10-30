package me.zensormychat.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mysql.jdbc.PreparedStatement;

import me.zensormychat.main.MySQL;



public class AddCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		cs.sendMessage("§6----------ZensormyChat---------");
		cs.sendMessage("§6Made by ModdyLP");
		if (cs.hasPermission("zensormychat.add")) {
			
			if (!(args.length < 1)) {
				
				if (!(args.length > 0)) {
					
					cs.sendMessage("§2Bitte füge ein Wort ein was du bannen möchtest");
					
				} else {
					cs.sendMessage("§2Wort wird gebannt... Bitte warten!");;
					PreparedStatement ps = null;
					try {
						String banword = args[0];
						String banword2 = banword.toLowerCase();
						if (ListCommand.wordlist().contains(banword2)) {
							cs.sendMessage("§cDieses Wort ist bereits in der Datenbank!");
							return false;
						}
						else
						{
						ps = (PreparedStatement) MySQL.getConnection().prepareStatement("INSERT INTO censor (word, addedBy, forAll) VALUES ('" + banword2 + "', '" + cs + "', '0');");
						}
						} catch (SQLException e) {
						cs.sendMessage("§cMySQL Verbindung nicht vorhanden!");
					}
					try {
						ps.executeUpdate();
						cs.sendMessage("§2MySQL Eintrag erfolgreich");
						MySQL.closeRessources(null, ps);
					} catch (SQLException e) {
						cs.sendMessage("§cMySQL Eintragung fehlgeschlagen!");
					}
					
					try {
						ListCommand.wordlist().clear();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					try {
						ListCommand.wordlist();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			} else {
				cs.sendMessage("§cZuviele Argumente!");
			}
		} else {
			cs.sendMessage("§cDu bist nicht berechtigt für diesen Command");
		}
		
		return true;
}
}
