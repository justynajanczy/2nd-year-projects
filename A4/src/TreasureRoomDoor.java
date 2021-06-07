public interface TreasureRoomDoor
{
    void acquireRead(Actor actor);
    void releaseRead(Actor actor);
    void acquireWrite(Actor actor);
    void releaseWrite(Actor actor);
    void addValuableToTreasureRoom(Actor actor, MultitonValuables valuable);
    ListADT<MultitonValuables> takeValuableFromTreasureRoom(Actor actor, int random) throws InterruptedException;
    ListADT<MultitonValuables> lookAtValuables(Actor actor);
    int getSize();
}
