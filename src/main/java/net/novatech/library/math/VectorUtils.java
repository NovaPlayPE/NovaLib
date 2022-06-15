package net.novatech.library.math;

import java.util.ArrayList;

import net.novatech.library.math.vector.Vector;
import net.novatech.library.math.vector.Vector2;
import net.novatech.library.math.vector.Vector3;
import net.novatech.library.math.vector.Vector3d;

public class VectorUtils {
	
	public boolean isVector3(Vector vec) {
		return vec instanceof Vector3;
	}
	
	public static final double calculateDistance(Vector2 from, Vector2 to, boolean squared) {
		double xDis = to.getX() - from.getX();
		double yDis = to.getY() - from.getY();
		
		double distanceSquared = Math.pow(xDis, 2) + Math.pow(yDis,2);
		if(squared) {
			return distanceSquared;
		}
		return Math.sqrt(distanceSquared);
	}
	
	public static final Vector3d rotateAroundAxisX(Vector3d vec, double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = vec.getY() * cos - vec.getZ() * sin;
        z = vec.getY() * sin + vec.getZ() * cos;
        vec.setY(y);
        vec.setZ(z);
        return vec;
    }

    public static final Vector3d rotateAroundAxisY(Vector3d vec, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = vec.getX() * cos + vec.getZ() * sin;
        z = vec.getX() * -sin + vec.getZ() * cos;
        vec.setX(x);
        vec.setZ(z);
        return vec;
    }

    public static final Vector3d rotateAroundAxisZ(Vector3d vec, double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = vec.getX() * cos - vec.getY() * sin;
        y = vec.getX() * sin + vec.getY() * cos;
        vec.setX(x);
        vec.setY(y);
        return vec;
    }

    public static final Vector3d rotateVector(Vector3d vec, double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(vec, angleX);
        rotateAroundAxisY(vec, angleY);
        rotateAroundAxisZ(vec,angleZ);
        return vec;
    }

    public final Vector3d rotateVector(Vector3d v, double yawDegrees, double pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector3d(x, y, z);
    }
    
    public static final Vector2 rotate(Vector2 v, double degrees) {
    	double cos = Math.cos(Math.toRadians(degrees));
    	double sin = Math.sin(Math.toRadians(degrees));
    	
    	double newX, newY;
    	double x,y;
    	
    	newX = v.getX() * cos - v.getY() * sin;
    	newY = v.getX() * sin - v.getY() * cos;
    	x = newX;
    	y = newY;
    	
    	return new Vector2(x,y);
    }
    
    public static Vector3d flip(Vector3d vec, float degree) {
    	if(degree > 90 && degree < 270) {
    		vec = vec.multiply(-1);
    	}
    	return vec;
    }

    
	
}