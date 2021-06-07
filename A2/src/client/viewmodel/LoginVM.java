package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginVM
{
    private StringProperty username;
    private Model model;


    public LoginVM(Model model)
    {
        this.model = model;
        username = new SimpleStringProperty();
    }

    public void setUsername()
    {
        model.setId(username.get());
    }

    public StringProperty usernameProperty()
    {
        return username;
    }
}
