package es.prorix.crimshop.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();


    public static void cargarProperties() {
        String[] files = {
            "productos" + ".properties",
        };
    
        properties.clear();
    
        for (String filename : files) {
            String path;
            path = "src/main/resources/properties" + filename;

    
            File file = new File(path);
    
            if (!file.exists()) {
                System.err.println("Archivo de idioma no encontrado: " + path);
                continue;
            }
    
            try (FileInputStream input = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(input, "UTF-8")) {
                properties.load(isr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    

    /**
     * Metodo que inizializa la clase
     */
    public static void initialize() {

    }
}
