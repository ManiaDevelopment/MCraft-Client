package com.mojang.minecraft.render;

import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.render.texture.TextureFX;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipFile;

public class TextureManager
{
	public TextureManager(GameSettings settings)
	{
		this.settings = settings;

		minecraftFolder = Minecraft.mcDir;
		texturesFolder = new File(minecraftFolder, "texturepacks");

		if(!texturesFolder.exists())
		{
			texturesFolder.mkdir();
		}
	}

	public HashMap<String, Integer> textures = new HashMap<String, Integer>();
	public HashMap<Integer, BufferedImage> textureImages = new HashMap<Integer, BufferedImage>();
	public IntBuffer idBuffer = BufferUtils.createIntBuffer(1);
	public ByteBuffer textureBuffer = BufferUtils.createByteBuffer(262144);
	public List<TextureFX> animations = new ArrayList<TextureFX>();
	public GameSettings settings;

	public HashMap<String, Integer> externalTexturePacks = new HashMap<String, Integer>();

	public File minecraftFolder;
	public File texturesFolder;

	public int previousMipmapMode;

	public int load(String file)
	{
		if(textures.get(file) != null)
		{
			return textures.get(file);
		} else if(externalTexturePacks.get(file) != null) {
			return externalTexturePacks.get(file);
		} else {
			try {
				idBuffer.clear();

				GL11.glGenTextures(idBuffer);

				int textureID = idBuffer.get(0);

				if(file.endsWith(".png"))
				{
					if(file.startsWith("##"))
					{
						load(load1(ImageIO.read(TextureManager.class.getResourceAsStream(file.substring(2)))), textureID);
					} else {
						load(ImageIO.read(TextureManager.class.getResourceAsStream(file)), textureID);
					}

					textures.put(file, textureID);
				} else if(file.endsWith(".zip")) {
					ZipFile zip = new ZipFile(new File(minecraftFolder, "texturepacks/" + file));

					String terrainPNG = "terrain.png";

					if(zip.getEntry(terrainPNG.startsWith("/") ? terrainPNG.substring(1, terrainPNG.length()) : terrainPNG) != null)
					{
						load(ImageIO.read(zip.getInputStream(zip.getEntry(terrainPNG.startsWith("/") ? terrainPNG.substring(1, terrainPNG.length()) : terrainPNG))), textureID);
					} else {
						load(ImageIO.read(TextureManager.class.getResourceAsStream(terrainPNG)), textureID);
					}

					zip.close();

					externalTexturePacks.put(file, textureID);
				}

				return textureID;
			} catch (IOException e) {
				throw new RuntimeException("!!", e);
			}
		}
	}

	public static BufferedImage load1(BufferedImage image)
	{
		int charWidth = image.getWidth() / 16;
		BufferedImage image1 = new BufferedImage(16, image.getHeight() * charWidth, 2);
		Graphics graphics = image1.getGraphics();

		for(int i = 0; i < charWidth; i++)
		{
			graphics.drawImage(image, -i << 4, i * image.getHeight(), null);
		}

		graphics.dispose();

		return image1;
	}

	public int load(BufferedImage image)
	{
		idBuffer.clear();

		GL11.glGenTextures(idBuffer);

		int textureID = idBuffer.get(0);

		load(image, textureID);

		textureImages.put(textureID, image);

		return textureID;
	}

	public void load(BufferedImage image, int textureID)
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		if(settings.smoothing > 0)
		{
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 2);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		} else {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		}

		int width = image.getWidth();
		int height = image.getHeight();
		int[] pixels = new int[width * height];
		byte[] color = new byte[width * height << 2];

		image.getRGB(0, 0, width, height, pixels, 0, width);

		for(int pixel = 0; pixel < pixels.length; pixel++)
		{
			int alpha = pixels[pixel] >>> 24;
			int red = pixels[pixel] >> 16 & 0xFF;
			int green = pixels[pixel] >> 8 & 0xFF;
			int blue = pixels[pixel] & 0xFF;

			if(settings.anaglyph)
			{
				int rgba3D = (red * 30 + green * 59 + blue * 11) / 100;

				green = (red * 30 + green * 70) / 100;
				blue = (red * 30 + blue * 70) / 100;
				red = rgba3D;
			}

			color[pixel << 2] = (byte)red;
			color[(pixel << 2) + 1] = (byte)green;
			color[(pixel << 2) + 2] = (byte)blue;
			color[(pixel << 2) + 3] = (byte)alpha;
		}

		textureBuffer.clear();
		textureBuffer.put(color);
		textureBuffer.position(0).limit(color.length);

		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureBuffer);

		if(settings.smoothing > 0)
		{
			switch(settings.smoothing)
			{
				case 1:
					if(previousMipmapMode != settings.smoothing)
					{
						System.out.println("Using OpenGL 3.0 for mipmap generation.");
					}

					GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
					break;
				case 2:
					if(previousMipmapMode != settings.smoothing)
					{
						System.out.println("Using GL_EXT_framebuffer_object extension for mipmap generation.");
					}

					EXTFramebufferObject.glGenerateMipmapEXT(GL11.GL_TEXTURE_2D);
					break;
				case 3:
					if(previousMipmapMode != settings.smoothing)
					{
						System.out.println("Using GL_GENERATE_MIPMAP for mipmap generation. This might slow down with large textures.");
					}

					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_GENERATE_MIPMAP, GL11.GL_TRUE);
					break;
			}

			GL11.glAlphaFunc(GL11.GL_GEQUAL, 0.3F);
		}

		previousMipmapMode = settings.smoothing;
	}

	public void registerAnimation(TextureFX FX)
	{
		animations.add(FX);

		FX.animate();
	}
}
