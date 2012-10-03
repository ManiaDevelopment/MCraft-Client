package com.maniadevelopment.mcraft.client.minecraft.input;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 8:40 PM
 */

/**
 * Represents a key binding.
 */
public class KeyBinding
{
	/**
	 * Create a new binding.
	 *
	 * @param name The name of the key.
	 * @param key The key ID.
	 */
	public KeyBinding(String name, int key)
	{
		this.name = name;
		this.key = key;
	}

	/**
	 * The name of the key.
	 */
	public String name;
	/**
	 * The key ID.
	 */
	public int key;
}
