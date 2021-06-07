package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.ModelManager;

import java.beans.PropertyChangeEvent;


public class ModificationsVM
{
    private StringProperty lowest, highest, constraintsSubmittedLabel, constraintsLabel, heaterInfoLabel, heaterPowerLabel, heaterErrorLabel;

    private ModelManager modelManager;

    public ModificationsVM(ModelManager modelManager)
    {
        this.modelManager = modelManager;
        lowest = new SimpleStringProperty(String.valueOf(modelManager.getLowestCriticalValue()));
        highest = new SimpleStringProperty(String.valueOf(modelManager.getHighestCriticalValue()));
        constraintsSubmittedLabel = new SimpleStringProperty();
        constraintsLabel = new SimpleStringProperty();
        heaterInfoLabel = new SimpleStringProperty();
        heaterPowerLabel = new SimpleStringProperty(String.valueOf(modelManager.getPower()));
        heaterErrorLabel = new SimpleStringProperty();
        modelManager.addPropertyChangeListener("HeaterChangedTemp", evt -> updateHeaterState(evt));
    }

    public void updateHeaterState(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->
        {
            heaterPowerLabel.set(evt.getNewValue().toString());
        });
    }

    public void turnDownHeater()
    {
        if(modelManager.getPower() != 0)
        {
            heaterInfoLabel.set("Heater power value decreased");
            heaterErrorLabel.set("");
            modelManager.turnDownHeater();
        }
        else
        {
            heaterInfoLabel.set("");
            heaterErrorLabel.set("Minimum reached");
        }
    }

    public void turnUpHeater()
    {
        if(modelManager.getPower() != 3)
        {
            heaterInfoLabel.set("Heater power value increased");
            heaterErrorLabel.set("");
            modelManager.turnUpHeater();
        }
        else
        {
            heaterInfoLabel.set("");
            heaterErrorLabel.set("Maximum reached");
        }
    }

    public void updateConstraints()
    {
        if(Integer.parseInt(lowestProperty().get()) < Integer.parseInt(highestProperty().get()))
        {
            modelManager.setCriticalValues(lowestProperty().get(), highestProperty().get());
            constraintsSubmittedLabel.set("New temperature constraints added");
            constraintsLabel.set("");
        }
        else
        {
            constraintsSubmittedLabel.set("");
            constraintsLabel.set("Lowest constraint can't be higher then highest constraint. Please try again");
        }
    }

    public void reset()
    {
        constraintsSubmittedLabel.set("");
        heaterInfoLabel.set("");
        heaterErrorLabel.set("");
    }

    public StringProperty lowestProperty()
    {
        return lowest;
    }

    public StringProperty highestProperty()
    {
        return highest;
    }

    public StringProperty constraintsLabel()
    {
        return constraintsLabel;
    }

    public StringProperty constraintsSubmittedLabel()
    {
        return constraintsSubmittedLabel;
    }

    public StringProperty heaterInfoLabel()
    {
        return heaterInfoLabel;
    }

    public StringProperty heaterPowerLabel()
    {
        return heaterPowerLabel;
    }

    public StringProperty heaterErrorLabel()
    {
        return heaterErrorLabel;
    }
}
