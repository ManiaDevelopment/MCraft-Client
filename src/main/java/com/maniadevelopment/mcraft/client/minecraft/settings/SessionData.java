package com.maniadevelopment.mcraft.client.minecraft.settings;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 10/1/12
 * Time: 10:28 PM
 */

/**
 * Represents the current playing session data.
 */
public class SessionData
{
	/**
	 * Create a new sessiondata.
	 *
	 * @param username The player's username.
	 * @param sessionID The player's sessionID.
	 */
	public SessionData(String username, String sessionID)
	{
		this.username = username;
		this.sessionID = sessionID;
	}

	/**
	 * The player's username.
	 */
	public String username;
	/**
	 * The player's sessionID.
	 */
	public String sessionID;
	/**
	 * The player's encrypted password.
	 */
	public String mppass;
	/**
	 * If the player has premium.
	 */
	public boolean hasPaid;
}
