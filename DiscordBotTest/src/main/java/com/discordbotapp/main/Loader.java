package com.discordbotapp.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import com.discordbotapp.listeners.MessageListener;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

public class Loader {

	public Loader() {}
	
	public void load() {
		try {
			JDABuilder builder = new JDABuilder(AccountType.BOT);
			
			InputStream input = Main.class.getClassLoader().getResourceAsStream("local.properties");
	
	        Properties prop = new Properties();
	
	        if (input == null) {
	            System.out.println("Sorry, unable to find config.properties");
	            return;
	        }

			prop.load(input);
	
			builder.setToken(prop.getProperty("bot.token"));
			builder.addEventListeners(new MessageListener());

			builder.build();
		} catch (LoginException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
