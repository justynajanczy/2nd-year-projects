package client.core;

import client.A2;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewHandler
{
    private static ViewHandler instance = new ViewHandler();

    public static ViewHandler getInstance()
    {
        return instance;
    }

    private Stage stage;

    private ViewHandler(){}

    public void start() throws IOException
    {
        stage = new Stage();
        ViewFactory.init(stage);
        openLogin();
    }

    public void openChat()
    {
        Scene chatScene = ViewFactory.getScene("Chat");
        stage.setScene(chatScene);
        stage.setOnCloseRequest(e -> System.out.println("Exit"));
        stage.show();
    }

    public void openLogin()
    {
        Scene loginScene = ViewFactory.getScene("Login");
        stage.setScene(loginScene);
        stage.show();
    }
}
