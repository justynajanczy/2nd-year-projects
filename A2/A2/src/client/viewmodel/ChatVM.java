package client.viewmodel;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transferobjects.Message;
import java.beans.PropertyChangeEvent;

public class ChatVM
{
    private StringProperty chatInfo, newMessage;
    private ObservableList<String> messages;

    private Model model;

    public ChatVM(Model model)
    {
        this.model = model;
        System.out.println(model.getIds().toString());
        chatInfo = new SimpleStringProperty("Connected users: " + model.getIds().toString());
        newMessage = new SimpleStringProperty();
        messages = FXCollections.observableArrayList();
        model.addPropertyChangeListener("NewMessage", this::onNewMessage);
        model.addPropertyChangeListener("NewId", this::onNewIds);
        model.addPropertyChangeListener("NewSingleMessage", this::onNewMessage);
    }

    private void onNewIds(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->
        {
            chatInfo.set("");
            chatInfo.set("Connected users: " + model.getIds() + " You: " + model.getUsername());
        });
    }

    private void onNewMessage(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->
        {
            messages.add(String.valueOf(evt.getNewValue()));
        });
    }

    public void addNewMessage(Message msg)
    {
        model.sendMessage(msg);
    }    
    
    public void addToFirstMessage(Message message)
    {
        model.sendSingleMessage(message);
    }

    public String getClientUsername()
    {
        return model.getUsername();
    }

    public StringProperty chatInfoProperty()
    {
        return chatInfo;
    }

    public StringProperty newMessageProperty()
    {
        return newMessage;
    }

    public void loadMessages()
    {
        ObservableList<String> messagesList = FXCollections.observableArrayList();
        messages = FXCollections.observableArrayList(messagesList);
    }

    public ObservableList<String> messagesObservableList()
    {
        return messages;
    }


}
