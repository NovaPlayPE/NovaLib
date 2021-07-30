package net.novatech.library.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class NativeLibraryLoader {
	
	public static Map<SystemOS, String> libraries = new HashMap<SystemOS, String>(){{
		put(SystemOS.WINDOWS, "dll");
		put(SystemOS.LINUX, "so");
		put(SystemOS.OSX, "dylib");
	}};
	
	public boolean supports(String arch) {
		return SystemOS.SYSTEM_ARCHITECTURE.contains(arch);
	}
	
	public boolean load(String library, ClassLoader loader) {
		String ending = "." + libraries.get(SystemOS.CURRENT_OS);
		String name = SystemOS.SYSTEM_NAME.toLowerCase() + "_" + library + "_" + SystemOS.SYSTEM_ARCHITECTURE;
		try(InputStream so = getInput(name + ending, loader)){
			if(so == null) return false;
			File temp = File.createTempFile(name, ending);
			temp.deleteOnExit();
			
			try(OutputStream out = new FileOutputStream(temp)){
				copy(so, out);
			}
			
			System.load(temp.getPath());
			return true;
		}catch(Exception ex) {}
		return false;
	}
	
	private static InputStream getInput(String name, ClassLoader loader) {
		InputStream in = loader.getResourceAsStream(name);
		if(in == null) {
			try {
				in = new FileInputStream("./src/main/resources" + name);
			} catch(Exception ex) {}
		}
		return in;
	}
	
	private static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[8192];
		while(true) {
			int r = in.read(buf);
			if(r == -1) return;
			
			out.write(buf, 0, r);
		}
	}
	
}