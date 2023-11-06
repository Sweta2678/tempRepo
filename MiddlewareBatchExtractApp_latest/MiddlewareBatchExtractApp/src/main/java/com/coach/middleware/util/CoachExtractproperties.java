package com.coach.middleware.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class CoachExtractproperties {
	private String path = null;
	public static Properties properties = new Properties();

	public CoachExtractproperties() {

	}
	public CoachExtractproperties(String path) {
		this.path = path;
		loadProperties();
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}

	public void loadProperties() {
		try {
			File file = new File(path);// "src/main/resources/com/coach/middleware/batch/properties/LPskuproperties.properties"
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}