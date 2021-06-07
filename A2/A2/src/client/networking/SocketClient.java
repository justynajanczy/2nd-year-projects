package client.networking;

import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketClient implements Client
{
    private PropertyChangeSupport pcs;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public SocketClient()
    {
        pcs = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient()
    {
            Thread thread = new Thread(() -> listenToServer());
            thread.start();
    }

    private void listenToServer()
    {
        try(Socket socket = new Socket("localhost", 2345))
        {
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());

            outToServer.writeObject(new Request("Listener", null));
            while(true)
            {
                Request request = (Request) inFromServer.readObject();
                //it will be only in case of the message that has to be send to only first user
                if(String.valueOf(request.getArg()).contains(";;;;"))
                {
                    String [] arr = String.valueOf(request.getArg()).split(";;;;");
                    pcs.firePropertyChange(request.getType(), arr[0], arr[1]);
                }
                else
                {
                    pcs.firePropertyChange(request.getType(), null, request.getArg());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setId(String id)
    {
        try
        {
            request(id, "setId");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getIds()
    {
        try
        {
            Request response = request(null, "getIds");
            return (List<String>)response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> getMessageList()
    {
        try
        {
            Request response = request(null, "getMessageList");
            return (List<Message>)response.getArg();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendMessage(Message msg)
    {
        try
        {
            String message = msg.getUser() + ": " +msg.getText();
            request(message, "sendMessage");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendSingleMessage(Message msg)
    {
        try
        {
            String message = msg.getUser() + ": " +msg.getText();
            request(message, "singleMessage");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Request request(String arg, String type) throws IOException, ClassNotFoundException
    {
        Socket socket = new Socket("localhost", 2345);
        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
        outToServer.writeObject(new Request(type, arg));
        Request request = (Request)inFromServer.readObject();
        pcs.firePropertyChange(request.getType(), null, request.getArg());
        return request;
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
