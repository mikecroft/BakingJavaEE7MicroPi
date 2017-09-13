package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Mike Croft
 */

@Path("sse")
@ApplicationScoped
public class StockTickerResource {

    @Inject
    private StockTicker stockTicker;


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void eventOutput(){

    }

}
