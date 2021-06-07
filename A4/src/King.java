public class King implements Runnable
{
    private TreasureRoomDoor treasureRoom;

    public King(TreasureRoomDoor treasureRoom)
    {
        this.treasureRoom = treasureRoom;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                int min = 20;
                int max = 120;

                int random = (int)Math.floor(Math.random() * (max-min+1)+min);
                treasureRoom.acquireWrite(Actor.King);
                ListADT<MultitonValuables> valuables = treasureRoom.takeValuableFromTreasureRoom(Actor.King, random);
                Thread.sleep(1000);
                if(valuables != null)
                {
                    Log.getInstance().add("King got " + valuables.size() + " valuable/-s from treasure room \n King throws a party. Enough valuables");
                }
                else
                {
                    Log.getInstance().add("Treasure room was empty. King didn't get valuables. No party");
                }
                treasureRoom.releaseWrite(Actor.King);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
