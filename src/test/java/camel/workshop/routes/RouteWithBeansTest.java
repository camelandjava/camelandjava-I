/**
 *
 */
package camel.workshop.routes;

import java.io.File;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.test.CamelSpringTestSupport;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class RouteWithBeansTest extends CamelSpringTestSupport {

	private Logger log = Logger.getLogger(MyRouteBuilderTest.class);

	@EndpointInject
	private ProducerTemplate producer;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.apache.camel.test.CamelSpringTestSupport#createApplicationContext()
	 */
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/camel-context.xml");
	}

	@Override
	protected void postProcessTest() throws Exception {

		PropertiesComponent pc = new PropertiesComponent();
		pc.setLocation("classpath:META-INF/camel.properties");
		context.removeComponent("properties");
		context.addComponent("properties", pc);
		context.getComponent("properties");
		pc.start();
		context.setTracing(true);
		super.postProcessTest();
	}

	@Test
	public void testRoute() {
		log.info("testRoute");

		File inFile = new File("target/test-classes/testFile.txt");

		try {
			producer.sendBody("{{in.dir}}", inFile);

			Thread.sleep(5000);

			File outFile = FileUtils.getFile("target/classes/out/order.txt");

			assertTrue(outFile.exists());
			assertTrue(outFile.length() > 0);

		} catch(Exception e) {
			log.fatal(e);
			fail(e.toString());
		}
	}
}
