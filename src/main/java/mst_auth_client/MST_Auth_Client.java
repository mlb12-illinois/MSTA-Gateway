package mst_auth_client;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import mst_auth_library.MSTAException;
import mst_auth_library.MST_Auth_BaseClientWrapper;
import mst_auth_library.MST_Auth_BaseServlet;
import mst_auth_library.MST_Auth_ClientWrapper;
import mst_auth_library.MST_Auth_Servlet;



public class MST_Auth_Client {
	private MST_Auth_BaseClientWrapper msta_library;
	public Semaphore mysemaphore;
	String resp;		// this does not get reset each call
	public MST_Auth_Client() {
	}
	public void SetLibrary (MST_Auth_BaseClientWrapper MSTALibrary ) {
		msta_library = MSTALibrary;			
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws IOException, MSTAException {
		resp = new String();
		/*
    	JSONObject jsonobj =  new JSONObject();
    	jsonobj.put("string1",  "string1");
    	jsonobj.put("string2",  "string2");
    	jsonobj.put("Int1",  1);
    	jsonobj.put("Int2",  999999999);
	    System.out.println("jsonobj raw: " + jsonobj);
	    System.out.println("jsonobj string: " + jsonobj.toString());
	    
	    //String.format("%05d", 999);
	    System.out.println("jsonobj Int: " + String.format("%05d", 999));   	
		*/
//		long startTime = System.currentTimeMillis();

  	  	mysemaphore = new Semaphore(1);	// must be > 1
		// simulate a little work
		try {
		  TimeUnit.MILLISECONDS.sleep(500);	// add a little wait, to see if root will end
		}
		catch (JSONException | InterruptedException ie) {
			throw(new MSTAException (": InterruptedException" + ie));		
		}						  
  	  		
		for (int i = 0; i < 50; i++) {
			msta_library.SetMicroservice("MSTABusiness");
			//msta_library.SetMicroservice("http://localhost:8080/MSTA-BusinessService/MSTABusiness.html");
			msta_library.SetMethodWithBodyString("GET", "MSTABusiness OY a get");
			msta_library.SetHeader("Content-Type", "application/json; utf-8");
			msta_library.SendRequestA();
			//HttpResponse myresponse = msta_library.SendRequest();
			//resp = resp + myresponse.body().toString() + ": ";
		}
		//System.out.println("WaitA1");
		msta_library.WaitA();
		//System.out.println("WaitA2");
		response.getWriter().append(resp);
//		long endTime = System.currentTimeMillis();
//		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws IOException, MSTAException {
		resp = new String();
	    response.getWriter().append("doPost").append(request.getContextPath());
	}
	public void doPut(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws IOException, MSTAException {
		resp = new String();
	    response.getWriter().append("doPut Served at: ").append(request.getContextPath());
	}
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws IOException, MSTAException {
		resp = new String();
	    response.getWriter().append("doDelete Served at: ").append(request.getContextPath());
	}
	// OY This has to be thread safe
	public void callbackResponse(HttpResponse<String> parmmstresponse) {
		//System.out.println("callbackResponse");
		// THIS IS NOT!
		try {
			mysemaphore.acquire();
			if (parmmstresponse != null)
				resp = resp + parmmstresponse.body().toString() + ": ";
			mysemaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("callbackResponse2");
	}
}
