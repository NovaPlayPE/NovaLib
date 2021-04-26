package net.novatech.library.math;

import lombok.Getter;
import lombok.Setter;

public class RayTrace3 extends RayTrace{
	
	@Getter
	@Setter
	private CubisticFace cubisticFace;
	
	public RayTrace3(Vector begin, Vector direction, boolean isFinite, CubisticFace face) {
		super(begin, direction, isFinite);
		this.cubisticFace = face;
	}

}
