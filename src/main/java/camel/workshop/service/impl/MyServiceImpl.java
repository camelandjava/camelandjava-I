/**
 *
 */
package camel.workshop.service.impl;

import java.util.HashMap;

import javax.ws.rs.core.Response;

import camel.workshop.service.MyService;

/**
 *
 */
public class MyServiceImpl implements MyService {

	private HashMap<String, String> myResources;

	private String xmlResponse = "<response code='200'><resource>REPLACE_ME</resource></response>";
	private String xmlError = "<response code='400'><reason>REPLACE_ME</reason></response>";

	/*
	 * (non-Javadoc)
	 *
	 * @see camel.workshop.service.MyService#getResource(java.lang.String)
	 */

	public MyServiceImpl() {
		myResources = new HashMap<String, String>();
		myResources.put("1", "Resouce one");
		myResources.put("2", "Resouce two");
		myResources.put("3", "Resouce three");
		myResources.put("4", "Resouce four");
	}

	@Override
	public Response getResource(String resourceId) {
		String ret = myResources.get(resourceId);

		if (ret == null) {
			return Response.ok(replaceString("unknown resource", xmlError))
					.build();
		}

		return Response.ok(replaceString(ret, xmlResponse)).build();
	}

	/**
	 * @param ret
	 * @return
	 */
	private String replaceString(String ret, String response) {
		return response.replace("REPLACE_ME", ret);
	}
}
