/*public enum Key
{
    Diamond, GoldNugget, Jewel, Ruby, WoodenCoin
}*/

public enum Key
{
    DIAMOND("Diamond", 100),
    GOLDNUGGET("GoldNugget", 80),
    JEWEL("Jewel", 60),
    RUBY("Ruby", 40),
    WOODCOIN("WoodCoin", 20);

    private final String name;
    private final int price;

    private Key(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }
}
