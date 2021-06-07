package view;

import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import viewmodel.TemperaturesVM;

import java.io.IOException;

public class TemperaturesController
{
    @FXML
    private Label t1Label;
    @FXML
    private Label t2Label;
    @FXML
    private Label heatersLabel;
    @FXML
    private Label outTempLabel;
    @FXML
    private Label errorLabel;

    private TemperaturesVM temperaturesVM;
    private ViewHandler viewHandler;

    public void init(TemperaturesVM vm, ViewHandler vh)
    {
        temperaturesVM = vm;
        viewHandler = vh;
        t1Label.textProperty().bind(temperaturesVM.t1Property());
        t2Label.textProperty().bind(temperaturesVM.t2Property());
        heatersLabel.textProperty().bind(temperaturesVM.heaterProperty());
        outTempLabel.textProperty().bind(temperaturesVM.outTempProperty());
        errorLabel.textProperty().bind(temperaturesVM.errorProperty());
    }

    public void onModify(ActionEvent actionEvent) throws IOException
    {
        viewHandler.openModifications();
    }
}
