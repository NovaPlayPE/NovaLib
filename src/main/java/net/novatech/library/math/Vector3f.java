package net.novatech.library.math;

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
		if(obj instanceof Vector3f) {
			Vector3f v = (Vector3f) obj;
			return this.x == v.x && this.y == v.y && this.z == v.z;
		}
		return false;
	}
	
	public Vector3f add(double value) {
		this.x += value;
		this.y += value;
		this.z += value;
		return this;
	}

	public Vector3f add(@NonNull Vector3f v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3f subtract(double value) {
		this.x -= value;
		this.y -= value;
		this.z -= value;
		return this;
	}

	public Vector3f subtract(@NonNull Vector3f v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3f multiply(double value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	public Vector3f multiply(@NonNull Vector3f v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
	}

	public Vector3f divide(double value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		return this;
	}

	public Vector3f divide(@NonNull Vector3f v) {
		this.x /= v.x;
		this.y /= v.y;
		this.z /= v.z;
		return this;
	}

	public Vector3f copy(@NonNull Vector3f v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public Vector3f normalize() {
		double length = length();
		this.x /= length;
		this.y /= length;
		this.z /= length;
		return this;
	}

	public Vector3f zero() {
		this.x = 0F;
		this.y = 0F;
		this.z = 0F;
		return this;
	}

	public double length() {
		return Math.sqrt(MathUtils.square(this.x) + MathUtils.square(this.y) + MathUtils.square(this.z));
	}

	public double lengthSquared() {
		return MathUtils.square(this.x) + MathUtils.square(this.y) + MathUtils.square(this.z);
	}

	public double distance(@NonNull Vector3f v) {
		return Math
				.sqrt(MathUtils.square(this.x - v.x) + MathUtils.square(this.y - v.y) + MathUtils.square(this.z - v.z));
	}

	public double distanceSquared(@NonNull Vector3f v) {
		return MathUtils.square(this.x - v.x) + MathUtils.square(this.y - v.y) + MathUtils.square(this.z - v.z);
	}

	public float angle(@NonNull Vector3f other) {
		double dot = MathUtils.constrainDoubleToRange(dot(other) / (length() * other.length()), -1.0, 1.0);

		return (float) Math.acos(dot);
	}

	public double dot(@NonNull Vector3f other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public Vector3f crossProduct(@NonNull Vector3f o) {
		float newX = this.y * o.z - o.y * this.z;
		float newY = this.z * o.x - o.z * this.x;
		float newZ = this.x * o.y - o.x * this.y;

		this.x = newX;
		this.y = newY;
		this.z = newZ;
		return this;
	}

	public Vector3f getCrossProduct(@NonNull Vector3f o) {
		float x = this.y * o.z - o.y * this.z;
		float y = this.z * o.x - o.z * this.x;
		float z = this.x * o.y - o.x * this.y;
		return new Vector3f(x, y, z);
	}

	public Vector3f midpoint(@NonNull Vector3f other) {
		x = (x + other.x) / 2;
		y = (y + other.y) / 2;
		z = (z + other.z) / 2;
		return this;
	}

	public Vector3f getMidpoint(@NonNull Vector3f other) {
		float x = (this.x + other.x) / 2;
		float y = (this.y + other.y) / 2;
		float z = (this.z + other.z) / 2;
		return new Vector3f(x, y, z);
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
