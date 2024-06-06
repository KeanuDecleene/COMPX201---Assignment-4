import java.beans.Transient;
import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class MinHeapTest{
    private MinHeap heapTest;
    private final PrintStream output = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // functions to help testing 

    /*
     * before each test resets the heap and outputstream
     */
    @BeforeEach
    public void setup(){
        heapTest = new MinHeap();
        System.setOut(new PrintStream(outputStream));
    }

    /*
     * builds a heap by inserting rides into the heap
     */
    private void buildHeap(){
        Ride ride1 = new Ride(1, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2);
        Ride ride2 = new Ride(2, "03:12:23", new String[]{"Gerald", "Comet"}, 2, 3);
        Ride ride3 = new Ride(3, "07:12:23", new String[] {"Max", "Jubilee"}, 3, 4);
        Ride ride4 = new Ride(4, "10:12:23", new String[]{"Cooper", "Fair"}, 4, 5);
        Ride ride5 = new Ride(5, "1:12:23", new String[]{"Keanux", "Ronald"}, 5, 6);

        heapTest.insert(ride1);
        heapTest.insert(ride2);
        heapTest.insert(ride3);
        heapTest.insert(ride4);
        heapTest.insert(ride5);
    }

    /*
     * Function for verifying the properties of the heap are correct
     */
    private boolean verifyHeapOrder(){
        for(int i = 0; i < heapTest.k; i++){
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;
            if (leftChildIndex < heapTest.k && heapTest.rides[i].compareTo(heapTest.rides[leftChildIndex]) > 0) {
                return false;
            }
            if (rightChildIndex < heapTest.k && heapTest.rides[i].compareTo(heapTest.rides[rightChildIndex]) > 0) {
                return false;
            }
        }
        return true;
    }

    //Tests for insert 

    /*
     * Testing to see if ride is inserted correctly
     */
    @Test 
    @Order(1)
    @DisplayName("Inserting to empty Heap Test")
    public void testInsert(){
        String[] passengers = {"johnny", "mitchell"};
        Ride ride = new Ride(1, "04:12:23",passengers, 1, 2);
        heapTest.insert(ride);
        Assertions.assertEquals(ride, heapTest.peek());
    }

    /*
     * Testing to see if multiple rides are inserted correctly with the minimum time at the beginning of the minHeap and
     * that the minHeap array is maintained correctly whilst inserting
     */
    @Test
    @Order(2) 
    @DisplayName("Entire heap is inserted correctly and heap property is maintained")
    public void testInsertWithOrder(){
        buildHeap();
        Assertions.assertTrue(verifyHeapOrder());
    }

    //Tests for Remove

    /*
     * Testing to see if the correct ride was removed correctly and that the size of the minHeap is maintained when removing
     */
    @Test
    @Order(3)
    @DisplayName("Removing correct ride from Heap and size of heap is maintained")
    public void testRemove(){
        buildHeap();
        heapTest.remove(heapTest.rides[4]); // ride 1 from buildheap
        Assertions.assertEquals(4, heapTest.k);
        Assertions.assertNull(heapTest.rides[4]);
    }

    /*
     * checking non existing ride removal doesnt effect the size of the minheap 
     */
    @Test
    @Order(4)
    @DisplayName("Removing nonexistent ride")
    public void testRemoveNonExistent(){
        buildHeap();
        heapTest.remove(new Ride(88, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2));
        Assertions.assertEquals(5, heapTest.k); //size stays the same
    }

    /*
     * testing removing from an empty heap
     */
    @Test
    @Order(5)
    @DisplayName("Removing from empty heap")
    public void testRemoveEmpty(){
        heapTest.remove(new Ride(88, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2));
        Assertions.assertEquals(0, heapTest.k); //size stays the same
        
    }

    /*
     * Removing the minimum element from the heap multiple times and verifying the order
     */
    @Test
    @Order(6)
    @DisplayName("Removing minimum element from the heap multiple times and verifying heap order properties")
    public void testRemoveMin(){
        buildHeap();
        heapTest.remove(heapTest.peek()); // removes the minimum element from the heap
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        heapTest.remove(heapTest.peek()); 
        Assertions.assertTrue(verifyHeapOrder());
        Assertions.assertTrue(heapTest.isEmpty()); //empty heap now
    }

    //isEmpty tests

    /*
     * Testing to see if heap is empty
     */
    @Test
    @Order(7)
    @DisplayName("Empty Heap Test")
    public void testIsEmpty(){
        Assertions.assertTrue(heapTest.isEmpty());
    }

    /*
     * Testing to see if heap is not empty
     */
    @Test
    @Order(8)
    @DisplayName("Non Empty Heap Test")
    public void testIsNotEmpty(){
        buildHeap();
        Assertions.assertFalse(heapTest.isEmpty());
    }


    //Peek tests

    /*
     * Testing to see if the correct ride is returned when peek is called
     */
    @Test
    @Order(9)
    @DisplayName("Peek returns correct ride when heap is not empty")
    public void testPeek(){
        buildHeap();
        Assertions.assertEquals(heapTest.rides[0], heapTest.peek());
    }

    /*
     * Testing to see if peek returns null when heap is empty
     */
    @Test
    @Order(10)
    @DisplayName("Peek returns null when heap is empty")
    public void testPeekEmpty(){
        Assertions.assertNull(heapTest.peek());
    }


    //Heapify tests

    /*
     * Testing to see if heapify given rides array returns a valid heapified version
     */
    @Test
    @Order(11)
    @DisplayName("Heapify Test")
    public void testHeapify(){
        Ride[] giveRides = {new Ride(1, "04:12:23",new String[] {"Keanux", "Jubilee"}, 1, 2),
        new Ride(2, "03:12:23", new String[]{"Gerald", "Comet"}, 2, 3),
        new Ride(3, "07:12:23", new String[] {"Max", "Jubilee"}, 3, 4),
        new Ride(4, "10:12:23", new String[]{"Cooper", "Fair"}, 4, 5),
        new Ride(5, "1:12:23", new String[]{"Keanux", "Ronald"}, 5, 6)};

        heapTest.heapify(giveRides, 5);

        Assertions.assertTrue(verifyHeapOrder());
    }




}


