package net.novatech.library.utils;

import java.util.HashMap;
import java.util.Map;

public class ConsoleColor {
	
	public static String BLACK = "\u001b[30m";
    public static String RED = "\u001b[31m";
    public static String GREEN = "\u001b[32m";
    public static String YELLOW = "\u001b[33m";
    public static String BLUE = "\u001b[34m";
    public static String MAGENTA = "\u001b[35m";
    public static String CYAN = "\u001b[36m";
    public static String WHITE = "\u001b[37m";
    
    public static String BOLD = "\u001b[1m";
    public static String RESET = "\u001b[0m";
    public static String RESET_BOLD = "\u001b[21m";
    public static String UNDERLINED = "\u001b[4m";
    
    public static String BLACK_BACKGROUND = "\u001b[38m";
    public static String RED_BACKGROUND = "\u001b[39m";
    public static String YELLOW_BACKGROUND = "\u001b[43m";
    public static String BLUE_BACKGROUND = "\u001b[44m";
    public static String MAGENTA_BACKGROUND = "\u001b[45m";
    public static String CYAN_BACKGROUND = "\u001b[46m";
    public static String WHITE_BACKGROUND = "\u001b[47m";
    
	private static Map<String, String> textFormats = new HashMap<String, String>() {
		{
			this.put("§a", ConsoleColor.GREEN);
			this.put("§b", ConsoleColor.CYAN);
			this.put("§c", ConsoleColor.RED);
			this.put("§d", ConsoleColor.MAGENTA);
			this.put("§e", ConsoleColor.YELLOW);
			this.put("§f", ConsoleColor.WHITE);
			this.put("§0", ConsoleColor.RESET);
			this.put("§1", ConsoleColor.BLUE);
			this.put("§2", ConsoleColor.GREEN);
			this.put("§3", ConsoleColor.CYAN);
			this.put("§4", ConsoleColor.RED);
			this.put("§5", ConsoleColor.MAGENTA);
			this.put("§6", ConsoleColor.YELLOW);
			this.put("§9", ConsoleColor.BLUE);
			this.put("§r", ConsoleColor.RESET);
			this.put("§l", ConsoleColor.BOLD);
			this.put("§n", ConsoleColor.UNDERLINED);
		}
	};
	
	private static Map<String, String> textFormatsToMc = new HashMap<String, String>(){
		{
			this.put(ConsoleColor.GREEN, "§a");
			this.put(ConsoleColor.CYAN, "§b");
			this.put(ConsoleColor.RED, "§c");
			this.put(ConsoleColor.MAGENTA, "§d");
			this.put(ConsoleColor.YELLOW, "§e");
			this.put(ConsoleColor.WHITE, "§f");
			this.put(ConsoleColor.RESET, "§0");
			this.put(ConsoleColor.BLUE, "§1");
			this.put(ConsoleColor.GREEN, "§2");
			this.put(ConsoleColor.CYAN, "§3");
			this.put(ConsoleColor.RED, "§4");
			this.put(ConsoleColor.MAGENTA, "§5");
			this.put(ConsoleColor.YELLOW, "§6");
			this.put(ConsoleColor.BLUE, "§7");
			this.put(ConsoleColor.RESET, "§8");
			this.put(ConsoleColor.BOLD, "§9");
			this.put(ConsoleColor.UNDERLINED, "§10");
		}
	};
	
	public static String removeColors(String message) {
		String[] list = new String[] { "a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"r", "l", "n" };
		for (String colors : list) {
			message = message.replaceAll("§" + colors, "");
		}
		return message;
	}

	public static String colorize(String message) {
		for (Map.Entry<String, String> map : textFormats.entrySet()) {
			message = message.replaceAll(map.getKey(), map.getValue());
		}
		return message;
	}
	
	public static String colorizeMC(String message) {
		for (Map.Entry<String, String> map : textFormatsToMc.entrySet()) {
			message = message.replaceAll(map.getKey(), map.getValue());
		}
		return message;
	}
	
}