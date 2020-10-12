package net.novatech.library.math;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

public class Rotation implements Cloneable{
	
	@Getter
	@Setter
	public float yaw;
	
	@Getter
	@Setter
	public float pitch;
	
	public Rotation(@NonNull float yaw, @NonNull float pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
}