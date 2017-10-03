package com.epam.tests.base_test;

import com.epam.framework.core.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Pavel_Tabako on 10/3/2017.
 */
public class BaseTest {
    @BeforeMethod
    public void init() {
        Driver.init();
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
         Driver.clean();
    }
}
