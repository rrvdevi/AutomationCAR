package com.automation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StepDef {
    public static List<HashMap<String,String>> list = new ArrayList<>();
    List load_values = new ArrayList();
    List values = new ArrayList();
    HashMap<String,String> nullValues =new HashMap<String,String>();
    @Given("^Load Car Input Values From Text File$")
    public void load_Car_Input_Values_From_Text_File() throws Throwable {
      LoadText loadText = new LoadText();
        load_values=loadText.loadTextValues();
    }

    @When("^Selenium Main Method execute$")
    public void selenium_Main_Method_execute() throws Throwable {
        ExecuteSelenium executeSelenium = new ExecuteSelenium();
        values=executeSelenium.executeMainMethod(load_values);
    }

    @Then("^Assert OutPut Text File values With capture Values$")
    public void assert_OutPut_Text_File_values_With_capture_Values() throws Throwable {

        String line;
        List<HashMap<String,String>> output_value= new ArrayList<>();
        BufferedReader inputStream = new BufferedReader(new FileReader("src/car_output.txt"));
        int i=1;
        while ((line = inputStream.readLine()) != null) {
            String[] aux = line.split(",");
            if(i>1){
                HashMap<String, String> outPut = new HashMap<>();
                outPut.put("Registration", aux[0]);
                outPut.put("Make", aux[1]);
                outPut.put("Model", aux[2]);
                outPut.put("Color", aux[3]);
                outPut.put("Year", aux[4]);
                output_value.add(outPut);
            }
            i++;
        }

        for(int k=0;k<values.size();k++){
            HashMap<String,String> values1= new HashMap<>();
            values1= (HashMap<String, String>) values.get(k);
            for(int l=0;l<output_value.size();l++){
                HashMap<String,String> values2= new HashMap<>();
                values2= (HashMap<String, String>) output_value.get(l);
                if(values1.get("Make").equalsIgnoreCase(values2.get("Make"))) {
                    Assert.assertEquals(values1.get("Registration"), values2.get("Registration"));
                    Assert.assertEquals(values1.get("Make"), values2.get("Make"));
                    Assert.assertEquals(values1.get("Model"), values2.get("Model"));
                    Assert.assertEquals(values1.get("Color"), values2.get("Color"));
                    Assert.assertEquals(values1.get("Year"), values2.get("Year"));
                    break;
                }else if(values1.get("Make").equalsIgnoreCase("")){
                    nullValues.put("Registration",values1.get("Registration"));
                    nullValues.put("Make",values1.get("Make"));
                    nullValues.put("Model",values1.get("Model"));
                    nullValues.put("Color",values1.get("RegiColorstration"));
                    nullValues.put("Year",values1.get("Year"));
                    break;
                }
            }
        }

    }

    @Then("^Assert failed  scenario$")
    public void assert_failed_scenario() throws Throwable {
          Assert.fail(nullValues.get("Registration").toString()+"the registration number doesn't contain value");

    }

    private void checkingAssertNullcondition(HashMap<String, String> nullValues) {
        boolean value=false;
        if(nullValues.get("Registration").equalsIgnoreCase("")){
            value=true;
        }
      //  Assert.assertNotNull(nullValues.get("Registration").toString()+"the registration number doesn't contain value"+value);
    }


}
