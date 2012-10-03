package com.maniadevelopment.mcraft.client.minecraft.settings;

import com.maniadevelopment.mcraft.client.minecraft.input.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:04 PM
 */

/**
 * Handle the game settings and bindings.
 */
public class GameSettings
{
	/**
	 * Create a new GameSettings and pass in the minecraft folder.
	 *
	 * @param minecraftFolder
	 */
	public GameSettings(File minecraftFolder)
	{
		settingsFile = new File(minecraftFolder, "settings.txt");
		bindingsFile = new File(minecraftFolder, "bindings.txt");

		renderDistances = new String[] {"FAR", "NORMAL", "SHORT", "TINY"};

		forwardKey = new KeyBinding("Forward", 17);
		leftKey = new KeyBinding("Left", 30);
		backKey = new KeyBinding("Back", 31);
		rightKey = new KeyBinding("Right", 32);
		jumpKey = new KeyBinding("Jump", 57);
		buildKey = new KeyBinding("Build", 48);
		chatKey = new KeyBinding("Chat", 20);
		toggleFogKey = new KeyBinding("Toggle fog", 33);
		saveLocationKey = new KeyBinding("Save location", 28);
		loadLocationKey = new KeyBinding("Load location", 19);

		bindings = new KeyBinding[] {forwardKey, leftKey, backKey, rightKey, jumpKey, buildKey, chatKey, toggleFogKey, saveLocationKey, loadLocationKey};

		settingsCount = 7;

		loadSettings();
		loadBindings();
	}

	/**
	 * The file for the settings.
	 */
	public File settingsFile;
	/**
	 * The file for the bindings.
	 */
	public File bindingsFile;
	/**
	 * The amount of settings.
	 */
	public int settingsCount;

	/**
	 * The possible options for the render distance setting.
	 */
	public String[] renderDistances;

	/**
	 * Enable music.
	 */
	public boolean music = true;
	/**
	 * Enable sound.
	 */
	public boolean sound = true;
	/**
	 * Invert the mouse.
	 */
	public boolean invertMouse = false;
	/**
	 * Show the frame rate.
	 */
	public boolean showFrameRate = false;
	/**
	 * The current view distance setting.
	 */
	public int viewDistance = 0;
	/**
	 * View bobbing.
	 */
	public boolean viewBobbing = true;
	/**
	 * Limit the framerate.
	 */
	public boolean limitFramerate = false;

	/**
	 * All of the bindings.
	 */
	public KeyBinding[] bindings;

	/**
	 * Binding for the move forward.
	 */
	public KeyBinding forwardKey;
	/**
	 * Binding for the move left.
	 */
	public KeyBinding leftKey;
	/**
	 * Binding for the move back.
	 */
	public KeyBinding backKey;
	/**
	 * Binding for the move right.
	 */
	public KeyBinding rightKey;
	/**
	 * Binding for the jump.
	 */
	public KeyBinding jumpKey;
	/**
	 * Binding for the show inventory.
	 */
	public KeyBinding buildKey;
	/**
	 * Binding for the chat display.
	 */
	public KeyBinding chatKey;
	/**
	 * Binding to toggle the fog.
	 */
	public KeyBinding toggleFogKey;
	/**
	 * Binding to save the player's location.
	 */
	public KeyBinding saveLocationKey;
	/**
	 * Binding to load the player's saved location.
	 */
	public KeyBinding loadLocationKey;

	public void setBinding(int key, int keyID)
	{
		bindings[key].key = keyID;

		saveBindings();
	}

	/**
	 * Get a binding name and keyboard name by the key ID.
	 *
	 * @param key The key ID.
	 * @return The binding name and key name.
	 */
	public String getBinding(int key)
	{
		return bindings[key].name + ": " + Keyboard.getKeyName(bindings[key].key);
	}

	/**
	 * Toggle or run through a setting.
	 *
	 * @param setting The setting number.
	 * @param fogValue The fog value if we are changing the fog.
	 */
	public void toggleSetting(int setting, int fogValue)
	{
		if(setting == 0)
		{
			music = !music;
		}

		if(setting == 1)
		{
			sound = !sound;
		}

		if(setting == 2)
		{
			invertMouse = !invertMouse;
		}

		if(setting == 3)
		{
			showFrameRate = !showFrameRate;
		}

		if(setting == 4)
		{
			viewDistance = viewDistance + fogValue & 3;
		}

		if(setting == 5)
		{
			viewBobbing = !viewBobbing;
		}

		if(setting == 6)
		{
			limitFramerate = !limitFramerate;
		}

		saveSettings();
	}

	/**
	 * Get a setting by the ID.
	 *
	 * @param ID The setting ID.
	 * @return The setting name and value.
	 */
	public String getSetting(int ID)
	{
		return ID == 0 ? "Music: " + (music ? "ON" : "OFF")
				: (ID == 1 ? "Sound: " + (sound ? "ON" : "OFF")
				: (ID == 2 ? "Invert mouse: " + (invertMouse ? "ON" : "OFF")
				: (ID == 3 ? "Show FPS: " + (showFrameRate ? "ON" : "OFF")
				: (ID == 4 ? "Render distance: " + renderDistances[viewDistance]
				: (ID == 5 ? "View bobbing: " + (viewBobbing ? "ON" : "OFF")
				: (ID == 7 ? "Limit framerate: " + (limitFramerate ? "ON" : "OFF")
				: ""))))));
	}

	/**
	 * Load the settings.
	 */
	private void loadSettings()
	{
		try
		{
			if(settingsFile.exists())
			{
				FileReader fr = new FileReader(settingsFile);
				BufferedReader br = new BufferedReader(fr);

				String line;

				while((line = br.readLine()) != null)
				{
					String[] setting = line.split(":");

					if(setting[0].equals("music"))
					{
						music = setting[1].equals("true");
					}

					if(setting[0].equals("sound"))
					{
						sound = setting[1].equals("true");
					}

					if(setting[0].equals("invertYMouse"))
					{
						invertMouse = setting[1].equals("true");
					}

					if(setting[0].equals("showFrameRate"))
					{
						showFrameRate = setting[1].equals("true");
					}

					if(setting[0].equals("viewDistance"))
					{
						viewDistance = Integer.parseInt(setting[1]);
					}

					if(setting[0].equals("bobView"))
					{
						viewBobbing = setting[1].equals("true");
					}

					if(setting[0].equals("limitFramerate"))
					{
						limitFramerate = setting[1].equals("true");
					}
				}

				br.close();
				fr.close();
			}
		} catch (FileNotFoundException e) {
			try {
				throw new Exception("Failed to load settings.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			try {
				throw new Exception("Failed to load settings.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Load the bindings.
	 */
	private void loadBindings()
	{
		try
		{
			if(bindingsFile.exists())
			{
				FileReader fr = new FileReader(bindingsFile);
				BufferedReader br = new BufferedReader(fr);

				String line;

				while((line = br.readLine()) != null)
				{
					String[] setting = line.split(":");

					for(int index = 0; index < bindings.length; index++)
					{
						if(setting[0].equals("key_" + bindings[index].name))
						{
							bindings[index].key = Integer.parseInt(setting[1]);
						}
					}
				}

				br.close();
				fr.close();
			}
		} catch (FileNotFoundException e) {
			try {
				throw new Exception("Failed to load bindings.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			try {
				throw new Exception("Failed to load bindings.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Save the settings.
	 */
	private void saveSettings()
	{
		try
		{
			FileWriter fw = new FileWriter(settingsFile);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("music:" + music);
			pw.println("sound:" + sound);
			pw.println("invertYMouse:" + invertMouse);
			pw.println("showFrameRate:" + showFrameRate);
			pw.println("viewDistance:" + viewDistance);
			pw.println("bobView:" + viewBobbing);
			pw.println("limitFramerate:" + limitFramerate);

			pw.close();
			fw.close();
		} catch (IOException e) {
			try {
				throw new Exception("Failed to save settings.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Save the bindings.
	 */
	private void saveBindings()
	{
		try
		{
			FileWriter fw = new FileWriter(bindingsFile);
			PrintWriter pw = new PrintWriter(fw);

			for(int binding = 0; binding < bindings.length; binding++)
			{
				pw.println("key_" + bindings[binding].name + ":" + bindings[binding].key);
			}

			pw.close();
			fw.close();
		} catch (IOException e) {
			try {
				throw new Exception("Failed to save bindings.", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
