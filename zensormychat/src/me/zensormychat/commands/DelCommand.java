package me.zensormychat.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mysql.jdbc.PreparedStatement;

import me.zensormychat.main.MySQL;

public class DelCommand implements CommandExecutor {
	
	
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		cs.sendMessage("§6----------ZensormyChat---------");
		cs.sendMessage("§6Made by ModdyLP");
		if (cs.hasPermission("zensormychat.del")) {
			
			if (!(args.length < 1)) {
				
				if (!(args.length > 0)) {
					cs.sendMessage("§2Bitte gebe ein Wort ein das du Löschen möchtest");
					
				} else {
					cs.sendMessage("§2Wort " + args[0] + " wird gelöscht... Bitte warten!");
					String banword = args[0];
					String banword2 = banword.toLowerCase();
					if (!ListCommand.wordlist().contains(banword2)) {
						cs.sendMessage("§cDieses Wort ist nicht in der Datenbank!");
						return false;
					} else
					{
					PreparedStatement ps = null;
					
					try {
						
						ps = (PreparedStatement) MySQL.getConnection().prepareStatement("DELETE FROM censor WHERE word = '"+ args[0]+ "';");
						
						
					} catch (SQLException e) {
						
						cs.sendMessage("§cMySQL Verbindung nicht vorhanden!");
					}
					
					try {
						ps.executeUpdate();
						cs.sendMessage("MySQL Löschung erfolgreich");
						ps.close();
						MySQL.closeRessources(null, ps);
						
					} catch (SQLException e) {	
						cs.sendMessage("§cMySQL Löschung fehlgeschlagen!");
					}
					
					try {
						ListCommand.wordlist().clear();
						ps.close();
						ListCommand.wordlist();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
