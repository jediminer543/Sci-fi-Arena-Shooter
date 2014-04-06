package com.jediminer543.Arena;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import com.jediminer543.Arena.config.Config;
import com.jediminer543.Arena.entity.player.Player;
import com.jediminer543.Arena.globals.GLOBALS;
import com.jediminer543.Arena.renders.IRenderable;
import com.jediminer543.Arena.renders.basic.Cube;
import com.jediminer543.Arena.util.TextHandeler;

public class Game
{

	public static File configFile;
	public static Config config;
	

	
	
	public static void main(String[] args)
	{
		configFile = new File(System.getProperty("user.dir")+"\\Config.cfg");
		loadConfig();
		initDisplay();
		initGameComponents();
		initGl();
		gameLoop();
		
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
	
	public static void initGameComponents()
	{
		GLOBALS.FOV = 90;
		GLOBALS.TickPool.add(new Player(new Vector3f(0,2,0)));
		GLOBALS.RenderPool.add(new Cube(new Vector3f(-50,-5,-50), new Vector3f(100, 1, 100)));
		Font awtDebugFont = new Font("Courier", Font.PLAIN, 12);
		GLOBALS.DebugText = new TrueTypeFont(awtDebugFont, false);
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("/res/Fonts/INTERDIM.TTF");
			Font awtTitleFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtTitleFont = awtTitleFont.deriveFont(35f); 
			
			GLOBALS.TitleText = 	new TrueTypeFont(awtTitleFont, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Error loading fonts, Game will now quit.");
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
	
	public static void initGl()
	{
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(GLOBALS.FOV, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 100f);
        glGetFloat(GL_PROJECTION_MATRIX, GLOBALS.GLUMatrix);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glGetFloat(GL_PROJECTION_MATRIX, GLOBALS.HUDMatrix);
        glLoadMatrix(GLOBALS.GLUMatrix);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();


        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_BLEND);
	}
	
	public static void gameLoop()
	{
        Mouse.setGrabbed(true);
        
		while (!Display.isCloseRequested())
		{	

			
			Display.update();
			
			
			
			//p.updateTick();
			
			gameGL();
			gameRender();
			gameTick();
			//System.out.println("Game Loop Ran");
		}
		System.exit(0);
	}
	
	public static void gameTick()
	{
		for (Tickable t:GLOBALS.TickPool)
		{
			t.updateTick();
		}
	}
	
	public static void gameGL()
	{
		Display.sync(120);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void gameRender()
	{
		
		TextHandeler.drawTitleStringHUD((Display.getWidth() / 2) - 200, Display.getHeight() - 40, "Sci-fi Shooter", Color.red);
		
		for (IRenderable t:GLOBALS.RenderPool)
		{
			t.render();
		}
	}
	
	
	
	
}
