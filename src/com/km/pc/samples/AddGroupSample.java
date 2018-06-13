package com.km.pc.samples;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;

import com.km.pc.rest.Group;
import com.km.pc.rest.Log;
import com.km.pc.rest.LogOptions;
import com.km.pc.rest.Pacing;
import com.km.pc.rest.PcException;
import com.km.pc.rest.PcRestProxy;
import com.km.pc.rest.RTS;
import com.km.pc.rest.Script;
import com.km.pc.rest.ThinkTime;
import com.km.pc.rest.Type;

public class AddGroupSample {

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
		
		Group group = new Group();
		Script script = new Script();
		RTS rts = new RTS();
		Pacing pacing = new Pacing();
		
		
		LogOptions logOptions = new LogOptions(new Type("Standard"), 1);
		Log log = new Log(); log.setLogOptions(logOptions);
		ThinkTime thinktime = new ThinkTime();
		
		String body ="";
		
		StringEntity addGroupBody = new StringEntity(body, "text/xml");
		
		
		boolean response = rest.addGroup(Constants.TESTID,	group);

		System.out.println("Deleted: " + response);

	}

}
