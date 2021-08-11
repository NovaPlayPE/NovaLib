package net.novatech.library.net;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.io.Files;

public class CloudflareUtils {
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	public static void clearCache(CloudflareConfig cfg, String[] urls) {
		executor.submit(() -> {
			URL url = NetworkUtils.toURL("https://api.cloudflare.com/client/v4/zones/"+cfg.zoneId+"/purge_cache");
			JSONArray array = new JSONArray();
			for(String urll : urls) {
				array.put("http://" + cfg.cachedUrl + urll);
				if(cfg.httpCache) {
					array.put("http://" + cfg.cachedUrl + urll);
				}
			}
			JSONObject obj = new JSONObject();
			obj.put("files", array);
			try {
				String response = NetworkUtils.doPost(url, obj.toString(), "application/json", true, "Bearer " + cfg.key);
			} catch (IOException e) {}
		});
	}
	
	public static class CloudflareConfig {
		 public String zoneId;
		 public String key;
		 public String cachedUrl;
		 public boolean httpCache;
	}
	
}