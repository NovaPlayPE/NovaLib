package net.novatech.library.math.vector;

public interface Vector<T extends Vector<T>>{
	
	T add(T vector);
	T subtract(T vector);
	T multiply(T vector);
	T divide(T vector);
	T copy(T vector);
	T zero();
	T crossProduct(T vector);
}