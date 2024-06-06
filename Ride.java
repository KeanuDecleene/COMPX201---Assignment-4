import java.util.Date;
import java.text.SimpleDateFormat;
/*
 * A class for the nodes in the minheap data structure
 * 
 * @author Keanu De Cleene
 */
public class Ride implements Comparable<Ride>{
    public int rideID;
    public Date timeStamp;
    public String passengerName;
    public String passengerNames[];
    public int startLocationID;
    public int endLocationID;
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");


    /*
     * Initializes a ride with the given parameters
     * 
     * @param rideID the ID of the ride
     * @param givenTime the time of the ride
     * @param passengerNames the names of the passengers
     * @param startLocationID ID of the start location
     * @param endLocationID ID of the end location
     */
    public Ride(int rideID, String givenTime, String[] passengerNames, int startLocationID, int endLocationID) {
        this.rideID = rideID;
        try{
            this.timeStamp = time.parse(givenTime); //convert string to Date format
        }
        catch(Exception e){ System.out.println(e);} 
        if(passengerNames.length <= 6){// check the passenger names are within bounds of the maximum amount of people per ride
            this.passengerNames = passengerNames;
        }
        else{
            System.out.println("Error creating Ride - Too many passengers");
            return;
        }
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
        return this.timeStamp.compareTo(otherRide.timeStamp); 
    }

    /*
     * Implements the to string with proper formatting for a ride ticket
     */
    @Override
    public String toString() {
        String passengerList = String.join("\n", this.passengerNames);
        return String.format("--- Ride %03d -------\nTime: %tT\nStart ID: %d\nEnd ID: %d\nPassengers:\n%s\n--------------------",
        rideID, timeStamp, startLocationID, endLocationID, passengerList);
    }
}