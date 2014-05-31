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
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import com.jediminer543.Arena.config.Config;
import com.jediminer543.Arena.entity.player.Player;
import com.jediminer543.Arena.globals.GLOBALPHYS;
import com.jediminer543.Arena.globals.GLOBALS;
import com.jediminer543.Arena.levels.Debug;
import com.jediminer543.Arena.menu.hud.HUD;
import com.jediminer543.Arena.renders.IRenderable;

public class Game
{

	public static File configFile;
	public static Config config;
	
	static Player player = new Player(new Vector3f(0,2,0));
	
	
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
		initPhys();
		GLOBALS.FOV = 60;
		GLOBALS.TickPool.add(player);
		Debug.load();
		//GLOBALS.RenderPool.add(new Cube(new Vector3f(-50,-5,-50), new Vector3f(100, 1, 100)));
		GLOBALS.RenderPool.add(new HUD());
		
		//Fonts
		Font awtDebugFont = new Font("Courier", Font.PLAIN, 12);
		GLOBALS.DebugText = new TrueTypeFont(awtDebugFont, false);
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("/res/Fonts/SubZER0.ttf");
			Font awtTitleFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtTitleFont = awtTitleFont.deriveFont(35f); 
			GLOBALS.TitleText = new TrueTypeFont(awtTitleFont, false);
			InputStream inputStream1 = ResourceLoader.getResourceAsStream("/res/Fonts/xirod.ttf");
			Font awtHUD = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
			awtHUD = awtHUD.deriveFont(24f); 
			GLOBALS.HUDText = new TrueTypeFont(awtHUD, false);
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
        gluPerspective(GLOBALS.FOV, (float) Display.getWidth() / (float) Display.getHeight(), 0.01f, 100f);
        glGetFloat(GL_PROJECTION_MATRIX, GLOBALS.GLUMatrix);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glGetFloat(GL_PROJECTION_MATRIX, GLOBALS.HUDMatrix);
        glLoadMatrix(GLOBALS.GLUMatrix);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();


        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_BLEND);
	}
	
	public static void initPhys()
	{
		GLOBALPHYS.deceleration = new Vector3f(0.001f, 0.001f, 0.001f);
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
			player.physTick();
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
		Display.sync(60);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.2f, 6.08f, 8.08f, 0);
	}
	
	public static void gameRender()
	{
		for (IRenderable t:GLOBALS.RenderPool)
		{
			glPushMatrix();
			t.render();
			glPopMatrix();
		}
		//TextHandeler.drawTitleStringHUD(Display.getWidth()/2, Display.getHeight() / 2, "SciFi Arena", Color.red);
	}
	
	
	
	
}
