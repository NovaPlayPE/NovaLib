package net.novatech.library.math.vector;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

public class Vector2 {
	
	@Getter
	@Setter
	public double x;
	@Getter
	@Setter
	public double y;

	public Vector2(@NonNull double x, @NonNull double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector2 fromVector(Vector3d v) {
		return fromVector(v, Exclude.Y);
	}
	
	public static Vector2 fromVector(Vector3d v, Exclude e) {
		Vector2 vector = null;
		switch(e) {
		case X:
			vector = new Vector2(v.getY(), v.getZ());
			break;
		case Y:
			vector = new Vector2(v.getX(), v.getZ());
			break;
		case Z:
			vector = new Vector2(v.getX(), v.getY());
			break;
		}
		return vector;
	}
	
}

enum Exclude{
	X,
	Y,
	Z;
}
