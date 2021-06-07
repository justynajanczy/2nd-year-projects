package server;

import server.model.ModelManagerServer;
import server.networking.RMIServerImpl;
import shared.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        RMIServerImpl ss = new RMIServerImpl(new ModelManagerServer());
        ss.startServer();
    }
}
