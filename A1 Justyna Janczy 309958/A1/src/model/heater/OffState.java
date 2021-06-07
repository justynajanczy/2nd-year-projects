package model.heater;

public class OffState implements HeaterState
{
    static final int POWER = 0;

    public OffState()
    {
        System.out.println("PowerState: " + POWER);
    }

    @Override
    public void turnUp(Heater heater)
    {
        heater.setPowerState(new Power1State());
    }

    @Override
    public void turnDown(Heater heater)
    {
        //nothing
    }

    @Override
    public int getPower()
    {
        return POWER;
    }
}
