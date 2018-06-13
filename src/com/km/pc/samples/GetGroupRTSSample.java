package com.km.pc.samples;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.km.pc.rest.PcException;
import com.km.pc.rest.PcRestProxy;
import com.km.pc.rest.RTS;

public class GetGroupRTSSample {

	/**
	 * @param args
	 * @throws IOException
	 * @throws PcException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			PcException, IOException {
		PcRestProxy rest = new PcRestProxy("16.150.59.71", "DEFAULT", "DEV12");

		boolean isLoggedIn = rest.authenticate("admin", "sa");

		System.out.println("Authenticated: " + isLoggedIn);

		RTS rts = rest.getGroupRTS(Constants.TESTID, "webhttphtml1");

		System.out.println(rts.getThinkTime().getType().getType());

		/*
		 * System.out.println("\n" + rts.getPacing().getNumberOfIterations() +
		 * "\n" + rts.getPacing().getStartNewIteration().getType().getType() +
		 * "\n" + rts.getLog().getType().getType() + "\n" +
		 * rts.getLog().getParametersSubstitution() + "\n" +
		 * rts.getLog().getDataReturnedByServer() + "\n" +
		 * rts.getLog().getAdvanceTrace() + "\n" +
		 * rts.getLog().getLogOptions().getType().getType());
		 */

	}

}
