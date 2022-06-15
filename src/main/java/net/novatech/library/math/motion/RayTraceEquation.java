package net.novatech.library.math.motion;

import lombok.Getter;
import lombok.Setter;
import net.novatech.library.math.vector.Vector;
import net.novatech.library.math.vector.Vector3d;

public class RayTraceEquation {
	
	@Getter
	@Setter
	public RayTrace rayTrace;
	
	public RayTraceEquation(RayTrace ray) {
		this.rayTrace = ray;
	}
	
	/*
	 * Currently supports only 3D vectors...
	 */
	public boolean containsVector(Vector vec) {
		if(getRayTrace() instanceof RayTrace3) {
			RayTrace3 ray = (RayTrace3)getRayTrace();
			Vector3d v = (Vector3d)vec;
			double c1 = (v.getX() - ((Vector3d)ray.getBeginPosition()).getX()) / ((Vector3d)ray.getDirection()).getX();
			double c2 = (v.getY() - ((Vector3d)ray.getBeginPosition()).getY()) / ((Vector3d)ray.getDirection()).getY();
			double c3 = (v.getZ() - ((Vector3d)ray.getBeginPosition()).getZ()) / ((Vector3d)ray.getDirection()).getZ();
			
			if(c1 == c2 && c2 == c3 && c3 == c1) {
				return true;
			}
		}
		return false;
	}
	
}