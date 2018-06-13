package com.km.pc.samples;

import java.io.File;
import org.apache.commons.io.IOUtils;
import com.km.pc.rest.PcRestProxy;
import com.km.pc.rest.PcRunResult;
import com.km.pc.rest.PcRunResults;

public class DownloadResultsSample {

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

			String reportDirectory = "C:\\Temp\\" + Constants.RUNID;
			PcRunResults runResultsList = rest.getRunResults(Constants.RUNID);

			if (runResultsList.getResultsList() != null) {
				for (PcRunResult result : runResultsList.getResultsList()) {
					File dir = new File(reportDirectory);
					dir.mkdirs();
					String reportArchiveFullPath = dir.getCanonicalPath()
							+ IOUtils.DIR_SEPARATOR + result.getName();
					System.out.println("Publishing Run Data");
					rest.GetRunResultData(Constants.RUNID, result.getID(),
							reportArchiveFullPath);
				}
				System.out.println("Run Results saved at " + reportDirectory);
			} else {
				System.out.println("No ResultsList");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
