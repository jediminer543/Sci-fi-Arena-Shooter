package com.jediminer543.Arena;

import java.io.File;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.jediminer543.Arena.config.Config;
import com.jediminer543.Arena.globals.GLOBALS;

public class Game
{

	public static File configFile;
	public static Config config;
	
	public static void main(String[] args)
	{
		configFile = new File(System.getProperty("user.dir")+"\\Config.cfg");
		loadConfig();
		initDisplay();
	}
	
	public static void loadConfig()
	{
		try {
			if (!configFile.exists())
			{
				configFile.createNewFile();
				config = new Config(configFile);
				config.set("Screen.X", 800);
				config.set("Screen.Y", 600);
				
			}
			config = new Config(configFile);
			GLOBALS.SCREEN_X = config.readInteger("Screen.X");
			GLOBALS.SCREEN_Y = config.readInteger("Screen.Y");
		} catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("Error loading config, Game will now quit.");
			System.exit(0);
		}
	}
	
	public static void initDisplay()
	{
		try {
			Display.setDisplayMode(new DisplayMode(GLOBALS.SCREEN_X,GLOBALS.SCREEN_Y));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
