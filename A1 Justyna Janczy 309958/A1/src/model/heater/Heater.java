package model.heater;
import mediator.ModelManager;

public class Heater
{
    //default power state = 3
    private HeaterState currentState = new Power3State(this);
    private ModelManager modelManager;

    public Heater(ModelManager modelManager)
    {
        this.modelManager = modelManager;
    }

    public void turnUp()
    {
        try {
            HeaterState old = currentState;
            currentState.turnUp(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        modelManager.heaterChangedState();
    }

    public void turnDown()
    {
        HeaterState old = currentState;
        currentState.turnDown(this);
        modelManager.heaterChangedState();
    }

    public int getPower()
    {
        return currentState.getPower();
    }

    public void setPowerState(HeaterState state)
    {
        currentState = state;
        modelManager.heaterChangedState();
    }
}
