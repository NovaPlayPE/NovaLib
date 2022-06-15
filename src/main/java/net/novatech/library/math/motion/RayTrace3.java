package net.novatech.library.math.motion;

import lombok.Getter;
import lombok.Setter;
import net.novatech.library.math.CubisticFace;
import net.novatech.library.math.vector.Vector;

public class RayTrace3 extends RayTrace{
	
	@Getter
	@Setter
	private CubisticFace cubisticFace;
	
	public RayTrace3(Vector begin, Vector direction, boolean isFinite, CubisticFace face) {
		super(begin, direction, isFinite);
		this.cubisticFace = face;
	}

}
