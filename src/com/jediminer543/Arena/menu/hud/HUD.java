package com.jediminer543.Arena.menu.hud;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.jediminer543.Arena.globals.GLOBALS;
import com.jediminer543.Arena.renders.IRenderable;

public class HUD implements IRenderable {

	@Override
	public void render()
	{

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadMatrix(GLOBALS.HUDMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glEnable(GL_BLEND);
		GLOBALS.HUDText.drawString(10, Display.getHeight() - 90, "Map: "+ "DEBUG");
		GLOBALS.HUDText.drawString(10, Display.getHeight() - 60, "Gamemode: "+ "DEBUG");
		GLOBALS.HUDText.drawString(10, Display.getHeight() - 30, "Score: "+ "DEBUG");
		GLOBALS.HUDText.drawString(Display.getWidth() - 400, Display.getHeight() - 90, "Name: "+ "Jediminer543");
		GLOBALS.HUDText.drawString(Display.getWidth() - 400, Display.getHeight() - 60, "Health: "+ "300");
		GLOBALS.HUDText.drawString(Display.getWidth() - 400 , Display.getHeight() - 30, "Ammo: "+ "300");
		//GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		//GL11.glBegin(GL11.GL_QUADS);
		//GL11.glColor3f(5, 5, 5);
		//GL11.glVertex2f(-100f, -100f);
		//GL11.glVertex2f( 100f, -100f);
		//GL11.glVertex2f( 100f,  100f);
		//GL11.glVertex2f(-100f,  100f);
		//GL11.glEnd();
		GL11.glDisable(GL_BLEND);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadMatrix(GLOBALS.GLUMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}



}
