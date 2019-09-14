package net.novaplay.library.math;

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
	
}
