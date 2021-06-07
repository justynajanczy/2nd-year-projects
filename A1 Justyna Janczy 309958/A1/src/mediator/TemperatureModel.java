package mediator;

import model.temperature.Temperature;
import util.PropertyChangeSubject;

public interface TemperatureModel extends PropertyChangeSubject
{
    void addTemperature(String id, double t);
    Temperature getLastInsertedTemperature();
    Temperature getLastInsertedTemperature(String id);
    void setCriticalValues(String lowest, String highest);
    void setLowestCriticalValue(String lowest);
    int getLowestCriticalValue();
    void setHighestCriticalValue(String highest);
    int getHighestCriticalValue();
}
