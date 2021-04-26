package net.novatech.library.math;

import lombok.Getter;
import lombok.Setter;

public class RayTrace {
	
	@Getter
	@Setter
	public Vector beginPosition = null;
	
	@Getter
	@Setter
	public Vector direction;
	@Getter
	private boolean bothDirection = false;
		
	private boolean isFinite;
	
	public RayTrace(Vector begin, Vector direction) {
		this(begin, direction, true);
	}
	
	
	public RayTrace(Vector begin, Vector direction, boolean isFinite) {
		this.beginPosition = begin;
		this.direction = direction;
		this.isFinite = isFinite;
	}
	
	public boolean isFinite() {
		return this.isFinite;
	}
	
	public Vector getEnd() {
		if(isFinite()) {
			return this.direction;
		} else {
			throw new MathematicException("RayTrace has no end position");
		}
	}
	
	public void setLineDirection(boolean enable) {
		if(!isFinite()) {
			bothDirection = enable;
			return;
		}
		throw new MathematicException("Non-endless rayTrace cannot be set to both directions");
	}
	
}