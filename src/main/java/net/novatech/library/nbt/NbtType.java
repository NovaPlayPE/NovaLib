package net.novatech.library.nbt;

import net.novatech.library.nbt.tags.*;

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
	
	public Class<? extends Tag> getClassInstance(){
		switch(this.getId()) {
		case 0:
			return null;
		case 1:
			return ByteTag.class;
		case 2:
			return ShortTag.class;
		case 3:
			return IntTag.class;
		case 4:
			return LongTag.class;
		case 5:
			return FloatTag.class;
		case 6:
			return DoubleTag.class;
		case 7:
			return ByteArrayTag.class;
		case 8:
			return StringTag.class;
		case 9:
			return ListTag.class;
		case 10:
			return CompoundTag.class;
		case 11:
			return IntArrayTag.class;
		case 12:
			return LongArrayTag.class;
		}
		return null;
	}
	
}