package net.novaplay.library.math;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Vector3i implements Cloneable{

	
	@Getter
	@Setter
	public int x;
	@Getter
	@Setter
	public int y;
	@Getter
	@Setter
	public int z;

	public Vector3i(@NonNull int x, @NonNull int y, @NonNull int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Vector3d) {
			Vector3d v = (Vector3d) obj;
			return this.x == v.x && this.y == v.y && this.z == v.z;
		}
		return false;
	}
	
    public final Vector3i rotateAroundAxisX(double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = getY() * cos - getZ() * sin;
        z = getY() * sin + getZ() * cos;
        setY((int)y);
        setZ((int)z);
        return this;
    }

    public final Vector3i rotateAroundAxisY(double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = getX() * cos + getZ() * sin;
        z = getX() * -sin + getZ() * cos;
        setX((int)x);
        setZ((int)z);
        return this;
    }

    public final Vector3i rotateAroundAxisZ(double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = getX() * cos - getY() * sin;
        y = getX() * sin + getY() * cos;
        setX((int)x);
        setY((int)y);
        return this;
    }

    public final Vector3i rotateVector(double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(angleX);
        rotateAroundAxisY(angleY);
        rotateAroundAxisZ(angleZ);
        return this;
    }

    public final Vector3i rotateVector(double yawDegrees, double pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        initialX = getX();
        initialY = getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        initialZ = getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector3i((int)x, (int)y, (int)z);
    }

    public final double angleToXAxis() {
        return Math.atan2(getX(), getY());
    }
    
    public Vector3d asDouble() {
    	Vector3d vec = new Vector3d((double)getX(), (double)getY(), (double)getZ());
    	return vec;
    }
    
    public Vector3f asFloat() {
    	Vector3f vec = new Vector3f((float)getX(),(float)getY(),(float)getZ());
    	return vec;
    }
	
	public Vector3i clone() {
		try {
            return (Vector3i) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
	}
	
}
