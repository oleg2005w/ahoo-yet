package student.examples;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import student.examples.devices.HasBattery;
import student.examples.devices.VacuumCleaner;

import java.sql.*;

public class ItterativeChargeTest {
    private Connection connection;
    private HasBattery hasBattery;
    @BeforeTest
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://192.168.222.129/test_data_db?user=postgres&password=qazwsx&ssl=false");
    }
    @BeforeTest
    public void setup1(){
        hasBattery = new VacuumCleaner(1, "Atom");
    }
    @Test
    public void testChsrge() throws SQLException {
        System.out.println(connection.getClass());
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM charge_test_data ORDER BY (id) ASC;");
        while (resultSet.next()){
            System.out.println("Number of scenario ====> " + resultSet.getString(1));
            System.out.println("Input data ------------> " + resultSet.getInt(2));
            System.out.println("Expected data ---------> " + resultSet.getInt(3));


            hasBattery.setCharge(resultSet.getInt(2));

            Assert.assertEquals(resultSet.getInt(3), hasBattery.getCharge());
        }
    }
}
