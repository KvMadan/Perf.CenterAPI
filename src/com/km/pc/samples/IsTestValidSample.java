package com.km.pc.samples;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.km.pc.rest.PcException;
import com.km.pc.rest.PcRestProxy;

public class IsTestValidSample {

	/**
	 * @param args
	 * @throws IOException
	 * @throws PcException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			PcException, IOException {
		PcRestProxy rest = new PcRestProxy(Constants.PCHOST, Constants.DOMAIN,
				Constants.PROJECT);

		boolean isLoggedIn = rest.authenticate(Constants.USERNAME,
				Constants.PASSWORD);
		if (isLoggedIn)
			System.out.println("Logged in to " + Constants.DOMAIN + ":"
					+ Constants.PROJECT);
		else {
			System.out.println("Login Failed");
			System.exit(1);
		}

		System.out.println("Test #" + Constants.TESTID + " is "
				+ (rest.isTestValid(Constants.TESTID) ? "valid" : "invalid"));

	}

}
