package com.jediminer543.Arena.renders.basic;

import static org.lwjgl.opengl.GL11.GL_QUADS;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.jediminer543.Arena.renders.IRenderable;

public class Cube extends RenderBaseBasic implements IRenderable
{
	


	public Cube(Vector3f pos, Vector3f size)
	{
		this.pos = pos;
		this.size = size;
	}

	@Override
	public void render()
	{
		
		GL11.glTranslatef(pos.x, pos.y, pos.z);
	    GL11.glBegin(GL_QUADS);
		
	    	//Backup Colour
	    	GL11.glColor3f(1, 1, 1);
	    
	    	// Front Face
	    	GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(-size.x, -size.y,  size.z);   // Bottom Left Of The Texture and Quad
            GL11.glVertex3f( size.x, -size.y,  size.z);   // Bottom Right Of The Texture and Quad
            GL11.glVertex3f( size.x,  size.y,  size.z);   // Top Right Of The Texture and Quad
            GL11.glVertex3f(-size.x,  size.y,  size.z);   // Top Left Of The Texture and Quad

            // Back Face
            GL11.glVertex3f(-size.x, -size.y, -size.z);   // Bottom Right Of The Texture and Quad
            GL11.glVertex3f(-size.x,  size.y, -size.z);   // Top Right Of The Texture and Quad
            GL11.glVertex3f( size.x,  size.y, -size.z);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f( size.x, -size.y, -size.z);   // Bottom Left Of The Texture and Quad

            // Top Face
            GL11.glVertex3f(-size.x,  size.y, -size.z);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(-size.x,  size.y,  size.z);   // Bottom Left Of The Texture and Quad
            GL11.glVertex3f( size.x,  size.y,  size.z);   // Bottom Right Of The Texture and Quad
            GL11.glVertex3f( size.x,  size.y, -size.z);   // Top Right Of The Texture and Quad

            // Bottom Face
            GL11.glVertex3f(-size.x, -size.y, -size.z);   // Top Right Of The Texture and Quad
            GL11.glVertex3f( size.x, -size.y, -size.z);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f( size.x, -size.y,  size.z);   // Bottom Left Of The Texture and Quad
            GL11.glVertex3f(-size.x, -size.y,  size.z);   // Bottom Right Of The Texture and Quad

            // Right face

            GL11.glVertex3f( size.x, -size.y, -size.z);   // Bottom Right Of The Texture and Quad
            GL11.glVertex3f( size.x,  size.y, -size.z);   // Top Right Of The Texture and Quad
            GL11.glVertex3f( size.x,  size.y,  size.z);   // Top Left Of The Texture and Quad
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f( size.x, -size.y,  size.z);   // Bottom Left Of The Texture and Quad

            // Left Face
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex3f(-size.x, -size.y, -size.z);   // Bottom Left Of The Texture and Quad
            GL11.glVertex3f(-size.x, -size.y,  size.z);   // Bottom Right Of The Texture and Quad
            GL11.glVertex3f(-size.x,  size.y,  size.z);   // Top Right Of The Texture and Quad
            GL11.glVertex3f(-size.x,  size.y, -size.z);   // Top Left Of The Texture and Quad
		GL11.glEnd();
	}

}
