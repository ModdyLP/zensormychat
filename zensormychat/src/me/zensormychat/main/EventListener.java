package me.zensormychat.main;


import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.zensormychat.commands.ListCommand;


public class EventListener implements Listener {
	
	//-- Bereitstellung des Eventlisteners
	main plugin;
	private String msgclear;
	
	//--
	
	//--EventListener
	public EventListener(main plugin) {
		this.plugin = plugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		
	}
	
	//--
	//-- Blacklisted Words Filter

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) throws SQLException {
		Player p = event.getPlayer();
		String namep = event.getPlayer().getName();
		Player adminp = null;
		for(Player op : Bukkit.getOnlinePlayers()) {
			if(op.hasPermission("zensormychat.admin")){
			adminp = op;
			}
		}
		String msg = event.getMessage();
		String[] splitResult = msg.split(" "); // –> splitten an den Leerzeichen
		
		//-- Auslesen und Überprüfen der Blacklist
		
			
		for (int i2 = 0;splitResult.length > i2;i2++)  {
		
			if (!(p.hasPermission("zensormychat.bypass"))) {
				String s = msg;
				int count = 0;
				for (int i3 = 0, len = s.length(); i3 < len; i3++) {
					if (Character.isDigit(s.charAt(i3))) {
						count++;
						}
					if (count > 4) {
						msg = msg.replaceAll("\\d", "§c*§r");	
						}
				}
				
				if (ListCommand.wordlist().contains(splitResult[i2].toLowerCase()))
				{
					msgclear = msg;	
					for (int i = 0; i < ListCommand.wordlist().size(); i++ ) 
					{
					
					msg = msg.toLowerCase().replaceAll(ListCommand.wordlist().get(i), "§c###§r");
					event.setMessage(msg);
					}	
				}
				
				else 
				{
					event.setMessage(msg);
				}
			
			}
		
		}
		
		if (adminp != null && msgclear != null) {
			adminp.sendMessage("§c[ACHTUNG] §b"+namep+" §6wollte schreiben: §c"+msgclear);
			msgclear = null;
		}
		
	}
}
