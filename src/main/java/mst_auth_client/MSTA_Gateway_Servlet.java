package mst_auth_client;

import mst_auth_library.MST_Auth_ClientServlet;
import mst_auth_library.MST_Auth_Microservice;

public class MSTA_Gateway_Servlet extends MST_Auth_ClientServlet {

	public MST_Auth_Microservice  GetService () {		// override this
		return new MSTA_Gateway();			
	}

}
