package view;

import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import viewmodel.ModificationsVM;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class ModificationsController
{
    @FXML
    private TextField lowestField;
    @FXML
    private TextField highestField;
    @FXML
    private ImageView imageView;
    @FXML
    private Label constraintsSubmittedLabel;
    @FXML
    private Label constraintsLabel;
    @FXML
    private Label heaterInfoLabel;
    @FXML
    private Label heaterPowerLabel;
    @FXML
    private Label heaterErrorLabel;

    private ModificationsVM modificationsVM;
    private ViewHandler viewHandler;

    public void init(ModificationsVM vm, ViewHandler vh)
    {
        modificationsVM = vm;
        viewHandler = vh;
        lowestField.textProperty().bindBidirectional(modificationsVM.lowestProperty());
        highestField.textProperty().bindBidirectional(modificationsVM.highestProperty());
        constraintsSubmittedLabel.textProperty().bind(modificationsVM.constraintsSubmittedLabel());
        constraintsLabel.textProperty().bind(modificationsVM.constraintsLabel());
        heaterInfoLabel.textProperty().bind(modificationsVM.heaterInfoLabel());
        heaterPowerLabel.textProperty().bind(modificationsVM.heaterPowerLabel());
        heaterErrorLabel.textProperty().bind(modificationsVM.heaterErrorLabel());
    }

    public void onDownHeater(ActionEvent actionEvent)
    {
        modificationsVM.turnDownHeater();
    }

    public void onUpHeater(ActionEvent actionEvent)
    {
        modificationsVM.turnUpHeater();
    }

    public void onChange(ActionEvent actionEvent)
    {
        modificationsVM.updateConstraints();
    }

    public void onBackButton(ActionEvent actionEvent) throws IOException, InterruptedException
    {
        modificationsVM.reset();
        viewHandler.openTemperatures();
    }
}
