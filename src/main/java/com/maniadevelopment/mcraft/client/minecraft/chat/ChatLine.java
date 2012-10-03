package com.maniadevelopment.mcraft.client.minecraft.chat;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 8:38 PM
 */

/**
 * Representing a line in the chat.
 */
public class ChatLine
{
	/**
	 * Create a new ChatLine with a message.
	 *
	 * @param message The message sent.
	 */
	public ChatLine(String message)
	{
		this.message = message;
		time = 0;
	}

	/**
	 * The message sent.
	 */
	public String message;
	/**
	 * Time.
	 */
	public int time;
}
