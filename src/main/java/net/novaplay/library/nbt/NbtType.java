package net.novaplay.library.nbt;

public enum NbtType {
	
	END(0),
	BYTE(1),
	SHORT(2),
	INT(3),
	LONG(4),
	FLOAT(5),
	DOUBLE(6),
	BYTE_ARRAY(7),
	STRING(8),
	LIST(9),
	COMPOUND(10),
	INT_ARRAY(11),
	LONG_ARRAY(12);
	
	NbtType(int id) {
		this.id = id;
	}
	
	private int id;
	
	public int getId() {
		return this.id;
	}
	
}