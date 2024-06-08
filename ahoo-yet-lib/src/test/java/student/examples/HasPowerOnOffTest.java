package student.examples;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import student.examples.devices.HasPowerOnOff;
import student.examples.devices.VacuumCleaner;

public class HasPowerOnOffTest {

    private HasPowerOnOff hasPowerOnOff;
    @BeforeTest(alwaysRun = true)
    public void setup() {
        hasPowerOnOff = new VacuumCleaner();
    }
    @Test(groups = {"unit"})
    public void testSwithOFF(){
        hasPowerOnOff.switchOff();
        Assert.assertFalse(hasPowerOnOff.isOn());
    }
    @Test(groups = {"unit"})
    public void testSwithON(){
        hasPowerOnOff.switchOn();
        Assert.assertTrue(hasPowerOnOff.isOn());
    }
}
