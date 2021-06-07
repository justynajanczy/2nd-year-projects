package client.core;

import client.model.Model;
import client.model.ModelManager;

import java.io.IOException;

public class ModelFactory
{
    private static ModelFactory instance = new ModelFactory();
    public static ModelFactory getInstance()
    {
        return instance;
    }

    private Model model;
    private ModelFactory(){}

    public Model getModel() throws IOException
    {
        if(model == null)
        {
            model = new ModelManager(ClientFactory.getInstance().getClient());
        }
        return model;
    }
}
