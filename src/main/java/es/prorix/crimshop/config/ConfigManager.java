package es.prorix.crimshop.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    public static void cargarProperties() {
        String[] files = {
            "productos.properties"
        };
        properties.clear();

        for (String filename : files) {
            try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(filename)) {
                if (input == null) {
                    System.err.println("Archivo de propiedades no encontrado: " + filename);
                    continue;
                }

                try (InputStreamReader isr = new InputStreamReader(input, "UTF-8")) {
                    properties.load(isr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void initialize() {
    }
}
