public class Test
{
    public static void main(String[] args)
    {
        Mine mine = new Mine();
        Deposit deposit = new Deposit();
        TreasureRoomDoor treasureRoom = new Guardsman(new TreasureRoom());


        for (int i = 0; i < 4; i++) {
            new Thread(new Miner(deposit, mine)).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new ValuableTransporter(deposit, treasureRoom)).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Accountant(treasureRoom)).start();
        }

        new Thread(new King(treasureRoom)).start();
    }
}
