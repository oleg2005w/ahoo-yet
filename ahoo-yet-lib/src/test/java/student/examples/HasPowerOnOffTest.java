package student.examples;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.examples.devices.HasPowerOnOff;
import student.examples.devices.VacuumCleaner;

public class HasPowerOnOffTest {

    private HasPowerOnOff hasPowerOnOff;
    @BeforeEach
    public void setup() {
        hasPowerOnOff = new VacuumCleaner();
    }
    @Test
    public void testSwithOFF(){
        hasPowerOnOff.switchOff();
        Assert.assertFalse(hasPowerOnOff.isOn());
    }
    @Test
    public void testSwithON(){
        hasPowerOnOff.switchOn();
        Assert.assertTrue(hasPowerOnOff.isOn());
    }
}
