package com.epam.framework.utils;


import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Pavel_Tabako on 10/3/2017.
 */
public class Propertiess {

    private static FileInputStream fis;
    private static Properties properties = new Properties();

    public static void init() {
        try {
            fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
            properties.load(fis);

        } catch (IOException e) {
            System.out.println("dhgfv");
            e.printStackTrace();
            Assert.fail(e.getMessage());
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
