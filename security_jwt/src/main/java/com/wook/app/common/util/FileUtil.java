package com.wook.app.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

public class FileUtil {

	public static Resource downloadFile(String path) {
		
		File downloadFile = new File(path);
		InputStream is = null;
		
		try {
			is = FileUtils.openInputStream(downloadFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // try~catch end
		
		return new InputStreamResource(is);
		
	} // downloadFile() end
	
} // FileUtil end