package com.km.pc.samples;

import java.io.File;

import org.apache.commons.io.IOUtils;

import com.km.pc.rest.PcRestProxy;
import com.km.pc.rest.PcRunResult;
import com.km.pc.rest.PcRunResults;

public class AnalyzeResultsSample {

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

			String runstatus = rest.getRunData(Constants.RUNID).getRunState();

			System.out.println("Run ID " + Constants.RUNID + " State"
					+ runstatus);

			if (runstatus.endsWith("Creating Analysis Data")) {
				int analyzeStatus = rest.analyzeResults(Constants.RUNID);

				if (analyzeStatus == 201)
					System.out.println("Analysis Started");
				else
					System.out.println("Analysis Failed" + analyzeStatus);

			} else {
				System.out.println("Run Status:" + runstatus);
			}

			isLoggedIn = rest.logout();

			System.out.println("Logged Out: " + isLoggedIn);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
