public class Guardsman implements TreasureRoomDoor
{
    private TreasureRoom treasureRoom;

    public Guardsman(TreasureRoom treasureRoom)
    {
        this.treasureRoom = treasureRoom;
    }

    @Override
    public void acquireRead(Actor actor)
    {
        if(actor == Actor.Accountant)
        {
            treasureRoom.acquireRead(actor);
        }
    }

    @Override
    public void releaseRead(Actor actor)
    {
        if(actor == Actor.Accountant)
            treasureRoom.releaseRead(actor);
    }

    @Override
    public void acquireWrite(Actor actor)
    {
        if(actor == Actor.King || actor == Actor.ValuableTransporter)
        {
            treasureRoom.acquireWrite(actor);
            Log.getInstance().add("Guardsman gave " + actor + " write access to treasure room");

        }
    }

    @Override
    public void releaseWrite(Actor actor)
    {
        if(actor == Actor.King || actor == Actor.ValuableTransporter)
        {
            treasureRoom.releaseWrite(actor);
            Log.getInstance().add("Guardsman released write access from  " + actor);
        }

    }

    @Override
    public void addValuableToTreasureRoom(Actor actor, MultitonValuables valuable)
    {
        if(actor == Actor.ValuableTransporter || actor == Actor.King)
        {
            treasureRoom.addValuableToTreasureRoom(actor, valuable);
        }

    }

    @Override
    public ListADT<MultitonValuables> takeValuableFromTreasureRoom(Actor actor, int random)
    {
        if(actor == Actor.King && treasureRoom.getSize() != 0)
        {
            return treasureRoom.takeValuableFromTreasureRoom(actor, random);
        }
        else
        {
            return null;
        }
    }

    @Override
    public ListADT<MultitonValuables> lookAtValuables(Actor actor)
    {
        if(actor == Actor.Accountant)
        {
            return treasureRoom.lookAtValuables(actor);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getSize()
    {
        if(treasureRoom.getSize() != 0)
        {
            return treasureRoom.getSize();
        }
        return -1;
    }
}
