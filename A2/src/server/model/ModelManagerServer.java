package server.model;

import transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModelManagerServer implements Model
{

    private PropertyChangeSupport pcs;
    private List<Message> messages;
    private ArrayList<String> idArray = new ArrayList<>();

    public ModelManagerServer()
    {
        pcs = new PropertyChangeSupport(this);
        messages = new ArrayList<>();
    }

    @Override
    public void setId(String id)
    {
        idArray.add(id);
        pcs.firePropertyChange("NewId", null, idArray);
    }

    @Override
    public List<String> getIds()
    {
        return idArray;
    }

    @Override
    public List<Message> getMessageList()
    {
        return new ArrayList<>(messages);
    }

    @Override
    public void sendMessage(Message msg)
    {
        messages.add(msg);
        pcs.firePropertyChange("NewMessage", null, msg.toString());
    }

    @Override
    public void sendSingleMessage(Message msg)
    {
        messages.add(msg);
        pcs.firePropertyChange("NewSingleMessage", messages.get(0).getUser(), msg.toString());
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
