package com.taotao.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FileUtil {

	public static File getCreatedFile(String path, String name) throws IOException {

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		File result = new File(file, name);
		if (!result.exists()) {
			result.createNewFile();
		}
		return result;
	}

	public static String writeFile(String path, String name, InputStream is) throws IOException {
		String id = UUID.randomUUID().toString();
		name = id + "_" + name;
		File file = getCreatedFile(path, name);
		FileOutputStream fos = new FileOutputStream(file);
		try {
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = is.read(bytes)) != -1) {
				fos.write(bytes, 0, len);
			}
		} finally {

			fos.close();
		}

		return name;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("F:\\in.txt");
		writeFile("D:\\test\\tmp", "a.txt", new FileInputStream(file));
	}

	public static byte[] getByte(String path, String name) throws IOException {
		File file = new File(path, name);
		if (!file.exists()) {
			return null;
		}
		FileInputStream fis = new FileInputStream(file);
		try {
			if (fis.available() > 20 * 1024 * 1024) {

				return null;
			}
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			return bytes;
		} finally {
			fis.close();
		}
	}
	
	
}
