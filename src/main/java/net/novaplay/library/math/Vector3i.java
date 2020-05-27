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
		if(obj instanceof Vector3i) {
			Vector3i v = (Vector3i) obj;
			return this.x == v.x && this.y == v.y && this.z == v.z;
		}
		return false;
	}
	
	public Vector3i add(double value) {
		this.x += value;
		this.y += value;
		this.z += value;
		return this;
	}

	public Vector3i add(@NonNull Vector3i v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3i subtract(double value) {
		this.x -= value;
		this.y -= value;
		this.z -= value;
		return this;
	}

	public Vector3i subtract(@NonNull Vector3i v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3i multiply(double value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	public Vector3i multiply(@NonNull Vector3i v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
	}

	public Vector3i divide(double value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		return this;
	}

	public Vector3i divide(@NonNull Vector3i v) {
		this.x /= v.x;
		this.y /= v.y;
		this.z /= v.z;
		return this;
	}

	public Vector3i copy(@NonNull Vector3i v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public Vector3i normalize() {
		double length = length();
		this.x /= length;
		this.y /= length;
		this.z /= length;
		return this;
	}

	public Vector3i zero() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}

	public double length() {
		return Math.sqrt(MathUtils.square(this.x) + MathUtils.square(this.y) + MathUtils.square(this.z));
	}

	public double lengthSquared() {
		return MathUtils.square(this.x) + MathUtils.square(this.y) + MathUtils.square(this.z);
	}

	public double distance(@NonNull Vector3d v) {
		return Math
				.sqrt(MathUtils.square(this.x - v.x) + MathUtils.square(this.y - v.y) + MathUtils.square(this.z - v.z));
	}

	public double distanceSquared(@NonNull Vector3d v) {
		return MathUtils.square(this.x - v.x) + MathUtils.square(this.y - v.y) + MathUtils.square(this.z - v.z);
	}

	public float angle(@NonNull Vector3i other) {
		double dot = MathUtils.constrainDoubleToRange(dot(other) / (length() * other.length()), -1.0, 1.0);

		return (float) Math.acos(dot);
	}

	public double dot(@NonNull Vector3i other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public Vector3i crossProduct(@NonNull Vector3i o) {
		int newX = this.y * o.z - o.y * this.z;
		int newY = this.z * o.x - o.z * this.x;
		int newZ = this.x * o.y - o.x * this.y;

		this.x = newX;
		this.y = newY;
		this.z = newZ;
		return this;
	}

	public Vector3i getCrossProduct(@NonNull Vector3i o) {
		int x = this.y * o.z - o.y * this.z;
		int y = this.z * o.x - o.z * this.x;
		int z = this.x * o.y - o.x * this.y;
		return new Vector3i(x, y, z);
	}

	public Vector3i midpoint(@NonNull Vector3i other) {
		x = (x + other.x) / 2;
		y = (y + other.y) / 2;
		z = (z + other.z) / 2;
		return this;
	}

	public Vector3i getMidpoint(@NonNull Vector3i other) {
		int x = (this.x + other.x) / 2;
		int y = (this.y + other.y) / 2;
		int z = (this.z + other.z) / 2;
		return new Vector3i(x, y, z);
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
