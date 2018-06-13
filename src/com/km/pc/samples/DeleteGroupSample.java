package com.km.pc.samples;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.km.pc.rest.PcException;
import com.km.pc.rest.PcRestProxy;

public class DeleteGroupSample {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws PcException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, PcException, IOException {
		PcRestProxy rest = new PcRestProxy("16.150.59.71", "DEFAULT", "DEV12");

		boolean isLoggedIn = rest.authenticate("admin", "sa");

		System.out.println("Authenticated: " + isLoggedIn);
		
		//For all Groups
		boolean response = rest.deleteGroup(Constants.TESTID);

		//For specific Group
		//boolean response = rest.deleteGroup(Constants.TESTID, "webhttphtml1");
		
		System.out.println("Deleted: " + response);

	}

}
