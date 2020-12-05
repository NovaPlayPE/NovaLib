package net.novatech.library.math;

import lombok.Getter;
import lombok.Setter;

public class RayTrace {
	
	@Getter
	@Setter
	public Vector3d beginPosition = null;
	
	@Getter
	@Setter
	public Vector3d direction;
	
	private boolean isFinite;
	
	public RayTrace(Vector3d begin, Vector3d direction) {
		this(begin, direction, true);
	}
	
	public RayTrace(Vector3d begin, Vector3d direction, boolean isFinite) {
		this.beginPosition = begin;
		this.direction = direction;
		this.isFinite = isFinite;
	}
	
	public boolean isFinite() {
		return this.isFinite;
	}
	
	public Vector3d getEnd() {
		if(isFinite()) {
			return this.direction;
		} else {
			throw new MathematicException("RayTrace has no end position");
		}
	}
	
}