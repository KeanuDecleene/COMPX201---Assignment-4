import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private boolean isValid; //added for checking amount of passengers is valid
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
            this.isValid = true;
        }
        else{
            System.out.println("Error creating Ride - Too many passengers");
            this.isValid = false;
        }
        this.startLocationID = startLocationID;
        this.endLocationID = endLocationID;
    }

    /*
     * Checks if this ride and the ride given are able to be combined for optimisation purposes
     * 
     * @param otherRide ride to compare with
     * @return true if we can combine with other ride false otherwise
     */
    public boolean canCombine(Ride otherRide){
        long timeDiff = Math.abs(this.timeStamp.getTime() - otherRide.timeStamp.getTime());
        boolean timeDiffValid = timeDiff <= 10 * 60 *1000; // 10 minutes
        boolean validPassengers = (this.passengerNames.length + otherRide.passengerNames.length) <= 6;
        return timeDiffValid && validPassengers;
    }

    public void combine(Ride otherRide){
        //copying the passengers into combined passengers
        String[] combinedPassengers = new String[this.passengerNames.length + otherRide.passengerNames.length]; 
        System.arraycopy(this.passengerNames, 0, combinedPassengers, 0, this.passengerNames.length);
        System.arraycopy(otherRide.passengerNames, 0, combinedPassengers, this.passengerNames.length, otherRide.passengerNames.length);
        this.passengerNames = combinedPassengers;
        this.timeStamp = otherRide.timeStamp;
        this.rideID = otherRide.rideID+1;
        
    }

    /*
     * Checks if the ride is valid
     * @return true if the ride is valid, false otherwise
     */
    public boolean isValid(){
        return this.isValid;
    }
    /*
     * Implements the compareTo method to compare based on timestamp
     * 
     * @param otherRide the other ride to compare against
     */
    @Override
    public int compareTo(Ride otherRide) {
        if(otherRide == null){
            return 1; // this assumes any ride is less than a null ride 
        }
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