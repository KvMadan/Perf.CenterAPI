package com.km.pc.samples;

import com.km.pc.rest.PcGroupResponse;
import com.km.pc.rest.PcRestProxy;

public class GetGroupInfoSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		PcRestProxy rest = new PcRestProxy("16.150.59.71", "DEFAULT", "DEV12");

		boolean isLoggedIn = rest.authenticate("admin", "sa");

		System.out.println("Authenticated: " + isLoggedIn);

		PcGroupResponse pcGroupResponse = rest.getGroup(Constants.TESTID);
		
		System.out.println(pcGroupResponse.getGroup()[0].getName());
//		System.out.println(pcGroupResponse.getGroup().getVusers());
//		System.out.println(pcGroupResponse.getGroup().getScript().getID());
//		System.out.println(pcGroupResponse.getGroup().getRTS().getPacing()
//				.getNumberOfIterations());
//		System.out.println("Start New Iteration: "
//				+ pcGroupResponse.getGroup().getRTS().getPacing()
//						.getStartNewIteration().getType().getType());
//
//		System.out.println(pcGroupResponse.getGroup().getRTS().getLog()
//				.getParametersSubstitution());
//		System.out.println(pcGroupResponse.getGroup().getRTS().getLog()
//				.getDataReturnedByServer());
//		System.out.println(pcGroupResponse.getGroup().getRTS().getLog()
//				.getAdvanceTrace());
//
//		System.out.println("Log Type: "
//				+ pcGroupResponse.getGroup().getRTS().getLog().getType()
//						.getType());
//		System.out.println(pcGroupResponse.getGroup().getRTS().getLog()
//				.getLogOptions().getCacheSize());
//
//		System.out.println("Log Options: "
//				+ pcGroupResponse.getGroup().getRTS().getLog().getLogOptions()
//						.getType().getType());
//		System.out.println("ThinkTime: "
//				+ pcGroupResponse.getGroup().getRTS().getThinkTime().getType()
//						.getType());

	}

}
