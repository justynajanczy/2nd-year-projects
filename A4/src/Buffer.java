public interface Buffer<T>
{
    void put(T element) throws InterruptedException;
    T take() throws InterruptedException;
    T look();
    boolean isEmpty();
    boolean isFull();
    int size();
}
