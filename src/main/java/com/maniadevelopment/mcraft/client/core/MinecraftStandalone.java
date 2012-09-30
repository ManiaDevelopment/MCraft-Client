package com.maniadevelopment.mcraft.client.core;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.MinecraftApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 7:16 PM
 */
public class MinecraftStandalone
{
	public MinecraftStandalone()
	{
	}

	public void startMinecraft()
	{
		MinecraftFrame minecraftFrame = new MinecraftFrame();

		minecraftFrame.startMinecraft();
	}

	private class MinecraftFrame extends JFrame
	{
		public MinecraftFrame()
		{
			setSize(854, 480);
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());

			addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent e)
				{
					minecraft.running = false;
				}
			});
		}

		private Minecraft minecraft;

		public void startMinecraft()
		{
			MCraftApplet applet = new MCraftApplet();
			final MinecraftCanvas canvas = new MinecraftCanvas();
			minecraft = new Minecraft(canvas, applet, getWidth(), getHeight(), false);

			canvas.setMinecraft(minecraft);
			canvas.setSize(getSize());

			add(canvas, "Center");

			canvas.setFocusable(true);

			pack();
			setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
			setVisible(true);

			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while(true)
					{
						if(!minecraft.running)
						{
							minecraft.shutdown();
							dispose();
						}

						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		private class MCraftApplet extends MinecraftApplet
		{
			public MCraftApplet()
			{
				parameters = new HashMap<String, String>();
			}

			@Override
			public URL getDocumentBase()
			{
				try {
					return new URL("http://minecraft.net:80/play.jsp");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			public URL getCodeBase()
			{
				try {
					return new URL("http://minecraft.net:80/");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			public String getParameter(String name)
			{
				return parameters.get(name);
			}

			private Map<String, String> parameters;
		}

		private class MinecraftCanvas extends Canvas
		{
			public MinecraftCanvas()
			{
			}

			@Override
			public synchronized void addNotify()
			{
				super.addNotify();

				startThread();
			}

			@Override
			public synchronized void removeNotify()
			{
				stopThread();

				super.removeNotify();
			}

			private static final long serialVersionUID = 1L;

			private Minecraft minecraft;
			private Thread thread;

			public void setMinecraft(Minecraft minecraft)
			{
				this.minecraft = minecraft;
			}

			private synchronized void startThread()
			{
				if(thread == null)
				{
					thread = new Thread(minecraft, "Client");

					thread.start();
				}
			}

			private synchronized void stopThread()
			{
				if(thread != null)
				{
					minecraft.running = false;

					try
					{
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();

						minecraft.shutdown();
					}

					thread = null;
				}
			}
		}
	}
}
