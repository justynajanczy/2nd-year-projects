package server.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

import java.util.List;

public interface Model extends PropertyChangeSubject
{
    void setId(String id);
    List<String> getIds();
    List<Message> getMessageList();
    void sendMessage(Message msg);

    void sendSingleMessage(Message msg);
}
