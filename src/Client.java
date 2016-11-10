import java.math.BigInteger;
import java.util.LinkedList;

/**
 * Created by autoy on 2015/9/12.
 */
public class Client {
    Point start, designation;
    boolean availability;
    boolean isOnboard;
    int timer=0;
    Client(City city, Area area) {
        availability = true;
        isOnboard=false;
        this.start = area.topLeft.randomize(area.bottomDown);
        this.designation = city.topLeft.randomize(city.bottomDown);
    }
}
