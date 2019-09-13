package com.discordbotapp.main;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {

	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String token = "NjIxMDM2ODI1MDc0NTk3OTEw.XXwUUw.x-OdMOW5EgzpjQBFttlbhJEvONI";
		builder.setToken(token);
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
