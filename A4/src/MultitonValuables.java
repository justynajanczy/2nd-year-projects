import java.util.*;

public class MultitonValuables
{
    private static Map<Key, MultitonValuables> allInstances = new HashMap<>();
    private Key key;

    //tutaj moge jeszcze dac value i w konstruktorze nadac value

    private MultitonValuables(Key key)
    {
        this.key = key;
    }

    public static MultitonValuables getInstance(Key key)
    {
        MultitonValuables instance = allInstances.get(key);
        if(instance == null)
        {
            synchronized (allInstances)
            {
                instance = allInstances.get(key);
                if(instance == null)
                {
                    instance = new MultitonValuables(key);
                    allInstances.put(key, instance);
                }
            }
        }
        return instance;
    }

    public static MultitonValuables getRandomInstance()
    {
        Random random = new Random();
        final List<Key> KEYS = Collections.unmodifiableList(Arrays.asList(Key.values()));
        final int SIZE = KEYS.size();

        Key randomKey = KEYS.get(random.nextInt(SIZE));

        return MultitonValuables.getInstance(randomKey);
    }

    public Key getKey()
    {
        return key;
    }
}
