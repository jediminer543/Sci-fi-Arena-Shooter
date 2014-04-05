package com.jediminer543.Arena.phys;

import org.lwjgl.util.vector.Vector3f;

public class AABB
{
	public Vector3f pos, size;
	
	public boolean collides(AABB target)
	{
		float tx = pos.x;
		float ty = pos.y;
		float tz = pos.z;
		float rx = target.pos.x;
		float ry = target.pos.y;
		float rz = target.pos.z;
		
		float tw = size.x;
		float th = size.y;
		float td = size.z;
		float rw = target.size.x;
		float rh = target.size.y;
		float rd = target.size.z;

		
		if (tw <= 0 || th <= 0 || td <= 0 || rw <= 0 || rh <= 0 || rd <= 0)
			return false;
		
		tw += tx;
		th += ty;
		td += tz;
		rw += rx;
		rh += ry;
		rd += rz;
		
		return ((rw < rx || rw > tx) && (rh < ry || rh > ty) && (rd < rz || rd > tz) && (tw < tx || tw > rx) && (th < ty || th > ry) && (td < tz || td > rz));
	}
	
}
