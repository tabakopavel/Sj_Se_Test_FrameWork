package com.epam.framework.utils;


import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Pavel_Tabako on 10/3/2017.
 */
public class Propertiess {

    private static FileInputStream fis;
    private static Properties properties = new Properties();
    private static Propertiess propertiess=null;

    private Propertiess() {

    }

    public static void init() {
        if (propertiess == null) {
            propertiess = new Propertiess();
        }
        try {
            File file = new File(propertiess.getClass().getClassLoader().getResource("config.properties").getFile());
            fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Propertiess.getNamesProp();
    }
    @SuppressWarnings("unchecked")
    public static void getNamesProp() {
        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            System.setProperty(key, properties.getProperty(key));
            Reporter.log(key + " - " + properties.getProperty(key), 2, true);
        }
    }

}
