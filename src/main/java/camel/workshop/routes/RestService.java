/**
 *
 */
package camel.workshop.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 *
 */
public class RestService extends RouteBuilder {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {

		getContext().setTracing(true);

		from("jetty:http://localhost:8032?matchOnUriPrefix=true")
				.to("cxfbean:resourceService");
	}

}
