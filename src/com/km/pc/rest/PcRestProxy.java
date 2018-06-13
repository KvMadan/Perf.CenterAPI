package com.km.pc.rest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.thoughtworks.xstream.XStream;

@SuppressWarnings("unused")
public class PcRestProxy {
	protected static final String BASE_PC_API_URL = "http://%s/LoadTest/rest";
	protected static final String BASE_PC_API_AUTHENTICATION_URL = "http://%s/LoadTest/rest/authentication-point";
	protected static final String AUTHENTICATION_LOGIN_URL = "http://%s/LoadTest/rest/authentication-point/authenticate";
	protected static final String AUTHENTICATION_LOGOUT_URL = "http://%s/LoadTest/rest/authentication-point/logout";
	protected static final String PC_API_RESOURCES_TEMPLATE = "http://%s/LoadTest/rest/domains/%s/projects/%s";
	protected static final String RUNS_RESOURCE_NAME = "Runs";
	protected static final String RESULTS_RESOURCE_NAME = "Results";
	protected static final String CONTENT_TYPE_XML = "application/xml";
	static final String PC_API_XMLNS = "http://www.hp.com/PC/REST/API";
	protected static final List<Integer> validStatusCodes = Arrays
			.asList(new Integer[] { Integer.valueOf(200), Integer.valueOf(201),
					Integer.valueOf(202), Integer.valueOf(204) });
	private String baseURL;
	private String pcServer;
	private String domain;
	private String project;
	private HttpClient client;
	private HttpContext context;
	private CookieStore cookieStore;

	public PcRestProxy(String pcServerName, String almDomain, String almProject) {
		this.pcServer = pcServerName;
		this.domain = almDomain;
		this.project = almProject;
		this.baseURL = String.format(
				"http://%s/LoadTest/rest/domains/%s/projects/%s", new Object[] {
						this.pcServer, this.domain, this.project });

		PoolingClientConnectionManager cxMgr = new PoolingClientConnectionManager(
				SchemeRegistryFactory.createDefault());
		cxMgr.setMaxTotal(100);
		cxMgr.setDefaultMaxPerRoute(20);

		this.client = new DefaultHttpClient(cxMgr);
		this.context = new BasicHttpContext();
		this.cookieStore = new BasicCookieStore();
		this.context.setAttribute("http.cookie-store", this.cookieStore);
	}

	public boolean authenticate(String userName, String password)
			throws PcException, ClientProtocolException, IOException {
		String userNameAndPassword = userName + ":" + password;
		String encodedCredentials = Base64Encoder.encode(userNameAndPassword
				.getBytes());
		HttpGet authRequest = new HttpGet(String.format(
				"http://%s/LoadTest/rest/authentication-point/authenticate",
				new Object[] { this.pcServer }));
		authRequest.addHeader("Authorization",
				String.format("Basic %s", new Object[] { encodedCredentials }));
		executeRequest(authRequest);
		return true;
	}

	public boolean isTestValid(int testId) throws ClientProtocolException,
			PcException, IOException {
		HttpGet isTestValidRequest = new HttpGet(String.format(baseURL
				+ "/%s/%s/%s", new Object[] { "Tests", testId, "validity" }));
		isTestValidRequest.addHeader("Accept", "application/xml");
		HttpResponse response = executeRequest(isTestValidRequest);
		String isTestValidResponse = IOUtils.toString(response.getEntity()
				.getContent());

		System.out.println(isTestValidResponse);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			return true;
		else
			return false;
	}

	public RTS getGroupRTS(int testId, String groupName)
			throws ClientProtocolException, PcException, IOException {
		HttpGet getGroupRTSRequest = new HttpGet(String.format(baseURL
				+ "/%s/%s/%s/%s/%s", new Object[] { "Tests", testId, "Groups",
				groupName, "RTS" }));
		getGroupRTSRequest.addHeader("Accept", "application/xml");
		HttpResponse response = executeRequest(getGroupRTSRequest);
	
		String getGroupRTSResponse = IOUtils.toString(response.getEntity()
				.getContent());
	
		System.out.println(getGroupRTSResponse);
	
		XStream xstream = new XStream();
		xstream.alias("RTS", RTS.class);
		xstream.alias("Pacing", Pacing.class);
		xstream.alias("ThinkTime", ThinkTime.class);
		xstream.alias("Log", Log.class);
		xstream.alias("LogOptions", LogOptions.class);
		xstream.useAttributeFor(Log.class, "Type");
		xstream.registerConverter(new TypeConverter());
		xstream.useAttributeFor(StartNewIteration.class, "Type");
		xstream.useAttributeFor(ThinkTime.class, "Type");
		xstream.useAttributeFor(LogOptions.class, "Type");
	
		return (RTS) xstream.fromXML(getGroupRTSResponse);
	
	}

	public PcGroupResponse getGroup(int testId) throws PcException,
			ClientProtocolException, IOException {
		HttpGet getGroupRequest = new HttpGet(String.format(baseURL
				+ "/%s/%s/%s", new Object[] { "Tests", testId, "Groups" }));
		getGroupRequest.addHeader("Accept", "application/xml");

		HttpResponse response = executeRequest(getGroupRequest);

		String getGroupResponse = IOUtils.toString(response.getEntity()
				.getContent());
		System.out.println(getGroupResponse);

		XStream xstream = new XStream();
		xstream.alias("Groups", PcGroupResponse.class);

		// For handling the Array of Groups.
		xstream.addImplicitArray(PcGroupResponse.class, "Group");

		xstream.alias("Group", Group.class);
		xstream.alias("Script", Script.class);
		xstream.alias("RTS", RTS.class);
		xstream.alias("Pacing", Pacing.class);
		xstream.alias("ThinkTime", ThinkTime.class);
		xstream.alias("Log", Log.class);
		xstream.alias("LogOptions", LogOptions.class);

		xstream.useAttributeFor(Log.class, "Type");
		xstream.registerConverter(new TypeConverter());
		xstream.useAttributeFor(StartNewIteration.class, "Type");
		xstream.useAttributeFor(ThinkTime.class, "Type");
		xstream.useAttributeFor(LogOptions.class, "Type");

		PcGroupResponse pcGroupResponse = (PcGroupResponse) xstream
				.fromXML(getGroupResponse);

		// return PcGroupResponse.xmlToObject(getGroupResponse);
		return pcGroupResponse;

	}

	public void createTest(String testName, String testFolderPath,
			Content content) throws ClientProtocolException, PcException,
			IOException {
		HttpPost createTestRequest = new HttpPost(String.format(this.baseURL
				+ "/%s", new Object[] { "tests" }));
		createTestRequest.addHeader("Content-Type", "application/xml");
		PcCreateTestRequest createTestData = new PcCreateTestRequest(testName,
				testFolderPath, content);
		createTestRequest.setEntity(new StringEntity(createTestData
				.objectToXML(), ContentType.APPLICATION_XML));

		System.out.println(createTestData.objectToXML());

		/*
		 * HttpResponse response = executeRequest(createTestRequest); String
		 * createTestResponse = IOUtils.toString(response.getEntity()
		 * .getContent());
		 * 
		 * System.out.println(createTestResponse);
		 */

	}

	public boolean deleteGroup(int testId) throws ClientProtocolException,
			PcException, IOException {

		HttpDelete deleteGroupRequest = new HttpDelete(String.format(baseURL
				+ "/%s/%s/%s", new Object[] { "Tests", testId, "Groups" }));

		HttpResponse response = executeRequest(deleteGroupRequest);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			return true;
		else
			return false;

	}

	public boolean deleteGroup(int testId, String groupName)
			throws ClientProtocolException, PcException, IOException {

		HttpDelete deleteGroupRequest = new HttpDelete(String.format(baseURL
				+ "/%s/%s/%s/%s", new Object[] { "Tests", testId, "Groups",
				groupName }));

		HttpResponse response = executeRequest(deleteGroupRequest);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			return true;
		else
			return false;

	}

	public boolean addGroup(int testId, Group group)
			throws ClientProtocolException, PcException, IOException {

		HttpPut addGroupRequest = new HttpPut(String.format(baseURL
				+ "/%s/%s/%s/%s", new Object[] { "Tests", testId, "Groups",
				group.getName() }));

		// addGroupRequest.setEntity(new
		// StringEntity(group.objectToXml(group)));

		HttpResponse response = executeRequest(addGroupRequest);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			return true;
		else
			return false;

	}

	public PcRunResponse startRun(int testId, int testInstaceId,
			TimeslotDuration timeslotDuration, String postRunAction,
			boolean vudsMode) throws PcException, ClientProtocolException,
			IOException {
		HttpPost startRunRequest = new HttpPost(String.format(this.baseURL
				+ "/%s", new Object[] { "Runs" }));
		startRunRequest.addHeader("Content-Type", "application/xml");
		PcRunRequest runRequestData = new PcRunRequest(testId, testInstaceId,
				0, timeslotDuration, postRunAction, vudsMode);
		startRunRequest.setEntity(new StringEntity(
				runRequestData.objectToXML(), ContentType.APPLICATION_XML));
		HttpResponse response = executeRequest(startRunRequest);
		String startRunResponse = IOUtils.toString(response.getEntity()
				.getContent());
		return PcRunResponse.xmlToObject(startRunResponse);
	}

	public boolean stopRun(int runId, String stopMode) throws PcException,
			ClientProtocolException, IOException {
		String stopUrl = String.format(this.baseURL + "/%s/%s/%s",
				new Object[] { "Runs", Integer.valueOf(runId), stopMode });
		HttpPost stopRunRequest = new HttpPost(stopUrl);
		ReleaseTimeslot releaseTimesloteRequest = new ReleaseTimeslot(true,
				"Do Not Collate");
		stopRunRequest.addHeader("Content-Type", "application/xml");
		stopRunRequest.setEntity(new StringEntity(releaseTimesloteRequest
				.objectToXML(), ContentType.APPLICATION_XML));
		executeRequest(stopRunRequest);
		return true;
	}

	public PcRunResponse getRunData(int runId) throws PcException,
			ClientProtocolException, IOException {
		HttpGet getRunDataRequest = new HttpGet(String.format(this.baseURL
				+ "/%s/%s", new Object[] { "Runs", Integer.valueOf(runId) }));
		HttpResponse response = executeRequest(getRunDataRequest);
		String runData = IOUtils.toString(response.getEntity().getContent());
		return PcRunResponse.xmlToObject(runData);
	}

	public PcRunResults getRunResults(int runId) throws PcException,
			ClientProtocolException, IOException {
		String getRunResultsUrl = String.format(this.baseURL + "/%s/%s/%s",
				new Object[] { "Runs", Integer.valueOf(runId), "Results" });

		HttpGet getRunResultsRequest = new HttpGet(getRunResultsUrl);
		HttpResponse response = executeRequest(getRunResultsRequest);
		String runResults = IOUtils.toString(response.getEntity().getContent());
		return PcRunResults.xmlToObject(runResults);
	}

	public boolean GetRunResultData(int runId, int resultId,
			String localFilePath) throws PcException, ClientProtocolException,
			IOException {
		String getRunResultDataUrl = String.format(this.baseURL
				+ "/%s/%s/%s/%s/data",
				new Object[] { "Runs", Integer.valueOf(runId), "Results",
						Integer.valueOf(resultId) });

		HttpGet getRunResultRequest = new HttpGet(getRunResultDataUrl);
		HttpResponse response = executeRequest(getRunResultRequest);
		OutputStream out = new FileOutputStream(localFilePath);
		InputStream in = response.getEntity().getContent();
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);
		return true;
	}

	public int analyzeResults(int runId) throws PcException,
			ClientProtocolException, IOException {

		String analyzeUrl = String.format(this.baseURL + "/%s/%s/%s",
				new Object[] { "Runs", Integer.valueOf(runId), "Analyze" });
		System.out.println(analyzeUrl);
		HttpPost analyzeRequest = new HttpPost(analyzeUrl);
		HttpResponse response = executeRequest(analyzeRequest);
		StatusLine line = response.getStatusLine();

		return line.getStatusCode();
	}

	public int collateResults(int runId) throws PcException,
			ClientProtocolException, IOException {

		String collateUrl = String.format(this.baseURL + "/%s/%s/%s",
				new Object[] { "Runs", Integer.valueOf(runId), "Collate" });
		System.out.println(collateUrl);
		HttpPost collateRequest = new HttpPost(collateUrl);
		HttpResponse response = executeRequest(collateRequest);
		StatusLine line = response.getStatusLine();

		return line.getStatusCode();
	}

	public boolean logout() throws PcException, ClientProtocolException,
			IOException {
		HttpGet logoutRequest = new HttpGet(String.format(
				"http://%s/LoadTest/rest/authentication-point/logout",
				new Object[] { this.pcServer }));
		executeRequest(logoutRequest);
		return true;
	}

	protected HttpResponse executeRequest(HttpRequestBase request)
			throws PcException, ClientProtocolException, IOException {
		HttpResponse response = this.client.execute(request, this.context);
		if (!isOk(response)) {
			String message;
			try {
				String content = IOUtils.toString(response.getEntity()
						.getContent());
				PcErrorResponse exception = PcErrorResponse
						.xmlToObject(content);
				message = String.format(
						"%s Error code: %s",
						new Object[] { exception.ExceptionMessage,
								Integer.valueOf(exception.ErrorCode) });
			} catch (Exception ex) {
				message = response.getStatusLine().toString();
			}
			throw new PcException(message);
		}
		return response;
	}

	public static boolean isOk(HttpResponse response) {
		System.out.println(response.getStatusLine().getStatusCode());
		return validStatusCodes.contains(Integer.valueOf(response
				.getStatusLine().getStatusCode()));
	}

	protected String getBaseURL() {
		return this.baseURL;
	}
}