package com.epam.tests.base_test;

import com.epam.framework.core.driver.Driver;
import com.epam.tests.base_test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by Pavel_Tabako on 10/3/2017.
 */
public class TestClass extends BaseTest {



    @Test
    public void test(){
       WebDriver driver = Driver.getDriver();
        driver.get("https://www.onliner.by/");

        if(driver.findElements(By.xpath(".//*[@id='container']/div/div[2]/header/div[2]/div/div[1]/a/img")).size()!=0){
            System.out.println("true");
        }
    }
}
