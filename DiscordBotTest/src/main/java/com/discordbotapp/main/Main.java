package com.discordbotapp.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {

	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		
		InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties");

        Properties prop = new Properties();

        if (input == null) {
            System.out.println("Sorry, unable to find config.properties");
            return;
        }
        
        try {
			prop.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        System.out.println(prop.getProperty("bot.token"));
		
		builder.setToken(prop.getProperty("bot.token"));
		builder.addEventListeners(new Main());
		try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		super.onMessageReceived(event);
		System.out.println("We received a message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
		if (event.getMessage().getContentRaw().equals("!ping")) {
			event.getChannel().sendMessage("Pong!").queue();
		}
	}

}
