package me.zensormychat.commands;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mysql.jdbc.PreparedStatement;

import me.zensormychat.main.MySQL;



public class ListCommand implements CommandExecutor{
	
	static Connection conn = null;
    PreparedStatement ps = null;
    ResultSet st = null;
	// WortListe ausgeben auf Command
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		cs.sendMessage("§6----------ZensormyChat---------");
		cs.sendMessage("§6Made by ModdyLP");
		
		if(cs.hasPermission("zensormychat.list")) {
			
			if (!(args.length < 0)) {
				
				try {
					cs.sendMessage(getwords());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				try {
					cs.sendMessage("Größe:  " + wordlist().size());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			} else {
				
				cs.sendMessage("§cZuviele Argumente!");
			}
			
		} else {
			
			cs.sendMessage("§cDu bist nicht berechtigt für diesen Command");
			
		}		
			return true;
	}
	
	
	
	// Methode zum Ausgeben einer Liste der Wörter
	
public synchronized static String getwords() {
	
    List<String> words = new ArrayList<String>();
    try {
    	
      PreparedStatement ps = (PreparedStatement)MySQL.conn.prepareStatement("SELECT * FROM censor");
      ResultSet st = ps.executeQuery();
      StringBuilder sb = new StringBuilder();
      
      		while (st.next()) 
      		{
    	    sb.append(st.getString("word")).append(" | ");
      		}
      		MySQL.closeRessources(st, ps);
      
      return sb.toString().substring(0, sb.length() > 0 ? sb.length() - 3 : 0);
      
    	}
    
    catch(Exception exception){
    System.out.println("Fehler bei der Funktion: getwords()");
      return "Error whilst listing words.";
    }
    
    finally {
    	words.clear();
    }
    
    
  }
public static ArrayList<String> wordlist() {
	ArrayList<String> wordlist = new ArrayList<String>();
    try {
    	
      PreparedStatement ps = (PreparedStatement)MySQL.conn.prepareStatement("SELECT * FROM censor");
      ResultSet st = ps.executeQuery();
      
      		while (st.next()) 
      			{
    	    wordlist.add(st.getString("word"));
      			}
      		MySQL.closeRessources(st, ps);
    	}
    
    catch(Exception exception)
    	{
    	System.out.println("Fehler bei der Funktion: wordlist()");
    	
      
    	}
    return wordlist;
  }


}


