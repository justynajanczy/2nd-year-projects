package model.heater;

public class Power3State implements HeaterState
{
    private final int POWER = 3;
    private Thread thread;

    class InnerForThread implements Runnable
    {
        Heater heater;

        public InnerForThread(Heater heater)
        {
            this.heater = heater;
        }

        @Override
        public void run()
        {
            try {
                Thread.sleep(40000);
                heater.setPowerState(new Power2State());
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");;
            }
        }
    }

    public Power3State(Heater heater)
    {
        System.out.println("PowerState: " + POWER);
        thread = new Thread(new InnerForThread(heater));
        //after 40sec turns back down to Power2State
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void turnUp(Heater heater)
    {
        //nothing
    }

    @Override
    public void turnDown(Heater heater)
    {
        thread.interrupt();
        heater.setPowerState(new Power2State());
    }

    @Override
    public int getPower()
    {
        return POWER;
    }
}
