package net.novatech.library.math;

import lombok.Getter;
import lombok.Setter;

public class BoundingBox implements Cloneable {

	@Getter
	private double minX;
	@Getter
	private double minY;
	@Getter
	private double minZ;
	@Getter
	private double maxX;
	@Getter
	private double maxY;
	@Getter
	private double maxZ;

	public BoundingBox() {
		this(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	}

	public BoundingBox(Vector3d v1, Vector3d v2) {
		this(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z);
	}

	public BoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.resize(x1, y1, z1, x2, y2, z2);
	}

	public BoundingBox resize(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.minX = Math.min(x1, x2);
		this.minY = Math.min(y1, y2);
		this.minZ = Math.min(z1, z2);
		this.maxX = Math.max(x1, x2);
		this.maxY = Math.max(y1, y2);
		this.maxZ = Math.max(z1, z2);
		return this;
	}

	public Vector3d getMin() {
		return new Vector3d(this.minX, this.minY, this.minZ);
	}

	public Vector3d getMax() {
		return new Vector3d(this.maxX, this.maxY, this.maxZ);
	}

	public double getWidthX() {
		return (this.maxX - this.minX);
	}

	public double getWidthZ() {
		return (this.maxZ - this.minZ);
	}

	public double getHeight() {
		return (this.maxY - this.minY);
	}

	public double getCenterX() {
		return (this.minX + this.getWidthX() * 0.5D);
	}

	public double getCenterZ() {
		return (this.minZ + this.getWidthZ() * 0.5D);
	}

	public double getCenterY() {
		return (this.minY + this.getHeight() * 0.5D);
	}

	public double getVolume() {
		return (this.getHeight() * this.getWidthX() * this.getWidthZ());
	}

	public Vector3d getCenter() {
		return new Vector3d(this.getCenterX(), this.getCenterY(), this.getCenterZ());
	}

	public BoundingBox copy(BoundingBox b) {
		return this.resize(b.getMinX(), b.getMinY(), b.getMinZ(), b.getMaxX(), b.getMaxY(), b.getMaxZ());
	}

	public BoundingBox expand(Vector3d vec) {
		return this.expand(vec.getX(), vec.getY(), vec.getZ());
	}
	
	public BoundingBox expand(double x, double y, double z) {
		return this.expand(x, y, z, x, y, z);
	}
	
	public BoundingBox expand(double distance) {
		return this.expand(distance, distance, distance, distance, distance, distance);
	}
	
	public BoundingBox expand(Vector3d vec, double expansion) {
		return this.expand(vec.getX(), vec.getY(), vec.getZ(), expansion);
	}
	
	public BoundingBox expand(double dirX, double dirY, double dirZ, double expansion) {
		if (expansion == 0.0D) return this;
		if (dirX == 0.0D && dirY == 0.0D && dirZ == 0.0D) return this;
		double negativeX = (dirX < 0.0D ? (-dirX * expansion) : 0.0D);
		double negativeY = (dirY < 0.0D ? (-dirY * expansion) : 0.0D);
		double negativeZ = (dirZ < 0.0D ? (-dirZ * expansion) : 0.0D);
		double positiveX = (dirX > 0.0D ? (dirX * expansion) : 0.0D);
		double positiveY = (dirY > 0.0D ? (dirY * expansion) : 0.0D);
		double positiveZ = (dirZ > 0.0D ? (dirZ * expansion) : 0.0D);
		return this.expand(negativeX, negativeY, negativeZ, positiveX, positiveY, positiveZ);
	}
	
	public BoundingBox expand(double negativeX, double negativeY, double negativeZ, double positiveX, double positiveY, double positiveZ) {
		if (negativeX == 0.0D && negativeY == 0.0D && negativeZ == 0.0D && positiveX == 0.0D && positiveY == 0.0D
				&& positiveZ == 0.0D) {
			return this;
		}
		double newMinX = this.minX - negativeX;
		double newMinY = this.minY - negativeY;
		double newMinZ = this.minZ - negativeZ;
		double newMaxX = this.maxX + positiveX;
		double newMaxY = this.maxY + positiveY;
		double newMaxZ = this.maxZ + positiveZ;

		if (newMinX > newMaxX) {
			double centerX = this.getCenterX();
			if (newMaxX >= centerX) {
				newMinX = newMaxX;
			} else if (newMinX <= centerX) {
				newMaxX = newMinX;
			} else {
				newMinX = centerX;
				newMaxX = centerX;
			}
		}
		if (newMinY > newMaxY) {
			double centerY = this.getCenterY();
			if (newMaxY >= centerY) {
				newMinY = newMaxY;
			} else if (newMinY <= centerY) {
				newMaxY = newMinY;
			} else {
				newMinY = centerY;
				newMaxY = centerY;
			}
		}
		if (newMinZ > newMaxZ) {
			double centerZ = this.getCenterZ();
			if (newMaxZ >= centerZ) {
				newMinZ = newMaxZ;
			} else if (newMinZ <= centerZ) {
				newMaxZ = newMinZ;
			} else {
				newMinZ = centerZ;
				newMaxZ = centerZ;
			}
		}
		return this.resize(newMinX,newMinY,newMinZ,newMaxX,newMaxY,newMaxZ);
	}
	
	public BoundingBox expandDirectional(Vector3d vec) {
		return this.expand(vec.getX(), vec.getY(), vec.getZ(), 1.0D);
	}
	
	public BoundingBox expandDirectional(double x, double y, double z) {
		return this.expand(x, y, z, 1.0D);
	}
	
	public BoundingBox union(double posX, double posY, double posZ) {
		double newMinX = Math.min(this.minX, posX);
		double newMinY = Math.min(this.minY, posY);
		double newMinZ = Math.min(this.minZ, posZ);
		double newMaxX = Math.max(this.maxX, posX);
		double newMaxY = Math.max(this.maxY, posY);
		double newMaxZ = Math.max(this.maxZ, posZ);
		if (newMinX == this.minX && newMinY == this.minY && newMinZ == this.minZ && newMaxX == this.maxX
				&& newMaxY == this.maxY && newMaxZ == this.maxZ) {
			return this;
		}
		return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
	}

	public BoundingBox union(Vector3d position) {
		return this.union(position.getX(), position.getY(), position.getZ());
	}

	public BoundingBox union(BoundingBox other) {
		if (this.contains(other))
			return this;
		double newMinX = Math.min(this.minX, other.minX);
		double newMinY = Math.min(this.minY, other.minY);
		double newMinZ = Math.min(this.minZ, other.minZ);
		double newMaxX = Math.max(this.maxX, other.maxX);
		double newMaxY = Math.max(this.maxY, other.maxY);
		double newMaxZ = Math.max(this.maxZ, other.maxZ);
		return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
	}

	public BoundingBox intersection(BoundingBox other) {
		double newMinX = Math.max(this.minX, other.minX);
		double newMinY = Math.max(this.minY, other.minY);
		double newMinZ = Math.max(this.minZ, other.minZ);
		double newMaxX = Math.min(this.maxX, other.maxX);
		double newMaxY = Math.min(this.maxY, other.maxY);
		double newMaxZ = Math.min(this.maxZ, other.maxZ);
		return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
	}
	
	public boolean intersects(BoundingBox other) {
		return (this.getMinY() < other.getMaxY() && this.getMaxY() > other.getMinY())
				&& (this.getMinZ() < other.getMaxZ() && this.getMaxZ() > other.getMinZ())
				&& (this.getMinX() < other.getMaxX() && this.getMaxX() > other.getMinX());
	}

	public BoundingBox shift(double shiftX, double shiftY, double shiftZ) {
		if (shiftX == 0.0D && shiftY == 0.0D && shiftZ == 0.0D)
			return this;
		return this.resize(this.minX + shiftX, this.minY + shiftY, this.minZ + shiftZ, this.maxX + shiftX,
				this.maxY + shiftY, this.maxZ + shiftZ);
	}

	public BoundingBox shift(Vector3d shift) {
		return this.shift(shift.getX(), shift.getY(), shift.getZ());
	}
	
	private boolean overlaps(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ
				&& this.maxZ > minZ;
	}
	public boolean overlaps(BoundingBox other) {
		return this.overlaps(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
	}
	
	public boolean overlaps(Vector3d min, Vector3d max) {
		double x1 = min.getX();
		double y1 = min.getY();
		double z1 = min.getZ();
		double x2 = max.getX();
		double y2 = max.getY();
		double z2 = max.getZ();
		return this.overlaps(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2),
				Math.max(z1, z2));
	}
	
	public boolean contains(Vector3d min, Vector3d max) {
		double x1 = min.getX();
		double y1 = min.getY();
		double z1 = min.getZ();
		double x2 = max.getX();
		double y2 = max.getY();
		double z2 = max.getZ();
		return this.contains(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2),
				Math.max(z1, z2));
	}
	
	public boolean contains(BoundingBox other) {
		return this.contains(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
	}
	
	private boolean contains(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		return this.minX <= minX && this.maxX >= maxX && this.minY <= minY && this.maxY >= maxY && this.minZ <= minZ
				&& this.maxZ >= maxZ;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BoundingBox))
			return false;
		BoundingBox other = (BoundingBox) obj;
		if (Double.doubleToLongBits(maxX) != Double.doubleToLongBits(other.maxX))
			return false;
		if (Double.doubleToLongBits(maxY) != Double.doubleToLongBits(other.maxY))
			return false;
		if (Double.doubleToLongBits(maxZ) != Double.doubleToLongBits(other.maxZ))
			return false;
		if (Double.doubleToLongBits(minX) != Double.doubleToLongBits(other.minX))
			return false;
		if (Double.doubleToLongBits(minY) != Double.doubleToLongBits(other.minY))
			return false;
		if (Double.doubleToLongBits(minZ) != Double.doubleToLongBits(other.minZ))
			return false;
		return true;
	}

	public BoundingBox clone() {
		try {
			return (BoundingBox) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
}