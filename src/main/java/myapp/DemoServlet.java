/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.client.Client;
import com.google.api.client.http.HttpResponse;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Client client;
	
	private static HttpResponse httpResponse;
	private static JsonReader reader;

	
	public DemoServlet() {
	   client = new Client("","");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/plain");
		
	    URI uriPR =
		        client.getService().getEndpoint(
		            "https://jira-stats-api.appspot.com/stats/projects");

		    JsonObject jsonObject = executeHttpRequest(uriPR);
		
		    resp.getWriter().println(jsonObject.toString());		
	}
	
	
	
	
	public String testing() throws IOException, SQLException {
		  
		   // String project = "ESBEP";
//		    String board = "ESBEP BUGS from UAT";

		    URI uriPR =
		        client.getService().getEndpoint(
		            "https://jira-stats-api.appspot.com/stats/issues");

		    JsonObject jsonObject = executeHttpRequest(uriPR);

		    Map<String, String> headers = new HashMap<String, String>();
		    
		    headers.put("inputHeaderProject", "ESBEP");
		    headers.put("inputHeaderBoard", "ESBEP BUGS from UAT");
		    
		    
		    System.out.println(((JsonObject) jsonObject).get("xata").toString());
		    
		    //System.out.println(jsonObject.get("xata"));

		    return jsonObject.toString();
		 	  
	  }

	
	
	
	
	
	
	
	
	
	
	
	 public void writing(String data) {
		    try {
		      File statText = new File("issue-relations.txt");
		      FileOutputStream is = new FileOutputStream(statText);
		      OutputStreamWriter osw = new OutputStreamWriter(is);
		      Writer w = new BufferedWriter(osw);

		      w.write(data);
		      
		      w.close();
		    } catch (IOException e) {
		      System.err.println("Problem writing to the file issue-relations.txt");
		    }
		  }
	
	
	  public JsonObject executeHttpRequest(URI uriPR, String header) throws IOException {

		    try {
		      httpResponse = client.getService().executeURIHeader(uriPR,header);
		    } catch (InterruptedException e) {
		      System.out.println("interrupted");
		    }

		    if (httpResponse != null) {
		      reader = Json.createReader(httpResponse.getContent());
		      return reader.readObject();
		    }
		    InputStream stream = new ByteArrayInputStream("".getBytes());
		    reader = Json.createReader(stream);
		    return reader.readObject();

		  }
	
	
	  public JsonObject executeHttpRequest(URI uriPR) throws IOException {

		    try {
		      httpResponse = client.getService().executeURI(uriPR);
		    } catch (InterruptedException e) {
		      System.out.println("interrupted");
		    }

		    if (httpResponse != null) {
		      reader = Json.createReader(httpResponse.getContent());
		      return reader.readObject();
		    }
		    InputStream stream = new ByteArrayInputStream("".getBytes());
		    reader = Json.createReader(stream);
		    return reader.readObject();

		  }
	
}