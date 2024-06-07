package student.examples.devices;

import static student.examples.devices.PowerState.OFF;
import static student.examples.devices.PowerState.ON;

public class VacuumCleaner extends Device implements HasPowerOnOff, HasBattery{
    private final int MIN_CHARGE = 10;
    private int charge;
    private PowerState powerState;
    public VacuumCleaner(){
        init();
    }
    public VacuumCleaner(int id, String name){
        super(id, name);
        init();
    }
    private void init(){
        switchOff();
        setCharge(0);
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    @Override
    public boolean switchOn() {
        powerState = ON;
        return true;
    }

    @Override
    public boolean switchOff() {
        powerState = OFF;
        return true;
    }

    @Override
    public boolean isOn() {
        return powerState != OFF;
    }

    @Override
    public boolean charge() {
        setCharge(charge +5);
        return false;
    }

    @Override
    public boolean disCharge() {
        setCharge(charge - 5);
        return false;
    }

    @Override
    public boolean isCharge() {
        return charge >= MIN_CHARGE;
    }

    @Override
    public int getGharge() {
        return 0;
    }
}
