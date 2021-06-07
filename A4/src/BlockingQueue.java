public class BlockingQueue<T> implements Buffer<T>
{
    private ListADT<T> myList = new ArrayList<>();

    @Override
    public synchronized void put(T element) throws InterruptedException
    {
        if(element == null)
            throw new IllegalArgumentException("Class: BlockingQueue, tried to add null element");
        while(myList.isFull())
        {
            wait();
        }
        myList.add(element);
        notifyAll();
        Thread.sleep(1000);
    }

    @Override
    public synchronized T take() throws InterruptedException
    {
        while(myList.isEmpty())
        {
            wait();
        }
        T val = myList.remove(myList.size()-1);
        notifyAll();
        Thread.sleep(1000);
        return val;
    }

    //first element from the ArrayList
    @Override
    public synchronized T look()
    {
        if(myList.isEmpty())
            return null;
        return myList.get(0);
    }

    @Override
    public synchronized boolean isEmpty()
    {
        return myList.isEmpty();
    }

    @Override
    public synchronized boolean isFull()
    {
        return myList.isFull();
    }

    @Override
    public synchronized int size()
    {
        return myList.size();
    }

    public String toString()
    {
        return myList.toString();
    }
}
