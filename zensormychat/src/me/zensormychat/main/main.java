package me.zensormychat.main;



import java.sql.SQLException;


import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.mysql.jdbc.PreparedStatement;

import me.zensormychat.commands.AddCommand;
import me.zensormychat.commands.DelCommand;
import me.zensormychat.commands.HelpCommand;
import me.zensormychat.commands.ListCommand;


public class main extends JavaPlugin {
	


	@Override
	public void onEnable(){
	//-- Aktiviert Meldung
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin ZensormyChat wird gestartet.");
		System.out.println("[Zensormychat] Das Plugin wurde aktiviert!");
		System.out.println("[Zensormychat] Create by Niklas Hartmann");
	//-- Meldungen ausgegeben
	//--MySql
		MySQLfile file = new MySQLfile();
		file.setStandard();
		file.readData();
		
		MySQL.connect();
		//--Tabelle generieren wenn nicht vorhanden
		try {
			PreparedStatement ps = (PreparedStatement) MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `censor` (`word` varchar(120) NOT NULL,`addedBy` varchar(90) NOT NULL,`forAll` tinyint(1) NOT NULL DEFAULT '0',PRIMARY KEY (`word`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			ps.executeUpdate();
			System.out.println("[Zensormychat] [MySQL] Tabelle geladen");
			MySQL.closeRessources(null, ps);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	//-- Erzeugung und Übergabe des Plugins
		
		new EventListener(this);
	//--Fertig
		//-- Commands
		this.getCommand("zmcadd").setExecutor(new AddCommand());
		this.getCommand("zmchelp").setExecutor(new HelpCommand());
		this.getCommand("zmclist").setExecutor(new ListCommand());
		this.getCommand("zmcdel").setExecutor(new DelCommand());
		//
		
		
	//-- Blacklist laden
	
	
	//-- Blacklist laden fertig
	
	}
	@Override
	public void onDisable(){
		System.out.println("Das Plugin wurde deaktiviert!");
	}

}
