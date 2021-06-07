package client.networking;

import shared.RMIServer;
import transferobjects.Message;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIClientImpl implements Client, shared.RMIClient
{
    private RMIServer server;
    private PropertyChangeSupport pcs;

    public RMIClientImpl()
    {
        pcs = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient()
    {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (RMIServer)registry.lookup("Server");
            server.registerClient(this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setId(String id)
    {
        try {
            server.setId(id);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public List<String> getIds()
    {
        try {
            return server.getIds();
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public List<Message> getMessageList()
    {
        try {
            return server.getMessagesList();
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public void sendMessage(Message msg)
    {
        try {
            server.sendMessage(msg);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public void sendSingleMessage(Message msg)
    {
        try {
            server.sendSingleMessage(msg);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }
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


    //RMIClient interface methods
    @Override
    public void updateMessages(String msg)
    {
        pcs.firePropertyChange("NewMessage", null, msg);
    }

    @Override
    public void updateIds(ArrayList<String> id)
    {
        pcs.firePropertyChange("NewId", null, id);
    }

    @Override
    public void updateSingleMessage(String toWhom, String msg)
    {
        pcs.firePropertyChange("NewSingleMessage", toWhom, msg);
    }
}
