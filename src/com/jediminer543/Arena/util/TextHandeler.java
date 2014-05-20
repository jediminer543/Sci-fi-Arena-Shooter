package com.jediminer543.Arena.util;

import static org.lwjgl.opengl.GL11.GL_BLEND;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.jediminer543.Arena.globals.GLOBALS;

public class TextHandeler
{
	public static void drawMainStringHUD(float x, float y, String text, Color colour)
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadMatrix(GLOBALS.HUDMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glEnable(GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GLOBALS.DebugText.drawString(x, y, text, colour);
		GL11.glDisable(GL_BLEND);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadMatrix(GLOBALS.GLUMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public static void drawTitleStringHUD(float x, float y, String text, Color colour)
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadMatrix(GLOBALS.HUDMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glEnable(GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GLOBALS.TitleText.drawString(x, y, text, colour);
		GL11.glDisable(GL_BLEND);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadMatrix(GLOBALS.GLUMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	
}
