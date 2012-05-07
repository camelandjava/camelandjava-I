/**
 *
 */
package camel.workshop.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.apache.camel.Body;
import org.apache.camel.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 */
public class OrderHandler {

	private Logger log = Logger.getLogger(OrderHandler.class);

	public File initialize(@Body InputStream order) throws IOException {

		File destination = FileUtil.createTempFile("init", "tmp");
		log.info(destination.getAbsolutePath());

		FileUtils.copyInputStreamToFile(order, destination);

		Collection<String> lines = new LinkedList<String>();
		String orderId = String.valueOf((new Date()).getTime());
		lines.add("\n");
		lines.add("orderId: " + orderId);
		String date = Calendar.getInstance().getTime().toString();
		lines.add("date:    " + date);

		FileUtils.writeLines(destination, lines , true);

		log.debug("business logic executed");

		return destination;
	}
}
