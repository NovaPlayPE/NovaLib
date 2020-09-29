package net.novaplay.library.math;

import java.util.ArrayList;

public class VectorUtils {
	
	public final double calculateDistance(Vector2 from, Vector2 to, boolean squared) {
		double xDis = to.getX() - from.getX();
		double yDis = to.getY() - from.getY();
		
		double distanceSquared = Math.pow(xDis, 2) + Math.pow(yDis,2);
		if(squared) {
			return distanceSquared;
		}
		return Math.sqrt(distanceSquared);
	}
	
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
    
    public final ArrayList<Vector3d> calculate2dCircle(Vector3d d, double radius) {
    	ArrayList<Vector3d> circle = new ArrayList<Vector3d>();
    	ArrayList<Vector3d> circledCalculation = new ArrayList<Vector3d>();
    	for(int i = 0; i <= 2 * Math.PI; i++) {
    		switch(i) {
    		case 0 :
    			circledCalculation.add(new Vector3d(0,0,radius));
    			continue;
    		case (int) (Math.PI / 2):
    			circledCalculation.add(new Vector3d(radius,0,0));
    			continue;
    		case (int) Math.PI:
    			circledCalculation.add(new Vector3d(0,0,-radius));
    			continue;
    		case (int) ((Math.PI * 2) / 3):
    			circledCalculation.add(new Vector3d(-radius,0,0));
    			continue;
    		}
    		double x = Math.cos(i) * radius;
    		double z = Math.sin(i) * radius;
    		circledCalculation.add(new Vector3d(x,0,z));
    	}
    	
    	for(Vector3d vec : circledCalculation) {
    		circle.add(d.add(vec));
    	}
    	
    	return circle;
    }
    
    public final ArrayList<Vector3d> calculate2dCircleInside(Vector3d v, double radius, double precision) {
    	ArrayList<Vector3d> circle = new ArrayList<Vector3d>();
    	double exponent = Math.pow(radius,2);
    	Vector3d rV = new Vector3d(radius, radius, radius);
    	
    	Vector3d max = v.add(rV);
    	Vector3d min = v.subtract(rV);
    	
    	for(double x = max.getX(); x >= min.getX(); x-=precision) {
    		double alphaX = x - v.getX();
    		double epsilonX = (Math.pow(alphaX, 2) / exponent);
    		for(double z = max.getZ(); z >= min.getZ(); z -=precision) {
    			double alphaZ = z - v.getZ();
    			double epsilonZ = (Math.pow(alphaZ, 2) / exponent);
    			double epsilon = epsilonX + epsilonZ;
    			if(epsilon <= 1.0D) {
    				circle.add(new Vector3d(x, v.getY(), z));
    			}
    		}
    	}
    	
    	return circle;
    }
    
    public final ArrayList<Vector3d> calculateSphere(Vector3d v, double radius, double precision, boolean fill){
    	ArrayList<Vector3d> sphere = new ArrayList<Vector3d>();
    	double exponent = Math.pow(radius,2);
    	Vector3d rV = new Vector3d(radius, radius, radius);
    	
    	Vector3d max = v.add(rV);
    	Vector3d min = v.subtract(rV);
    	
    	for(double x = max.getX(); x >= min.getX(); x-=precision) {
    		double alphaX = x - v.getX();
    		double epsilonX = (Math.pow(alphaX, 2) / exponent);
    		for(double y = max.getY(); y >= min.getY(); y-=precision) {
        		double alphaY = y - v.getY();
        		double epsilonY = (Math.pow(alphaY, 2) / exponent);
        		for(double z = max.getZ(); z >= min.getZ(); z -=precision) {
        			double alphaZ = z - v.getZ();
        			double epsilonZ = (Math.pow(alphaZ, 2) / exponent);
        			double epsilon = epsilonX + epsilonY + epsilonZ;
        			if(epsilon <= 1.0D) {
        				if(!fill && epsilon < 0.85D) {
        					continue;
        				}
        				sphere.add(new Vector3d(x,y,z));
        			}
        		}
    		}
    	}
    	return sphere;
    }
	
}