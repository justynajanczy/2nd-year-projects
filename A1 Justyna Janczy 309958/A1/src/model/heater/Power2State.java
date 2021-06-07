package model.heater;

public class Power2State implements HeaterState
{
    private final int POWER = 2;

    public Power2State()
    {
        System.out.println("PowerState: " + POWER);
    }
    @Override
    public void turnUp(Heater heater)
    {
        heater.setPowerState(new Power3State(heater));
    }

    @Override
    public void turnDown(Heater heater)
    {
        heater.setPowerState(new Power1State());
    }

    @Override
    public int getPower()
    {
        return POWER;
    }
}
