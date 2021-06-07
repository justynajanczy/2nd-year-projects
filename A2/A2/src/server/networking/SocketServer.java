package server.networking;

import server.model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer
{
    private Model model;

    public SocketServer(Model model)
    {
        this.model = model;
    }

    public void startServer()
    {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2345);
            List<ServerSocketHandler> handlers = new ArrayList<>();
            while (true) {
                Socket socket = welcomeSocket.accept();
                ServerSocketHandler handler = new ServerSocketHandler(socket, model);
                handlers.add(handler);
                System.out.println("handlers:" + handlers.size());
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
