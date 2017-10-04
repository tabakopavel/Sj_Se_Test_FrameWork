package com.epam.tests.base_test;

import com.epam.framework.utils.Propertiess;

import java.io.FileNotFoundException;

/**
 * Created by Pavel_Tabako on 10/3/2017.
 */
public class Testre {
    public static void main(String[] args) {


      //Testre testre = new Testre();
      //testre.init();

            Propertiess.init();


        System.out.println(System.getProperty("test.browser"));
    }

   public void  init(){
       String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
       System.out.println(getClass());
       System.out.println(getClass().getProtectionDomain());
       System.out.println(getClass().getProtectionDomain().getCodeSource());
       System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
       System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
    }
}
