package net.novaplay.library.math;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Vector3d implements Cloneable{

	
	@Getter
	@Setter
	public double x;
	@Getter
	@Setter
	public double y;
	@Getter
	@Setter
	public double z;

	public Vector3d(@NonNull double x, @NonNull double y, @NonNull double z) {
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
	
    public final Vector3d rotateAroundAxisX(double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = getY() * cos - getZ() * sin;
        z = getY() * sin + getZ() * cos;
        setY(y);
        setZ(z);
        return this;
    }

    public final Vector3d rotateAroundAxisY(double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = getX() * cos + getZ() * sin;
        z = getX() * -sin + getZ() * cos;
        setX(x);
        setZ(z);
        return this;
    }

    public final Vector3d rotateAroundAxisZ(double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = getX() * cos - getY() * sin;
        y = getX() * sin + getY() * cos;
        setX(x);
        setY(y);
        return this;
    }

    public final Vector3d rotateVector(double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(angleX);
        rotateAroundAxisY(angleY);
        rotateAroundAxisZ(angleZ);
        return this;
    }

    public final Vector3d rotateVector(double yawDegrees, double pitchDegrees) {
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

        return new Vector3d(x, y, z);
    }

    public final double angleToXAxis() {
        return Math.atan2(getX(), getY());
    }
    
    public Vector3f asFloat() {
    	Vector3f vec = new Vector3f((float)getX(), (float)getY(), (float)getZ());
    	return vec;
    }
    
    public Vector3i asInt() {
    	Vector3i vec = new Vector3i((int)getX(),(int)getY(),(int)getZ());
    	return vec;
    }
	
	public Vector3d clone() {
		try {
            return (Vector3d) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
	}
	
}
