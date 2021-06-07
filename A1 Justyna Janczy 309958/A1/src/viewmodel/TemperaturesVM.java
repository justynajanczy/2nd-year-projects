package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import mediator.ModelManager;
import model.temperature.Temperature;

import java.beans.PropertyChangeEvent;

public class TemperaturesVM
{
    private StringProperty t1;
    private StringProperty t2;
    private StringProperty heater;
    private StringProperty outTemp;
    private StringProperty error;

    private ModelManager modelManager;

    public TemperaturesVM(ModelManager model)
    {
        modelManager = model;
        t1 = new SimpleStringProperty();
        t2 = new SimpleStringProperty();
        heater = new SimpleStringProperty(String.valueOf(modelManager.getPower()));
        outTemp = new SimpleStringProperty();
        error = new SimpleStringProperty();
        model.addPropertyChangeListener("Temperature Changed", evt -> updateTemperature(evt));
        model.addPropertyChangeListener("HeaterChangedTemp", evt -> updateHeater(evt));
    }

    public void updateTemperature(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->
        {
            Temperature t = (Temperature)evt.getNewValue();
            if(t.getId() == "t1")
            {
                t1.set(Math.round(t.getValue() * 10)/10.0 + "°C");
            }
            else if(t.getId() == "t2")
            {
                t2.set(Math.round(t.getValue() * 10)/10.0 + "°C");
            }
            else if(t.getId() == "out")
            {
                outTemp.set(Math.round(t.getValue() * 10)/10.0 + "°C");
            }

            if(modelManager.getLastInsertedTemperature("t1").getValue() > modelManager.getHighestCriticalValue() && modelManager.getLastInsertedTemperature("t2").getValue() > modelManager.getHighestCriticalValue())
            {
                error.set("Temperatures of both thermometers t1 and t2 are above the critical value of "+ modelManager.getHighestCriticalValue() + "°C");
            }
            else if(modelManager.getLastInsertedTemperature("t1").getValue() < modelManager.getLowestCriticalValue() && modelManager.getLastInsertedTemperature("t2").getValue() < modelManager.getLowestCriticalValue())
            {
                error.set("Temperatures of both thermometers t1 and t2 are below the critical value of " + modelManager.getLowestCriticalValue() + "°C");
            }
            else if(t.getValue() > modelManager.getHighestCriticalValue() && (t.getId() != "out"))
            {
                error.set("Temperature of thermometer " + t.getId() + " is above the critical value of " + modelManager.getHighestCriticalValue() + "°C");
            }
            else if(t.getValue() < modelManager.getLowestCriticalValue() && (t.getId() != "out"))
            {
                error.set("Temperature of thermometer " + t.getId() + " is below the critical value of " + modelManager.getLowestCriticalValue() + "°C");
            }
            else
            {
                error.set("");
            }
        });
    }

    public void updateHeater(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->
        {
            heater.set(evt.getNewValue().toString());
        });
    }

    public StringProperty t1Property()
    {
        return t1;
    }

    public StringProperty t2Property()
    {
        return t2;
    }

    public StringProperty heaterProperty()
    {
        return heater;
    }

    public StringProperty outTempProperty()
    {
        return outTemp;
    }

    public StringProperty errorProperty()
    {
        return error;
    }
}
