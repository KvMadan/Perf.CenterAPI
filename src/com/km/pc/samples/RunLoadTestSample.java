package com.km.pc.samples;

import com.km.pc.rest.PcRestProxy;
import com.km.pc.rest.PcRunResponse;
import com.km.pc.rest.PostRunAction;
import com.km.pc.rest.TimeslotDuration;

public class RunLoadTestSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			PcRestProxy rest = new PcRestProxy(Constants.PCHOST,
					Constants.DOMAIN, Constants.PROJECT);

			boolean isLoggedIn = rest.authenticate(Constants.USERNAME,
					Constants.PASSWORD);
			if (isLoggedIn)
				System.out.println("Logged in to " + Constants.DOMAIN + ":"
						+ Constants.PROJECT);
			else {
				System.out.println("Login Failed");
				System.exit(1);
			}

			TimeslotDuration duration = new TimeslotDuration(0, 15);
			PostRunAction postRunAction = PostRunAction.COLLATE;
			PcRunResponse response = rest.startRun(Constants.TESTID,
					Constants.TESTINSTANCE, duration, postRunAction.getValue(),
					false);
			System.out.println("Request sent to start the Load Test ");
			int runId = response.getID();
			System.out.println("Load Test Started, Run ID: " + runId);

			while (true) {
				Thread.sleep(9000);
				PcRunResponse currentStatus = rest.getRunData(response.getID());
				String status = currentStatus.getRunState();
				System.out.println(status);
				if (status.equals("Before Creating Analysis Data"))
					break;
			}

			System.out.println("Load Test Finished");
			isLoggedIn = rest.logout();

			System.out.println("Logged Out: " + isLoggedIn);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
