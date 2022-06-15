package net.novatech.library.math.vector;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.novatech.library.math.ConversionUtil;
import net.novatech.library.math.MathUtils;

public class Vector3d extends Vector3<Double> implements Vector<Vector3d>, Cloneable {

	public Vector3d(@NonNull double x, @NonNull double y, @NonNull double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Vector3d) {
			Vector3d v = (Vector3d) obj;
			return this.x == v.x && this.y == v.y && this.z == v.z;
		}
		return false;
	}

	public Vector3d add(double value) {
		this.x += value;
		this.y += value;
		this.z += value;
		return this;
	}

	public Vector3d add(@NonNull Vector3d v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3d subtract(double value) {
		this.x -= value;
		this.y -= value;
		this.z -= value;
		return this;
	}

	public Vector3d subtract(@NonNull Vector3d v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3d multiply(double value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	public Vector3d multiply(@NonNull Vector3d v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
	}

	public Vector3d divide(double value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		return this;
	}

	public Vector3d divide(@NonNull Vector3d v) {
		this.x /= v.x;
		this.y /= v.y;
		this.z /= v.z;
		return this;
	}

	public Vector3d copy(@NonNull Vector3d v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public Vector3d normalize() {
		double length = length();
		this.x /= length;
		this.y /= length;
		this.z /= length;
		return this;
	}

	public Vector3d zero() {
		this.x = 0D;
		this.y = 0D;
		this.z = 0D;
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

	public float angle(@NonNull Vector3d other) {
		double dot = MathUtils.constrainDoubleToRange(dot(other) / (length() * other.length()), -1.0, 1.0);

		return (float) Math.acos(dot);
	}

	public double dot(@NonNull Vector3d other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public Vector3d crossProduct(@NonNull Vector3d o) {
		double newX = this.y * o.z - o.y * this.z;
		double newY = this.z * o.x - o.z * this.x;
		double newZ = this.x * o.y - o.x * this.y;

		this.x = newX;
		this.y = newY;
		this.z = newZ;
		return this;
	}

	public Vector3d getCrossProduct(@NonNull Vector3d o) {
		double x = this.y * o.z - o.y * this.z;
		double y = this.z * o.x - o.z * this.x;
		double z = this.x * o.y - o.x * this.y;
		return new Vector3d(x, y, z);
	}

	public Vector3d midpoint(@NonNull Vector3d other) {
		x = (x + other.x) / 2;
		y = (y + other.y) / 2;
		z = (z + other.z) / 2;
		return this;
	}

	public Vector3d getMidpoint(@NonNull Vector3d other) {
		double x = (this.x + other.x) / 2;
		double y = (this.y + other.y) / 2;
		double z = (this.z + other.z) / 2;
		return new Vector3d(x, y, z);
	}

	public Vector3f asFloat() {
		Vector3f vec = new Vector3f(ConversionUtil.floatFromDouble(getX()), ConversionUtil.floatFromDouble(getY()), ConversionUtil.floatFromDouble(getZ()));
		return vec;
	}

	public Vector3i asInt() {
		Vector3i vec = new Vector3i(ConversionUtil.intFromDouble(getX()), ConversionUtil.intFromDouble(getY()), ConversionUtil.intFromDouble(getZ()));
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
