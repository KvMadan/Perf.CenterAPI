package com.km.pc.samples;

import com.km.pc.rest.PcRestProxy;

public class LoginLogoutSample {

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
			isLoggedIn = rest.logout();

			System.out.println("Logged Out: " + isLoggedIn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
