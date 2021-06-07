
public class Deposit
{
    Buffer<MultitonValuables> depositValuables = new BlockingQueue<>();

    public synchronized void addValuable(MultitonValuables valuable) throws InterruptedException
    {
        while(depositValuables.isFull())
        {
            try
            {
                wait();
                Log.getInstance().add("Miner have to wait. Deposit is full");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        depositValuables.put(valuable);
        Log.getInstance().add("Miner added Valuable to the deposit. Now " + depositValuables.size() +" valuables in the deposit");
        notifyAll();
    }

    public synchronized MultitonValuables takeValuable() throws InterruptedException
    {
        while(depositValuables.isEmpty())
        {
            try
            {
                wait();
                Log.getInstance().add("ValuableTransporter have to wait. Deposit is empty");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MultitonValuables val = depositValuables.take();
        Log.getInstance().add("ValuableTransporter took Valuable from the deposit");
        notifyAll();
        return val;
    }

}
