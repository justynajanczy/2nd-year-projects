import core.ModelFactory;
import core.ViewHandler;
import core.ViewModelFactory;
import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ModelManager;

public class A1App extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ModelManager modelManager = new ModelManager();
        ModelFactory mf = new ModelFactory(modelManager);
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.start();

        Thread t1 = new Thread(new Thermometer("t1", 5, 1, modelManager, false));
        Thread t2 = new Thread(new Thermometer("t2", 1, 7, modelManager, false));
        Thread out = new Thread(new Thermometer("out", -2,0, modelManager,true));
        t1.start();
        t2.start();
        out.start();
    }
}
