package server;

import server.model.ModelManagerServer;
import server.networking.SocketServer;

public class RunServer
{
    public static void main(String[] args)
    {
        SocketServer ss = new SocketServer(new ModelManagerServer());
        ss.startServer();
    }
}
