
/*
 * A class for the nodes in the minheap data structure
 * 
 * @author Keanu De Cleene
 */
public class Ride implements Comparable<Ride>{
    public int rideID;
    public long timeStamp;
    public String passengerNames;
    public int startLocationID;
    public int endLocationID;

    /*
     * Initializes a ride with the given parameters
     * 
     * @param rideID the ID of the ride
     * @param timeStamp the time of the ride
     * @param passengerNames the names of the passengers
     * @param startLocationID ID of the start location
     * @param endLocationID ID of the end location
     */
    public Ride(int rideID, long timestamp, String passengerNames, int startLocationID, int endLocationID) {
        this.rideID = rideID;
        this.timeStamp = timestamp;
        this.passengerNames = passengerNames;
        this.startLocationID = startLocationID;
        this.endLocationID = endLocationID;
    }

    /*
     * Implements the compareTo method to compare based on timestamp
     * 
     * @param otherRide the other ride to compare against
     */
    @Override
    public int compareTo(Ride otherRide) {
        // Compare based on timestamp
        if (this.timeStamp < otherRide.timeStamp) {
            return -1;
        } else if (this.timeStamp > otherRide.timeStamp) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
     * Implements the to string with proper formatting for a ride ticket
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--- Ride ").append(String.format("%03d", rideID)).append(" ---").append("\n");
        stringBuilder.append("Time: ").append(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(timeStamp))).append("\n");
        stringBuilder.append("Start ID: ").append(startLocationID).append("\n");
        stringBuilder.append("Passengers:").append("\n").append(passengerNames).append("\n");
        stringBuilder.append("--------------------");
        return stringBuilder.toString();
    }
}