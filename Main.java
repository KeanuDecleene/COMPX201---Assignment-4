
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            String timeString = "15:30:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date timeStamp = dateFormat.parse(timeString);

            Ride ride1 = new Ride(1, timeStamp, "Joe Bloggs\nMary Smith\nCharlie Brown", 324, 586);

            System.out.println(ride1);
        }
        catch(Exception ex){
            System.err.println(ex);

        }
    }
}

