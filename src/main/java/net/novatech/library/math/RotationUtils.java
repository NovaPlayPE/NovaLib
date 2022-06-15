package net.novatech.library.math;

import net.novatech.library.math.motion.Rotation;
import net.novatech.library.math.vector.Vector3;

public class RotationUtils {
	
	public static boolean compareRotation(Rotation rot1, Rotation rot2) {
		return compareRotation(rot1, rot2, 20f, 20f);
	}
	
	public static boolean compareRotation(Rotation rot1, Rotation rot2, float precisionYaw, float precisionPitch) {
		float yaw1 = rot1.getYaw();
		float yaw2 = rot2.getYaw();
		float deltaYaw = Math.abs(yaw1 - yaw2);
		boolean yawCheck = false;
		if(deltaYaw <= precisionYaw) {
			yawCheck = true;
		}
		
		float pitch1 = rot1.getPitch();
		float pitch2 = rot2.getPitch();
		float deltaPitch = Math.abs(yaw1 - yaw2);
		boolean pitchCheck = false;
		if(deltaPitch <= precisionPitch) {
			pitchCheck = true;
		}
		
		return yawCheck && pitchCheck;
	}
	
	public Vector3 getRelativeVector(Rotation rot, CubisticFace side) {
		return null;
	}
	
}