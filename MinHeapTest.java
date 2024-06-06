import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class MinHeapTest{
    private MinHeap heapTest;
    private final PrintStream output = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


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
        String[] ride1 = {"Keanux", "Jubilee"};
        String[] ride2 = {"Gerald", "Comet"};
        String[] ride3 = {"Jumbo", "Chris"};
    
        String[] ride4 = {"Max", "Jubilee"};
        String[] ride5 = {"Keanux", "Ronald"};

        heapTest.insert(new Ride(1, "04:12:23", ride1, 1, 2));
        heapTest.insert(new Ride(2, "03:12:23", ride2, 2, 3));
        heapTest.insert(new Ride(3, "07:12:23",ride3 , 3, 4));
        heapTest.insert(new Ride(4, "10:12:23", ride4, 4, 5));
        heapTest.insert(new Ride(5, "1:12:23", ride5, 5, 6));
    }

    /*
     * Testing to see if ride is inserted correctly
     */
    @Test 
    @DisplayName("Inserting to empty Heap Test")
    public void testInsert(){
        String[] passengers = {"johnny", "mitchell"};
        Ride ride = new Ride(1, "04:12:23",passengers, 1, 2);
        heapTest.insert(ride);
        Assertions.assertEquals(ride, heapTest.peek());
    }

    /*
     * Testing to see if multiple rides are inserted correctly with the minimum time at the beginning of the  minHeap
     */
    @Test 
    @DisplayName("Entire heap array is inserted correctly with maintaining correct heaporder")
    public void testInsertWithOrder(){
        buildHeap();
        Boolean pass;
        if (heapTest.rides[0].rideID == 5 && heapTest.rides[1].rideID == 2 && 
        heapTest.rides[2].rideID == 3 && heapTest.rides[3].rideID == 3 && 
        heapTest.rides[4].rideID == 1){
            pass = true;
        }
        else {
            pass = false;
        }

        Assertions.assertTrue(pass);
    }







}


