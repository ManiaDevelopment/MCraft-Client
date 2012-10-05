package com.mojang.minecraft;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ResourceDownloadThread extends Thread
{
	public ResourceDownloadThread(File minecraftFolder, Minecraft minecraft)
	{
		this.minecraft = minecraft;

		this.setName("Resource download thread");
		this.setDaemon(true);

		dir = new File(minecraftFolder, "resources/");

		if(!dir.exists() && !dir.mkdirs())
		{
			throw new RuntimeException("The working directory could not be created: " + dir);
		}
	}

	@Override
	public void run()
	{
		String[] files = new String[]
				{
						"music/calm1.ogg", "music/calm2.ogg", "music/calm3.ogg",
						"newmusic/hal1.ogg", "newmusic/hal2.ogg", "newmusic/hal3.ogg", "newmusic/hal4.ogg",
						"sound/step/grass1.ogg", "sound/step/grass2.ogg", "sound/step/grass3.ogg", "sound/step/grass4.ogg",
						"sound/step/gravel1.ogg", "sound/step/gravel2.ogg", "sound/step/gravel3.ogg", "sound/step/gravel4.ogg",
						"sound/step/stone1.ogg", "sound/step/stone2.ogg", "sound/step/stone3.ogg", "sound/step/stone4.ogg",
						"sound/step/wood1.ogg", "sound/step/wood2.ogg", "sound/step/wood3.ogg", "sound/step/wood4.ogg"
				};

		URL url;
		ReadableByteChannel rbc;
		File file;
		FileOutputStream fos;

		File folder = new File(dir, "music");
		folder.mkdir();
		folder = new File(dir, "newmusic");
		folder.mkdir();
		folder = new File(dir, "sound");
		folder.mkdir();
		folder = new File(folder, "step");
		folder.mkdir();

		try
		{
			for(int i = 0; i < files.length; i++)
			{
				file = new File(dir, files[i]);

				if(!file.exists())
				{
					System.out.println("Downloading http://s3.amazonaws.com/MinecraftResources/" + files[i] + "...");

					url = new URL("http://s3.amazonaws.com/MinecraftResources/" + files[i]);
					rbc = Channels.newChannel(url.openStream());
					fos = new FileOutputStream(file);

					fos.getChannel().transferFrom(rbc, 0, 1 << 24);

					System.out.println("Downloaded http://s3.amazonaws.com/MinecraftResources/" + files[i] + "!");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File musicFolder = new File(dir, "music");

		for(int i = 1; i <= 3; i++)
		{
			minecraft.sound.registerMusic("calm" + i + ".ogg", new File(musicFolder, "calm" + i + ".ogg"));
		}

		File newMusicFolder = new File(dir, "newmusic");

		for(int i = 1; i <= 4; i++)
		{
			minecraft.sound.registerMusic("calm" + i + ".ogg", new File(newMusicFolder, "hal" + i + ".ogg"));
		}

		File stepsFolder = new File(dir, "sound/step");

		for(int i = 1; i <= 4; i++)
		{
			minecraft.sound.registerSound(new File(stepsFolder, "grass" + i + ".ogg"), "step/grass" + i + ".ogg");
			minecraft.sound.registerSound(new File(stepsFolder, "gravel" + i + ".ogg"), "step/gravel" + i + ".ogg");
			minecraft.sound.registerSound(new File(stepsFolder, "stone" + i + ".ogg"), "step/stone" + i + ".ogg");
			minecraft.sound.registerSound(new File(stepsFolder, "wood" + i + ".ogg"), "step/wood" + i + ".ogg");
		}

		finished = true;
	}

	private File dir;
	private Minecraft minecraft;
	boolean running = false;

	private boolean finished = false;

	public boolean isFinished()
	{
		return finished;
	}
}
