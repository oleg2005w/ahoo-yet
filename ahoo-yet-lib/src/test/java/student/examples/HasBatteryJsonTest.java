package student.examples;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import student.examples.devices.HasBattery;
import student.examples.devices.VacuumCleaner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HasBatteryJsonTest {
    private HasBattery hasBattery;
    @BeforeTest(alwaysRun = true)
    public void setup(){
        hasBattery = new VacuumCleaner(1, "Atom");
        hasBattery.setCharge(50);
    }
    @DataProvider(name = "test from json")
    public String[] jsonreader() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src\\test\\resources\\data.json");
        Object object1 = jsonParser.parse(reader);

        JSONObject jsonObject = (JSONObject) object1;
        JSONArray array1 = (JSONArray) jsonObject.get("dataset");

        String arr[] = new String[array1.size()];

        for (int i = 0; i < array1.size(); i++) {
            JSONObject dataset = (JSONObject) array1.get(i);
            String input = (String)  dataset.get("input");
            String expected =(String) dataset.get("expected");

            arr[i] = input + "," + expected;
        }

        return arr;
    }
    @Test(dataProvider = "test from json")
    public void reader (String data){
        String user[] = data.split(",");
        System.out.println("Input data is -->" + user[0]);
        System.out.println("Expected data is -->" + user[1]);
        hasBattery.setCharge(Integer.valueOf(user[0]));

        Assert.assertEquals(Integer.valueOf(user[1]), hasBattery.getCharge());
    }
}
