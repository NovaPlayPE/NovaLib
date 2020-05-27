package net.novaplay.library.math;

public class Vector3Utils {
	
	public final Vector3d rotateAroundAxisX(Vector3d vec, double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = vec.getY() * cos - vec.getZ() * sin;
        z = vec.getY() * sin + vec.getZ() * cos;
        vec.setY(y);
        vec.setZ(z);
        return vec;
    }

    public final Vector3d rotateAroundAxisY(Vector3d vec, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = vec.getX() * cos + vec.getZ() * sin;
        z = vec.getX() * -sin + vec.getZ() * cos;
        vec.setX(x);
        vec.setZ(z);
        return vec;
    }

    public final Vector3d rotateAroundAxisZ(Vector3d vec, double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = vec.getX() * cos - vec.getY() * sin;
        y = vec.getX() * sin + vec.getY() * cos;
        vec.setX(x);
        vec.setY(y);
        return vec;
    }

    public final Vector3d rotateVector(Vector3d vec, double angleX, double angleY, double angleZ) {
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

    public final double angleToXAxis(Vector3d v) {
        return Math.atan2(v.getX(), v.getY());
    }
	
}