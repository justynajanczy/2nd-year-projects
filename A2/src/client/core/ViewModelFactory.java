package client.core;

import client.viewmodel.ChatVM;
import client.viewmodel.LoginVM;

import java.io.IOException;

public class ViewModelFactory
{
    private static ViewModelFactory instance = new ViewModelFactory();

    public static ViewModelFactory getInstance()
    {
        return instance;
    }

    private LoginVM loginViewModel;
    private ChatVM chatViewModel;

    private ViewModelFactory()
    {

    }

    public LoginVM getLoginViewModel() throws IOException
    {
        if(loginViewModel == null)
        {
            loginViewModel = new LoginVM(ModelFactory.getInstance().getModel());
        }
        return loginViewModel;
    }

    public ChatVM getChatViewModel() throws IOException
    {
        if(chatViewModel == null)
        {
            chatViewModel = new ChatVM(ModelFactory.getInstance().getModel());
        }
        return chatViewModel;
    }
}
