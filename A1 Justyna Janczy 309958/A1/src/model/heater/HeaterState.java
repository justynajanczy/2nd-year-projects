package model.heater;

public interface HeaterState
{
    void turnUp(Heater heater) throws InterruptedException;
    void turnDown(Heater heater);
    int getPower();
}
