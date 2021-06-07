package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;

import java.io.IOException;

public interface ViewController
{
    void init(ViewHandler vh, ViewModelFactory vmf) throws IOException;
}
