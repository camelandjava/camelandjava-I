/**
 *
 */
package camel.workshop.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.spring.Main;

/**
 *
 */
public class RouteWithBeans extends RouteBuilder {

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

		if (getContext().hasComponent("properties") == null) {
			PropertiesComponent pc = new PropertiesComponent();
			pc.setLocation("classpath:META-INF/camel.properties");
			getContext().addComponent("properties", pc);
		}

		from("{{in.dir}}").beanRef("orderHandler", "initialize").to(
				"{{out.dir}}/?fileName=order.txt");

	}
}
