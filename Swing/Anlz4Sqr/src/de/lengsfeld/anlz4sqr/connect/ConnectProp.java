package de.lengsfeld.anlz4sqr.connect;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConnectProp {
	private static Properties props;

	private ConnectProp() {
	}

	static {
		props = new Properties();
		try {
			props.load(new BufferedInputStream(new FileInputStream(
					"fs.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}
