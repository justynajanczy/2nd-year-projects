import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest
{
    private ArrayList<Integer> toTest = new ArrayList<>();

    @Test
    void insertOneElement()
    {
        //doesn't check if element is null
        assertThrows(IndexOutOfBoundsException.class, () ->
        {
            toTest.add(-1, 2);
        });

        toTest.add(2);
        assertTrue(toTest.size() == 1);
    }

    private void addElements()
    {
        for (int i = 0; i < 10; i++) {
            toTest.add(i);
        }
    }

    @Test
    void set()
    {
        addElements();
        assertThrows(IndexOutOfBoundsException.class, () ->
        {
            toTest.set(toTest.size(), 4);
        });

        toTest.set(7, 3);
        assertTrue(toTest.get(7) == 3);
    }

    @Test
    void testRemoveByIndex()
    {
        addElements();
        int startSize = toTest.size();
        toTest.remove(5);
        assertTrue(startSize -1 == toTest.size());
        assertThrows(IndexOutOfBoundsException.class, () ->
        {
            toTest.remove(-12);
        });
    }

    @Test
    void removeByElement()
    {
        //problematic with Integer elements. Imposiible to remove by element cuz it takes it as an index
        addElements();
        toTest.add(20);
        int removed = toTest.remove(20);
        assertEquals(removed, 20);
    }

    @Test
    void testIndexOf()
    {
        addElements();
        assertEquals(toTest.indexOf(3), 3);
        assertEquals(toTest.indexOf(12), -1);
    }

    @Test
    void testContain()
    {
        addElements();
        assertTrue(toTest.contains(2));
    }

    @Test
    void testEmpty()
    {
        assertTrue(toTest.isEmpty());
    }

    //no test for isFull because list is not bounded

}