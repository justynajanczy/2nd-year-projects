package core;

import viewmodel.ModificationsVM;
import viewmodel.TemperaturesVM;

public class ViewModelFactory
{
    private ModelFactory modelFactory;
    private TemperaturesVM temperaturesVM;
    private ModificationsVM modificationsVM;

    public ViewModelFactory(ModelFactory modelFactory)
    {
        this.modelFactory = modelFactory;
    }

    public TemperaturesVM getTemperaturesVM()
    {
        if(temperaturesVM == null)
        {
            temperaturesVM = new TemperaturesVM(modelFactory.getModelManager());
        }
        return temperaturesVM;
    }

    public ModificationsVM getModificationsVM()
    {
        if(modificationsVM == null)
        {
            modificationsVM = new ModificationsVM(modelFactory.getModelManager());
        }
        return modificationsVM;
    }
}
