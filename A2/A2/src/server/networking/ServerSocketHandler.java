package server.networking;

import server.model.Model;
import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerSocketHandler implements Runnable
{
    private Socket socket;
    private Model model;

    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    public ServerSocketHandler(Socket socket, Model model)
    {
        this.socket = socket;
        this.model = model;

        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run()
    {
        try
        {
            Request request = (Request)inFromClient.readObject();
            if("Listener".equals(request.getType())) {
                model.addPropertyChangeListener("NewId", this::onNewId);
                model.addPropertyChangeListener("NewMessage", this::onNewMessage);
                model.addPropertyChangeListener("NewSingleMessage", this::onNewSingleMessage);
            }
            else if("getMessageList".equals(request.getType()))
            {
                List<Message> msgs = model.getMessageList();
                outToClient.writeObject(new Request("getMessageList", msgs));
            }
            else if("sendMessage".equals(request.getType()))
            {
                String[] arr = ((String)request.getArg()).split(": ");
                Message newMessage = new Message(arr[1], arr[0]);
                model.sendMessage(newMessage);
                outToClient.writeObject(new Request("sendMessage", "received"));
            }
            else if("singleMessage".equals(request.getType()))
            {
                String[] arr = ((String)request.getArg()).split(": ");
                Message newMessage = new Message(arr[1], arr[0]);
                model.sendSingleMessage(newMessage);
                outToClient.writeObject(new Request("singleMessage", "received"));
            }
            else if("setId".equals(request.getType()))
            {
                model.setId((String)request.getArg());
                outToClient.writeObject(new Request("setId", "approved"));
            }
            else if("getIds".equals(request.getType()))
            {
                List<String> usernames = model.getIds();
                outToClient.writeObject(new Request("getIds", usernames));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void onNewId(PropertyChangeEvent evt)
    {
        try
        {
            outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onNewMessage(PropertyChangeEvent evt)
    {
        try {
            outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void onNewSingleMessage(PropertyChangeEvent evt)
    {
        try
        {
            String packedUsernameAndMessage = evt.getOldValue() + ";;;;" + evt.getNewValue();
            outToClient.writeObject(new Request(evt.getPropertyName(), packedUsernameAndMessage));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
