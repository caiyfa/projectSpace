package psn.cyf.structural.pattern.facade;

import psn.cyf.structural.pattern.facade.subsystem.Airport;
import psn.cyf.structural.pattern.facade.subsystem.Chauffeur;
import psn.cyf.structural.pattern.facade.subsystem.Hotel;
import psn.cyf.structural.pattern.facade.subsystem.Restaurant;

/**
 * 秘书
 */
public class Secretary {
    private String localAddress="青岛";
    private Airport airport=new Airport();
    private Chauffeur chauffeur=new Chauffeur();
    private Hotel hotel=new Hotel();
    private Restaurant restaurant=new Restaurant();

    /**
     * 出差
     * @param to 出发地
     * @param days  天数
     */
    public  void trip(String to,int days){
        airport.bookTicket(localAddress,to);
        chauffeur.drive("机场");
        hotel.reserve(days);
    }
    public void repast(int num){
        restaurant.reserve(num);
        chauffeur.drive("酒店");
    }
}
