public class TreasureRoom implements TreasureRoomDoor
{
    private ListADT<MultitonValuables> valuables;
    private int activeReaders;
    private boolean activeWriter;
    private int waitingWriters;

    public TreasureRoom()
    {
        valuables = new ArrayList<>();
    }

    @Override
    public synchronized void acquireRead(Actor actor)
    {
        while(activeWriter || waitingWriters > 0)
        {
            try
            {
                wait();
                Log.getInstance().add(actor + " have to wait. King is in Treasure Room");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activeReaders++;
        Log.getInstance().add(actor + " starts analyzing");
    }

    @Override
    public synchronized void releaseRead(Actor actor)
    {
        activeReaders--;
        Log.getInstance().add(actor + " finished analyzing");
        if(activeReaders == 0)
            notifyAll();
    }

    @Override
    public synchronized void acquireWrite(Actor actor)
    {
        waitingWriters++;
        while(activeReaders > 0 || activeWriter)
        {
            try
            {
                Log.getInstance().add(actor + " have to wait. Accountants read or there is another writer (king/transporter)");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriters--;
        activeWriter = true;
        Log.getInstance().add(actor + " enters Treasure Room");
    }

    @Override
    public synchronized void releaseWrite(Actor actor)
    {
        activeWriter = false;
        Log.getInstance().add(actor + " exits Treasure Room");
        notifyAll();
    }

    @Override
    public void addValuableToTreasureRoom(Actor actor, MultitonValuables valuable)
    {
        valuables.add(valuable);
        Log.getInstance().add(actor + " added " + valuable.getKey().getName() + " to Treasure Room. Now " + valuables.size() + " at treasure room");
    }

    @Override
    public ListADT<MultitonValuables> takeValuableFromTreasureRoom(Actor actor, int random)
    {
        ListADT<MultitonValuables> kingsValuables = new ArrayList<>();
        int total = 0;
        while(!valuables.isEmpty() && total < random)
        {
            MultitonValuables val = valuables.remove(valuables.size()-1);
            kingsValuables.add(val);
            total +=val.getKey().getPrice();
            Log.getInstance().add(actor + " took " + val.getKey().getName() +  " from the Treasure Room worth " + val.getKey().getPrice());
        }
        if(total >= random)
        {
            Log.getInstance().add(actor + " took " + kingsValuables.size() + " valuables from the treasure room");
            return kingsValuables;
        }
        else
        {
            while(!kingsValuables.isEmpty())
            {
                valuables.add(kingsValuables.remove(0));
            }
            return null;
        }
    }

    @Override
    public ListADT<MultitonValuables> lookAtValuables(Actor actor)
    {
        //looks at the last one (the newest one)
        Log.getInstance().add(actor + " looked at the valuables in the Treasure Room");
        return valuables;
    }

    @Override
    public int getSize()
    {
        return valuables.size();
    }
}
