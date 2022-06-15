package net.novatech.library.math;

import java.util.ArrayList;

import net.novatech.library.math.vector.Vector3d;

public class Trigonometry {
	
	public static final double angleToXAxis(Vector3d v) {
        return Math.atan2(v.getX(), v.getY());
    }
    
    public static final ArrayList<Vector3d> calculate2dCircle(Vector3d d, double radius) {
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
    
    public static final ArrayList<Vector3d> calculate2dCircleInside(Vector3d v, double radius, double precision) {
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
    
    public static final ArrayList<Vector3d> calculateSphere(Vector3d v, double radius, double precision, boolean fill){
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