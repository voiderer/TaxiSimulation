import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by autoy on 2015/9/12.
 */

public class Area {
    Point topLeft;
    Point bottomDown;
    double population;
    int emptycount=0,totalcount=0;
    int timer=0, k=0;
    LinkedList<Client> clientList;
    Area(City city,Point topLeft,Point bottomDown,double initialPopulation,double initialTaxiSum)
    {
        clientList=new LinkedList<Client>();
        this.topLeft=topLeft;
        this.bottomDown=bottomDown;
        this.population =initialPopulation;
        for (int i=0;i<initialTaxiSum;i++) {
            city.taxiList.addLast(new Taxi(0.2f, city, this));
        }
    }
    void introduceNewClient(City city)
    {
        population+=(new Random().nextDouble())*population*2;
        Client cli;
        for (double i=1;i<population;population-=1) {
            city.clientList.addLast(cli=new Client(city, this));
            clientList.addLast(cli);
        }
    }
    void print()
    {
        Integer bb;
        Map.Entry entry ;
        TreeMap<Integer,Integer> hangman  =  new  TreeMap<Integer,Integer>();
        for (Client aa:clientList)
        {
            timer+=aa.timer;
            k++;
            if((bb=hangman.get(new Integer(aa.timer)))==null)
            {
                hangman.put(aa.timer,1);
            }
            else
            {
                hangman.put(aa.timer,++bb);
            }
        }
    /*    Iterator it = hangman.entrySet().iterator();
        while (it.hasNext())
        {
            entry=(Map.Entry)it.next();
            System.out.println(""+entry.getKey()+" "+entry.getValue());
        }*/
    }
}
