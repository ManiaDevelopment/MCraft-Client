package com.maniadevelopment.mcraft.client.minecraft.render;

import com.maniadevelopment.mcraft.client.minecraft.render.texture.TextureFX;
import com.maniadevelopment.mcraft.client.minecraft.settings.GameSettings;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:36 PM
 */

/**
 * Way to manage textures.
 */
public class TextureManager
{
	/**
	 * Create a new texture manager and pass in the game settings.
	 *
	 * @param gameSettings
	 */
	public TextureManager(GameSettings gameSettings)
	{
		this.gameSettings = gameSettings;

		textures = new HashMap<String, Integer>();
		textureImages = new HashMap<Integer, BufferedImage>();
		idBuffer = BufferUtils.createIntBuffer(1);
		textureBuffer = BufferUtils.createByteBuffer(262144);
		animations = new ArrayList<TextureFX>();
	}

	/**
	 * The game settings.
	 */
	public GameSettings gameSettings;

	/**
	 * A map of all the texture names with their ID.
	 */
	public HashMap<String, Integer> textures;
	/**
	 * A map of all the texture ID's with their image.
	 */
	public HashMap<Integer, BufferedImage> textureImages;
	/**
	 * The buffer for the texture ID.
	 */
	public IntBuffer idBuffer;
	/**
	 * The buffer of the texture image.
	 */
	public ByteBuffer textureBuffer;
	/**
	 * A list of all the block animations.
	 */
	public List<TextureFX> animations;

	/**
	 * Load a texture from the Minecraft Classic jar by name.
	 *
	 * @param name The file name.
	 * @return The texture ID.
	 */
	public int load(String name)
	{
		Integer integer = textures.get(name);

		if(integer == null)
		{
			idBuffer.clear();

			GL11.glGenTextures(idBuffer);

			int textureID = idBuffer.get(0);

			try
			{
				if(name.startsWith("##"))
				{
					load(getImage(ImageIO.read(TextureManager.class.getResourceAsStream(name.substring(2)))), textureID);
				} else {
					load(ImageIO.read(TextureManager.class.getResourceAsStream(name)), textureID);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			textures.put(name, textureID);

			return textureID;
		} else {
			return integer;
		}
	}

	public BufferedImage getImage(BufferedImage image)
	{
		int spots = image.getWidth() / 16;
		int height = image.getHeight();
		BufferedImage bi = new BufferedImage(16, height * spots, 2);
		Graphics graphics = bi.getGraphics();

		for(int i = 0; i < spots; i++)
		{
			graphics.drawImage(image, -i << 4, i * height, null);
		}

		graphics.dispose();

		return bi;
	}

	/**
	 * Load a texture from the Minecraft Classic jar by bufferedimage.
	 *
	 * @param image The image.
	 * @return The texture ID.
	 */
	public int load(BufferedImage image)
	{
		idBuffer.clear();

		GL11.glGenTextures(idBuffer);

		int textureID = idBuffer.get(0);

		load(image, textureID);

		textureImages.put(textureID, image);

		return textureID;
	}

	/**
	 * Actually load the texture.
	 *
	 * @param image The texture image.
	 * @param textureID The texture ID.
	 */
	public void load(BufferedImage image, int textureID)
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		int width = image.getWidth();
		int height = image.getHeight();

		int[] pixels = new int[width * height];
		byte[] bytes = new byte[width * height << 2];

		image.getRGB(0, 0, width, height, pixels, 0, width);

		for(int pixel : pixels)
		{
			int alpha = pixel >>> 24;
			int red = pixel >> 16 & 0xFF;
			int green = pixel >> 8 & 0xFF;
			int blue = pixel & 0xFF;

			bytes[pixel << 2] = (byte)alpha;
			bytes[(pixel << 2) + 1] = (byte)red;
			bytes[(pixel << 2) + 2] = (byte)green;
			bytes[(pixel << 2) + 3] = (byte)blue;
		}

		textureBuffer.clear();
		textureBuffer.put(bytes);
		textureBuffer.position(0).limit(bytes.length);

		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureBuffer);
	}

	/**
	 * Register an animation.
	 *
	 * @param fx The FX/animation.
	 */
	public void registerAnimation(TextureFX fx)
	{
		animations.add(fx);

		fx.animate();
	}
}
