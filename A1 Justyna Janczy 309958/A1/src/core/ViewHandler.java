package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ModificationsController;
import view.TemperaturesController;

import java.io.IOException;

public class ViewHandler
{
    private ViewModelFactory vmf;
    private Scene temperaturesScene;
    private Scene modificationsScene;
    private Stage stage;

    public ViewHandler(ViewModelFactory vmf)
    {
        this.vmf = vmf;
    }

    public void start() throws IOException
    {
        stage = new Stage();
        openTemperatures();
    }

    public void openTemperatures() throws IOException
    {
        if(temperaturesScene == null) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("../view/TemperaturesView.fxml"));
            Parent root = load.load();

            TemperaturesController temperaturesController = load.getController();
            temperaturesController.init(vmf.getTemperaturesVM(), this);

            stage.setTitle("Temperatures");
            temperaturesScene = new Scene(root);
        }
        stage.setScene(temperaturesScene);
        stage.show();
    }

    public void openModifications() throws IOException
    {
        if(modificationsScene == null) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("../view/ModificationsView.fxml"));
            Parent root = load.load();

            ModificationsController modificationsController = load.getController();
            modificationsController.init(vmf.getModificationsVM(), this);

            stage.setTitle("Modifications");
            modificationsScene = new Scene(root);
        }
        stage.setScene(modificationsScene);
        stage.show();
    }
}
