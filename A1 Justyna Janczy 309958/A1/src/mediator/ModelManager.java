package mediator;

import model.heater.Heater;
import model.temperature.Temperature;
import model.temperature.TemperatureList;
import util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements HeaterModel, TemperatureModel, PropertyChangeSubject
{
    private TemperatureList temperatureList;
    private PropertyChangeSupport pcs;
    private int lowest = -5, highest = 25;

    private Heater heater;
    private int heaterPower;

    public ModelManager()
    {
        temperatureList = new TemperatureList();
        pcs = new PropertyChangeSupport(this);
        heater = new Heater(this);
        heaterPower = heater.getPower();
    }

    public TemperatureList getTemperatureList()
    {
        return temperatureList;
    }

    @Override
    public void addTemperature(String id, double t)
    {
        Temperature temperature = new Temperature(id, t);
        Temperature old = getLastInsertedTemperature();
        this.temperatureList.addTemperature(temperature);
        if (old != null && old.getValue() != temperature.getValue())
        {
            pcs.firePropertyChange("Temperature Changed", old, temperature);
        }
    }

    @Override
    public void heaterChangedState()
    {
        if(heaterPower != heater.getPower())
        {
            pcs.firePropertyChange("HeaterChangedTemp", heaterPower, heater.getPower());
            heaterPower = heater.getPower();
        }
    }

    @Override
    public void setCriticalValues(String lowest, String highest)
    {
        this.lowest = Integer.parseInt(lowest);
        this.highest = Integer.parseInt(highest);
    }

    @Override
    public void setLowestCriticalValue(String lowest)
    {
        this.lowest = Integer.parseInt(lowest);
    }

    @Override
    public int getLowestCriticalValue()
    {
        return lowest;
    }

    @Override
    public void setHighestCriticalValue(String highest)
    {
        this.highest = Integer.parseInt(highest);
    }

    @Override
    public int getHighestCriticalValue()
    {
        return highest;
    }

    @Override
    public int getPower()
    {
        return heater.getPower();
    }

    @Override
    public void turnDownHeater()
    {
        heater.turnDown();
    }

    @Override
    public void turnUpHeater()
    {
        heater.turnUp();
    }

    @Override
    public Temperature getLastInsertedTemperature()
    {
        return temperatureList.getLastTemperature(null);
    }

    @Override
    public Temperature getLastInsertedTemperature(String id)
    {
        return temperatureList.getLastTemperature(id);
    }

    //observers subject methods
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(listener);
    }


}
