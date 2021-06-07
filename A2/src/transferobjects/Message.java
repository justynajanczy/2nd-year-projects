package transferobjects;

import java.io.Serializable;

public class Message implements Serializable
{
    private String text, user;

    public Message(String txt, String clientUsername)
    {
        text = txt;
        user = clientUsername;
    }

    public String getText()
    {
        return text;
    }

    public String getUser()
    {
        return user;
    }

    public String toString()
    {
        return user + ": " + text;
    }
}
