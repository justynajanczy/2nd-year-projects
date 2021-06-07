package server.networking;

import server.model.Model;
import shared.RMIClient;
import shared.RMIServer;
import transferobjects.Message;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements RMIServer
{
    private final Model model;

    public RMIServerImpl(Model model) throws RemoteException
    {
        UnicastRemoteObject.exportObject(this, 0);
        this.model = model;
    }

    public void startServer() throws RemoteException, AlreadyBoundException
    {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Server", this);
    }

    @Override
    public void setId(String id)
    {
        model.setId(id);
    }

    @Override
    public List<String> getIds()
    {
        return model.getIds();
    }

    @Override
    public List<Message> getMessagesList()
    {
        return model.getMessageList();
    }

    @Override
    public void sendMessage(Message msg)
    {
        model.sendMessage(msg);
    }

    @Override
    public void sendSingleMessage(Message msg)
    {
        model.sendSingleMessage(msg);
    }

    @Override
    public void registerClient(RMIClient client)
    {
        model.addPropertyChangeListener("NewMessage", evt ->
        {
            try
            {
                client.updateMessages((String)evt.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        model.addPropertyChangeListener("NewId", evt->
        {
            try
            {
                client.updateIds((ArrayList<String>)evt.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        model.addPropertyChangeListener("NewSingleMessage", evt ->
        {
            try
            {
                client.updateSingleMessage((String)evt.getOldValue(), (String)evt.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
