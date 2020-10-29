package psn.cyf.structural.pattern.facade.subsystem;

/**
 * 机场
 */
public class Airport {
    public void bookTicket(String from ,String to){
        System.out.println("订购了从"+from+"到"+to+"的机票");
    }
}
