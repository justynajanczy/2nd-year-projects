
public class ValuableTransporter implements Runnable
{
    private Deposit deposit;
    private TreasureRoomDoor treasureRoom;
    private ArrayList<MultitonValuables> listOfValuables;

    public ValuableTransporter(Deposit deposit, TreasureRoomDoor treasureRoom)
    {
        this.deposit = deposit;
        this.treasureRoom = treasureRoom;
        listOfValuables = new ArrayList<>();
    }

    @Override
    public void run()
    {
        int min = 50;
        int max = 200;

        int random = (int)Math.floor(Math.random() * (max-min+1)+min);
        int sum = 0;
        try
        {
            //single valuable worth 40
            while(sum <= random)
            {
                MultitonValuables taken = deposit.takeValuable();
                listOfValuables.add(taken);
                sum+=taken.getKey().getPrice();
                Thread.sleep(1500);
            }
            Log.getInstance().add("ValuableTransporter: got enough valuables. Now transport to treasure room");
            treasureRoom.acquireWrite(Actor.ValuableTransporter);
            for (int i = 0; i < listOfValuables.size(); i++) {
                treasureRoom.addValuableToTreasureRoom(Actor.ValuableTransporter, listOfValuables.get(i));
            }
            Log.getInstance().add("ValuableTransporter added Valuables to treasure room");
            treasureRoom.releaseWrite(Actor.ValuableTransporter);
            listOfValuables = new ArrayList<>();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
