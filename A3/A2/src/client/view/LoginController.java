package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.LoginVM;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements ViewController
{
    @FXML
    private TextField usernameField;

    private LoginVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) throws IOException
    {
        this.vh = vh;
        this.vm = vmf.getLoginViewModel();

        usernameField.textProperty().bindBidirectional(vm.usernameProperty());
    }

    public void onSubmitUsername(javafx.event.ActionEvent actionEvent)
    {
        if(!usernameField.equals("") || usernameField == null)
        {
            vm.setUsername();
            vh.openChat();
        }
    }
}
