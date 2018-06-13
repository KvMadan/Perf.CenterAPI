package com.km.pc.rest;

import java.io.File;

import org.apache.commons.io.IOUtils;

public class Sanity {

	public static void main(String[] args) {
		
		try {
			
			String userNameAndPassword = "admin" + ":" + "sa";
			System.out.println(Base64Encoder.encode(userNameAndPassword.getBytes()));
			System.out.println("Decrypted Text:" + EncryptionUtils.Encrypt("MADAN", "Kavarthapu"));
			
			System.out.println("Decrypted Text: " + EncryptionUtils.Decrypt("q2hicr6gzWpXZXsVPC4Y4g==","Kavarthapu"));
			System.exit(0);
			PcRestProxy rest = new PcRestProxy("16.150.59.71", "DEFAULT", "DEV12");
			
			boolean isLoggedIn = rest.authenticate("admin", "sa");
			
			System.out.println("Authenticated: " +  isLoggedIn);
			
			//.**** Collate ****.//
			String runStatus = rest.getRunData(27).getRunState();
			
			if (runStatus.endsWith("Collating Results")){
				int collateStatus = rest.collateResults(27);
			
				if (collateStatus == 201){
					System.out.println("Collation Started");
				}
				else
				{
					System.out.println("Unable to start Collation " + collateStatus);
				}
			}
			else {
				System.out.println("Run Stauts: " + runStatus);
			}
			System.exit(1);
			//.**** Collate ****.//
			
			//.**** Analysis ****.// 
			String runstatus = rest.getRunData(27).getRunState();
			
			if(runstatus.endsWith("Creating Analysis Data")){
				int analyzeStatus = rest.analyzeResults(27);
				
				if(analyzeStatus == 201)
					System.out.println("Analysis Started");
				else
					System.out.println("Analysis Failed" + analyzeStatus);
					
			}
			else
			{
				System.out.println("Run Status:" + runstatus);
			}
			
			System.exit(0);
			//.**** Analysis ****.//			
			
			TimeslotDuration duration = new TimeslotDuration(0, 15);
			PostRunAction postRunAction = PostRunAction.COLLATE;
			//PcRunResponse response = rest.startRun(2, 1, duration, postRunAction.getValue(), false);
			PcRunResponse response = rest.startRun(7, 5, duration, postRunAction.getValue(), false);
			Thread.sleep(5000);
			int runId = response.getID();
			System.out.println("Run ID: " + runId);
			
			while(true)
			{
				Thread.sleep(9000);
				PcRunResponse currentStatus = rest.getRunData(response.getID());
				String status = currentStatus.getRunState();
				System.out.println(status);
				if (status.equals("Before Creating Analysis Data"))
					break;
			}
			
			String reportDirectory = "C:\\Temp\\" + runId;
			 PcRunResults runResultsList = rest.getRunResults(runId);
			 
			    if (runResultsList.getResultsList() != null) {
			      for (PcRunResult result : runResultsList.getResultsList()) {
			          File dir = new File(reportDirectory);
			          dir.mkdirs();
			          String reportArchiveFullPath = dir.getCanonicalPath() + IOUtils.DIR_SEPARATOR + result.getName();
			          System.out.println("Publishing Run Data");
			          rest.GetRunResultData(runId, result.getID(), reportArchiveFullPath);
			      }
			    }
			    else
			    {
			    	System.out.println("No ResultsList");
			    }
			
			isLoggedIn = rest.logout();
			
			System.out.println("Logged Out: " + isLoggedIn);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
