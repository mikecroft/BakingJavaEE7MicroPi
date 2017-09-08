import fish.payara.demo.BakingJavaEE8MicroPi.Stock;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import fish.payara.micro.cdi.Outbound;

/**
 * @author Mike Croft
 */
@Singleton
@Startup
public class StockTicker {

    private Stock stock;

    @Inject
    @Outbound
    private Event<Stock> stockEvents;

    @Schedule(hour = "*", minute="*", second = "*/1", persistent = false)
    private void generatePrice() {
        stock = new Stock("PYA", "", Math.random() * 100.0);
        System.out.println(stock);
        stockEvents.fire(stock);
    }

    public Stock getStock() {
        return stock;
    }

}
