public class Miner implements Runnable
{
    private Deposit deposit;
    private Mine mine;

    public Miner(Deposit deposit, Mine mine)
    {
        this.deposit = deposit;
        this.mine = mine;
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                deposit.addValuable(mine.getValuable());
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
