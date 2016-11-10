/**
 * Created by autoy on 2015/9/12.
 */
import java.util.Random;
public class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point randomize(Point that) {
        Random rdn = new Random();
        return new Point(this.x + (that.x - this.x) * rdn.nextFloat(), this.y + (that.x - this.x) * rdn.nextFloat());
    }

    double measureDistance(Point that) {
        return Math.sqrt((this.x - that.x) * (this.x - that.x) + (this.y - that.y) * (this.y - that.y));
    }

    Point moveToward(Point designation, double distance) {
        double totalDistance = this.measureDistance(designation);
        return new Point(this.x + (designation.x - this.x) * distance / totalDistance, this.y + (designation.y - this.y) * distance / totalDistance);
    }


}
