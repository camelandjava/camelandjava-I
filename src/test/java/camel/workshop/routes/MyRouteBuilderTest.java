package camel.workshop.routes;

import java.io.File;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.CamelSpringTestSupport;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
	 *
	 */
public class MyRouteBuilderTest extends CamelSpringTestSupport {

	Logger log = Logger.getLogger(MyRouteBuilderTest.class);

	@EndpointInject
	ProducerTemplate producer;

	@Test
	public void testUkFile() {

		log.info("testUkFile");

		File inFile = new File("src/test/resources/data/message1.xml");

		try {

			producer.sendBody("file:target/test-classes/data?noop=true", inFile);
			Thread.sleep(5000);
			File output = new File("target/messages/uk/" + inFile.getName());
			log.info(output.getAbsolutePath());
			assertTrue(output.exists());

		} catch (Exception e) {

			log.fatal(e);
			assertNull(e);

		}
	}

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

}
