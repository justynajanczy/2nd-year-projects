package shared;

import transferobjects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote
{
    void setId(String id) throws RemoteException;
    List<String> getIds() throws RemoteException;
    List<Message> getMessagesList() throws RemoteException;
    void sendMessage(Message msg) throws RemoteException;
    void sendSingleMessage(Message msg) throws RemoteException;
    void registerClient(RMIClient client) throws RemoteException;
}
