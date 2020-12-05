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
	
	@Getter
	@Setter
	private CubisticFace cubisticFace;
	
	private boolean isFinite;
	
	public RayTrace(Vector3d begin, Vector3d direction) {
		this(begin, direction, null);
	}
	
	public RayTrace(Vector3d begin, Vector3d direction, CubisticFace face) {
		this(begin, direction, true, face);
	}
	
	public RayTrace(Vector3d begin, Vector3d direction, boolean isFinite, CubisticFace face) {
		this.beginPosition = begin;
		this.direction = direction;
		this.isFinite = isFinite;
		this.cubisticFace = face;
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