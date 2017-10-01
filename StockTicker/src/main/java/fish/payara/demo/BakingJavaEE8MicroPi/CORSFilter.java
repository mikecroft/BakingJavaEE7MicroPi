package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author Mike Croft
 */

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres) throws IOException {

        // We need to add the right headers for the JS SSE client
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
    }
}