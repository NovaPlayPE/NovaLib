package net.novatech.library.math.motion;

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
	
	@Getter
	@Setter
	public float headYaw;
	
	public Rotation(@NonNull float yaw, @NonNull float pitch) {
		this.yaw = yaw;
		this.headYaw = yaw;
		this.pitch = pitch;
	}
	
}