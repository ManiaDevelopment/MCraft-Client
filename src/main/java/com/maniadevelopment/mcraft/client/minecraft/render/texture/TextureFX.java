package com.maniadevelopment.mcraft.client.minecraft.render.texture;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:28 PM
 */
public abstract class TextureFX
{
	public TextureFX(int textureID)
	{
		this.textureID = textureID;
	}

	public byte[] textureData = new byte[1024];
	public int textureID;

	public abstract void animate();
}
