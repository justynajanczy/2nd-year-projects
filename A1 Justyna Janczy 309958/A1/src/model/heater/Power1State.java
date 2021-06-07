package model.heater;

public class Power1State implements HeaterState
{
    private final int POWER = 1;

    public Power1State()
    {
        System.out.println("PowerState: " + POWER);
    }
    @Override
    public void turnUp(Heater heater)
    {
        heater.setPowerState(new Power2State());
    }

    @Override
    public void turnDown(Heater heater)
    {
        heater.setPowerState(new OffState());
    }

    @Override
    public int getPower()
    {
        return POWER;
    }
}