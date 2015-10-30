package me.zensormychat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;



public class HelpCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		cs.sendMessage("§6----------ZensormyChat---------");
		cs.sendMessage("§6Made by ModdyLP Version 6.0");
		cs.sendMessage("§6-------------------------------");
		cs.sendMessage("§2/zmcadd [wort]");
		cs.sendMessage("§2Zum hinzufügen von Wörtern zur Banliste");
		cs.sendMessage("§6-------------------------------");
		cs.sendMessage("§2/zmcdel [wort]");
		cs.sendMessage("§2Zum löschen von Wörtern von der Banliste");
		cs.sendMessage("§6-------------------------------");
		cs.sendMessage("§2/zmchelp");
		cs.sendMessage("§2Um diese Hilfe anzuzeigen");
		cs.sendMessage("§6-------------------------------");
		cs.sendMessage("§2/zmclist");
		cs.sendMessage("§2Gibt die Banliste aus");
		cs.sendMessage("§6-------------------------------");
		
		return true;
	}

}
