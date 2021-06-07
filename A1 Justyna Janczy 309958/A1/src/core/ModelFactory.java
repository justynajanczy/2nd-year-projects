package core;

import mediator.ModelManager;

public class ModelFactory
{
    private ModelManager modelManager;

    public ModelFactory(ModelManager mm)
    {
        modelManager = mm;
    }

    public ModelManager getModelManager()
    {
        if(modelManager == null)
        {
            modelManager = new ModelManager();
        }
        return modelManager;
    }
}
