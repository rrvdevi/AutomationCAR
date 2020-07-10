package com.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExecuteSelenium {
    List captured_values= new ArrayList();

    public static List<HashMap<String,String>> list = new ArrayList<>();

    public static  WebDriver cheromDriver = null;
    public List executeMainMethod(List text_values) {
        for(int i=0;i<text_values.size();i++) {
            try {
                final ChromeOptions options = new ChromeOptions();
                //options.addArguments("start-fullscreen");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                cheromDriver = new ChromeDriver();
                cheromDriver.manage().window().maximize();
                System.out.println(text_values.get(i));
                cheromDriver.get("https://cartaxcheck.co.uk/");
                String value = (String) text_values.get(i);
                PageObjectclass pageObjectclass = new PageObjectclass(cheromDriver);
                pageObjectclass.loadPage(value);
                HashMap<String,String> text_Values= new HashMap<>();
                        text_Values=  pageObjectclass.getValuesFromScreen(value);
                list.add(text_Values);
                cheromDriver.close();

            } finally {

            }
        }
        return list;
    }
}
