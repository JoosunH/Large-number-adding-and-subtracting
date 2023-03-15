/**
 * Author: Devin Lo
 * Created for York University, EECS 2011, Winter 2023, Sections M and N
 * Random test was inspired by the test made for LargeInt by Halt.#7981 on Discord
 */

package a1;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

import a1.LargeIntInterface;
import a1.LargeInt;
import a1.SpecializedList;

public class SpecializedListTest {
    public static final int TIMEOUT = 200;

    @Test
    // @Description(description="specializedList test 1: empty list")
    public void testSpecializedListEmpty() {
        SpecializedList emptyList = new SpecializedList();
        assertEquals(0, emptyList.lengthIs());
    }

    @Test
    // @Description(description="specializedList test 2: one front element")
    public void testSpecializedListOneElemFront() {
        byte elem = 3;

        SpecializedList oneListFront = new SpecializedList();
        assertEquals(0, oneListFront.lengthIs());
        oneListFront.insertFront(elem);
        assertEquals(1, oneListFront.lengthIs());
        oneListFront.resetForward();
        assertEquals(elem, oneListFront.getNextItem());
        assertEquals(elem, oneListFront.getNextItem());
        oneListFront.resetBackward();
        assertEquals(elem, oneListFront.getPriorItem());
        assertEquals(elem, oneListFront.getPriorItem());
    }

    @Test
    // @Description(description="specializedList test 3: one element back")
    public void testSpecializedListOneElemBack() {
        byte elem = 3;

        SpecializedList oneListBack = new SpecializedList();
        assertEquals(0, oneListBack.lengthIs());
        oneListBack.insertEnd(elem);
        assertEquals(1, oneListBack.lengthIs());

        // test iterator methods
        oneListBack.resetForward();
        assertEquals(elem, oneListBack.getNextItem());
        assertEquals(elem, oneListBack.getNextItem());
        oneListBack.resetBackward();
        assertEquals(elem, oneListBack.getPriorItem());
        assertEquals(elem, oneListBack.getPriorItem());
    }

    @Test
    // @Description(description="specializedList test 4: three elements")
    public void testSpecializedListThreeElem() {
        byte elem = 3;
        byte elem2 = 4;
        byte elem3 = 5;

        SpecializedList threeList = new SpecializedList();
        assertEquals(0, threeList.lengthIs());
        threeList.insertFront(elem);
        threeList.insertEnd(elem2);
        threeList.insertEnd(elem3);
        assertEquals(3, threeList.lengthIs());

        // test iterator methods
        threeList.resetForward();
        assertEquals(elem, threeList.getNextItem());
        threeList.resetBackward();
        assertEquals(elem3, threeList.getPriorItem());

        // iterators should both be on the middle element
        assertEquals(elem2, threeList.getNextItem());
        assertEquals(elem2, threeList.getPriorItem());

        // test reset methods
        threeList.resetForward();
        assertEquals(elem, threeList.getNextItem());
        threeList.resetBackward();
        assertEquals(elem3, threeList.getPriorItem());
    }

    @Test
    // @Description(description="specializedList test 5: three elements")
    public void testSpecializedListThreeElemFRF() {
        byte elem = 3;
        byte elem2 = 4;
        byte elem3 = 5;

        SpecializedList threeListFRF = new SpecializedList();
        assertEquals(0, threeListFRF.lengthIs());
        threeListFRF.insertFront(elem);
        threeListFRF.insertEnd(elem2);
        threeListFRF.insertFront(elem3);
        assertEquals(3, threeListFRF.lengthIs());

        // test iterator methods
        threeListFRF.resetForward();
        assertEquals(elem3, threeListFRF.getNextItem());
        threeListFRF.resetBackward();
        assertEquals(elem2, threeListFRF.getPriorItem());

        // iterators should both be on the middle element
        assertEquals(elem, threeListFRF.getNextItem());
        assertEquals(elem, threeListFRF.getPriorItem());

        // test reset methods
        threeListFRF.resetForward();
        assertEquals(elem3, threeListFRF.getNextItem());
        threeListFRF.resetBackward();
        assertEquals(elem2, threeListFRF.getPriorItem());
    }

    @Test
    // @Description(description="specializedList test 6: seven elements")
    public void testSpecializedListSevenElemRFRRFRF() {
        byte elem = 3;
        byte elem2 = 4;
        byte elem3 = 5;
        byte elem4 = -7;
        byte elem5 = 23;
        byte elem6 = -128;
        byte elem7 = 127;

        // 7 5 2 1 3 4 6
        // 127, 23, 4, 3, 5, -7, -128
        ArrayList<Byte> list = new ArrayList<>();
        list.add(elem7);
        list.add(elem5);
        list.add(elem2);
        list.add(elem);
        list.add(elem3);
        list.add(elem4);
        list.add(elem6);

        SpecializedList sevenListRFRRFRF = new SpecializedList();
        assertEquals(0, sevenListRFRRFRF.lengthIs());
        sevenListRFRRFRF.insertEnd(elem);
        sevenListRFRRFRF.insertFront(elem2);
        sevenListRFRRFRF.insertEnd(elem3);
        sevenListRFRRFRF.insertEnd(elem4);
        sevenListRFRRFRF.insertFront(elem5);
        sevenListRFRRFRF.insertEnd(elem6);
        sevenListRFRRFRF.insertFront(elem7);
        assertEquals(list.size(), sevenListRFRRFRF.lengthIs());

        // test iterator methods
        int frontIndex = 0;
        int rearIndex = list.size() - 1;
        int front;
        int rear;
        sevenListRFRRFRF.resetForward();
        front = list.get(frontIndex);
        assertEquals(front, sevenListRFRRFRF.getNextItem());
        frontIndex++;
        sevenListRFRRFRF.resetBackward();
        rear = list.get(rearIndex);
        assertEquals(rear, sevenListRFRRFRF.getPriorItem());
        rearIndex--;

        // iterators should both be on the next elements
        front = list.get(frontIndex);
        assertEquals(front, sevenListRFRRFRF.getNextItem());
        front++;
        rear = list.get(rearIndex);
        assertEquals(rear, sevenListRFRRFRF.getPriorItem());
        rearIndex--;

        // test reset methods
        sevenListRFRRFRF.resetForward();
        int first = list.get(0);
        assertEquals(first, sevenListRFRRFRF.getNextItem());
        sevenListRFRRFRF.resetBackward();
        int end = list.get(list.size() - 1);
        assertEquals(end, sevenListRFRRFRF.getPriorItem());

        // test forward iteration fully
        sevenListRFRRFRF.resetForward();
        for (int i = 0; i < list.size(); i++) {
            int ith = list.get(i);
            assertEquals(ith, sevenListRFRRFRF.getNextItem());
        }
        // ensure forward iterator looped back to front
        first = list.get(0);
        assertEquals(first, sevenListRFRRFRF.getNextItem());

        // test backward iteration fully
        sevenListRFRRFRF.resetBackward();
        for (int i = list.size() - 1; i >= 0; i--) {
            int ith = list.get(i);
            assertEquals(ith, sevenListRFRRFRF.getPriorItem());
        }
        // ensure backward iterator looped back to rear
        end = list.get(list.size() - 1);
        assertEquals(end, sevenListRFRRFRF.getPriorItem());

        // now, add item to back, and see if it's there
        byte elem8 = 45;
        list.add(elem8);
        sevenListRFRRFRF.insertEnd(elem8);
        assertEquals(list.size(), sevenListRFRRFRF.lengthIs());
        sevenListRFRRFRF.resetBackward();
        assertEquals(elem8, sevenListRFRRFRF.getPriorItem());

        // now, add item to front, and see if it's there
        byte elem9 = -90;
        list.add(0, elem9);
        sevenListRFRRFRF.insertFront(elem9);
        assertEquals(list.size(), sevenListRFRRFRF.lengthIs());
        sevenListRFRRFRF.resetForward();
        assertEquals(elem9, sevenListRFRRFRF.getNextItem());
    }

    @Test
    // @Description(description="specializedList test 7: random insertion")
    public void testSpecializedListRandomElemInsertionAndIterate() {
        Random rd = new Random();
        // seed random byte array
        int maxElems = 20 + rd.nextInt(20); // [20, 40)
        byte[] randomValues = new byte[maxElems];
        rd.nextBytes(randomValues);

        LinkedList<Byte> linked = new LinkedList<>();
        SpecializedList special = new SpecializedList();
        assertEquals(linked.size(), special.lengthIs());

        for (int i = 0; i < maxElems; i++) {
            if (rd.nextBoolean()) {
                linked.addLast(randomValues[i]);
                special.insertEnd(randomValues[i]);
            } else {
                linked.addFirst(randomValues[i]);
                special.insertFront(randomValues[i]);
            }
            assertEquals(linked.size(), special.lengthIs());

            // if length > 2, test partial iteration + reset
            if (linked.size() > 2) {
                special.resetForward();
                Iterator<Byte> linkedFront = linked.iterator();
                assertEquals(linkedFront.next().byteValue(),special.getNextItem());
                assertEquals(linkedFront.next().byteValue(),special.getNextItem());
                special.resetForward();
                assertEquals(linked.getFirst().byteValue(),special.getNextItem());

                special.resetBackward();
                Iterator<Byte> linkedRear = linked.descendingIterator();
                assertEquals(linkedRear.next().byteValue(),special.getPriorItem());
                assertEquals(linkedRear.next().byteValue(),special.getPriorItem());
                special.resetBackward();
                assertEquals(linked.getLast().byteValue(),special.getPriorItem());
            }

            // test full forward iteration
            special.resetForward();
            Iterator<Byte> linkedFront = linked.iterator();
            for (int j = 0; j < linked.size(); j++) {
                assertEquals(linkedFront.next().byteValue(),special.getNextItem());
            }
            assertEquals(linked.getFirst().byteValue(),special.getNextItem());

            // test full backward iteration
            special.resetBackward();
            Iterator<Byte> linkedRear = linked.descendingIterator();
            for (int j = 0; j < linked.size(); j++) {
                assertEquals(linkedRear.next().byteValue(),special.getPriorItem());
            }
            assertEquals(linked.getLast().byteValue(),special.getPriorItem());
        }
    }
}