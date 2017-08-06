package io.github.pluginscdtribe.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {
	public static void downloadAsync(URL url, String name, File folder, Callback callback) {
		new Thread(new Runnable(){
			@Override
			public void run() {
				downloadUrl(url,name,folder);
				callback.run();
			}
		} ).start();
	}
	
	public static boolean downloadUrl(URL url, String name, File folder) {
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13");
			conn.setConnectTimeout(3000);
			InputStream ins = conn.getInputStream();
			byte[] data;
			{
				byte[] buffer = new byte[1024];
				int length = 0;
				ByteArrayOutputStream ba = new ByteArrayOutputStream();
				while ((length = ins.read(buffer)) != -1) {
					ba.write(buffer, 0, length);
				}
				ba.close();
				data = ba.toByteArray();
			}
			if (!folder.exists()) {
				folder.mkdir();
			}
			File target = new File(folder, name);
			try (FileOutputStream fos = new FileOutputStream(target)) {
				fos.write(data);
			}
			ins.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean copyFile(File inFile, File outFile) {
		return org.bukkit.util.FileUtil.copy(inFile, outFile);
	}
}
