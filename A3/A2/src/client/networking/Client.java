package client.networking;

import transferobjects.Message;
import util.PropertyChangeSubject;

import java.io.IOException;
import java.util.List;

public interface Client extends PropertyChangeSubject
{
    void setId(String id);
    List<String> getIds();
    List<Message> getMessageList();
    void sendMessage(Message msg);
    void sendSingleMessage(Message msg);

    void startClient();
}
