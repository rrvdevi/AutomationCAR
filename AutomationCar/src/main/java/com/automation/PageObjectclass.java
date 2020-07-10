package com.automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.HashMap;


public class PageObjectclass {
    @FindBy(how = How.NAME, using = "vrm")
    private WebElement freeCarCheck;
    @FindBy(how = How.XPATH, using = "//button[text()='Free Car Check']")
    private WebElement freeCarCheckPath;
    @FindBy(how = How.XPATH, using = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[1]/dd")
    private WebElement registration;

    @FindBy(how = How.XPATH, using = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[2]/dd")
    private WebElement make;

    @FindBy(how = How.XPATH, using = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[3]/dd")
    private WebElement model;

    @FindBy(how = How.XPATH, using = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[4]/dd")
    private WebElement color;

    @FindBy(how = How.XPATH, using = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[5]/dd")
    private WebElement year;

    WebDriver driver;
    public PageObjectclass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public void loadPage(String key){
        waitUntilName("vrm",driver);
        freeCarCheck.sendKeys(key);
        freeCarCheckPath.click();

    }

    public HashMap<String,String> getValuesFromScreen(String key){
        HashMap<String,String> car_values= new HashMap<>();
        waitUntilXpath( "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[1]/dd",driver);
        car_values.put("Registration",key);
        car_values.put("Make",make.getText().toString());
        car_values.put("Model",model.getText().toString());
        car_values.put("Color",color.getText().toString());
        car_values.put("Year",year.getText().toString());
       // ExecuteSelenium.personal_details_dataCapture.putAll(car_values);
        return car_values;
    }
    public static void waitUntilName(String inputElement,WebDriver fdriver) {
        try {
            WebDriverWait waitFor = new WebDriverWait(fdriver, 60);
            waitFor.until(ExpectedConditions.
                    //     visibilityOfElementLocated(By.name(inputElement)));
                            presenceOfElementLocated(By.name(inputElement)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilXpath(String inputElement,WebDriver fdriver) {
        try {
            WebDriverWait waitFor = new WebDriverWait(fdriver, 60);
            waitFor.until(ExpectedConditions.
                            presenceOfElementLocated(By.xpath(inputElement)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
