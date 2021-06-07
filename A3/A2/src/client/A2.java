package client;

import client.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class A2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ViewHandler.getInstance().start();
    }
}
