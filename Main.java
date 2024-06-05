public class Main {
    public static void main(String[] args) {
        try{
        Ride car = new Ride( 4, "09:12:23", "06:12:23", 2, 4);
        Ride kean = new Ride( 4, "09:12:23", "keanu", 2, 4);
        //System.out.println(car);
        //System.out.println(kean);
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(car);
        minHeap.insert(kean);
        minHeap.dump();
        } catch (Exception e){
            System.out.println();
        }

        }
}

