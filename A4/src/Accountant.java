public class Accountant implements Runnable
{
    private TreasureRoomDoor treasureRoom;

    public Accountant(TreasureRoomDoor treasureRoom)
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
                treasureRoom.acquireRead(Actor.Accountant);
                ListADT<MultitonValuables> listToCount = treasureRoom.lookAtValuables(Actor.Accountant);
                //simulate time it takes to count the valuables
                Thread.sleep(1000);
                int sum = 0;
                for (int i = 0; i < listToCount.size(); i++) {
                    sum+= listToCount.get(i).getKey().getPrice();
                }
                Log.getInstance().add("Accountant: Valuables in Treasure Room are worth: " + sum);
                treasureRoom.releaseRead(Actor.Accountant);
                Log.getInstance().add("Accountant released");
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
