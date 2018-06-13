package com.km.pc.samples;

import com.km.pc.rest.PcRestProxy;

public class CollateResultsSample {

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
			String runStatus = rest.getRunData(Constants.RUNID).getRunState();

			if (runStatus.endsWith("Collating Results")) {
				int collateStatus = rest.collateResults(Constants.RUNID);

				if (collateStatus == 201) {
					System.out.println("Collation Started");
				} else {
					System.out.println("Unable to start Collation "
							+ collateStatus);
				}
			} else {
				System.out.println("Run Stauts: " + runStatus);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
