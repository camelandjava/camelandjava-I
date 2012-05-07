/**
 *
 */
package camel.workshop.bean;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

/**
 *
 */
public class OrderHandlerTest {

	@Test
	public void testInitialize() {
		File inFile = new File("target/test-classes/testFile.txt");

		InputStream is = null;
		try {

			is = new FileInputStream(inFile);
			OrderHandler oh = new OrderHandler();
			File f = oh.initialize(is);

			assertNotNull(f);

		} catch(Exception e) {
			fail(e.toString());
		}

	}
}
