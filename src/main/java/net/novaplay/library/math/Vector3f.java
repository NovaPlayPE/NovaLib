package net.novaplay.library.math;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Vector3f implements Cloneable{

	
	@Getter
	@Setter
	public float x;
	@Getter
	@Setter
	public float y;
	@Getter
	@Setter
	public float z;

	public Vector3f(@NonNull float x, @NonNull float y, @NonNull float z) {
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
	
    public final Vector3f rotateAroundAxisX(double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = getY() * cos - getZ() * sin;
        z = getY() * sin + getZ() * cos;
        setY((float)y);
        setZ((float)z);
        return this;
    }

    public final Vector3f rotateAroundAxisY(double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = getX() * cos + getZ() * sin;
        z = getX() * -sin + getZ() * cos;
        setX((float)x);
        setZ((float)z);
        return this;
    }

    public final Vector3f rotateAroundAxisZ(double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = getX() * cos - getY() * sin;
        y = getX() * sin + getY() * cos;
        setX((float)x);
        setY((float)y);
        return this;
    }

    public final Vector3f rotateVector(double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(angleX);
        rotateAroundAxisY(angleY);
        rotateAroundAxisZ(angleZ);
        return this;
    }

    public final Vector3f rotateVector(double yawDegrees, double pitchDegrees) {
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

        return new Vector3f((float)x, (float)y, (float)z);
    }

    public final double angleToXAxis() {
        return Math.atan2(getX(), getY());
    }
    
    public Vector3d asDouble() {
    	Vector3d vec = new Vector3d((double)getX(), (double)getY(), (double)getZ());
    	return vec;
    }
    
    public Vector3i asInt() {
    	Vector3i vec = new Vector3i((int)getX(),(int)getY(),(int)getZ());
    	return vec;
    }
	
	public Vector3f clone() {
		try {
            return (Vector3f) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
	}
	
}
