package client.model;

import client.networking.Client;
import transferobjects.Message;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;

public class ModelManager implements Model
{
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Client client;
    private String username;

    public ModelManager(Client client)
    {
        this.client = client;
        client.startClient();
        client.addPropertyChangeListener("NewMessage", this::onNewMessage);
        client.addPropertyChangeListener("NewId", this::onNewId);
        client.addPropertyChangeListener("NewSingleMessage", this::onNewSingleMessage);
    }

    private void onNewSingleMessage(PropertyChangeEvent evt)
    {
        String [] arr = String.valueOf(evt.getNewValue()).split(": ");

        if((username.equals(evt.getOldValue()) || arr[0].equals(username)))
        {
            pcs.firePropertyChange("NewSingleMessage", evt.getOldValue(), evt.getNewValue());
        }
    }

    public void onNewMessage(PropertyChangeEvent evt)
    {
        pcs.firePropertyChange("NewMessage", null, evt.getNewValue());
    }

    public void onNewId(PropertyChangeEvent evt)
    {
        pcs.firePropertyChange("NewId", null, evt.getNewValue());
    }

    @Override
    public void setId(String id)
    {
        client.setId(id);
        username = id;
    }

    @Override
    public List<String> getIds()
    {
        return client.getIds();
    }

    @Override
    public List<Message> getMessageList()
    {
        return client.getMessageList();
    }

    @Override
    public void sendMessage(Message msg)
    {
        client.sendMessage(msg);
    }

    @Override
    public void sendSingleMessage(Message msg)
    {
        client.sendSingleMessage(msg);
    }

    @Override
    public String getUsername()
    {
        return username;
    }
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(listener);
    }
}
