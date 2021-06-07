package mediator;

import util.PropertyChangeSubject;

public interface HeaterModel extends PropertyChangeSubject
{
    int getPower();
    void turnDownHeater();
    void turnUpHeater();
    void heaterChangedState();
}
