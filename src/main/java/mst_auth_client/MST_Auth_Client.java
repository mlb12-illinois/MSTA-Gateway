package mst_auth_client;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import mst_auth_library.MSTAException;
import mst_auth_library.MST_Auth_Servlet;



public class MST_Auth_Client {
	private MST_Auth_Servlet msta_library;
	public MST_Auth_Client() {
	}
	public void SetLibrary (MST_Auth_Servlet MSTALibrary ) {
		msta_library = MSTALibrary;			
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws ServletException, IOException, MSTAException {
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
		
		msta_library.SetMicroservice("MSTABusiness");
		msta_library.SetMethodWithBodyString("GET", "MSTABusiness OY a get");
		msta_library.SetHeader("Content-Type", "application/json; utf-8");
		HttpResponse resp = msta_library.SendRequest();
	    response.getWriter().append(resp.body().toString());
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws ServletException, IOException, MSTAException {
	    response.getWriter().append("doPost").append(request.getContextPath());
	}
	public void doPut(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws ServletException, IOException, MSTAException {
	    response.getWriter().append("doPut Served at: ").append(request.getContextPath());
	}
	public void doDelete(HttpServletRequest request, HttpServletResponse response, String trustedbody) throws ServletException, IOException, MSTAException {
	    response.getWriter().append("doDelete Served at: ").append(request.getContextPath());
	}
}
