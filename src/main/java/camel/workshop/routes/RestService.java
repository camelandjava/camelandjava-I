/**
 *
 */
package camel.workshop.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

/**
 *
 */
public class RestService extends RouteBuilder {

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		Main.main(args);
	}

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
