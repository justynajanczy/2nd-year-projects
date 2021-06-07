package client.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.view.ViewController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewFactory
{
    private static Map<String, Scene> scenes;
    private static Stage stage;

    static
    {
        scenes = new HashMap<>();
    }

    public static void init(Stage theStage) throws IOException
    {
        stage = theStage;
        createScene("Login");
        createScene("Chat");
    }

    public static void createScene(String sceneName) throws IOException
    {
        Scene scene = null;
        if(sceneName.equals("Chat"))
        {
            System.out.println("Create chat");
            Parent root = loadFXML("../view/Chat.fxml");

            stage.setTitle("Chat");
            scene = new Scene(root);
        } else if (sceneName.equals("Login"))
        {
            System.out.println("Create Login");
            Parent root = loadFXML("../view/Login.fxml");


            stage.setTitle("Login");
            scene = new Scene(root);
        }
        scenes.put(sceneName, scene);
    }

    private static Parent loadFXML(String path) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewFactory.class.getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(ViewHandler.getInstance(), ViewModelFactory.getInstance());
        return root;
    }

    public static Scene getScene(String sceneName)
    {
        return scenes.get(sceneName);
    }
}