package net.novatech.library.utils;

import javax.management.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.*;
import java.io.File;
import java.lang.management.ManagementFactory;

public enum SystemOS {

	WINDOWS("windows"), LINUX("linux"), OSX("osx"), UNKNOWN("universal");

	private final String checkedName;

	SystemOS(String checkedName) {
		this.checkedName = checkedName;
	}

	public String getCheckedName() {
		return checkedName;
	}

	public static final SystemOS CURRENT_OS;
	public static final String SYSTEM_NAME;
	public static final String CURRENT_USER;
	public static final int TOTAL_MEMORY;

	public static final int SUGGESTED_MEMORY;

	public static final String PATH_SEPARATOR = File.pathSeparator;
	public static final String FILE_SEPARATOR = File.separator;
	public static final String LINE_SEPARATOR = System.lineSeparator();

	public static final String ENCODING = System.getProperty("sun.jnu.encoding", Charset.defaultCharset().name());
	public static final String SYSTEM_VERSION = System.getProperty("os.version");
	public static final String SYSTEM_ARCHITECTURE;

	public static final Pattern INVALID_RESOURCE_CHARACTERS;
	private static final String[] INVALID_RESOURCE_BASENAMES;
	private static final String[] INVALID_RESOURCE_FULLNAMES;

	static {
		String name = System.getProperty("os.name").toLowerCase(Locale.US);
		if(name.contains("win")) {
			CURRENT_OS = WINDOWS;
		} else if(name.contains("mac")) {
			CURRENT_OS = OSX;
		} else if(name.contains("solaris") || name.contains("linux") || name.contains("unix") || name.contains("sunos")) {
			CURRENT_OS = LINUX;
		} else {
			CURRENT_OS = UNKNOWN;
		}
		
		SYSTEM_NAME = name;
		CURRENT_USER = System.getProperty("user.name");
		TOTAL_MEMORY = getTotalPhysicalMemorySize().map(bytes -> (int) (bytes / 1024 / 1024)).orElse(1024);
		SUGGESTED_MEMORY = (int) (Math.round(1.0 * TOTAL_MEMORY / 4.0 / 128.0) * 128);

		String arch = System.getProperty("sun.arch.data.model");
		if(arch == null)
			arch = System.getProperty("os.arch");
		SYSTEM_ARCHITECTURE = arch;
		if(CURRENT_OS == WINDOWS) {
			INVALID_RESOURCE_CHARACTERS = Pattern.compile("[/\"<>|?*:\\\\]");
			INVALID_RESOURCE_BASENAMES = new String[] { "aux", "com1", "com2", "com3", "com4", "com5", "com6", "com7",
					"com8", "com9", "con", "lpt1", "lpt2", "lpt3", "lpt4", "lpt5", "lpt6", "lpt7", "lpt8", "lpt9",
					"nul", "prn" };
			Arrays.sort(INVALID_RESOURCE_BASENAMES);
			INVALID_RESOURCE_FULLNAMES = new String[] { "clock$" };
		} else {
			INVALID_RESOURCE_CHARACTERS = null;
			INVALID_RESOURCE_BASENAMES = null;
			INVALID_RESOURCE_FULLNAMES = null;
		}
	}

	private static Optional<Long> getTotalPhysicalMemorySize() {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			Object attribute = mBeanServer.getAttribute(new ObjectName("java.lang", "type", "OperatingSystem"),
					"TotalPhysicalMemorySize");
			if(attribute instanceof Long) {
				return Optional.of((Long) attribute);
			}
		} catch(JMException e) {
			return Optional.empty();
		}
		return Optional.empty();
	}

	public static void forceGC() {
		System.gc();
		System.runFinalization();
		System.gc();
	}

	public static Path getWorkingDirectory(String folder) {
		String home = System.getProperty("user.home", ".");
		switch (SystemOS.CURRENT_OS) {
		case LINUX:
			return Paths.get(home, "." + folder);
		case WINDOWS:
			String appdata = System.getenv("APPDATA");
			return Paths.get(appdata == null ? home : appdata, "." + folder);
		case OSX:
			return Paths.get(home, "Library", "Application Support", folder);
		default:
			return Paths.get(home, folder);
		}
	}

	public static boolean isNameValid(String name) {
		if(name.equals(".") || name.equals("..") || name.indexOf('/') == 0 || name.indexOf('\0') >= 0)
			return false;
		if(CURRENT_OS == WINDOWS) {
			final int length = name.length();
			if(length == 0)
				return false;
			final char lastChar = name.charAt(length - 1);
			if(lastChar == '.')
				return false;
			if(Character.isWhitespace(lastChar))
				return false;
			int dot = name.indexOf('.');
			String basename = dot == -1 ? name : name.substring(0, dot);
			if(Arrays.binarySearch(INVALID_RESOURCE_BASENAMES, basename.toLowerCase()) >= 0)
				return false;
			if(Arrays.binarySearch(INVALID_RESOURCE_FULLNAMES, name.toLowerCase()) >= 0)
				return false;
			if(INVALID_RESOURCE_CHARACTERS != null && INVALID_RESOURCE_CHARACTERS.matcher(name).find())
				return false;
		}

		return true;
	}
}