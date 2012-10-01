package com.maniadevelopment.mcraft.client.minecraft.chat;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 8:38 PM
 */
public class ChatLine
{
	public ChatLine(String message)
	{
		this.message = message;
		time = 0;
	}

	public String message;
	public int time;
}
