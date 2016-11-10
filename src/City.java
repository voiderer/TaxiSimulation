/**
 * Created by autoy on 2015/9/12.
 */
import java.util.LinkedList;
public class City {
    Point topLeft;
    Point bottomDown;
    LinkedList<Area> areas;
    LinkedList<Client> clientList;
    LinkedList<Taxi> taxiList;
    LinkedList<Client> onBoredLast;
    City(double taxirate[],double passengerrate[]) {
        topLeft = new Point(100, 100);
        bottomDown = new Point(1000, 1000);
        areas = new LinkedList<Area>();
        clientList = new LinkedList<Client>();
        taxiList = new LinkedList<Taxi>();
        onBoredLast =new LinkedList<Client>();
        Area tempArea;
        Point areaTopLeft, areaBottomDown;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                areaTopLeft = new Point(topLeft.x + i * (bottomDown.x - topLeft.x) / 3, topLeft.y + j * (bottomDown.y - topLeft.y) / 3);
                areaBottomDown = new Point(topLeft.x + (i + 1) * (bottomDown.x - topLeft.x) / 3, topLeft.y + (j + 1) * (bottomDown.y - topLeft.y) / 3);
                tempArea = new Area(this, areaTopLeft, areaBottomDown, passengerrate[i*3+j], taxirate[i*3+1]);
                this.areas.addLast(tempArea);
            }
        }
    }

    void run(MainForm form) {

        for (Area ar : areas) {
            ar.introduceNewClient(this);
        }
        Area aea;
        for (Taxi ta : taxiList) {
            aea=getArea(ta.currentPosition);
            aea.totalcount++;
            ta.timerTotal++;
            if (ta.isLoaded) {
                ta.timerTotal++;
                if (ta.moveToDesignation()) {
                    ta.isLoaded = false;
                    ta.hasAppointment = false;

                }
                ta.appointedClient.start = ta.currentPosition;
            } else if (ta.hasAppointment) {
                ta.timerTotal++;
                if (ta.moveToDesignation()) {
                    ta.designation = ta.appointedClient.designation;
                    ta.isLoaded = true;
                    ta.appointedClient.isOnboard = true;
                    clientList.remove(ta.appointedClient);
                    onBoredLast.addLast(ta.appointedClient);
                    ta.appointedClient.timer++;
                }
            } else {
                ta.timerEmpty++;
                ta.timerTotal++;
                aea.emptycount++;
                if (!ta.getNewAppointment(this)) {
                    ta.goSomeWhere(this);
                }
            }
        }
        for(Client cli:clientList)
        {
            if(cli.availability)
            {
                cli.timer++;
            }
        }
     //   form.drawDots(this);
    }
    Area getArea(Point point) {
        for (Area ii : areas) {
            if (point.x >= ii.topLeft.x && point.x <= ii.bottomDown.x && point.y > ii.topLeft.y && point.y < ii.bottomDown.y) {
                return ii;
            }
        }
        return null;
    }

}
