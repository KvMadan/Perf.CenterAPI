package com.km.pc.samples;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.km.pc.rest.Action;
import com.km.pc.rest.Content;
import com.km.pc.rest.Duration;
import com.km.pc.rest.Group;
import com.km.pc.rest.Initialize;
import com.km.pc.rest.LGDistribution;
import com.km.pc.rest.PcCreateTestRequest;
import com.km.pc.rest.PcException;
import com.km.pc.rest.PcGroupResponse;
import com.km.pc.rest.RTS;
import com.km.pc.rest.Ramp;
import com.km.pc.rest.Scheduler;
import com.km.pc.rest.Script;
import com.km.pc.rest.StartVusers;
import com.km.pc.rest.StopVusers;
import com.km.pc.rest.TimeInterval;
import com.km.pc.rest.Type;
import com.km.pc.rest.WorkloadType;

public class CreateLoadTestSample {

	/**
	 * @author - Kavartha
	 * @param args
	 * @throws IOException
	 * @throws PcException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			PcException, IOException {
		/*
		 * PcRestProxy rest = new PcRestProxy("16.150.59.71", "DEFAULT",
		 * "DEV12");
		 * 
		 * boolean isLoggedIn = rest.authenticate("admin", "sa");
		 * 
		 * System.out.println("Authenticated: " + isLoggedIn);
		 */

		String testName = "fromKMRestCall";
		String testFolderPath = "Subject\\Tests";
		WorkloadType workloadType = new WorkloadType("real-world", "by test",
				"by percentage");
		LGDistribution lgDistribution = new LGDistribution("all to each group",
				10);
		// Group groups[] = new Group[1];
		// Action actions[] = new Action[4];

		Initialize initialize = new Initialize(new Type(
				"just before vuser runs"), 5);
		StartVusers startVusers = new StartVusers(new Type(), 10, new Ramp(
				new TimeInterval(0, 0, 0, 10), 2));
		Duration duration = new Duration(new Type("run for"), new TimeInterval(
				0, 0, 30, 1));
		StopVusers stopVusers = new StopVusers(new Type("gradually"), 0,
				new Ramp(new TimeInterval(0, 0, 0, 20), 2));

		Action testActions[] = { initialize, startVusers, duration, stopVusers };

		Group group = new Group("group1", 10, new Script(1), new RTS());
		Group groups[] = { group,
				new Group("group2", 10, new Script(1), new RTS()) };

		PcGroupResponse pcGroupResponse = new PcGroupResponse(groups);

		Scheduler scheduler = new Scheduler(testActions);
		Content content = new Content(workloadType, lgDistribution,
				pcGroupResponse,
				scheduler);

		PcCreateTestRequest createTestData = new PcCreateTestRequest(testName,
				testFolderPath, content);

		System.out.println(createTestData.objectToXML());
		// rest.createTest(testName, testFolderPath, content);

	}
}
