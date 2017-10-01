package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;


/**
 * Created by mike on 28/09/17.
 */
@Singleton
@Startup
public class StockTickerSseClient {

    private String stockTickerURL = "http://localhost:8080/StockTicker-1.0-SNAPSHOT/rest/sse";

    private SseEventSource source;
    private Stock stock;

    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(stockTickerURL);
        source = SseEventSource.target(target).build();
        timerService.createSingleActionTimer(500, null);
    }

    @Timeout
    private void observe() {
        source.register((sseEvent)
                -> {
            this.stock = JsonbBuilder.create().fromJson(sseEvent.readData(), Stock.class);
            System.out.println("Received stock over SSE: " +stock.toString());
        });
        source.open();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { }
        source.close();
    }

    @PreDestroy
    private void destroy() {
        if (source.isOpen()) source.close();
    }

}