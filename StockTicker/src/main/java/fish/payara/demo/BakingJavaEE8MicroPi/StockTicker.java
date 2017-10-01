package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import fish.payara.cluster.Clustered;
import fish.payara.micro.cdi.Outbound;

import java.io.Serializable;

/**
 * @author Mike Croft
 */
@Clustered
@Singleton
@Startup
public class StockTicker implements Serializable{

    private Stock stock;

    @Inject
    @Outbound(loopBack = true)
    private Event<Stock> stockEvents;

    @Schedule(hour = "*", minute="*", second = "*/1", persistent = false)
    private void generatePrice() {
        stock = new Stock("PYA", "Some very long description of Payara", Math.random() * 100.0);
        System.out.println(stock);
        stockEvents.fire(stock);
    }

    public Stock getStock() {
        return stock;
    }

}
