package com.jediminer543.Arena.renders;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Texture
{
	private int target = GL_TEXTURE_2D;
	private int id;
	private int width;
	private int height;
	
	private int wrap = GL_REPEAT;
	private int filter = GL_LINEAR;
	
	ByteBuffer buffer;
	
	public Texture(String pngRef) throws IOException
	{
		this(new FileInputStream(pngRef));
	}
	
	public Texture(InputStream input) throws IOException
	{
		PNGDecoder decoder = new PNGDecoder(input);
		
		width = decoder.getWidth();
		height = decoder.getHeight();
		
		buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
		
		decoder.decode(buffer, 4 * decoder.getWidth(), PNGDecoder.Format.RGBA);	
		
		buffer.flip();
	}

	public void bind() throws IOException
	{

		glEnable(GL_TEXTURE_2D);
		id = glGenTextures();
		
		glBindTexture(target, id);
		
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glPixelStorei(GL_PACK_ALIGNMENT, 1);

		
		glTexParameteri(target, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(target, GL_TEXTURE_MAG_FILTER, filter);
		glTexParameteri(target, GL_TEXTURE_WRAP_S, wrap);
		glTexParameteri(target, GL_TEXTURE_WRAP_T, wrap);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, getWidth(), getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}

	public int getWidth() {
		return width;
	}

	public int getWrap() {
		return wrap;
	}

	public void setWrap(int wrap) {
		
		if(wrap == GL_CLAMP | wrap == GL_CLAMP_TO_EDGE | wrap == GL_REPEAT)
		this.wrap = wrap;
		else
		throw new IllegalArgumentException();
	}

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter)
	{
		if(filter == GL_LINEAR | filter == GL_NEAREST)
		this.filter = filter;
		else
		throw new IllegalArgumentException();
	}

	public int getHeight() {
		return height;
	}
}
