package shared;

import transferobjects.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIClient extends Remote
{
    void updateMessages(String msg) throws RemoteException;
    void updateIds(ArrayList<String> id) throws RemoteException;
    void updateSingleMessage(String toWhom, String msg) throws RemoteException;
}
