package net.novatech.library.math;

import lombok.NonNull;

public class MathUtils {

	public static int floor(double num) {
		final int floor = (int) num;
		return floor == num ? floor : floor - (int) (Double.doubleToRawLongBits(num) >>> 63);
	}

	public static int ceil(final double num) {
		final int floor = (int) num;
		return floor == num ? floor : floor + (int) (~Double.doubleToRawLongBits(num) >>> 63);
	}

	public static int round(double num) {
		return floor(num + 0.5d);
	}

	public static double square(double num) {
		return num * num;
	}

	public static int toInt(Object object) {
		if (object instanceof Number) {
			return ((Number) object).intValue();
		}
		try {
			return Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static float toFloat(Object object) {
		if (object instanceof Number) {
			return ((Number) object).floatValue();
		}

		try {
			return Float.parseFloat(object.toString());
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static double toDouble(Object object) {
		if (object instanceof Number) {
			return ((Number) object).doubleValue();
		}

		try {
			return Double.parseDouble(object.toString());
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static long toLong(Object object) {
		if (object instanceof Number) {
			return ((Number) object).longValue();
		}

		try {
			return Long.parseLong(object.toString());
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static short toShort(Object object) {
		if (object instanceof Number) {
			return ((Number) object).shortValue();
		}

		try {
			return Short.parseShort(object.toString());
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}

	public static byte toByte(Object object) {
		if (object instanceof Number) {
			return ((Number) object).byteValue();
		}

		try {
			return Byte.parseByte(object.toString());
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}
	
	public static double constrainDoubleToRange(double value, double min, double max) {
		return Math.min(Math.max(value,min),max);
	}
	
	public static float constrainFloatToRange(float value, float min, float max) {
		return Math.min(Math.max(value,min),max);
	}
	
	public static int constrainIntToRange(int value, int min, int max) {
		return Math.min(Math.max(value,min),max);
	}
	
	public static boolean isFinite(int i) {
		return Math.abs(i) <= Integer.MAX_VALUE;
	}
	
	public static boolean isFinite(double d) {
		return Math.abs(d) <= Double.MAX_VALUE;
	}

	public static boolean isFinite(float f) {
		return Math.abs(f) <= Float.MAX_VALUE;
	}

	public static void checkFinite(int i, @NonNull String message) {
		if (!isFinite(i)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void checkFinite(double d, @NonNull String message) {
		if (!isFinite(d)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void checkFinite(float d, @NonNull String message) {
		if (!isFinite(d)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static boolean isInRange(int number, int begin, int end) {
		return isInRange(number, begin, end, false, false);
	}
	
	public static boolean isInRange(int number, int begin, int end, boolean closedBegin, boolean closedEnd) {
		if(closedBegin && closedEnd) {
			return number >= begin && number <= end;
		} else if(closedBegin && !closedEnd) {
			return number >= begin && number < end;
		} else if(!closedBegin && closedEnd) {
			return number > begin && number <= end;
		} else if(!(closedBegin && closedEnd)) {
			return number > begin && number < end;
		}
		return false;
	}
	
	public static boolean isInRange(double number, double begin, double end) {
		return isInRange(number, begin, end, false, false);
	}
	
	public static boolean isInRange(double number, double begin, double end, boolean closedBegin, boolean closedEnd) {
		if(closedBegin && closedEnd) {
			return number >= begin && number <= end;
		} else if(closedBegin && !closedEnd) {
			return number >= begin && number < end;
		} else if(!closedBegin && closedEnd) {
			return number > begin && number <= end;
		} else if(!(closedBegin && closedEnd)) {
			return number > begin && number < end;
		}
		return false;
	}
	
	public static boolean isInRange(float number, float begin, float end) {
		return isInRange(number, begin, end, false, false);
	}
	
	public static boolean isInRange(float number, float begin, float end, boolean closedBegin, boolean closedEnd) {
		if(closedBegin && closedEnd) {
			return number >= begin && number <= end;
		} else if(closedBegin && !closedEnd) {
			return number >= begin && number < end;
		} else if(!closedBegin && closedEnd) {
			return number > begin && number <= end;
		} else if(!(closedBegin && closedEnd)) {
			return number > begin && number < end;
		}
		return false;
	}

}