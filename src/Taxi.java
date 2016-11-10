/**
 * Created by autoy on 2015/9/12.
 */
import sun.applet.Main;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Random;
public class Taxi {
    boolean isLoaded;
    boolean hasAppointment;
    Client appointedClient;
    Point currentPosition;
    Point designation;
    double taxiSpeed;
    int timerEmpty,timerTotal;
    Taxi(float emptyRate, City city, Area area) {
        taxiSpeed=10;

        this.currentPosition = area.topLeft.randomize(area.bottomDown);
        isLoaded=false;
    }

    boolean moveToDesignation() {
        double dist=this.currentPosition.measureDistance(this.designation);
        if ( dist<= taxiSpeed) {
            this.currentPosition = designation;
            this.designation = appointedClient.designation;
            return true;
        } else {
            this.currentPosition = this.currentPosition.moveToward(this.designation, taxiSpeed);
            return false;
        }
    }

    boolean getNewAppointment(City city) {
        Iterator<Client> it = city.clientList.iterator();
        Client selected = null;
        Client tempClient;
        double min = 0;
        double temple;
        while (it.hasNext())
        {
            if((selected=it.next()).availability) {
                min = getPreference(selected);
                break;
            }
        }
        while (it.hasNext()) {
            tempClient = it.next();
            if (tempClient.availability) {
                temple = getPreference(tempClient);
                if (min > temple) {
                    selected = tempClient;
                    min=temple;
                }
            }
        }
        if (min != 0&min<100){
            this.designation=selected.start;
            appointedClient=selected;
            this.hasAppointment=true;
            selected.availability=false;
            selected.isOnboard=false;
            return true;
        }
        return false;
    }
    void goSomeWhere(City city)
    {
        Random rdn=new Random();
        Double dX=(rdn.nextDouble()-0.5);
        Double dY=(rdn.nextDouble()-0.5);
        Double dS=Math.sqrt(dX*dX+dY*dY);
        currentPosition.x+=dX*taxiSpeed/dS;
        currentPosition.y+=dY*taxiSpeed/dS;
        if(currentPosition.x<city.topLeft.x)
        {
            currentPosition.x=city.topLeft.x+0.00000000001;
        }
        else if(currentPosition.x>city.bottomDown.x)
        {
            currentPosition.x=city.bottomDown.x-0.00000000001;
        }
        if(currentPosition.y<city.topLeft.y)
        {
            currentPosition.y=city.topLeft.y+0.00000000001;
        }
        else if(currentPosition.y>city.bottomDown.y)
        {
            currentPosition.y=city.bottomDown.y-0.00000000001;
        }
    }
    double getPreference(Client client)
    {
        return 1/this.currentPosition.measureDistance(client.start)+1.2*client.start.measureDistance(client.designation);
    }
}
