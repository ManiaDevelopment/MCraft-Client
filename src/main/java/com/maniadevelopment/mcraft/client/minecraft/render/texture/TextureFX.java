package com.maniadevelopment.mcraft.client.minecraft.render.texture;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:28 PM
 */

/**
 * A block texture animation.
 */
public abstract class TextureFX
{
	/**
	 * Create a new animation.
	 *
	 * @param textureID The ID of the texture.
	 */
	public TextureFX(int textureID)
	{
		this.textureID = textureID;
	}

	/**
	 * The data of the texture.
	 */
	public byte[] textureData = new byte[1024];
	/**
	 * The ID of the texture.
	 */
	public int textureID;

	/**
	 * Animate the texture.
	 */
	public abstract void animate();
}
