package client.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

import java.util.List;

public interface Model extends PropertyChangeSubject
{
    void setId(String id);
    List<String> getIds();
    List<Message> getMessageList();
    void sendMessage(Message msg);
    String getUsername();

    void sendSingleMessage(Message msg);
}
